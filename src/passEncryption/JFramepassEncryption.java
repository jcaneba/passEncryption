package passEncryption;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JDialog;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFramepassEncryption extends JFrame {

	//Warning en la clase: se debe de definir un serialVersionUID
	private static final long serialVersionUID = 3817826696598819821L;
	//Para cambiar el fondo a negro
	public static JPanel contentPane;

	//Componentes publicos que se utilizan en otras clases
	//Para mostrar informacion desde los metodos
	public static JTextArea info;
	//Para desactivar y activar las pestanas 2 y 3
	public static JTabbedPane tabbedPane;
	public static Panel1 panel1;
	public static Panel2 panel2;
	public static Panel3 panel3;
	public static Panel4 panel4;


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Abre el JDialog al iniciar el programa
				try {
					JDialogCredentials dialog = new JDialogCredentials();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public JFramepassEncryption() {

		//Icono de la ventana
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(JFramepassEncryption.class.getResource("/resources/image.png")));

		setResizable(false);
		setTitle("Encriptación de contraseñas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 739);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);

		JMenuItem menuChangePath = new JMenuItem("Cambiar ruta");
		menuChangePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileManage.changePath();
				
			}
		});
		mnNewMenu.add(menuChangePath);
		
		JMenuItem menuExport = new JMenuItem("Exportar");
		menuExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileManage.exportFile();
				
			}
		});
		
		JMenuItem menuImport = new JMenuItem("Importar");
		menuImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileManage.importFile();
				PassManage.showPasswd("update");
				
			}
		});
		
		JMenuItem menuPrint = new JMenuItem("Imprimir");
		menuPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileManage.printFile();
				
			}
		});
		mnNewMenu.add(menuPrint);
		mnNewMenu.add(menuImport);
		mnNewMenu.add(menuExport);
		
		JMenu menuDarkMode = new JMenu("Ventana");
		menuBar.add(menuDarkMode);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Modo oscuro");
		//Nuevo tema
		SwitchTheme theme=new SwitchTheme();
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theme.darkMode();
			}
		});
		menuDarkMode.add(mntmNewMenuItem_3);

		JMenu mnNewMenu_1 = new JMenu("Ayuda");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem MenuAbout = new JMenuItem("Acerca de...");
		mnNewMenu_1.add(MenuAbout);
		
		JMenuItem menuHelp = new JMenuItem("Ayuda");
		mnNewMenu_1.add(menuHelp);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Label Informacion del proceso
		JLabel lblInfo = new JLabel("Información del proceso");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblInfo.setBounds(105, 514, 223, 28);
		contentPane.add(lblInfo);
		
		//Caja para mostrar informacion
		JScrollPane scrollPaneInfo = new JScrollPane();
		scrollPaneInfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneInfo.setBounds(24, 553, 385, 95);
		contentPane.add(scrollPaneInfo);
		
		//JTextArea
		info = new JTextArea();
		//No es editable, solo muestra informacion
		info.setEditable(false);
		//Salto de linea automatico. Saltara de linea respetando las palabras
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		scrollPaneInfo.setViewportView(info);

		//Pestana 1
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 425, 498);
		contentPane.add(tabbedPane);

		//Inicializar y anadir panel 1, panel 2 y panel 3 al tabbedPane
		panel1=new Panel1();
		tabbedPane.addTab("Encriptación", null, panel1, null);

		panel2=new Panel2();
		tabbedPane.addTab("Desencriptación", null, panel2, null);
		
		panel3=new Panel3();
		tabbedPane.addTab("Gestión de contraseñas", null, panel3, null);
		
		panel4=new Panel4();
		tabbedPane.addTab("Generación de contraseñas", null, panel4, null);
		
		//Inicializar el archivo pass.csv y el archivo temporal
		FileManage.getFile();
		
		//Mostrar contrasenas en panel 2 y panel 3
		PassManage.showPasswd("create");
		

	}
}