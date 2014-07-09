package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class ScoreWindow extends StatusWindow{
	
	private int score;
	private Stage stg;
	private Vector2f imgp, nump;
	private Image img, num1, num2, num3, num4, num5, num6, num;
	private int num01, num02, num03, num04, num05, num06;


	
	public ScoreWindow(Stage stg) {
		this.stg = stg;
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 16 - 50, Main.W_HEIGHT / 10);
		this.nump = new Vector2f(imgp.x + 60, imgp.y + 5);

		try {
			img = new Image("res/image/window/score.gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO 自動生成されたメソッド・スタブ
		this.score = stg.getScore();

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		
		try {
			num = new Image("res/image/num/num.gif");
			num01 = score / 100000 % 10;
			num02 = score / 10000 % 10;
			num03 = score / 1000 % 10;
			num04 = score / 100 % 10;
			num05 = score / 10 % 10;
//			num1 = new Image("res/image/num/" + score / 100000 % 10 + ".gif");
//			num2 = new Image("res/image/num/" + score / 10000 % 10 + ".gif");
//			num3 = new Image("res/image/num/" + score / 1000 % 10 + ".gif");
//			num4 = new Image("res/image/num/" + score / 100 % 10 + ".gif");
//			num5 = new Image("res/image/num/" + score / 10 % 10 + ".gif");
//			num6 = new Image("res/image/num/" + score % 10 + ".gif");

		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.black);
		//g.drawString("" + score, p.x, p.y + 3);
		
		g.drawImage(img, imgp.x - 100, imgp.y, imgp.x -55, imgp.y + 40, 0, 0, 100,70);
		g.drawImage(num, nump.x, nump.y, nump.x + 27, nump.y + 24, 0, 0, 95, 130);
		if(num05 < 5) {
			g.drawImage(num, nump.x - 20, nump.y, nump.x - 20 + 27, nump.y + 24, 100 * num05, 0, 95 + 100 * num05 ,130);			
		} else {
			g.drawImage(num, nump.x - 20, nump.y, nump.x - 20 + 27, nump.y + 24, 100 * (num05 - 5), 155, 95 + 100 * (num05 - 5), 285);
		}
		if(num04 < 5) {
			g.drawImage(num, nump.x - 40, nump.y, nump.x - 40 + 27, nump.y + 24, 100 * num04, 0, 95 + 100 * num04 ,130);			
		} else {
			g.drawImage(num, nump.x - 40, nump.y, nump.x - 40 + 27, nump.y + 24, 100 * (num04 - 5), 155, 95 + 100 * (num04 - 5), 285);
		}
		if(num03 < 5) {
			g.drawImage(num, nump.x - 60, nump.y, nump.x - 60 + 27, nump.y + 24, 100 * num03, 0, 95 + 100 * num03 ,130);			
		} else {
			g.drawImage(num, nump.x - 60, nump.y, nump.x - 60 + 27, nump.y + 24, 100 * (num03 - 5), 155, 95 + 100 * (num03 - 5), 285);
		}
		if(num02 < 5) {
			g.drawImage(num, nump.x - 80, nump.y, nump.x - 80 + 27, nump.y + 24, 100 * num02, 0, 95 + 100 * num02 ,130);			
		} else {
			g.drawImage(num, nump.x - 80, nump.y, nump.x - 80 + 27, nump.y + 24, 100 * (num02 - 5), 155, 95 + 100 * (num02 - 5), 285);
		}
		if(num01 < 5) {
			g.drawImage(num, nump.x - 100, nump.y, nump.x - 100 + 27, nump.y + 24, 100 * num01, 0, 95 + 100 * num01 ,130);			
		} else {
			g.drawImage(num, nump.x - 100, nump.y, nump.x - 100 + 27, nump.y + 24, 100 * (num01 - 5), 155, 95 + 100 * (num01 - 5), 285);
		}
		


	}
}
