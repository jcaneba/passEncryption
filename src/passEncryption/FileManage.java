package passEncryption;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.ini4j.Ini;


public class FileManage {

	private static File FILE_PASS_CSV;
	private static File FILE_PASS2_CSV;

	public static void getFile(){

		String sistemaOperativo = System.getProperty("os.name");
		String user=System.getProperty("user.name");
		String path,path2;
		
		//Sistema operativo Windows. Validar con expresiones regulares (Case Insensitive)
		if(Pattern.matches(".*(?i)Windows.*", sistemaOperativo)) {

			File ruta1=new File("D:\\Windows\\Users\\"+user+"\\Documents");
			File ruta2=new File("C:\\Users\\"+user+"\\Documents");

			if(ruta1.exists() && ruta1.isDirectory() && ruta1.canRead() && ruta1.canWrite()) {

				path = "D:\\Windows\\Users\\"+user+"\\Documents\\pass.csv";
				path2="D:\\Windows\\Users\\"+user+"\\AppData\\Local\\Temp\\pass.csv";

			}else if(ruta2.exists() && ruta2.isDirectory() && ruta2.canRead() && ruta2.canWrite()) {

				path = "C:\\Users\\"+user+"\\Documents\\pass.csv";
				path2="C:\\Users\\"+user+"\\AppData\\Local\\Temp\\pass.csv";

			}else {
				System.out.println("No se ha encontrado ningun directorio valido para Windows. Introduce uno valido");
				changePath();
				//Return en el metodo void para salir de la ejecucion del metodo
				return;
			}

			FILE_PASS_CSV=new File(path);
			FILE_PASS2_CSV=new File(path2);


		//Sistema operativo Linux
		}else if(sistemaOperativo.equals("Linux")){

			File ruta3=new File("/home/"+user);
			path2="/tmp/.pass.csv";
			FILE_PASS2_CSV=new File(path2);
			if(ruta3.exists() && ruta3.isDirectory() && ruta3.canRead() && ruta3.canWrite()) {
				path = "/home/"+user+"/.pass.csv";
				FILE_PASS_CSV=new File(path);

			}else {
				System.out.println("No se ha encontrado ningun directorio valido para Linux. Introduce uno valido");
				changePath();
				return;
			}
			
		}else {
			System.out.println("* Sistema operativo no soportado");
			return;
		}
		
		//Si el archivo no existe se crea vacio
		if(!FILE_PASS_CSV.exists()) {
			try {
				FILE_PASS_CSV.createNewFile();
				JFramepassEncryption.info.append("Se ha creado un nuevo archivo en "+FILE_PASS_CSV.getPath()+System.lineSeparator());
				if(Pattern.matches(".*(?i)Windows.*", sistemaOperativo) && !FILE_PASS_CSV.isHidden()){
					Files.setAttribute(Paths.get(path), "dos:hidden", true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			JFramepassEncryption.info.append("Archivo encontrado en "+FILE_PASS_CSV.getPath()+System.lineSeparator());
		}
	}
	
	//Metodos para el menu
	public static void changePath(){
	
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Carpeta nueva");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int option = fileChooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			File newFile;
			if(Pattern.matches(".*(?i)Windows.*", System.getProperty("os.name"))) {
				newFile=new File(fileChooser.getSelectedFile().getPath()+"\\pass.csv");
			}else{
				newFile=new File(fileChooser.getSelectedFile().getPath()+"/.pass.csv");
			}
			
			if(FILE_PASS_CSV.exists()) {
				try {
					Files.move(Paths.get(FILE_PASS_CSV.getAbsolutePath()),Paths.get(newFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
					JFramepassEncryption.info.setText("Archivo movido correctamente a la ubicacion "+newFile.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			FILE_PASS_CSV=newFile;
			
	    }
	
	}
	
	public static void printFile(){
	
		try {
			Desktop.getDesktop().print(FILE_PASS_CSV);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void importFile(){

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Archivo CSV a importar");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//Filtro para escoger unicamente archivos .csv
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
		fileChooser.setFileFilter(filter);
        
        int option = fileChooser.showOpenDialog(null);
        
		if (option == JFileChooser.APPROVE_OPTION) {
			try {
				//TODO Validar si el archivo importado tiene el formato correcto con expresiones regulares
				
				Files.copy(Paths.get(fileChooser.getSelectedFile().getPath()),Paths.get(FILE_PASS_CSV.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
				JFramepassEncryption.info.setText("Archivo importado con éxito");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void exportFile(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Carpeta nueva");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//Mostrar el fileChooser guardando el codigo de error (1 si todo va bien)
		int option = fileChooser.showOpenDialog(null);
		
		/*
		//TODO Validacion para sobreescribir el archivo si existe
		File selectedFile=new File(fileChooser.getSelectedFile().getPath()+completeDay);
		
		if(fileChooser.getSelectedFile().exists() && fileChooser.getSelectedFile().canRead() && fileChooser.getSelectedFile().canWrite()) {
			int result = JOptionPane.showConfirmDialog(null,"El archivo pass.csv ya existe. ¿Sobreescribir?","Archivo existe",JOptionPane.YES_NO_CANCEL_OPTION);
			// 0=yes, 1=no, 2=cancel
			JOptionPane.showConfirmDialog(null,"Archivo sobreescrito correctamente", JOptionPane.DEFAULT_OPTION);
		}
		*/
		if (option == JFileChooser.APPROVE_OPTION) {
			//Nombre de archivo con fecha actual
			Date currentDate=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(currentDate);
			int numberDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
			int day=calendar.get(Calendar.DAY_OF_MONTH);
			int month=calendar.get(Calendar.MONTH);
			int year=calendar.get(Calendar.YEAR);
			int hours=calendar.get(Calendar.HOUR_OF_DAY);
			int minutes=calendar.get(Calendar.MINUTE);
			//int seconds=calendar.get(Calendar.SECOND);
			String[] days={"Dom","Lun","Mar","Mier","Jue","Vie","Sab"};
			String completeFileName="["+days[numberDayOfWeek-1]+day+"-"+month+"-"+year+";"+hours+minutes+"]pass.csv";
			try {
				//if(result==JOptionPane.YES_OPTION) {
				Files.copy(Paths.get(FILE_PASS_CSV.getAbsolutePath()),Paths.get(fileChooser.getSelectedFile().getPath(),completeFileName),StandardCopyOption.REPLACE_EXISTING);
				//}else if(result==JOptionPane.YES_OPTION){}
				JFramepassEncryption.info.setText("Archivo exportado con éxito");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	public static List<List<String>> fileReader(){

		BufferedReader br;
		PassManage.counter=0;
		try {
			
			//Creacion del arraylist donde se almacenara todo
			List<List<String>> arraylist = new ArrayList<List<String>>();
			
			br = new BufferedReader(new FileReader(FILE_PASS_CSV));
			//Lee dos veces para saltarse la cabecera (que se supone que siempre la tiene)
			String line = br.readLine();
			line = br.readLine();
			//Repetir el proceso hasta que la linea este vacia y no existan mas contrasenas
			while (line != null) {
				//Aumentar en 1 el contador de las lineas
				PassManage.counter++;
				//Array que almacena los dos campos de la linea separados por comas
				String[] campos = line.split(",");
				
				//Creacion de una nueva lista que simula una fila
				List<String> row = new ArrayList<String>();
				
				//Bucle para rellenar la fila con descripcion y contrasena
				for(int i=0;i<campos.length;i++) {
					//Quitar las comillas de los campos
					String encryptedText=campos[i].replace("\"", "");
					//Almacenar el texto anterior en la fila
					row.add(encryptedText);
				}
				
				//Anadir la fila al arraylist
				arraylist.add(row);
				//Volver a leer la siguiente linea del archivo
				line = br.readLine();
			}

			br.close();
			return arraylist;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
		
	}

	public static void fileWriter(String[] text){

		String description=text[0];
		String passwd=text[1];

		try {

			//Encriptacion: DES - BLOWFISH - AES

			String encryptedDescription=StringEncrypt.aesEncrypt(StringEncrypt.blowfishEncrypt(StringEncrypt.desEncrypt(description)));
			String encryptedPass=StringEncrypt.aesEncrypt(StringEncrypt.blowfishEncrypt(StringEncrypt.desEncrypt(passwd)));

			//Proceso para escribir en el archivo sin sobreescribir:
			//Inicializar filewriter y filereader
			FileWriter fw = new FileWriter(FILE_PASS_CSV, true);
			FileReader fr=new FileReader(FILE_PASS_CSV);

			//Agregar cabeceras si el archivo esta vacio
			int valor=fr.read();
			if(valor==-1) {
				fw.write("Description,Pass"+System.lineSeparator());
			}

			//Agregar la linea con password y descripcion
			fw.write("\""+encryptedDescription+"\",\""+encryptedPass+"\""+System.lineSeparator());

			//Cerrar fw y fr al acabar el proceso
			fw.flush();
			fw.close();
			fr.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		PassManage.showPasswd("update");
		
	}


	public static void lineUpdate(String option,String description,String toUpdate,String newString){
		
			try {
				BufferedReader br = new BufferedReader(new FileReader(FILE_PASS_CSV));
				BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_PASS2_CSV));
				
				
				String line=br.readLine();
				line=br.readLine();
				
				FILE_PASS2_CSV.deleteOnExit();
				Files.setAttribute(Paths.get(FILE_PASS2_CSV.getAbsolutePath()), "dos:hidden", true);
				
				bw.write("Description,Pass"+System.lineSeparator());
				while(line!=null) {
					if(!line.equals("\""+description+"\",\""+toUpdate+"\"")) {
							bw.write(line+System.lineSeparator());
					}else {
						
						if(option.equals("update")) {
							
							String campos[]=line.split(",");
							bw.write(campos[0]+",\""+newString+"\""+System.lineSeparator());
							
						}
					}
					
					line=br.readLine();
				}
				
				br.close();
				bw.close();
				
				
				Files.move(Paths.get(FILE_PASS2_CSV.getAbsolutePath()),Paths.get(FILE_PASS_CSV.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	public static void switchTabs() {
		if(PassManage.counter==0 && JFramepassEncryption.panel2.isEnabled() && JFramepassEncryption.panel3.isEnabled()) {
			JFramepassEncryption.tabbedPane.setEnabledAt(1, false);
			JFramepassEncryption.tabbedPane.setEnabledAt(2, false);
			JFramepassEncryption.tabbedPane.setSelectedIndex(0);
		}else if(PassManage.counter>0 && JFramepassEncryption.panel2.isEnabled()==true && JFramepassEncryption.panel3.isEnabled()==true){
			JFramepassEncryption.tabbedPane.setEnabledAt(1, true);
			JFramepassEncryption.tabbedPane.setEnabledAt(2, true);
		}
	}


	public static void initFile() {
		
		//TODO Archivo de configuracion .ini con la libreria Ini4J
		Ini init=new Ini();
		init.clear();
		
		
	}
	

}
