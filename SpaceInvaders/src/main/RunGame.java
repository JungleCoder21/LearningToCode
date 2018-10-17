package main;
import java.awt.Toolkit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RunGame {
	public static void main(String[] args){
		new GameFrame();
	//	new BulletSoundsFX();
		for(String s: Toolkit.getDefaultToolkit().getFontList()){
			System.out.println(s);
		}
	//	System.out.println(Toolkit.getDefaultToolkit().getF);
	//	System.out.println(new SimpleDateFormat("dd.mmmmmm.yyyy"));
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		String sCntDateDebContrat = "10-MAY-18";
		Date dt = new Date();
		try {
			dt = format.parse("01.02.2018");
			format = new SimpleDateFormat("dd MMMMM yyyy");
			sCntDateDebContrat =  format.format(dt);
			System.out.println(sCntDateDebContrat);
		} catch (ParseException e) {
			System.out.println("error!");
			dt = new Date();
		}
		
	}
}
