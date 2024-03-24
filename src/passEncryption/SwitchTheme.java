package passEncryption;

import java.awt.Color;


public class SwitchTheme {

	private boolean dark;
	
	public SwitchTheme() {
		this.dark=false;
	}
	
	public void darkMode(){
		
		if(this.dark==false) {
			this.dark=true;
			
			JFramepassEncryption.contentPane.setBackground(Color.DARK_GRAY);
			JFramepassEncryption.contentPane.getComponent(0).setForeground(Color.white);
			//Pestanas
			for(int j=0;j<JFramepassEncryption.tabbedPane.getComponentCount();j++) {
				//Titulo de las pestanas
				JFramepassEncryption.tabbedPane.getComponents()[j].setBackground(Color.GRAY);
				//Fondo de los paneles
				JFramepassEncryption.tabbedPane.setBackgroundAt(j,Color.black);
				JFramepassEncryption.tabbedPane.setForegroundAt(j, Color.white);
			}
			//Contenedores de los paneles 2 y 3
			Panel2.container2.setBackground(Color.LIGHT_GRAY);
			Panel2.container2.setForeground(Color.white);
			Panel3.container3.setBackground(Color.LIGHT_GRAY);
			Panel3.container3.setForeground(Color.white);

			JFramepassEncryption.info.setBackground(Color.LIGHT_GRAY);
			JFramepassEncryption.info.setForeground(Color.black);
			

		}else {
			
			this.dark=false;
			Color defaultColor=new Color(238,238,238);
			
			JFramepassEncryption.contentPane.setBackground(defaultColor);
			JFramepassEncryption.contentPane.getComponent(0).setForeground(Color.black);
			for(int j=0;j<JFramepassEncryption.tabbedPane.getComponentCount();j++) {
				JFramepassEncryption.tabbedPane.getComponents()[j].setBackground(defaultColor);
				JFramepassEncryption.tabbedPane.setBackgroundAt(j,defaultColor);
				JFramepassEncryption.tabbedPane.setForegroundAt(j, Color.black);
			}
			
			Panel2.container2.setBackground(defaultColor);
			Panel3.container3.setBackground(defaultColor);
			
			JFramepassEncryption.info.setBackground(Color.white);
			JFramepassEncryption.info.setForeground(Color.black);
			
			
		}

	}

}
