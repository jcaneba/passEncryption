package passEncryption;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class PassManage {

	public static int counter;
	public static ButtonGroup opciones;
	
	
	public static void showPasswd(String option){
		opciones = new ButtonGroup();

		List<List<String>> arraylist=FileManage.fileReader();
		if(option.equals("update")) {
			//Si se quiere actualizar los contenedores, se borran previamente todas las contrasenas para escribirlas de nuevo
			Panel2.container2.removeAll();
			Panel3.container3.removeAll();
		}

		//Coordenadas Y de las lineas de cada contenedor
		int y1=5;
		int y2=5;

		//Bucle
		for(int n=0;n<PassManage.counter;n++) {
			String encryptedDescr=arraylist.get(n).get(0);
			String encryptedUser=arraylist.get(n).get(1);
			String encryptedPass=arraylist.get(n).get(2);
			String decryptedDescr=StringEncrypt.desDecrypt(StringEncrypt.blowfishDecrypt(StringEncrypt.aesDecrypt(encryptedDescr)));
			String decryptedUser=StringEncrypt.desDecrypt(StringEncrypt.blowfishDecrypt(StringEncrypt.aesDecrypt(encryptedUser)));
			String decryptedPass=StringEncrypt.desDecrypt(StringEncrypt.blowfishDecrypt(StringEncrypt.aesDecrypt(encryptedPass)));

			//Panel 2
			//Descripcion desencriptada
			JLabel tag = new JLabel(decryptedDescr+": "+decryptedUser);
			// Posicion del tag
			tag.setBounds(25, y1, 194, 19);
			Panel2.container2.add(tag);

			//Botones para cada contrasena guardada
			JButton mostrar = new JButton("Ver");
			JButton copyButton = new JButton("Copiar");
			//Posicion del boton. Aumenta en 30 la altura de cada uno
			mostrar.setBounds(244, y1, 64, 23);
			copyButton.setBounds(315, y1, 73, 23);
			copyButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StringSelection stringSelection = new StringSelection(decryptedPass);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					JFramepassEncryption.info.setText("Contraseña copiada al portapapeles");
				}
			});

			//Anadir el boton
			Panel2.container2.add(mostrar);
			Panel2.container2.add(copyButton);

			//Eventos del ratón
			String hiddenPass = "*".repeat(decryptedPass.length());
			mostrar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Panel2.container1.setText(decryptedPass);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					Panel2.container1.setText(hiddenPass);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					Panel2.container1.setText(hiddenPass);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					Panel2.container1.setText("");
				}
			});
			//Aumenta la posicion en el eje Y para posicionarlos en linea
			y1+=30;

			//Panel 3
			JRadioButton rbpass = new JRadioButton(decryptedDescr);
			rbpass.setName(encryptedPass);
			opciones.add(rbpass);
			rbpass.setBounds(0, y2, 250, 23);
			y2+=20;
			Panel3.container3.add(rbpass);
		}
		
		
		//Activar o desactivar las pestanas 2 y 3 si hay contraseñas
		FileManage.switchTabs();

		
		//TODO: Ordenar los campos: array ordenado con Tertiary Collator
/*
		List<String> sortedTertiaryCollator = new ArrayList();

		for(int i=0;i<=Panel2.container2.getComponentCount();i+=2) {
			
			String labelText = ((JLabel) Panel2.container2.getComponent(i)).getText();
			
			System.out.println(labelText.split(": ")[1]);
			sortedTertiaryCollator.add(labelText.split(": ")[1]);
			
			
			
		}

		
		String labelText = ((JLabel) Panel2.container2.getComponent(8)).getText();
		
		System.out.println(labelText.split(": ")[1]);
*/



		// Incrementar la posicion de los contenedores
		Panel2.container2.setPreferredSize(new Dimension(382,y1));
		Panel3.container3.setPreferredSize(new Dimension(290,y2));

		//Actualizar los contenedores para mostrar las contrasenas nuevas
		Panel2.container2.updateUI();
		Panel3.container3.updateUI();

	}
	
	
}
