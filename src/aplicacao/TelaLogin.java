package aplicacao;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachadaold;
import modelo.Funcionario;
import uteis.PasswordUtils;

public class TelaLogin extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNome;
	private JLabel lblSenha;
	private JButton btnLogin;
	private JLabel lblmsg;
	private JButton btnCancelar;
	private JPasswordField passwordField;
	private boolean succeeded;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaLogin(Frame parent) {
		super(parent, "Validação usuário", true);
		
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 314, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 14, 46, 14);
		contentPane.add(lblNome);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 51, 46, 14);
		contentPane.add(lblSenha);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 12, 233, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int tentativas=0;
					String senha = passwordField.getText(); 
					
					List<Funcionario> funcionarios = Fachadaold.listarFuncionarios(textFieldNome.getText());
					
					for (Funcionario funcionario : funcionarios) {
						if (PasswordUtils.verifyUserPassword(senha, funcionario.getSenha(), funcionario.getSalt())) {
							succeeded = true;
							JOptionPane.showMessageDialog(contentPane, "Login realizado");
							TelaLogin.this.setVisible(false);
							
						}else {
							JOptionPane.showMessageDialog(null, "Senha invalida");
							tentativas++;
						}
						if (tentativas == 3) {
							JOptionPane.showMessageDialog(null, "tentativas excedidas!");
							Fachadaold.finalizar();
							System.exit(0);	
						}
							
					}
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnLogin.setBounds(48, 86, 115, 23);
		contentPane.add(btnLogin);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(26, 121, 273, 14);
		contentPane.add(lblmsg);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Fachadaold.finalizar();
				System.exit(0);	
			}
		});
		btnCancelar.setBounds(175, 86, 111, 23);
		contentPane.add(btnCancelar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 49, 115, 19);
		contentPane.add(passwordField);
		
		passwordField.addKeyListener(new KeyAdapter() {
			 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});

	}
	
	public boolean isSucceeded() {
        return succeeded;
    }
}
