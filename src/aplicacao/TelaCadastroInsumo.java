package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Insumo;

public class TelaCadastroInsumo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodInsumo;
	private JTextField textFieldNomeInsumo;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JCheckBox chckbxLactose;
	private JCheckBox checkBoxGlutem;

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
	public TelaCadastroInsumo() {
		setTitle("Cadastrar Insumo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldCodInsumo = new JTextField();
		textFieldCodInsumo.setBounds(66, 11, 86, 20);
		contentPane.add(textFieldCodInsumo);
		textFieldCodInsumo.setColumns(10);

		lblNomeInsumo = new JLabel("Nome");
		lblNomeInsumo.setBounds(10, 52, 46, 14);
		contentPane.add(lblNomeInsumo);

		lblCodInsumo = new JLabel("Cod");
		lblCodInsumo.setBounds(10, 14, 46, 14);
		contentPane.add(lblCodInsumo);

		textFieldNomeInsumo = new JTextField();
		textFieldNomeInsumo.setBounds(66, 49, 233, 20);
		contentPane.add(textFieldNomeInsumo);
		textFieldNomeInsumo.setColumns(10);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int codigo = Integer.parseInt(textFieldCodInsumo.getText());
					String nome = textFieldNomeInsumo.getText();
					Boolean lactose = chckbxLactose.isSelected();
					Boolean glutem = checkBoxGlutem.isSelected();
					
					Insumo i = Fachada.cadastrarInsumo(codigo,nome,lactose,glutem);
					lblmsg.setText("cadastrado "+i.getNome());
					
					textFieldCodInsumo.setText("");
					textFieldNomeInsumo.setText("");
					textFieldCodInsumo.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(184, 70, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(26, 97, 273, 14);
		contentPane.add(lblmsg);
		
		chckbxLactose = new JCheckBox("Lactose");
		chckbxLactose.setBounds(202, 10, 97, 23);
		contentPane.add(chckbxLactose);
		
		checkBoxGlutem = new JCheckBox("Glutem");
		checkBoxGlutem.setBounds(202, 30, 97, 23);
		contentPane.add(checkBoxGlutem);
	}
}
