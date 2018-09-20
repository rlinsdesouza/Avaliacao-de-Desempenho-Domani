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

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConsulta_1;
	private JButton btnConsulta_2;
	private JButton btnConsulta_3;
	private JButton btnMesasSemGarcom;
	private JButton button;
	private JButton btnDescontoMedioGarcom;

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

//		btnConsulta_2 = new JButton("Consultar Conta Parcial da mesa");
//		btnConsulta_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int resposta = Integer.parseInt(JOptionPane.showInputDialog(null, "Favor digite a mesa: "));
//				List<Conta> lista1 = Fachada.listarContas();
//				String texto = "Listagem de contas para mesa:"+resposta+"\n";
//				List<Conta> lista2 = new ArrayList<Conta>();
//				for (Conta conta : lista1) {
//					Conta contaAdd = conta;
//					if (contaAdd.getMesa().getId() == resposta && contaAdd.getDtfechamento() == null) {
//						lista2.add(contaAdd);
//					}
//				}
//
//				if (lista2.isEmpty())
//					texto += "n�o tem\n";
//				else
//					for(Conta p: lista2)
//						texto +=  p + "\n";
//
//				textArea.setText(texto);
//			}
//		});
//		btnConsulta_2.setBounds(414, 47, 271, 23);
//		contentPane.add(btnConsulta_2);
//
//		btnConsulta_3 = new JButton("Gorjeta Garçom");
//		btnConsulta_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String nome = JOptionPane.showInputDialog("nome do garçom");
//					//String data = JOptionPane.showInputDialog("Data (Dia/Mes/Ano): ");
//					String texto;
//					List<Conta> lista1;
//
//					lista1 = Fachada.listarContas(nome);
//					texto = "A gorjeta do garçom "+nome+" foi: "+Fachada.calcularGorjeta(nome)+"\n";
//					texto += "Listagem de contas: \n";
//					if (lista1.isEmpty())
//						texto += "n�o existe";
//					else
//						for(Conta p: lista1)
//							texto +=  p + "\n";
//
//					textArea.setText(texto);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					textArea.setText(e1.getMessage());
//				}
//			}
//		});
//		btnConsulta_3.setBounds(414, 112, 271, 23);
//		contentPane.add(btnConsulta_3);
//
//		btnMesasSemGarcom = new JButton("Mesas sem garcom");
//		btnMesasSemGarcom.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				ArrayList<Mesa> resultado = new ArrayList<Mesa>();
//
//				for (Mesa mesa : Fachada.listarMesas()) {
//					if(mesa.getGarcom()==null) {
//						resultado.add(mesa);
//					}
//				}
//
//				String texto = "Listagem de mesas sem garcom: \n";
//				if (resultado.isEmpty())
//					texto += "n�o existe";
//				else
//					for(Mesa m: resultado)
//						texto +=  m + "\n";
//
//				textArea.setText(texto);
//			}
//		});
//		btnMesasSemGarcom.setBounds(414, 175, 271, 23);
//		contentPane.add(btnMesasSemGarcom);
//
//		button = new JButton("Consultar Conta da mesa");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					int resposta = Integer.parseInt(JOptionPane.showInputDialog(null, "Favor digite a mesa: "));
//					Conta contaConsulta = Fachada.consultarConta(resposta);
//					String texto = "Listagem de conta para mesa:"+resposta+"\n";
//					textArea.setText(contaConsulta.toString());
//				} catch (Exception e) {
//					textArea.setText(e.getMessage());
//				}
//
//			}
//		});
//		button.setBounds(414, 81, 271, 23);
//		contentPane.add(button);
//
//		btnDescontoMedioGarcom = new JButton("Desconto Medio Garçom");
//		btnDescontoMedioGarcom.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					String nome = JOptionPane.showInputDialog("nome do garçom");
//					//String data = JOptionPane.showInputDialog("Data (Dia/Mes/Ano): ");
//					String texto;
//					ArrayList<Conta> lista1;
//
//					texto = "O desconto médio do garçom "+nome+" foi: "+Fachada.calcularPercentualMedio(nome)+"\n";
//
//					textArea.setText(texto);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//					textArea.setText(e1.getMessage());
//				}
//			}
//		});
//		btnDescontoMedioGarcom.setBounds(414, 147, 271, 23);
//		contentPane.add(btnDescontoMedioGarcom);
	}
}
