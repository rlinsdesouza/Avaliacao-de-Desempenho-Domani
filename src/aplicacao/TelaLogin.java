package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Funcionario;
import uteis.PasswordUtils;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblCodInsumo;
	private JButton btnLogin;
	private JLabel lblmsg;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroProduto frame = new TelaCadastroProduto();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Identifica\u00E7\u00E3o do Usu\u00E1rio");
		setResizable(false);
		setBounds(100, 100, 314, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodInsumo = new JLabel("Senha");
		lblCodInsumo.setBounds(49, 43, 46, 14);
		contentPane.add(lblCodInsumo);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int tentativas=0;
					String senha = passwordField.getText(); 
					
					List<Funcionario> funcionarios = Fachada.listarFuncionarios();
					
					for (Funcionario funcionario : funcionarios) {
						if (PasswordUtils.verifyUserPassword(senha, funcionario.getSenha(), funcionario.getSalt())) {
							dispose();
						}else {
							lblmsg.setText("Senha não reconhecida");
							tentativas++;
						}
						if (tentativas == 3)
						JOptionPane.showMessageDialog(null, "tentativas excedidas!");
						Fachada.finalizar();
						System.exit(0);	
					}
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnLogin.setBounds(92, 87, 115, 23);
		contentPane.add(btnLogin);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 121, 273, 14);
		contentPane.add(lblmsg);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 40, 115, 20);
		contentPane.add(passwordField);
	}
	
//	@Override
//	public void windowClosing(WindowEvent e) {
//		JOptionPane.showMessageDialog(null, "ate breve !");
//		Fachada.finalizar();
//		System.exit(0);	
//	}
}
