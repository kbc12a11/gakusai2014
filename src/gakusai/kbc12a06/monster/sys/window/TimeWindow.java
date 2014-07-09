package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class TimeWindow extends StatusWindow{
	
	private Vector2f imgp, nump;
	private String str;
	
	/**現在の残り時間*/
	private int time;
	/**初期残り時間*/
	private int start = 100;
	/**赤文字になる基準*/
	private int limit = 20;
	private Image img, num1, num2, num3;
	
	public TimeWindow() {
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 16 - 50, Main.W_HEIGHT / 29 - 4);
		nump = new Vector2f(imgp.x + 35, imgp.y - 8);

		this.start = start * 60 + 39;
		this.limit = limit * 60 + 39;
		this.time = start;
		
		try {
			img = new Image("res/image/window/時間.gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(time >= 0) time --;
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		str = Integer.toString(time / 60);
		if(time < limit) {
			g.setColor(Color.red);
		} else {
			g.setColor(Color.black);
		}
		try {
			num1 = new Image("res/image/num/" + time / 60 / 100 + ".gif");
			num2 = new Image("res/image/num/" + time / 60 / 10 % 10 + ".gif");
			num3 = new Image("res/image/num/" + time / 60 % 10 + ".gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		g.drawImage(img, imgp.x, imgp.y, imgp.x + 40, imgp.y + 40, 0, 0, 100,70);
		g.drawImage(num3, nump.x, nump.y, nump.x + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num2, nump.x - 20, nump.y, nump.x - 20 + 45, nump.y + 40, 0, 0, 160,160);
		g.drawImage(num1, nump.x - 40, nump.y, nump.x - 40 + 45, nump.y + 40, 0, 0, 160,160);

	}
	
	public int getTime() {
		return time;
	}
}
