package servidor;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Frame;

public class TelaServidor extends JFrame{
	private Servidor servidor = new Servidor();
    /**
     * Construtor da classe.
     * http://andersonneto.blogspot.com/2014/06/minimizar-aplicacao-para-barra-de.html
     * @param nome
     */
    public TelaServidor(String nome) {
        super(nome); // Coloca o t�tulo da Janela
        setState(Frame.ICONIFIED);
        setVisible(true); // Define a janela como vis�vel
        setBounds(50,50,370,150); // Define o tamanho e posi��o da tela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o modo de fechamento
        setResizable(false); // Define como n�o redimension�vel
        getContentPane().setLayout(null);
        
        JButton btnFecharServidor = new JButton("Fechar Servidor");
        btnFecharServidor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		servidor.close();
        		System.exit(0);
        	}
        });
        btnFecharServidor.setBounds(47, 26, 151, 23);
        getContentPane().add(btnFecharServidor);
    }
 
    public static void main(String[] args) {
        // Instancia nova janela
        final TelaServidor main = new TelaServidor("Servidor - PGD");

 
        // Instancia um novo SystemTray
        SystemTray tray = SystemTray.getSystemTray();
 
        /**
         *  Pega uma imagem para definir como �cone.
         *
         *  main.getClass().getClassLoader().getResource("icone.jpg")
         *  pega a imagem do pacote onde a Classe se encontra.
         *  Ser� bem �til na hora de exportar a aplica��o.
         */
//        Image imageIcon = new ImageIcon((main.getClass()
//                                                .getClassLoader()
//                                                .getResource("icondomani.jpg")))
//                                                .getImage();
 
        Image imageIcon = new ImageIcon("icondomani.jpg").getImage();
        
        // Instancia e Define o icone do TrayIcon
        TrayIcon trayIcon = new TrayIcon(imageIcon);
 
        // Define o auto-ajuste da imagem
        trayIcon.setImageAutoSize(true);
 
        /**
         * Instancia um mouse listener para ser usado no TrayIcon
         */
        MouseListener mlOpcoes = new MouseListener(){  
 
            public void mouseClicked(MouseEvent e) {}  
 
            public void mousePressed(MouseEvent e) {}  
 
            public void mouseReleased(MouseEvent e) {
                /**
                 *  Se o mouse for clicado com a roda do mouse ou com
                 *  o bot�o direito fechar� a aplica��o.
                 */
                if(e.getButton() == 1 || e.getButton() == 2 || e.getButton() == 3){
                    main.setVisible(true);
                }
            }  
 
            public void mouseEntered(MouseEvent e) {}  
 
            public void mouseExited(MouseEvent e) {}
        };
        // adiciona o mouseListener no TrayIcon
        trayIcon.addMouseListener(mlOpcoes);
 
        try {
            // Adiciona o �cone no SystemTray
            tray.add(trayIcon);
        } catch (AWTException e) {}
 
    }
}
