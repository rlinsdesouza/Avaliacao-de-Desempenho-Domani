package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.db4o.internal.query.processor.ParentCandidate;
import com.toedter.calendar.JDateChooser;

import fachada.Fachada;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;

public class TelaCadastroProducao extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNomeInsumo;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JButton btnNovo;
	private DefaultListModel listModel;
	private JList list;
	private JButton btnAddPrato;
	private JButton btnRemoverPrato;
	private JScrollPane scrollPane;
	private JButton btnCancelar;
	private JLabel lblPratos;
	private JDateChooser datePicker;
	private JTextField textFieldCodFuncionario;
	
	private DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	
	/**
	 * Create the frame.
	 */
	public TelaCadastroProducao() {
		setTitle("Cadastro Produ\u00E7\u00E3o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNomeInsumo = new JLabel("Nome");
		lblNomeInsumo.setBounds(10, 52, 46, 14);
		contentPane.add(lblNomeInsumo);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 49, 233, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		btnCriar = new JButton("Salvar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja salvar/atualizar essas produc�es para o funcion�rio?", "Confirmação",0);
					if (opcao==0) {
					
						Funcionario f = Fachada.localizarFuncionario(Integer.parseInt(textFieldCodFuncionario.getText()));
						
						String data = sf.format(datePicker.getDate());
						List<Producao> producoes = Fachada.listarProducoesPorDataFuncionario(data, f.getId());
						List<Prato> pratosproducao = new ArrayList<Prato>();
						List<Prato> pratoslist = new ArrayList<Prato>();
						
						for (Producao producao : producoes) {
							pratosproducao.add(producao.getPrato());
						}
		
						
						for (int i=0;i<listModel.getSize();i++) {
							Prato p = (Prato) listModel.get(i);
							pratoslist.add(p);
							if (!pratosproducao.contains(p)&&p!=null)
								Fachada.cadastrarProducao (data,p,f);
						}
						
						
						for (Prato prato : pratosproducao) {
							if (!pratoslist.contains(prato)) {
								for (Producao p : producoes) {
									if (p.getPrato().getId()==prato.getId())
										Fachada.removerProducao (p);
								}
							}
								
						}
						lblmsg.setText("Salvo/Atualizado com sucesso!");
					}
					
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(213, 364, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(181, 399, 347, 14);
		contentPane.add(lblmsg);
		
		btnNovo = new JButton("Nova produ\u00E7\u00E3o");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Funcionario selecionado;
				String nome = JOptionPane.showInputDialog(contentPane, "Nome do funcion�rio", "Localiza funcion�rio", 0);
				List<Funcionario> funcionarios = Fachada.listarFuncionarios(nome); 
				
				if (funcionarios.size()>1) {
					selecionado = seleciona (funcionarios);
				}else {
					selecionado = (Funcionario) funcionarios.toArray()[0];
				}
				datePicker.setDate(new Date ());

				textFieldNome.setText(selecionado.getNome());
				textFieldCodFuncionario.setText(Integer.toString(selecionado.getId()));

				
				

				listModel.clear();
			}
		});
		btnNovo.setBounds(302, 9, 157, 25);
		contentPane.add(btnNovo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(342, 363, 117, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(22, 14, 46, 15);
		contentPane.add(lblData);
		

		datePicker = new JDateChooser ();
		datePicker.setBounds(66, 9, 200, 20);

		contentPane.add(datePicker);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 137, 214, 188);
		contentPane.add(scrollPane);
		listModel = new DefaultListModel<Insumo>();
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		
		btnAddPrato = new JButton("Add Prato");
		btnAddPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado = null;
				String nome = JOptionPane.showInputDialog(btnAddPrato, "Nome do prato", "Localiza prato",1);
				List<Prato> pratos = Fachada.listarPratos(nome);
				if (pratos.size() != 0) {
					if (pratos.size()>1) {
						selecionado = seleciona(pratos);
					}else {
						if (pratos.size()==1) {
							selecionado = (Prato) pratos.toArray()[0];
						}					
					}
					listModel.addElement(selecionado);
				}else {
					JOptionPane.showMessageDialog(contentPane, "N�o localizado!", "Aten��o", 2);
				}

			}
		});
		btnAddPrato.setBounds(476, 168, 117, 25);
		contentPane.add(btnAddPrato);
		
		btnRemoverPrato = new JButton("Remover Prato");
		btnRemoverPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemoverPrato.setBounds(476, 209, 156, 25);
		contentPane.add(btnRemoverPrato);
		
		lblPratos = new JLabel("Pratos produzidos");
		lblPratos.setBounds(272, 111, 134, 15);
		contentPane.add(lblPratos);
		
		JButton buttonLocalizarProducao = new JButton("Localizar Produ\u00E7\u00E3o");
		buttonLocalizarProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (datePicker.getDate() == null) {
					JOptionPane.showMessageDialog(contentPane, "Favor selecionar uma data", "Atencao", 2);
				}else {
					String data = sf.format(datePicker.getDate());
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Confirma a data para busca:"+data, "Confirmação",0);
					if (opcao == 0) {
						String nome = JOptionPane.showInputDialog(contentPane, "Nome do cozinheiro", "Localiza cozinheiro",1);
						List<Funcionario> funcionarios = Fachada.listarFuncionarios(nome);

						Funcionario selecionado;
						if (funcionarios.size()>1) {
							selecionado = seleciona(funcionarios);
						}else {
							if (funcionarios.size()==1) {
								selecionado = (Funcionario) funcionarios.toArray()[0];
							}else {
								selecionado = null;
							}					
						}
						
						
						List<Producao> producoes = Fachada.listarProducoesPorDataFuncionario(data,selecionado.getId());
						atualizaDados(producoes);
					}
				}
			}
		});
		buttonLocalizarProducao.setBounds(488, 9, 146, 25);
		contentPane.add(buttonLocalizarProducao);
		
		textFieldCodFuncionario = new JTextField();
		textFieldCodFuncionario.setBounds(32, 225, 86, 20);
		contentPane.add(textFieldCodFuncionario);
		textFieldCodFuncionario.setColumns(10);
		textFieldCodFuncionario.setVisible(false);
		textFieldCodFuncionario.setText("0");
		
	}
	
	private void atualizaDados (List<Producao> producoes) {
		
		LocalDate data = LocalDate.parse(producoes.get(0).getData(), f);
		
		textFieldNome.setText(producoes.get(0).getCozinheiro().getNome());
		textFieldCodFuncionario.setText(Integer.toString(producoes.get(0).getCozinheiro().getId()));


		listModel.clear();
		if (producoes != null) {
			for (Producao p : producoes) {
				listModel.addElement(p.getPrato());
			}
		}
		lblmsg.setText("");
	}
	
	private <T> T seleciona (List<T> lista) {
			T selecionado = (T) JOptionPane.showInputDialog(contentPane, 
		        "Escolha apenas um item",
		        "Selecione",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        lista.toArray(), 
		        lista.toArray()[0]);
		return selecionado;
	}
}
