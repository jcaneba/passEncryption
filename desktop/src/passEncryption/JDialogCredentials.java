package passEncryption;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class JDialogCredentials extends JDialog {

	private static final long serialVersionUID = 4787045596310235252L;
	private final JPanel contentPanel = new JPanel();
	private JTextField userCred;
	private JPasswordField passCred;

	/**
	 * Create the dialog.
	 */
	public JDialogCredentials() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JDialogCredentials.class.getResource("/resources/users.png")));
		setTitle("Credenciales");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUser.setBounds(110, 56, 86, 27);
		contentPanel.add(lblUser);

		JLabel Passwd = new JLabel("Contrasena");
		Passwd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Passwd.setBounds(110, 120, 86, 27);
		contentPanel.add(Passwd);

		userCred = new JTextField();
		userCred.setBounds(231, 60, 86, 20);
		contentPanel.add(userCred);
		userCred.setColumns(10);

		passCred = new JPasswordField();
		passCred.setBounds(231, 124, 86, 20);
		contentPanel.add(passCred);

		JLabel lblValCred = new JLabel("");
		lblValCred.setBounds(28, 163, 375, 20);
		lblValCred.setForeground(Color.red);
		contentPanel.add(lblValCred);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String pass =new String(passCred.getPassword());

						if(userCred.getText().equals("")) {
							lblValCred.setText("Introduce Usuario");
						}else if(pass.equals("")) {
							lblValCred.setText("Introduce Contrase�a");
						}else {
							//Si las credenciales son correctas se abrira la aplicacion
							if(userCred.getText().equals("jeroje") && pass.equals("212223")) {
								try {
									JFramepassEncryption frame = new JFramepassEncryption();
									frame.setVisible(true);
								} catch (Exception e1) {
									e1.printStackTrace();
								}

								//Se mostrara un mensaje en la caja de informacion
								JFramepassEncryption.info.append("Contraseña correcta. Bienvenido.");

								//Cerrar el JDialog si las credenciales son correctas
								dispose();

							}else {
								lblValCred.setText("Usuario y contraseña incorrectos");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
