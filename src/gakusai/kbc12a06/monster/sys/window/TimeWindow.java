package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;


public class TimeWindow extends StatusWindow{
	
	private Vector2f p;
	private String str;
	
	/**現在の残り時間*/
	private int time;
	/**初期残り時間*/
	private int start = 100;
	/**赤文字になる基準*/
	private int limit = 20;
	
	public TimeWindow() {
		this.p = new Vector2f(1300, 20);
		this.start = start * 60 + 39;
		this.limit = limit * 60 + 39;
		this.time = start;

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
		g.drawString(str, p.x, p.y);
	}
	
	public int getTime() {
		return time;
	}
}
