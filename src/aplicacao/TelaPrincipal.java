package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programaï¿½ï¿½o Orientada a Objetos
 * Prof. Fausto Maranhï¿½o Ayres
 **********************************/

import java.awt.Desktop.Action;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {
	private JFrame frmPrincipal;
	private JMenuBar menuBar;
	
	private JMenu mnCadastrar;
	private JMenuItem mntmInsumos;
	private JMenuItem mntmPratos;
	private JMenuItem mntmFuncionario;

	private JMenu mnProducao;
	private JMenuItem mntmControleProducao;

	private JMenu mnAvaliacao;
	private JMenuItem mntmAvaliacaoCozinha;
	
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
		initialize();	
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
						frmPrincipal.setVisible(false);
						TelaLogin login = new TelaLogin(frmPrincipal);
						login.setVisible(true);
						if (login.isSucceeded()) {
							frmPrincipal.setVisible(true);
							login.dispose();
						}else {
						Fachada.finalizar();
						System.exit(0);
					}
					
						//  pre-cadastro
//						Fachada.cadastrar();
//						frmPrincipal.setVisible(true);
					}catch(Exception e){

						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
				@Override
				public void windowClosing(WindowEvent e) {
					JOptionPane.showMessageDialog(frmPrincipal, "até breve !");
					Fachada.finalizar();
				}
			});
			frmPrincipal.setSize(1650,1080);
//			frmPrincipal.setBounds(100, 100, 450, 300);
			frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmPrincipal.getContentPane().setLayout(null);
			
			AbstractAction logout = new AbstractAction()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        JFrame frame = (JFrame)e.getSource();
					Fachada.finalizar();
					System.exit(0);
			    }
			};
			
			InactivityListener listener = new InactivityListener(frmPrincipal, logout, 1);
			listener.start();

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

			mntmFuncionario = new JMenuItem("Funcionário");
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
			
			mnProducao = new JMenu("Produção");
			menuBar.add(mnProducao);

			mntmControleProducao = new JMenuItem("Controle de Produção");
			mntmControleProducao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TelaCadastroProducao j = new TelaCadastroProducao();
					j.setVisible(true);
				}
			});
			mnProducao.add(mntmControleProducao);
			
			mnAvaliacao = new JMenu("Avaliação");
			menuBar.add(mnAvaliacao);
			
			mntmAvaliacaoCozinha = new JMenuItem("Avaliação Cozinha");
			mntmAvaliacaoCozinha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TelaCadastroAvaliacaoCozinha j = new TelaCadastroAvaliacaoCozinha();
					j.setVisible(true);
				}
			});
			mnAvaliacao.add(mntmAvaliacaoCozinha);

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
