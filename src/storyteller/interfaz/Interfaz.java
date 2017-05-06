package storyteller.interfaz;
/*Importes de bibliotecas*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Clase Interfaz
 * 
 */
public class Interfaz extends JFrame{
	/*Variables Globales*/
	private JPanel jPanel00;
	private JPanel jPanel01;
	private JButton jBotonStart;
	private Color color = Color.black; 				//color negro por defecto
    private Image dbImage; 							//Doble buffer
    private Graphics dbg; 							//Doble buffer
	//private JButton jBoton;
	/*Constructor*/
	public Interfaz()
	{
		//Inicializo la interfaz 
		super("Proyecto Programado 02, Estructura de Datos, StoryTeller");
		jPanel00 = new JPanel();
		jPanel01 = new JPanel();
		jBotonStart = new JButton("<START>");
		
		setSize(1000, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Inicializo el panel de botones
		jPanel01=new JPanel();
		jPanel01.setBackground(Color.WHITE);
		JPanel panelBotones=new JPanel();
		panelBotones.setBackground(Color.DARK_GRAY);
		//Botones size
		panelBotones.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		jBotonStart.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked" })
			public void actionPerformed(ActionEvent evt) {
				String start = JOptionPane.showInputDialog(null,null,"¡Empieza el programa!",3);
			}
		});
		//
		jPanel00.add(jBotonStart);
		
		//Inicializar el contenedor con los dos paneles
		Container cp=getContentPane();
		cp.add(jPanel00,BorderLayout.WEST);
		cp.add(jPanel01,BorderLayout.CENTER);
	}
	
	/*Paint*/
	public void paint(Graphics g)
	{
		//doble buffer
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        //pinta
        g.drawImage(dbImage, 0, 0, this);  
	}
	
	/*PaintComponent*/
	public void paintComponent(Graphics g)
	{
		//Constructor
		super.paint(g);	
		//Propiedades Frame paint
		g.setColor(Color.black);
		//
		
	}
}

