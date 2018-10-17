package main;

public class EnemyType2 extends Enemy {
	public EnemyType2(int x, int y, String enemyPath) {
		super(x, y, enemyPath);
		// TODO Auto-generated constructor stub
	}
	
	public void addBullets(){
		GamePanel.enemyBullets.add(new Bullet(x-12, y-12, 150));
		GamePanel.enemyBullets.add(new Bullet(x-12, y, 30));
	}

}
