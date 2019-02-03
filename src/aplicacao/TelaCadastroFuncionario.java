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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Funcionario;
import modelo.Insumo;
import modelo.Producao;
import uteis.PasswordUtils;

public class TelaCadastroFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCod;
	private JTextField textFieldNome;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JButton btnNovo;
	private JTextField txtMatricula;
	private JButton btnLocalizar;
	private DefaultListModel listModel;
	private JList list;
	private JButton btnAddPermissoes;
	private JButton btnRemoverInsumo;
	private JScrollPane scrollPane;
	private JButton btnCancelar;
	private JLabel lblPermissoes;
	private JPasswordField passwordField;

	
	/**
	 * Create the frame.
	 */
	public TelaCadastroFuncionario() {
		setTitle("Cadastro Funcion\u00E1rio");
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
		textFieldNome.setEnabled(false);
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
						
						int matricula = Integer.parseInt(txtMatricula.getText());
//						List<Insumo> insumos = new ArrayList<Insumo>(); 
//						for (Object insumo : listModel.toArray()) {
//							insumos.add((Insumo) insumo);
//						};
//						
						String cpf = null;
						
						List<Integer> telefone = null;
						
						String email = null;
						String senhadigitada = passwordField.getText();
						String salt = PasswordUtils.getSalt(30);
						String senha = PasswordUtils.generateSecurePassword(senhadigitada, salt);
						
						Date dataAdmissao = null;
						Date dataDemissao = null;
//						ContaBancaria conta = null;
//						Endereco endereco = null;
						List<Producao> producoes=null;
		
						
						Funcionario p = Fachada.localizarFuncionario(Integer.parseInt(textFieldCod.getText()));					
						if (p == null) {
							p = Fachada.cadastrarFuncionario(matricula, nome,cpf ,telefone, email, senha,salt,dataAdmissao,dataDemissao, producoes);
						}else {
							Fachada.atualizarFuncionario(p.getId(),matricula, nome,cpf ,telefone, email, senha,salt,dataAdmissao,dataDemissao);
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
		
		btnNovo = new JButton("Novo funcion\u00E1rio");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldCod.setText("0");
				textFieldCod.setEnabled(false);
				textFieldNome.setEnabled(true);
				textFieldNome.setText("Digite o nome do funcionario");
				passwordField.setText(null);
				passwordField.setEnabled(true);
				txtMatricula.setText("0");
				txtMatricula.setEnabled(true);
				listModel.clear();
			}
		});
		btnNovo.setBounds(411, 9, 117, 25);
		contentPane.add(btnNovo);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNome.setEnabled(true);
				passwordField.setEnabled(true);
				txtMatricula.setEnabled(true);
				Funcionario selecionado;
				String nome = JOptionPane.showInputDialog(btnLocalizar, "Nome do funcion·rio", "Localiza funcion·rio", 0);
				List<Funcionario> funcionarios = Fachada.listarFuncionarios(nome); 
				
				if (funcionarios.size()>1) {
					selecionado = seleciona (funcionarios);
				}else {
					selecionado = (Funcionario) funcionarios.toArray()[0];
				}
				atualizaDados(selecionado);		
			}
		});
		btnLocalizar.setBounds(571, 11, 117, 25);
		contentPane.add(btnLocalizar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(342, 363, 117, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblMatricula = new JLabel("Matr\u00EDcula");
		lblMatricula.setBounds(160, 13, 91, 15);
		contentPane.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setEnabled(false);
		txtMatricula.setBounds(240, 11, 115, 19);
		contentPane.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 138, 214, 188);
		contentPane.add(scrollPane);
		
		listModel = new DefaultListModel<Insumo>();
		list = new JList(listModel);
		list.setEnabled(false);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnAddPermissoes = new JButton("Add Permiss\u00E3o");
		btnAddPermissoes.setEnabled(false);
		btnAddPermissoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Insumo selecionado;
//				String nome = JOptionPane.showInputDialog(btnAddPermissoes, "Nome do insumo", "Localiza insumo",1);
//				List<Insumo> insumos = Fachada.listarInsumo(nome);
//
//				if (insumos.size()>1) {
//					selecionado = seleciona(insumos);
//				}else {
//					if (insumos.size()==1) {
//						selecionado = (Insumo) insumos.toArray()[0];
//					}else {
//						selecionado = null;
//					}					
//				}
//				listModel.addElement(selecionado);
			}
		});
		btnAddPermissoes.setBounds(582, 166, 117, 25);
		contentPane.add(btnAddPermissoes);
		
		btnRemoverInsumo = new JButton("Remover Permiss\u00E3o");
		btnRemoverInsumo.setEnabled(false);
		btnRemoverInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemoverInsumo.setBounds(582, 203, 156, 25);
		contentPane.add(btnRemoverInsumo);
		
		lblPermissoes = new JLabel("Permiss\u00F5es");
		lblPermissoes.setBounds(360, 111, 134, 15);
		contentPane.add(lblPermissoes);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 85, 46, 14);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setBounds(66, 80, 97, 20);
		contentPane.add(passwordField);
	}
	private void atualizaDados (Funcionario selecionado) {
		textFieldCod.setText(Integer.toString(selecionado.getId()));
		textFieldNome.setText(selecionado.getNome());
		passwordField.setText(null);

		txtMatricula.setText(Integer.toString(selecionado.getMatricula()));

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
