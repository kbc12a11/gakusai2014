package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class ScoreWindow extends StatusWindow{
	
	private int score;
	private Stage stg;
	private Vector2f p;

	
	public ScoreWindow(Stage stg) {
		this.stg = stg;
		this.p = new Vector2f(1250, 60);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO 自動生成されたメソッド・スタブ
		this.score = stg.getScore();

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.black);
		g.drawString("score:" + score, p.x, p.y);
	}
}
