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
	private Image img, num;
	private int num01, num02, num03;
	
	
	public TimeWindow() {
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 16 - 50, Main.W_HEIGHT / 29 - 4);
		//nump = new Vector2f(imgp.x + 35, imgp.y - 8);
		nump = new Vector2f(imgp.x + 50, imgp.y - 3);

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
			num = new Image("res/image/num/num.gif");
			num01 = time / 60 % 10;
			num02 = time / 60 / 10 % 10;
			num03 = time / 60 / 100;
//			num1 = new Image("res/image/num/" + time / 60 / 100 + ".gif");
//			num2 = new Image("res/image/num/" + time / 60 / 10 % 10 + ".gif");
//			num3 = new Image("res/image/num/" + time / 60 % 10 + ".gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		g.drawImage(img, imgp.x - 40, imgp.y, imgp.x, imgp.y + 40, 0, 0, 100,70);
		if(num01 < 5) {
			g.drawImage(num, nump.x, nump.y, nump.x + 27, nump.y + 24, 100 * num01, 0, 95 + 100 * num01 ,130);
		} else {
			g.drawImage(num, nump.x, nump.y, nump.x + 27, nump.y + 24, 100 * (num01 - 5), 155, 95 + 100 * (num01 - 5), 285);
		}
		if(num02 < 5) {
			g.drawImage(num, nump.x - 22, nump.y, nump.x + 5, nump.y + 24, 100 * num02, 0, 95 + 100 * num02 ,130);
		} else {
			g.drawImage(num, nump.x - 22, nump.y, nump.x + 5, nump.y + 24, 100 * (num02 - 5), 155, 95 + 100 * (num02 - 5), 285);
		}
		if(num03 < 5) {
			g.drawImage(num, nump.x - 40, nump.y, nump.x - 13, nump.y + 24, 100 * num03, 0, 95 + 100 * num03 ,130);
		} else {
			g.drawImage(num, nump.x - 40, nump.y, nump.x - 13, nump.y + 24, 100 * (num03 - 5), 155, 95 + 100 * (num03 - 5), 285);
		}
		
		

	}
	
	public int getTime() {
		return time;
	}
}
