package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import fachada.Fachadaold;
import modelo.Insumo;
import modelo.Prato;
import uteis.CreateEtiquetasPDF;

public class TelaGeraEtiquetas extends JFrame {

	private JPanel contentPane;
	private JButton btnCriar;
	private JLabel lblmsg;
	private DefaultListModel listModel;
	private JList list;
	private JButton btnAddPrato;
	private JButton btnRemoverPrato;
	private JScrollPane scrollPane;
	private JButton btnCancelar;

	
	/**
	 * Create the frame.
	 */
	public TelaGeraEtiquetas() {
		setTitle("Gerar PDF Etiquetas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Gerar ArquivoPDF");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
						
					List<Prato> pratos = new ArrayList<Prato>(); 
					for (Object prato : listModel.toArray()) {
						pratos.add((Prato) prato);
					};
					
					CreateEtiquetasPDF etiquetas = new CreateEtiquetasPDF();
					etiquetas.createPdf("teste.pdf", pratos);
					JOptionPane.showMessageDialog(contentPane, "Arquivo gerado com sucesso!", "Confirmação", 1);
					dispose();
				
					
				}
				catch(Exception erro){
					erro.printStackTrace();
					//lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(12, 243, 168, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(181, 399, 347, 14);
		contentPane.add(lblmsg);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(192, 242, 117, 25);
		contentPane.add(btnCancelar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 39, 214, 188);
		contentPane.add(scrollPane);
		listModel = new DefaultListModel<Insumo>();
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		
		btnAddPrato = new JButton("Add Prato");
		btnAddPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prato selecionado;
				String nome = JOptionPane.showInputDialog(btnAddPrato, "Nome do prato", "Localiza prato",1);
				List<Prato> pratos = Fachadaold.listarPratos(nome);

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
		btnAddPrato.setBounds(12, 12, 117, 25);
		contentPane.add(btnAddPrato);
		
		btnRemoverPrato = new JButton("Remover prato");
		btnRemoverPrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.removeElementAt(list.getSelectedIndex());
			}
		});
		btnRemoverPrato.setBounds(141, 12, 156, 25);
		contentPane.add(btnRemoverPrato);
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
