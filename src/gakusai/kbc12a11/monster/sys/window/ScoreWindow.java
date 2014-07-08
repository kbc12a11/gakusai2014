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
	private Vector2f p;
	private Image img;

	
	public ScoreWindow(Stage stg) {
		this.stg = stg;
		this.p = new Vector2f(Main.W_WIDTH  * 15 / 16, Main.W_HEIGHT / 10);
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
		g.setColor(Color.black);
		g.drawString("" + score, p.x, p.y + 3);
		
		g.drawImage(img, p.x - 50, p.y - 4, p.x, p.y + 40, 0, 0, 100,70);

	}
}
