package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
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
	private JCheckBox checkBoxGluten;
	private JButton btnNovo;
	private JRadioButton rdbtnFacil;
	private JTextField txtTempo;
	private JButton btnLocalizar;
	private JRadioButton rdbtnMedio;
	private JRadioButton rdbtnDifcil;
	private JTextArea textArea;
	private DefaultListModel listModel;
	private JList list;
	private JButton btnAddInsumo;
	private JButton btnRemoverInsumo;
	private JButton btnAtualizarCarac;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnCancelar;

	
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
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja salvar/atualizar o produto?", "Confirmação",0);
					if (opcao==0) {
						int dificuldade=1;
						String nome = textFieldNome.getText();
						Boolean lactose = chckbxLactose.isSelected();
						Boolean gluten = checkBoxGluten.isSelected();
						
						if (rdbtnFacil.isSelected()) dificuldade = 1;
						if (rdbtnMedio.isSelected()) dificuldade = 2;
						if (rdbtnDifcil.isSelected()) dificuldade = 3;
						int tempo = Integer.parseInt(txtTempo.getText());
						String receita = textArea.getText();
						List<Insumo> insumos = new ArrayList<Insumo>(); 
						for (Object insumo : listModel.toArray()) {
							insumos.add((Insumo) insumo);
						};
						
						Prato p = Fachada.localizarPrato(Integer.parseInt(textFieldCod.getText()));					
						if (p == null) {
							p = Fachada.cadastrarPrato(nome, receita,dificuldade,tempo,lactose,gluten,insumos);
						}else {
							Fachada.atualizarPrato(p.getId(),nome, receita,dificuldade,tempo,lactose,gluten,insumos);
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
		
		chckbxLactose = new JCheckBox("Lactose");
		chckbxLactose.setBounds(307, 48, 97, 23);
		contentPane.add(chckbxLactose);
		
		checkBoxGluten = new JCheckBox("Gl�ten");
		checkBoxGluten.setBounds(408, 48, 97, 23);
		contentPane.add(checkBoxGluten);
		
		btnNovo = new JButton("Novo prato");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldCod.setText("0");
				textFieldCod.setEnabled(false);
				textFieldNome.setText("Digite o nome do produto");
				chckbxLactose.setSelected(false);
				checkBoxGluten.setSelected(false);
				rdbtnFacil.setSelected(false);
				txtTempo.setText("0");
				rdbtnMedio.setSelected(false);
				rdbtnDifcil.setSelected(false);
				textArea.setText("");
				listModel.clear();

			}
		});
		btnNovo.setBounds(327, 8, 117, 25);
		contentPane.add(btnNovo);
		
		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado;
				String nome = JOptionPane.showInputDialog(btnLocalizar, "Nome do prato", "Localiza prato", 0);
				List<Prato> pratos = Fachada.listarPratos(nome); 
				
				if (pratos.size()>1) {
					selecionado = seleciona (pratos);
				}else {
					selecionado = (Prato) pratos.toArray()[0];
				}
				atualizaDados(selecionado);		
			}
		});
		btnLocalizar.setBounds(456, 8, 117, 25);
		contentPane.add(btnLocalizar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 138, 214, 188);
		contentPane.add(scrollPane);
		
		listModel = new DefaultListModel<Insumo>();
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 138, 286, 188);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		btnAddInsumo = new JButton("Add Insumo");
		btnAddInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insumo selecionado;
				String nome = JOptionPane.showInputDialog(btnAddInsumo, "Nome do insumo", "Localiza insumo",1);
				List<Insumo> insumos = Fachada.listarInsumo(nome);

				if (insumos.size()>1) {
					selecionado = seleciona(insumos);
				}else {
					if (insumos.size()==1) {
						selecionado = (Insumo) insumos.toArray()[0];
					}else {
						selecionado = null;
					}					
				}
				listModel.addElement(selecionado);
//				Prato prato = Fachada.localizarPrato(Integer.parseInt(textFieldCod.getText())); 
//				if (prato.getInsumos()!=null) {
//					prato.getInsumos().add(selecionado);
//				}else {
//					prato.setInsumos(new ArrayList<Insumo>());
//					prato.getInsumos().add(selecionado);
//				}
//				Fachada.atualizarPrato (prato);
//				atualizaDados(prato);
			}
		});
		btnAddInsumo.setBounds(582, 166, 117, 25);
		contentPane.add(btnAddInsumo);
		
		btnRemoverInsumo = new JButton("Remover Insumo");
		btnRemoverInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
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
	private void atualizaDados (Prato selecionado) {
		textFieldCod.setText(Integer.toString(selecionado.getId()));
		textFieldNome.setText(selecionado.getNome());
		chckbxLactose.setSelected(selecionado.isLactose());
		checkBoxGluten.setSelected(selecionado.isGluten());
		txtTempo.setText(Integer.toString(selecionado.getTempoProduzir()));
		rdbtnFacil.setSelected(false);
		rdbtnMedio.setSelected(false);
		rdbtnDifcil.setSelected(false);
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
		listModel.clear();
		if (selecionado.getInsumos() != null) {
			for (Insumo i : selecionado.getInsumos()) {
				listModel.addElement(i);
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
