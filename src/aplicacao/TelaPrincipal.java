package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o Orientada a Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {
	private JFrame frmPrincipal;
	private JMenuBar menuBar;
	private JMenuItem mntmInsumos;
	private JMenuItem mntmCadastrarGarcom;
	private JMenuItem mntmMesasGarcom;
	private JMenuItem mntmFuncionario;
	private JMenuItem mntmListarGarcom;
	private JMenuItem mntmApagarGarcom;
	private JMenu mnCadastrar;
	private JMenu mnGarcom;
	private JMenuItem mntmPratos;
	private JMenu mnConsulta;
	private JMenuItem mntmConsultaGeral;
	private JMenuItem mntmGerarEtiquetas;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		Fachada.inicializar();
		TelaLogin j = new TelaLogin();
		j.setVisible(true);
		initialize();
//		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplica��o");

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Restaurante Domani - Gestão");
		try {
			frmPrincipal.setContentPane(new FundoTela("domani.jpg"));
		} catch (IOException e1) {
		}	

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try{
					//  pre-cadastro
//					Fachada.cadastrar();
				}catch(Exception e){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(null, "ate breve !");
				Fachada.finalizar();
			}
		});

		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		mntmInsumos = new JMenuItem("Insumos");
		mntmInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroInsumo j = new TelaCadastroInsumo();
				j.setVisible(true);
			}
		});
		mnCadastrar.add(mntmInsumos);

		mntmFuncionario = new JMenuItem("Funcionario");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroFuncionario j = new TelaCadastroFuncionario();
				j.setVisible(true);
			}
		});
		
		mntmPratos = new JMenuItem("Pratos");
		mntmPratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroPrato j = new TelaCadastroPrato();
				j.setVisible(true);
			}
		});
		mnCadastrar.add(mntmPratos);
		mnCadastrar.add(mntmFuncionario);
		
		mnGarcom = new JMenu("Garcom");
		menuBar.add(mnGarcom);

		mntmCadastrarGarcom = new JMenuItem("Cadastrar Garcom");
		mntmCadastrarGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaCadastroGarcom j = new TelaCadastroGarcom();
//				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmCadastrarGarcom);
		
		mntmMesasGarcom = new JMenuItem("Definir mesas Garcom");
		mntmMesasGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaCadastroGarcom j = new TelaCadastroGarcom();
//				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmMesasGarcom);

		mntmListarGarcom = new JMenuItem("Listar Garcom");
		mntmListarGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaListagemGarcom j = new TelaListagemGarcom();
//				j.setVisible(true);
			}
		});
		
		mntmApagarGarcom = new JMenuItem("Apagar Garcom");
		mntmApagarGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaRemoverGarcom j = new TelaRemoverGarcom();
//				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmApagarGarcom);
		mnGarcom.add(mntmListarGarcom);

		JMenu mnMesas = new JMenu("Mesas/Pedidos");
		menuBar.add(mnMesas);

		JMenuItem mntmCriar = new JMenuItem("Adicionar mesa");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaCadastroMesas j = new TelaCadastroMesas();
//				j.setVisible(true);
			}
		});
		mnMesas.add(mntmCriar);
		
		JMenuItem mntmCriar2 = new JMenuItem("Controle de contas");
		mntmCriar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaControleConta j = new TelaControleConta();
//				j.setVisible(true);
			}
		});
		mnMesas.add(mntmCriar2);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				TelaListagemMesas j = new TelaListagemMesas();
//				j.setVisible(true);
			}
		});
		mnMesas.add(mntmListar_1);

		JMenuItem mntmInserirProduto = new JMenuItem("Solicitar produto");
		mntmInserirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaInserirProdutoMesas j = new TelaInserirProdutoMesas();
//				j.setVisible(true);
			}
		});
		mnMesas.add(mntmInserirProduto);
		
		JMenuItem mntmRemoverProduto = new JMenuItem("Remover produto");
		mntmRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				TelaRemoverProdutoMesas j = new TelaRemoverProdutoMesas();
//				j.setVisible(true);
			}
		});
		mnMesas.add(mntmRemoverProduto);

		mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		
		mntmConsultaGeral = new JMenuItem("Consulta Geral");
		mntmConsultaGeral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConsulta j = new TelaConsulta();
				j.setVisible(true);
			}
		});
		mnConsulta.add(mntmConsultaGeral);
		
		mntmGerarEtiquetas = new JMenuItem("Gerar etiquetas");
		mntmGerarEtiquetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaGeraEtiquetas j = new TelaGeraEtiquetas();
				j.setVisible(true);
			}
		});
		mnConsulta.add(mntmGerarEtiquetas);
	
	
	}
}
