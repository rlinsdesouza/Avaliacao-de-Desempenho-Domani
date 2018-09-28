package aplicacao;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import fachada.Fachada;
import modelo.Avaliacao;
import modelo.Funcionario;
import modelo.Producao;

public class TelaAvaliacaoCozinha extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JLabel lblNomeCozinheiro;
	private JButton btnSalvar;
	private JList listAparecencia;
	private JButton btnCancelar;
	private JLabel lblAvaliados;
	private JDateChooser datePicker;
	
	private DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField textFieldAvaliador;
	private JList listSabor;
	private JLabel lblPratosNoAvaliados;
	private JLabel lblNomeDoAvaliador;
	private String[] opcoes = {"RUIM","SATISFAT�RIO","BOM"};
	private JLabel lblPrato;
	private JTextField textFieldPrato;
	private Avaliacao avaliacao;
	private JLabel lblJustificativa;
	private JTextField textFieldJusiticativa;
	
	/**
	 * Create the frame.
	 */
	public TelaAvaliacaoCozinha(final Producao producao, final Funcionario avaliador) {
		setModal(true);
		setTitle("Avalia\u00E7\u00E3o de um prato");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNomeCozinheiro = new JLabel("Cozinheiro");
		lblNomeCozinheiro.setBounds(10, 52, 59, 14);
		contentPane.add(lblNomeCozinheiro);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 49, 233, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		textFieldNome.setText(producao.getCozinheiro().getNome());
		textFieldNome.setEnabled(false);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja salvar/atualizar essas produc�es para o funcion�rio?", "Confirmação",0);
					if (opcao==0) {
						int notaSabor=10;
						int notaAparencia=10;
						
						
						switch (listAparecencia.getSelectedIndex()) {
						case 0:
							notaAparencia = 0;
							break;
						case 1:
							notaAparencia = 5;
							break;
						case 2:
							notaAparencia = 10;
							break;
						}
						
						switch (listSabor.getSelectedIndex()) {
						case 0:
							notaSabor = 0;
							break;
						case 1:
							notaSabor = 5;
							break;
						case 2:
							notaSabor = 10;
							break;
						}
						
						avaliacao = Fachada.cadastrarAvaliacao(producao,notaSabor,notaAparencia,textFieldJusiticativa.getText(),avaliador);					
						JOptionPane.showMessageDialog(contentPane, "Salvo/Atualizado com sucesso!", "Confirma��o",2);
						dispose();
					}
					
				}
				catch(Exception erro){
					erro.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(26, 252, 115, 23);
		contentPane.add(btnSalvar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(168, 251, 117, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(22, 14, 46, 15);
		contentPane.add(lblData);
		

		datePicker = new JDateChooser ();
		datePicker.setBounds(66, 9, 200, 20);

		contentPane.add(datePicker);
		try {
			datePicker.setDate(sf.parse(producao.getData()));
			datePicker.setEnabled(false);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblAvaliados = new JLabel("Aparencia?");
		lblAvaliados.setBounds(199, 133, 67, 15);
		contentPane.add(lblAvaliados);
		

		
		lblPratosNoAvaliados = new JLabel("Sabor?");
		lblPratosNoAvaliados.setBounds(54, 133, 61, 15);
		contentPane.add(lblPratosNoAvaliados);
		
		lblNomeDoAvaliador = new JLabel("Avaliador");
		lblNomeDoAvaliador.setBounds(10, 77, 59, 14);
		contentPane.add(lblNomeDoAvaliador);
		
		textFieldAvaliador = new JTextField();
		textFieldAvaliador.setColumns(10);
		textFieldAvaliador.setBounds(66, 74, 233, 20);
		contentPane.add(textFieldAvaliador);
		textFieldAvaliador.setText(avaliador.getNome());
		textFieldAvaliador.setEnabled(false);
		
		listSabor = new JList(opcoes);
		listSabor.setBounds(59, 159, 82, 56);
		contentPane.add(listSabor);
		listSabor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAparecencia = new JList(opcoes);
		listAparecencia.setBounds(203, 159, 82, 56);
		contentPane.add(listAparecencia);
		listAparecencia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		lblPrato = new JLabel("Prato");
		lblPrato.setBounds(10, 102, 59, 14);
		contentPane.add(lblPrato);
		
		textFieldPrato = new JTextField();
		textFieldPrato.setColumns(10);
		textFieldPrato.setBounds(66, 102, 233, 20);
		contentPane.add(textFieldPrato);
		textFieldPrato.setText(producao.getPrato().getNome());
		textFieldPrato.setEnabled(false);
		
		lblJustificativa = new JLabel("Justificativa");
		lblJustificativa.setBounds(0, 227, 59, 14);
		contentPane.add(lblJustificativa);
		
		textFieldJusiticativa = new JTextField();
		textFieldJusiticativa.setText((String) null);
		textFieldJusiticativa.setColumns(10);
		textFieldJusiticativa.setBounds(66, 226, 246, 20);
		contentPane.add(textFieldJusiticativa);
	}
	
	public Avaliacao getAvaliacao() {
        return avaliacao;
    }
}
