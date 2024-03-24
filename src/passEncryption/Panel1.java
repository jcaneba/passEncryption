package passEncryption;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Panel1 extends JPanel {

	private static final long serialVersionUID = 8236148550239545055L;
	
	
	public Panel1() {
	//Panel 1: Encriptar contrasenas
		setLayout(null);
		
		JLabel title1 = new JLabel("Encriptación de contraseñas");
		title1.setFont(new Font("Perpetua Titling MT", Font.BOLD, 20));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setBounds(10, 37, 400, 48);
		add(title1);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPass.setBounds(70, 198, 106, 30);
		add(lblPass);

		JLabel lblDescription = new JLabel("Descripción");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDescription.setBounds(70, 157, 106, 30);
		add(lblDescription);

		JTextField descr = new JTextField();
		lblDescription.setLabelFor(descr);
		descr.setColumns(10);
		descr.setBounds(211, 163, 126, 20);
		add(descr);

		JPasswordField pass = new JPasswordField();
		pass.setBounds(211, 204, 126, 20);
		add(pass);

		JLabel lblVal = new JLabel("");
		lblVal.setBounds(70, 241, 289, 20);
		lblVal.setForeground(Color.red);
		add(lblVal);

		JButton encript = new JButton("Encriptar");

		encript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Borrar el contenido de la etiqueta y la informacion
				lblVal.setText("");

				String passwd=new String(pass.getPassword());
				String description=descr.getText();

				if(passwd.equals("") || description.equals("")) {
					lblVal.setText("Rellena todos los campos");
				}else {
					
					// Pass y descripcion a encriptar
					String[] toInsert= {descr.getText(),new String(pass.getPassword())};
					FileManage.fileWriter(toInsert);

					JFramepassEncryption.info.setText("Contraseña \""+description+"\" encriptada y guardada con éxito\n");

					//Borrar el contenido de los inputs
					pass.setText("");
					descr.setText("");

				}
			}
		});
		encript.setBounds(161, 308, 89, 35);
		add(encript);
		
	}
	
}
