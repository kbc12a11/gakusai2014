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
	private Image img, num1, num2, num3, num4, num5, num6;

	
	public ScoreWindow(Stage stg) {
		this.stg = stg;
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 16 - 50, Main.W_HEIGHT / 10);
		this.nump = new Vector2f(imgp.x + 50, imgp.y);
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
			num1 = new Image("res/image/num/" + score / 100000 % 10 + ".gif");
			num2 = new Image("res/image/num/" + score / 10000 % 10 + ".gif");
			num3 = new Image("res/image/num/" + score / 1000 % 10 + ".gif");
			num4 = new Image("res/image/num/" + score / 100 % 10 + ".gif");
			num5 = new Image("res/image/num/" + score / 10 % 10 + ".gif");
			num6 = new Image("res/image/num/" + score % 10 + ".gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		g.setColor(Color.black);
		//g.drawString("" + score, p.x, p.y + 3);
		
		g.drawImage(img, imgp.x, imgp.y, imgp.x + 45, imgp.y + 40, 0, 0, 100,70);
		g.drawImage(num6, nump.x, nump.y, nump.x + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num5, nump.x - 20, nump.y, nump.x - 20 + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num4, nump.x - 40, nump.y, nump.x - 40 + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num3, nump.x - 60, nump.y, nump.x - 60 + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num2, nump.x - 80, nump.y, nump.x - 80 + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num1, nump.x - 100, nump.y, nump.x - 100 + 45, nump.y + 40, 0, 0, 160,160);

	}
}
