package main;
import javax.swing.JFrame;

public class GameFrame {
	public GameFrame(){
		JFrame frame = new JFrame("SpaceInvaders");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePanel panel = new GamePanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
