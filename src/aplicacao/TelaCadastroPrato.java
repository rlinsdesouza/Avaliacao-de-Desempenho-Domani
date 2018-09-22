package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Insumo;
import modelo.Prato;

public class TelaCadastroPrato extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCod;
	private JTextField textFieldNome;
	private JLabel lblNomeInsumo;
	private JLabel lblCodInsumo;
	private JButton btnCriar;
	private JLabel lblmsg;
	private JCheckBox chckbxLactose;
	private JCheckBox checkBoxGlutem;
	private JButton btnNovo;
	private JRadioButton rdbtnFacil;
	private JTextField txtTempo;
	private JButton btnLocalizar;
	private JRadioButton rdbtnMedio;
	private JRadioButton rdbtnDifcil;
	private JTextArea textArea;
	private JList list;
	private JButton btnAddInsumo;
	private JButton btnRemoverInsumo;
	private JButton btnAtualizarCarac;

	
	/**
	 * Create the frame.
	 */
	public TelaCadastroPrato() {
		setTitle("Cadastro Prato");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldCod = new JTextField();
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
					int codigo = Integer.parseInt(textFieldCod.getText());
					String nome = textFieldNome.getText();
					Boolean lactose = chckbxLactose.isSelected();
					Boolean glutem = checkBoxGlutem.isSelected();
					
					Insumo i = Fachada.cadastrarInsumo(codigo,nome,lactose,glutem);
					lblmsg.setText("cadastrado "+i.getNome());
					
					textFieldCod.setText("");
					textFieldNome.setText("");
					textFieldCod.requestFocus();
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
		
		chckbxLactose = new JCheckBox("Lactose");
		chckbxLactose.setBounds(307, 48, 97, 23);
		contentPane.add(chckbxLactose);
		
		checkBoxGlutem = new JCheckBox("Glutem");
		checkBoxGlutem.setBounds(408, 48, 97, 23);
		contentPane.add(checkBoxGlutem);
		
		btnNovo = new JButton("Novo prato");
		btnNovo.setBounds(327, 8, 117, 25);
		contentPane.add(btnNovo);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado;
				String nome = JOptionPane.showInputDialog(btnLocalizar, "Nome do prato", "Localiza prato", 0);
				List<Prato> pratos = new ArrayList <Prato>();
				for (Prato prato : Fachada.listarPratos()) {
					if (prato.getNome().toUpperCase().contains(nome.toUpperCase())) 
						pratos.add(prato);
				}
				if (pratos.size()>1) {
					selecionado = (Prato) JOptionPane.showInputDialog(btnLocalizar, 
				        "Escolha apenas um prato",
				        "Prato selecionado",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        pratos.toArray(), 
				        pratos.toArray()[0]);
				}else {
					selecionado = (Prato) pratos.toArray()[0];
				}
				textFieldCod.setText(Integer.toString(selecionado.getId()));
				textFieldNome.setText(selecionado.getNome());
				chckbxLactose.setSelected(selecionado.isLactose());
				checkBoxGlutem.setSelected(selecionado.isGlutem());
				txtTempo.setText(Integer.toString(selecionado.getTempoProduzir()));
				switch (selecionado.getDificuldade()) {
				case 1:
					rdbtnFacil.setSelected(true);
					break;
				case 2:
					rdbtnMedio.setSelected(true);
					break;
				case 3:
					rdbtnDifcil.setSelected(true);
					break;
					
				}
				textArea.setText(selecionado.getReceita());
				if (selecionado.getInsumos() != null) 
					list = new JList(selecionado.getInsumos().toArray());
			}
		});
		btnLocalizar.setBounds(456, 8, 117, 25);
		contentPane.add(btnLocalizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(342, 363, 117, 25);
		contentPane.add(btnCancelar);
		
		rdbtnFacil = new JRadioButton("Fácil");
		rdbtnFacil.setBounds(582, 58, 78, 23);
		contentPane.add(rdbtnFacil);
		
		JLabel lblTempo = new JLabel("Tempo(min)");
		lblTempo.setBounds(160, 13, 91, 15);
		contentPane.add(lblTempo);
		
		txtTempo = new JTextField();
		txtTempo.setBounds(269, 11, 46, 19);
		contentPane.add(txtTempo);
		txtTempo.setColumns(10);
		
		rdbtnMedio = new JRadioButton("Médio");
		rdbtnMedio.setBounds(582, 85, 78, 23);
		contentPane.add(rdbtnMedio);
		
		JLabel lblDificuldade = new JLabel("Dificuldade");
		lblDificuldade.setBounds(578, 35, 82, 15);
		contentPane.add(lblDificuldade);
		
		rdbtnDifcil = new JRadioButton("Difícil");
		rdbtnDifcil.setBounds(582, 107, 78, 23);
		contentPane.add(rdbtnDifcil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 138, 214, 188);
		contentPane.add(scrollPane);
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 138, 286, 188);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		btnAddInsumo = new JButton("Add Insumo");
		btnAddInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insumo selecionado;
				String nome = JOptionPane.showInputDialog(btnAddInsumo, "Nome do insumo", "Localiza insumo",1);
				List<Insumo> insumos = new ArrayList <Insumo>();
				for (Insumo insumo : Fachada.listarInsumo()) {
					if (insumo.getNome().toUpperCase().contains(nome.toUpperCase())) 
						insumos.add(insumo);
				}
				if (insumos.size()>1) {
					selecionado = (Insumo) JOptionPane.showInputDialog(btnLocalizar, 
				        "Escolha apenas um insumo",
				        "Insumo selecionado",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        insumos.toArray(), 
				        insumos.toArray()[0]);
				}else {
					if (insumos.size()==1) {
						selecionado = (Insumo) insumos.toArray()[0];
					}else {
						selecionado = null;
					}					
				}
				Prato prato = Fachada.localizarPrato(Integer.parseInt(textFieldCod.getText())); 
				if (prato.getInsumos()!=null) {
					prato.getInsumos().add(selecionado);
				}else {
					prato.setInsumos(new ArrayList<Insumo>());
					prato.getInsumos().add(selecionado);
				}
				Fachada.atualizarPrato (prato);
			}
		});
		btnAddInsumo.setBounds(582, 166, 117, 25);
		contentPane.add(btnAddInsumo);
		
		btnRemoverInsumo = new JButton("Remover Insumo");
		btnRemoverInsumo.setBounds(582, 203, 156, 25);
		contentPane.add(btnRemoverInsumo);
		
		JLabel lblModoDeFazer = new JLabel("Modo de fazer");
		lblModoDeFazer.setBounds(40, 111, 112, 15);
		contentPane.add(lblModoDeFazer);
		
		JLabel lblInsumos = new JLabel("Insumos");
		lblInsumos.setBounds(360, 111, 70, 15);
		contentPane.add(lblInsumos);
		
		btnAtualizarCarac = new JButton("Atualizar p/ insumos");
		btnAtualizarCarac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtualizarCarac.setBounds(317, 74, 211, 25);
		contentPane.add(btnAtualizarCarac);
	}
}
