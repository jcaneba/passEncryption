package passEncryption;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class Panel3 extends JPanel {

	private static final long serialVersionUID = -4947567285085035065L;

	private static String selectedDesc = "";
	private static String selectedPass = "";
	
	
	public static JLabel container3;

	public Panel3(){
		//Panel 3: gestion de contrasenas
		setLayout(null);

		JLabel title3 = new JLabel("Selecciona la contraseña que deseas modificar");
		title3.setBounds(10, 36, 400, 48);
		title3.setFont(new Font("Tahoma", Font.BOLD, 15));
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		add(title3);

		JScrollPane JScrollPane2 = new JScrollPane();
		JScrollPane2.setBounds(54, 108, 307, 231);
		JScrollPane2.setBorder(null);
		add(JScrollPane2);
		
		Panel3.container3 = new JLabel("");
		Panel3.container3.setHorizontalAlignment(SwingConstants.CENTER);
		JScrollPane2.setViewportView(Panel3.container3);
		//Velocidad del Scroll
		JScrollPane2.getVerticalScrollBar().setUnitIncrement(10);
		
		
		//Boton modificar
		JButton btnModify = new JButton("Modificar");
		
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PassManage.opciones.getSelection()!=null) {
					Enumeration<AbstractButton> elements = PassManage.opciones.getElements();
					while (elements.hasMoreElements()) {
						JRadioButton rbpass=(JRadioButton) elements.nextElement();
						if (rbpass.isSelected()) {
							selectedPass=rbpass.getName();
							selectedDesc=StringEncrypt.aesEncrypt(StringEncrypt.blowfishEncrypt(StringEncrypt.desEncrypt(rbpass.getText())));
							break;
				        }
					}
					removeAll();
					
					
					JLabel title4 = new JLabel("Selecciona una opción");
					title4.setBounds(10, 36, 400, 48);
					title4.setFont(new Font("Tahoma", Font.BOLD, 15));
					title4.setHorizontalAlignment(SwingConstants.CENTER);
					add(title4);
					
					
					ButtonGroup opciones2 = new ButtonGroup();
					JRadioButton rbUpdate = new JRadioButton("Nueva contraseña");
					JRadioButton rbRemove = new JRadioButton("Borrar contraseña");
					rbUpdate.setBounds(0, 150, 150, 23);
					rbRemove.setBounds(150, 200, 150, 23);
					
					JPasswordField newPass=new JPasswordField();
					newPass.setBounds(150, 150, 250, 23);
					add(newPass);
					
					opciones2.add(rbUpdate);
					opciones2.add(rbRemove);
					add(rbUpdate);
					add(rbRemove);
					
					//Boton modificar
					JButton btnOption = new JButton("Continuar");
					btnOption.setBounds(90, 369, 95, 23);
					btnOption.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(opciones2.getSelection()!=null) {
								
								if(rbUpdate.isSelected() && !new String(newPass.getPassword()).equals("")) {
									String encryptedPass=StringEncrypt.aesEncrypt(StringEncrypt.blowfishEncrypt(StringEncrypt.desEncrypt(new String(newPass.getPassword()))));
									
									//Llamada al metodo que modifica contrasenas
									FileManage.lineUpdate("update",selectedDesc,selectedPass,encryptedPass);
									JFramepassEncryption.info.setText("Contraseña modificada correctamente"+System.lineSeparator());
									
								}else if(rbRemove.isSelected()) {
									
									//Llamada al metodo que borra contrasenas
									FileManage.lineUpdate("delete",selectedDesc,selectedPass,"");
									JFramepassEncryption.info.setText("Contraseña borrada correctamente"+System.lineSeparator());
									
								}
								
								removeAll();
								add(title3);
								add(JScrollPane2);
								JScrollPane2.setViewportView(Panel3.container3);
								add(btnModify);
								
								PassManage.showPasswd("update");
								updateUI();
							}else {
								JFramepassEncryption.info.setText("No se ha seleccionado ninguna opcion"+System.lineSeparator());
							}
						}
					});
					add(btnOption);
					
					JButton btnCancel = new JButton("Cancelar");
					btnCancel.setBounds(240, 369, 95, 23);
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
					
							removeAll();
							add(title3);
							add(JScrollPane2);
							JScrollPane2.setViewportView(Panel3.container3);
							add(btnModify);
							updateUI();

						}
					});
					add(btnCancel);

					updateUI();

				}else {
					JFramepassEncryption.info.setText("No se ha seleccionado contraseña"+System.lineSeparator());
				}
			}
		});
		btnModify.setBounds(162, 369, 93, 35);
		add(btnModify);

	}
}
