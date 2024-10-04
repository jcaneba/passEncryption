package passEncryption;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class Panel4 extends JPanel {

	private static final long serialVersionUID = -4947567285085035065L;	
	
	public static JLabel container4;
	public static JLabel container5;
	private static final Map<String, String> CHARACTERS = new HashMap<>() {
		private static final long serialVersionUID = 1L;
		{
	        put("mayus", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	        put("minus", "abcdefghijklmnopqrstuvwxyz");
	        put("numbers", "0123456789");
	        put("symbols", "!@#$%^&*()-=+{}[]|;:\"'<>,.?/\\`~");
	    }
	};

	public Panel4(){
		//Panel 4: generación de contraseñas aleatorias
		setLayout(null);

		JLabel title4 = new JLabel("Contraseña aleatoria");
		title4.setBounds(10, 36, 400, 48);
		title4.setFont(new Font("Tahoma", Font.BOLD, 15));
		title4.setHorizontalAlignment(SwingConstants.CENTER);
		add(title4);
		
		Panel4.container4 = new JLabel("",SwingConstants.CENTER);
		Panel4.container4.setBounds(10, 117, 400, 46);
		Panel4.container4.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(Panel4.container4);
		
		Panel4.container5 = new JLabel("",SwingConstants.CENTER);
		Panel4.container5.setBounds(10, 185, 400, 274);
		add(Panel4.container5);
		

		JLabel tag = new JLabel("Número de caracteres");
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, null, 1); // Valor inicial, mínimo, máximo, paso
		JSpinner spinner = new JSpinner(model);
		JCheckBox checkBox1 = new JCheckBox("Mayúsculas");
		JCheckBox checkBox2 = new JCheckBox("Minúsculas");
		JCheckBox checkBox3 = new JCheckBox("Números");
		JCheckBox checkBox4 = new JCheckBox("Símbolos");

		tag.setBounds(60, 5, 194, 20);
		spinner.setBounds(0, 5, 50, 20);
		checkBox1.setBounds(0, 40, 200, 20);
		checkBox2.setBounds(0, 60, 200, 20);
		checkBox3.setBounds(0, 80, 200, 20);
		checkBox4.setBounds(0, 100, 200, 20);
		
		// Agregar los elementos al JLabel
		Panel4.container5.add(tag);
		Panel4.container5.add(spinner);
		Panel4.container5.add(checkBox1);
		Panel4.container5.add(checkBox2);
		Panel4.container5.add(checkBox3);
		Panel4.container5.add(checkBox4);
		
		Map<String, JCheckBox> checkBoxMap = new HashMap<>();
		checkBoxMap.put("mayus", checkBox1);
		checkBoxMap.put("minus", checkBox2);
		checkBoxMap.put("numbers", checkBox3);
		checkBoxMap.put("symbols", checkBox4);
		
		//Boton modificar
		JButton btnGenerate = new JButton("Generar");
		JButton copyButton = new JButton("Copiar");
		JButton importButton = new JButton("Importar");
		
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newPass = "";
				String characters = "";
				Random random = new Random();
				int passSize = (int) spinner.getValue();
		        
				for (Map.Entry<String, JCheckBox> entry : checkBoxMap.entrySet()) {
					if (entry.getValue().isSelected()) {
						characters += CHARACTERS.get(entry.getKey());
					}
				}
				int charLen = characters.length();
				if(charLen > 0) {
					for (int i = 0; i <= passSize-1; i++) {
						newPass += characters.charAt(random.nextInt(charLen));
					}
					Panel4.container4.setText(newPass);
				}else {
					JFramepassEncryption.info.setText("No se ha seleccionado ninguna opcion"+System.lineSeparator());
				}
				
			}
		});
		copyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pass = Panel4.container4.getText();
				StringSelection stringSelection = new StringSelection(pass);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				JFramepassEncryption.info.setText("Contraseña copiada al portapapeles");
			}
		});
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pass = Panel4.container4.getText();
				String[] toInsert= {"Random",pass};
				FileManage.fileWriter(toInsert);
				JOptionPane.showMessageDialog(null, "Contraseña importada con éxito");
			}
		});
		btnGenerate.setBounds(162, 369, 89, 35);
		copyButton.setBounds(115, 329, 90, 23);
		importButton.setBounds(212, 329, 90, 23);
		add(btnGenerate);
		add(copyButton);
		add(importButton);
	}
}