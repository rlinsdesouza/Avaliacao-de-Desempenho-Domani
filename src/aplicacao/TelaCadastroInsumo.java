package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachadaold;
import modelo.Insumo;

public class TelaCadastroInsumo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCodInsumo;
	private JTextField textFieldNomeInsumo;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
	private JButton btnSalvar;
	private JLabel lblmsg;
	private JCheckBox chckbxLactose;
	private JCheckBox checkBoxGluten;
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
		setBounds(100, 100, 337, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldCodInsumo = new JTextField();
		textFieldCodInsumo.setEnabled(false);
		textFieldCodInsumo.setBounds(66, 43, 86, 20);
		contentPane.add(textFieldCodInsumo);
		textFieldCodInsumo.setColumns(10);

		lblNomeInsumo = new JLabel("Nome");
		lblNomeInsumo.setBounds(10, 88, 46, 14);
		contentPane.add(lblNomeInsumo);

		lblCodInsumo = new JLabel("Cod");
		lblCodInsumo.setBounds(10, 46, 46, 14);
		contentPane.add(lblCodInsumo);

		textFieldNomeInsumo = new JTextField();
		textFieldNomeInsumo.setBounds(88, 85, 233, 20);
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
						Boolean gluten = checkBoxGluten.isSelected();
						
						Insumo p = Fachadaold.localizarInsumo(Integer.parseInt(textFieldCodInsumo.getText()));					
						if (p == null) {
							p = Fachadaold.cadastrarInsumo(nome,lactose,gluten);
						}else {
							Fachadaold.atualizarInsumo(p.getId(),nome,lactose,gluten);
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
		btnSalvar.setBounds(37, 114, 115, 23);
		contentPane.add(btnSalvar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(26, 153, 273, 14);
		contentPane.add(lblmsg);
		
		chckbxLactose = new JCheckBox("Lactose");
		chckbxLactose.setBounds(172, 40, 63, 23);
		contentPane.add(chckbxLactose);
		
		checkBoxGluten = new JCheckBox("Gl�ten");
		checkBoxGluten.setBounds(249, 42, 72, 23);
		contentPane.add(checkBoxGluten);
		
		btnNovoInsumo = new JButton("Novo insumo");
		btnNovoInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldCodInsumo.setText("0");
				textFieldCodInsumo.setEnabled(false);
				textFieldNomeInsumo.setText("Digite o nome");
				chckbxLactose.setSelected(false);
				checkBoxGluten.setSelected(false);
			}
		});
		btnNovoInsumo.setBounds(41, 9, 111, 23);
		contentPane.add(btnNovoInsumo);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Insumo selecionado;
				String nome = JOptionPane.showInputDialog(btnLocalizar, "Nome do insumo", "Localiza prato", 0);
				List<Insumo> insumos = Fachadaold.listarInsumo(nome); 
				
				if (insumos.size()>1) {
					selecionado = seleciona (insumos);
				}else {
					selecionado = (Insumo) insumos.toArray()[0];
				}
				atualizaDados(selecionado);	
			}
		});
		btnLocalizar.setBounds(172, 9, 89, 23);
		contentPane.add(btnLocalizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja REALMENTE excluir o insumo?", "Confirmação",0);
					if (opcao==0) {					
						Insumo p = Fachadaold.localizarInsumo(Integer.parseInt(textFieldCodInsumo.getText()));					
						if (p == null) {
							JOptionPane.showMessageDialog(contentPane, "Localize um insumo!");
						}else {
							Fachadaold.removerInsumo(p);
						}
						JOptionPane.showMessageDialog(contentPane, "Excluido com sucesso!");
						btnNovoInsumo.doClick();						
					}	
				} catch (Exception e2) {
					lblmsg.setText(e2.getMessage());
				}
				
			}
		});
		btnExcluir.setBounds(184, 114, 115, 23);
		contentPane.add(btnExcluir);
	}
	
	private void atualizaDados (Insumo selecionado) {
		textFieldCodInsumo.setText(Integer.toString(selecionado.getId()));
		textFieldNomeInsumo.setText(selecionado.getNome());
		chckbxLactose.setSelected(selecionado.isLactose());
		checkBoxGluten.setSelected(selecionado.isGluten());
		
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
