import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/* Jennifer Peshansky
 * 111049923
 * CSE 260
 * Feb 3, 2018 - HackHealth 2018 
 */

public class Enemy {
	private String name;
	private int health;
	private String appearance;
	private int[] damageOfEachAttack; //in percent of total health
	public boolean alive;

	public Enemy() {
		setDefaultValues();
		//TODO print to log "A wild " + name + " has appeared!!! What will you do?");
	}

	private void setDefaultValues() {
		name = "Evil Being";
		health = 100;
		appearance = randomAppearance();
		damageOfEachAttack = randomWeaknesses();
		alive = true;
	}

	private String randomAppearance() {
		String path = "";
		int rand = (int)(Math.random()*4);
		if  (rand == 0)  {
			path = "src/food.png";
			name = "Devourer";
		}
		else if (rand == 1) {
			path = "src/go outside.png";
			name = "Inner Demon";
		}
		else if (rand == 2) {
			path = "src/work out.png";
			name = "StrongSpoke";
		}
		else if (rand == 3) {
			path = "src/water.png";
			name = "RipTide";
		}
		return path;
	}

	private int[] randomWeaknesses() {
		int[] weaknesses = new int[User.NUMBER_OF_POSSIBLE_ATTACKS];
		for (int i = 0; i < weaknesses.length; i++) {
			int rand = (int)(Math.random()*15)+10;
			weaknesses[i] = rand;
		}
		return weaknesses;
	}

	public int getAttacked(int typeOfAttack) {
		int i = typeOfAttack%4;
		int damage = damageOfEachAttack[i];
		setHealth((getHealth() - damage));
		return getHealth();
	}
/*
	public void drawEnemy(GraphicsContext gc) {
		drawEnemy(gc, 20, 60);
	}

	public void drawEnemy(GraphicsContext gc, int x, int y) {
		BufferedImage bImg = new BufferedImage(appearance.getImage().getWidth(null), appearance.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bImg.createGraphics();
		graphics.drawImage(appearance.getImage(), 0, 0, null);
		graphics.dispose();
		Image fxImage = SwingFXUtils.toFXImage(bImg, null);
		gc.drawImage(fxImage, x, y);
	}
*/

	//basic getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isAlive() {
		return alive;
	}
	public String getAppearance() {
		return appearance;
	}
}
