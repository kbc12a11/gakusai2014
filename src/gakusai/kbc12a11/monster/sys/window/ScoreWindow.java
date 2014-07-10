package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class ScoreWindow extends StatusWindow{

	private int score;
	private Stage stg;
	private Vector2f imgp, nump;
	private Image img;


	public ScoreWindow(Stage stg) {
		this.stg = stg;
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 18, Main.W_HEIGHT / 8);
		nump = new Vector2f(imgp.x + 60, imgp.y);
		img = ImageBank.getImage(ImageBank.WD_SCORE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		this.score = stg.getScore();

	}

	@Override
	public void render(GameContainer gc, Graphics g) {

		g.setColor(Color.black);
		float w = img.getWidth(), h = img.getHeight();
		float sc = 0.2f;
		g.drawImage(img, imgp.x- w*sc, imgp.y - h*sc/2, imgp.x, imgp.y + h*sc/2, 0, 0, w,h);
		Util.drawNumber(g, score, nump.x, nump.y, 0.6f);
	}
}
