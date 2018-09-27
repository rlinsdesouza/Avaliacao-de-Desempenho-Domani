package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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

import com.toedter.calendar.JDateChooser;

import fachada.Fachada;
import modelo.Avaliacao;
import modelo.Funcionario;
import modelo.Prato;
import modelo.Producao;

public class TelaCadastroAvaliacaoCozinha extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNomeCozinheiro;
	private JLabel lblmsg;
	private DefaultListModel listModel;
	private DefaultListModel listModel2;
	private JList listPratosAvaliados;
	private JButton btnAddAvaliacao;
	private JButton btnRemoverPrato;
	private JScrollPane scrollPane;
	private JButton btnCancelar;
	private JLabel lblAvaliados;
	private JDateChooser datePicker;
	private JTextField textFieldCodFuncionario;
	
	private DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField textFieldNomeAvaliador;
	private JTextField textFieldCodAvaliador;
	private JButton buttonLocalizarProducao;
	private JList listPratosNavaliados;
	private JScrollPane scrollPane_1;
	private JLabel lblPratosNoAvaliados;
	private JLabel lblNomeDoAvaliador;

	
	/**
	 * Create the frame.
	 */
	public TelaCadastroAvaliacaoCozinha() {
		setTitle("Cadastro Avalia\u00E7\u00E3o");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNomeCozinheiro = new JLabel("Nome do cozinheiro");
		lblNomeCozinheiro.setBounds(10, 52, 96, 14);
		contentPane.add(lblNomeCozinheiro);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(128, 49, 233, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		textFieldNome.setEnabled(false);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(181, 399, 347, 14);
		contentPane.add(lblmsg);
		
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
		scrollPane.setBounds(420, 127, 214, 188);
		contentPane.add(scrollPane);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(85, 127, 214, 188);
		contentPane.add(scrollPane_1);
		
		listModel = new DefaultListModel<>();
		listModel2 = new DefaultListModel<>();
		
		listPratosAvaliados = new JList(listModel);
		scrollPane.setViewportView(listPratosAvaliados);
		listPratosAvaliados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		listPratosNavaliados = new JList(listModel2);
		scrollPane_1.setViewportView(listPratosNavaliados);
		listPratosNavaliados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnAddAvaliacao = new JButton("Add Avaliacao");
		btnAddAvaliacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado;
				
				Producao p = (Producao) listModel2.getElementAt(listPratosNavaliados.getSelectedIndex());
				Funcionario avaliador = Fachada.localizarFuncionario(Integer.parseInt(textFieldCodAvaliador.getText()));
				
				TelaAvaliacaoCozinha t = new TelaAvaliacaoCozinha(p, avaliador);
				t.setVisible(true);
             	String data = sf.format(datePicker.getDate());
				List<Producao> producoes = Fachada.listarProducoesPorData(data,Integer.parseInt(textFieldCodFuncionario.getText()));
				atualizaDados(producoes);				
			}
		});
		btnAddAvaliacao.setBounds(302, 202, 117, 25);
		contentPane.add(btnAddAvaliacao);
		
		btnRemoverPrato = new JButton("Remover Avalia��o");
		btnRemoverPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fachada.removerAvaliacao ((Avaliacao) listModel.getElementAt(listPratosAvaliados.getSelectedIndex()));
				String data = sf.format(datePicker.getDate());
				List<Producao> producoes = Fachada.listarProducoesPorData(data,Integer.parseInt(textFieldCodFuncionario.getText()));
				atualizaDados(producoes);
			}
		});
		btnRemoverPrato.setBounds(565, 326, 156, 25);
		contentPane.add(btnRemoverPrato);
		
		lblAvaliados = new JLabel("Pratos avaliados");
		lblAvaliados.setBounds(420, 101, 134, 15);
		contentPane.add(lblAvaliados);
		
		buttonLocalizarProducao = new JButton("Localizar Produ\u00E7\u00E3o");
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
						
						
						List<Producao> producoes = Fachada.listarProducoesPorData(data,selecionado.getId());
						
						nome = JOptionPane.showInputDialog(contentPane, "Nome do avaliador", "Localiza avaliador",1);
						List<Funcionario> avaliadores = Fachada.listarFuncionarios(nome);

						if (avaliadores.size()>1) {
							selecionado = seleciona(avaliadores);
						}else {
							if (avaliadores.size()==1) {
								selecionado = (Funcionario) avaliadores.toArray()[0];
							}else {
								selecionado = null;
							}					
						}
						
						textFieldCodAvaliador.setText(Integer.toString(selecionado.getId()));
						textFieldNomeAvaliador.setText(selecionado.getNome());
						atualizaDados(producoes);
					}
				}
			}
		});
		buttonLocalizarProducao.setBounds(302, 9, 146, 25);
		contentPane.add(buttonLocalizarProducao);
		
		textFieldCodFuncionario = new JTextField();
		textFieldCodFuncionario.setBounds(20, 77, 86, 20);
		contentPane.add(textFieldCodFuncionario);
		textFieldCodFuncionario.setColumns(10);
		textFieldCodFuncionario.setVisible(false);
		textFieldCodFuncionario.setText("0");
		
		lblPratosNoAvaliados = new JLabel("Pratos n\u00E3o avaliados");
		lblPratosNoAvaliados.setBounds(85, 101, 134, 15);
		contentPane.add(lblPratosNoAvaliados);
		
		lblNomeDoAvaliador = new JLabel("Nome do avaliador");
		lblNomeDoAvaliador.setBounds(371, 52, 96, 14);
		contentPane.add(lblNomeDoAvaliador);
		
		textFieldNomeAvaliador = new JTextField();
		textFieldNomeAvaliador.setColumns(10);
		textFieldNomeAvaliador.setBounds(474, 49, 233, 20);
		contentPane.add(textFieldNomeAvaliador);
		textFieldNomeAvaliador.setEnabled(false);
		
		textFieldCodAvaliador = new JTextField();
		textFieldCodAvaliador.setColumns(10);
		textFieldCodAvaliador.setBounds(484, 77, 86, 20);
		contentPane.add(textFieldCodAvaliador);
		textFieldCodAvaliador.setVisible(false);
		textFieldCodAvaliador.setText("0");
		
		
	}
	
	private void atualizaDados (List<Producao> producoes) {
				
		textFieldNome.setText(producoes.get(0).getCozinheiro().getNome());
		textFieldCodFuncionario.setText(Integer.toString(producoes.get(0).getCozinheiro().getId()));

		listModel.clear();
		listModel2.clear();
		if (producoes != null) {
			Avaliacao avaliacao = null;
			for (Producao p : producoes) {
				int teste = 0;
				for (int i = 0; i < p.getAvaliacoes().size(); i++) {
					if (p.getAvaliacoes().get(i)!= null && p.getAvaliacoes().get(i).getAvaliador().getId()==Integer.parseInt(textFieldCodAvaliador.getText()))
						teste = 1;
						avaliacao = p.getAvaliacoes().get(i);
//						break;
				}
				if (teste==0) {
					listModel2.addElement(p);	
				}else {
					listModel.addElement(avaliacao);
				}
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
