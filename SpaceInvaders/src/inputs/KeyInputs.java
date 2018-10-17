package inputs;

import main.GamePanel;
import main.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.Clip;

public class KeyInputs implements KeyListener {
	
	private Player player;
	private int powerUpCounter = 0;
	
	public KeyInputs(Player player){
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(true);
		}
		
		if(key == KeyEvent.VK_SPACE){
			player.setFiring(true);
		}
		if(key == KeyEvent.VK_P){
	//		pauseGame();
		}
		
		if(key == KeyEvent.VK_R){
		//	restart();
		}
		if(key == KeyEvent.VK_CONTROL){
			if(powerUpCounter <2 ){
				powerUpCounter ++;
			}
			for(String s: GamePanel.powerUpsMap.keySet()){
				if(s == "meteor" && powerUpCounter == 1){
					player.setRocketFiring(false);
					player.setMeteorFiring(true);
				}else if(s == "rocket" && powerUpCounter == 2){
					player.setMeteorFiring(false);
					player.setRocketFiring(true);
				}
			}
			
			if(powerUpCounter == 2){
				powerUpCounter = 0;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if(key == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(key == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
		if(key == KeyEvent.VK_UP){
			player.setUp(false);
		}
		if(key == KeyEvent.VK_SPACE){
			player.setFiring(false);
			//sound.stopPlaying();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
