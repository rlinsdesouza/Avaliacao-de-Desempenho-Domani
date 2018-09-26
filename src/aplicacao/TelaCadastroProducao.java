package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.toedter.calendar.JDateChooser;

import fachada.Fachada;
import modelo.ContaBancaria;
import modelo.Endereco;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Prato;
import modelo.Producao;
import uteis.PasswordUtils;

public class TelaCadastroProducao extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCod;
	private JTextField textFieldNome;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
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

		textFieldCod = new JTextField();
		textFieldCod.setEnabled(false);
		textFieldCod.setBounds(66, 11, 86, 20);
		contentPane.add(textFieldCod);
		textFieldCod.setColumns(10);

		lblNomeInsumo = new JLabel("Nome");
		lblNomeInsumo.setBounds(10, 52, 46, 14);
		contentPane.add(lblNomeInsumo);

		lblCodInsumo = new JLabel("Cod");
		lblCodInsumo.setBounds(10, 14, 46, 14);
		contentPane.add(lblCodInsumo);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 49, 233, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		btnCriar = new JButton("Salvar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja salvar/atualizar o funcion·rio?", "Confirma√ß√£o",0);
					if (opcao==0) {
						String nome = textFieldNome.getText();
						
//						int matricula = Integer.parseInt(txtData.getText());
//						List<Insumo> insumos = new ArrayList<Insumo>(); 
//						for (Object insumo : listModel.toArray()) {
//							insumos.add((Insumo) insumo);
//						};
//						
						int cpf = 0;
						
						List<Integer> telefone = null;
						
						String email = null;
//						String senhadigitada = passwordField.getText();
						String salt = PasswordUtils.getSalt(30);
//						String senha = PasswordUtils.generateSecurePassword(senhadigitada, salt);
						
						Date dataAdmissao = null;
						Date dataDemissao = null;
						ContaBancaria conta = null;
						Endereco endereco = null;
						List<Producao> producoes=null;
		
						
						Funcionario p = Fachada.localizarFuncionario(Integer.parseInt(textFieldCod.getText()));					
						if (p == null) {
							p = Fachada.cadastrarFuncionario(matricula, nome,cpf ,telefone, email, senha,salt,dataAdmissao,dataDemissao, conta, endereco, producoes);
						}else {
							Fachada.atualizarFuncionario(p.getId(),matricula, nome,cpf ,telefone, email, senha,salt,dataAdmissao,dataDemissao, conta, endereco, producoes);
						}
						atualizaDados(p);	
						lblmsg.setText("cadastrado/atualizado "+p.getNome());
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
				
				textFieldCod.setEnabled(false);
				Funcionario selecionado;
				String nome = JOptionPane.showInputDialog(contentPane, "Nome do funcion·rio", "Localiza funcion·rio", 0);
				List<Funcionario> funcionarios = Fachada.listarFuncionarios(nome); 
				
				if (funcionarios.size()>1) {
					selecionado = seleciona (funcionarios);
				}else {
					selecionado = (Funcionario) funcionarios.toArray()[0];
				}
				datePicker.setDate(new Date ());
				
				

				listModel.clear();
			}
		});
		btnNovo.setBounds(411, 9, 117, 25);
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
		lblData.setBounds(162, 14, 46, 15);
		contentPane.add(lblData);
		

		datePicker = new JDateChooser ();
		datePicker.setBounds(201, 11, 200, 20);

		contentPane.add(datePicker);
		contentPane.add(calendar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 137, 214, 188);
		contentPane.add(scrollPane);
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listModel = new DefaultListModel<Insumo>();
		
		btnAddPrato = new JButton("Add Prato");
		btnAddPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado;
				String nome = JOptionPane.showInputDialog(btnAddPrato, "Nome do prato", "Localiza prato",1);
				List<Prato> pratos = Fachada.listarPratos(nome);

				if (pratos.size()>1) {
					selecionado = seleciona(pratos);
				}else {
					if (pratos.size()==1) {
						selecionado = (Prato) pratos.toArray()[0];
					}else {
						selecionado = null;
					}					
				}
				listModel.addElement(selecionado);
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
		buttonLocalizarProducao.setBounds(540, 9, 146, 25);
		contentPane.add(buttonLocalizarProducao);
	}
	private void atualizaDados (Funcionario selecionado) {
		textFieldCod.setText(Integer.toString(selecionado.getId()));
		textFieldNome.setText(selecionado.getNome());

		txtData.setText(Integer.toString(selecionado.getMatricula()));

		listModel.clear();
//		if (selecionado.getInsumos() != null) {
//			for (Insumo i : selecionado.getInsumos()) {
//				listModel.addElement(i);
//			}
//		}
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
