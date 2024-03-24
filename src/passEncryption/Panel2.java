package passEncryption;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


public class Panel2 extends JPanel{

	private static final long serialVersionUID = -8119268876164370324L;

	public static JLabel container1;
	public static JLabel container2;
	
	public Panel2() {
		//Panel 2: desencriptar contrasenas
		
		setLayout(null);
		
		JLabel title2 = new JLabel("Desencriptado de contrase\u00F1as");
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
		title2.setBounds(10, 35, 400, 48);
		add(title2);
		
		//JLabel con el texto centrado
		Panel2.container1 = new JLabel("",SwingConstants.CENTER);
		Panel2.container1.setBounds(10, 117, 400, 46);
		Panel2.container1.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(Panel2.container1);

		JScrollPane JScrollPane1 = new JScrollPane();
		JScrollPane1.setBorder(null);
		//Nunca se mostrara el scroll horizontal
		JScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane1.setBounds(10, 185, 400, 274);
		add(JScrollPane1);

		//JLabel dentro del JScrollPane. En el se mostrara todos los botones y jlabel asociados a las contrasenas guardadas
		Panel2.container2 = new JLabel("");
		Panel2.container2.setHorizontalAlignment(SwingConstants.CENTER);
		JScrollPane1.setViewportView(Panel2.container2);
		JScrollPane1.getVerticalScrollBar().setUnitIncrement(15);
	}
}