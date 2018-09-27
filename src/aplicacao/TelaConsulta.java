package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Funcionario;
import modelo.Producao;
import uteis.CreateEtiquetasPDF;

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConsulta_1;
	private JButton btnConsulta_2;
	private JButton btnConsulta_3;
	private JButton btnMesasSemGarcom;
	private JButton button;
	private JButton btnDescontoMedioGarcom;
	private JButton btnPDFPlacasPratos;
	private JButton btnTodasAsProducoes;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaConsulta frame = new TelaConsulta();
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
	public TelaConsulta() {
		setTitle("Consultar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnConsulta_1 = new JButton("Todas os funcionarios do restaurante");
		btnConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String texto;
					List<Funcionario> lista1 = Fachada.listarFuncionarios();
					texto = "Listagem de funcionarios: \n";
					if (lista1.isEmpty())
						texto += "n�o existe";
					else
						for(Funcionario f: lista1)
							texto +=  f + "\n";

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnConsulta_1.setBounds(414, 13, 271, 23);
		contentPane.add(btnConsulta_1);

		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 11, 348, 228);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		btnPDFPlacasPratos = new JButton("PDF placas pratos");
		btnPDFPlacasPratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CreateEtiquetasPDF pdf = new CreateEtiquetasPDF();
					pdf.createPdf("teste.pdf", Fachada.listarPratos("arroz"));					
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		btnPDFPlacasPratos.setBounds(412, 48, 273, 25);
		contentPane.add(btnPDFPlacasPratos);
		
		btnTodasAsProducoes = new JButton("Todas as producoes");
		btnTodasAsProducoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String texto;
					List<Producao> lista1 = Fachada.listarProducoes();
					texto = "Listagem de funcionarios: \n";
					if (lista1.isEmpty())
						texto += "n�o existe";
					else
						for(Producao f: lista1)
							texto +=  f + "\n";

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnTodasAsProducoes.setBounds(414, 84, 271, 23);
		contentPane.add(btnTodasAsProducoes);
	}
}
