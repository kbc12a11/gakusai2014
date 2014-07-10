package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;


public class TimeWindow extends StatusWindow{

	private Vector2f imgp, nump;
	Stage stg;

	private int stageTime;

	/**現在の残り時間*/
	private int time;
	/**初期残り時間*/
	private int start = 100;
	/**赤文字になる基準*/
	private int limit = 20;
	private Image img;


	/***
	 *
	 * @param stage
	 * @param stageTime ステージの制限時間
	 */
	public TimeWindow(Stage stage, int stageTime) {
		this.imgp = new Vector2f(Main.W_WIDTH  * 15 / 18, Main.W_HEIGHT / 20);
		nump = new Vector2f(imgp.x + 60, imgp.y);
		this.stg = stage;
		this.stageTime = stageTime;

		img = ImageBank.getImage(ImageBank.WD_TIME);

	}

	@Override
	public void update(GameContainer gc, int delta) {
		long t = stg.getStopWatch().getTime();
		time = stageTime - (int)t/1000;
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float w = img.getWidth(), h = img.getHeight();
		float sc = 0.2f;
		g.drawImage(img, imgp.x - w*sc, imgp.y - h*sc/2, imgp.x, imgp.y + h*sc/2, 0, 0, w,h);
		Util.drawNumber(g, time >= 0 ? time : 0, nump.x, nump.y, 0.6f);
	}

	public int getTime() {
		return time;
	}
}
