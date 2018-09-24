package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Insumo;
import modelo.Prato;

public class TelaCadastroInsumo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodInsumo;
	private JTextField textFieldNomeInsumo;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
	private JButton btnSalvar;
	private JLabel lblmsg;
	private JCheckBox chckbxLactose;
	private JCheckBox checkBoxGlutem;
	private JButton btnNovoInsumo;
	private JButton btnLocalizar;

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
		setBounds(100, 100, 314, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldCodInsumo = new JTextField();
		textFieldCodInsumo.setBounds(66, 23, 86, 20);
		contentPane.add(textFieldCodInsumo);
		textFieldCodInsumo.setColumns(10);

		lblNomeInsumo = new JLabel("Nome");
		lblNomeInsumo.setBounds(10, 64, 46, 14);
		contentPane.add(lblNomeInsumo);

		lblCodInsumo = new JLabel("Cod");
		lblCodInsumo.setBounds(10, 26, 46, 14);
		contentPane.add(lblCodInsumo);

		textFieldNomeInsumo = new JTextField();
		textFieldNomeInsumo.setBounds(66, 61, 233, 20);
		contentPane.add(textFieldNomeInsumo);
		textFieldNomeInsumo.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja salvar/atualizar o insumo?", "Confirmação",0);
					if (opcao==0) {
						String nome = textFieldNomeInsumo.getText();
						Boolean lactose = chckbxLactose.isSelected();
						Boolean glutem = checkBoxGlutem.isSelected();
						
						Insumo p = Fachada.localizarInsumo(Integer.parseInt(textFieldCodInsumo.getText()));					
						if (p == null) {
							p = Fachada.cadastrarInsumo(nome,lactose,glutem);
						}else {
							Fachada.atualizarInsumo(p.getId(),nome,lactose,glutem);
						}
						JOptionPane.showMessageDialog(contentPane, "Cadastrado/atualizado com sucesso!");
						btnNovoInsumo.doClick();						
					}
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnSalvar.setBounds(104, 88, 115, 23);
		contentPane.add(btnSalvar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(26, 121, 273, 14);
		contentPane.add(lblmsg);
		
		chckbxLactose = new JCheckBox("Lactose");
		chckbxLactose.setBounds(202, 22, 97, 23);
		contentPane.add(chckbxLactose);
		
		checkBoxGlutem = new JCheckBox("Glutem");
		checkBoxGlutem.setBounds(202, 42, 97, 23);
		contentPane.add(checkBoxGlutem);
		
		btnNovoInsumo = new JButton("Novo insumo");
		btnNovoInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCodInsumo.setText("0");
				textFieldCodInsumo.setEnabled(false);
				textFieldNomeInsumo.setText("Digite o nome");
				chckbxLactose.setSelected(false);
				checkBoxGlutem.setSelected(false);
			}
		});
		btnNovoInsumo.setBounds(26, 0, 111, 23);
		contentPane.add(btnNovoInsumo);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo selecionado;
				String nome = JOptionPane.showInputDialog(btnLocalizar, "Nome do insumo", "Localiza prato", 0);
				List<Insumo> insumos = Fachada.listarInsumo(nome); 
				
				if (insumos.size()>1) {
					selecionado = seleciona (insumos);
				}else {
					selecionado = (Insumo) insumos.toArray()[0];
				}
				atualizaDados(selecionado);	
			}
		});
		btnLocalizar.setBounds(162, 0, 89, 23);
		contentPane.add(btnLocalizar);
	}
	
	private void atualizaDados (Insumo selecionado) {
		textFieldCodInsumo.setText(Integer.toString(selecionado.getId()));
		textFieldNomeInsumo.setText(selecionado.getNome());
		chckbxLactose.setSelected(selecionado.isLactose());
		checkBoxGlutem.setSelected(selecionado.isGlutem());
		
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
