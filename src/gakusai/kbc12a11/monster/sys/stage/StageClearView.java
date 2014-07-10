package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.util.Util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StageClearView extends BasicGameState{

	int time, score, killEnemy;

	int vwTime, vwScore, vwKillEnemy;

	int timer;
	int stTimer;

	int state;
	public static final int ST_INIT = 0;
	public static final int ST_VW_SCORE = 1;
	public static final int ST_VW_TIME = 2;
	public static final int ST_VW_KILLENEMY = 3;
	public static final int ST_VW_TOTALSCORE = 4;

	private Image bg;
	private Image clearImage;
	private Image star;

	private int vscy = 200;
	private Image img_score;
	private NumberView scoreView;

	private int vtmy = 300;
	private int subScoreTime = 10;//得点倍率
	private Image img_time;
	private NumberView timeView;

	private int vtotaly = 400;
	private Image img_total;
	private NumberView totalView;

	private ArrayList<Star> stars;
	private Color[] colors = {
			Color.blue,
			Color.green,
			Color.magenta,
			Color.orange,
			Color.pink,
			Color.red,
			Color.yellow,
			Color.gray
	};

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
//		bg = new Image("res/image/window/clear_bg.gif");
		bg = new Image("res/image/title/PPW_notenikakikomu500.jpg");
		clearImage = new Image("res/image/window/stageClear.gif");
		star = new Image("res/image/window/star.gif");

		stars = new ArrayList<Star>();

		scoreView = new NumberView(550, vscy, 100, 1.2f);
		img_score = new Image("res/image/window/md_score.gif");

		timeView = new NumberView(550, vtmy, 100, 1.2f);
		img_time = new Image("res/image/window/md_time.gif");

		totalView = new NumberView(550, vtotaly, 100, 1.5f);
		img_total = new Image("res/image/window/md_total.gif");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		SoundBank.stopAllSound();
		BgmBank.stopAllBGM();
		setState(ST_INIT);
		stars.clear();
		for (int i = 0; i < 10; i++) {
			setStar();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.drawImage(bg, 0, 0, new Color(255,255,255, 100));

		for (Star s : stars) {
			s.render(gc, sbg, g);
		}
		//g.setDrawMode(g.MODE_ADD);

		g.drawImage(clearImage, Main.W_WIDTH/2 - clearImage.getWidth()/2,
				20);

		float sc = 1f;
		float vw = img_score.getWidth();
		float vh = img_score.getHeight();
		float x = 250, y = vscy;

//		g.setDrawMode(g.MODE_COLOR_MULTIPLY_ALPHA);


		switch (state) {
		case ST_VW_TOTALSCORE:
			sc = 1.0f;
			vw = img_total.getWidth();
			vh = img_total.getHeight();
			y = vtotaly;
			g.drawImage(img_total, x - vw*sc, y - vh*sc/2, x, y + vh*sc/2,
					0, 0, vw, vh);
			totalView.render(gc, sbg, g);
		case ST_VW_TIME:
			sc = 0.9f;
			vw = img_time.getWidth();
			vh = img_time.getHeight();
			y = vtmy;
			g.drawImage(img_time, x - vw*sc, y - vh*sc/2, x, y + vh*sc/2,
					0, 0, vw, vh);
			timeView.render(gc, sbg, g);
		case ST_VW_SCORE:
			sc = 0.9f;
			vw = img_score.getWidth();
			vh = img_score.getHeight();
			y = vscy;
			g.drawImage(img_score, x - vw*sc, y - vh*sc/2, x, y + vh*sc/2,
					0, 0, vw, vh);
			scoreView.render(gc, sbg, g);
		case ST_INIT:
			g.setColor(Color.white);
		}

		g.setDrawMode(g.MODE_NORMAL);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;
		stTimer++;
		if (timer%20 == 0) {
			setStar();
		}
		for (int i = stars.size()-1; i >= 0; i--) {
			stars.get(i).update(gc, sbg, delta);
			if (!stars.get(i).isLive) {
				stars.remove(i);
			}
		}

		switch (state) {
		case ST_VW_TOTALSCORE:
			totalView.update(gc, sbg, delta);
		case ST_VW_TIME:
			if (state == ST_VW_TIME && stTimer > 60) {
				setState(ST_VW_TOTALSCORE);
			}
			timeView.update(gc, sbg, delta);
		case ST_VW_SCORE:
			if (state == ST_VW_SCORE && stTimer > 30) {
				setState(ST_VW_TIME);
			}
			scoreView.update(gc, sbg, delta);
		case ST_INIT:
			if (state == ST_INIT && stTimer > 30) {
				setState(ST_VW_SCORE);
			}
		}

		GameInput in = Util.getGameInput(null, gc);
		int fadeOutTime = 60;
		int fadeInTime = 60;
		if (in.isA()) {
			sbg.enterState(Main.Stage_TitleView,
					new FadeOutTransition(Color.black, fadeOutTime),
					new FadeInTransition(Color.black, fadeInTime) );
		}
	}

	public void setState(int state) {
		this.state = state;
		stTimer = 0;
	}

	private void setStar() {
		float scale = (float)Math.random()*2+0.3f;
		float dx = (float)Math.random()*1-0.5f;
		float dy = (float)Math.random()*3+0.3f;
		int index = (int)(Math.random()*colors.length);
		Star s = new Star((float)(Math.random()*Main.W_WIDTH),
				-20*scale, dx, dy, scale, colors[index]);
		stars.add(s);
	}

	public void set(int time, int score, int killenemy) {
		this.time = time * subScoreTime;
		this.score = score;
		this.killEnemy = killenemy;
		scoreView.set(this.score);
		timeView.set(this.time);
		totalView.set(this.time + this.score);
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageClearView;
	}

	////////////////////////////////////////数値表示クラス
	private class NumberView {
		int num;
		float vwNum;//表示用数値
		float upNum;//一度に表示用に加算される数値

		int maxFrame;//このフレームに達するまで加算する
		int timer;//現在のフレーム数

		float ofx, ofy;//左上座標
		float scale;


		public NumberView(int ofx, int ofy, int maxFrame, float scale) {
			this.ofx = ofx;
			this.ofy = ofy;
			this.maxFrame = maxFrame;
			this.scale = scale;
		}

		public void set(int num) {
			this.num = num;
			vwNum = 0;
			upNum = (float)(num)/maxFrame;
			timer = 0;
		}

		public NumberView(int num, int ofx, int ofy, int maxFrame, float scale) {
			this.ofx = ofx;
			this.ofy = ofy;
			this.maxFrame = maxFrame;
			this.scale = scale;
			set(num);
		}

		public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			if (timer < maxFrame) {
				timer++;
				vwNum += upNum;
			}else {
				vwNum = num;
			}
		}

		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			Util.drawNumber(g, (int)vwNum, ofx, ofy, scale);
		}
	}

	//星クラス
	private class Star {
		Vector2f p, d;
		float angle;
		float rad;
		float w, h;
		float scale;
		Color c;

		boolean isLive;
		public Star(float x, float y, float dx, float dy, float scale, Color c) {
			p = new Vector2f(x, y);
			d = new Vector2f(dx, dy);
			angle = (float)(Math.random()*2);
			rad = (float)(Math.random()*0.5 + 0.3);
			w = star.getWidth();
			h = star.getHeight();
			this.scale = scale;
			this.c = c;
			isLive = true;
		}

		public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			p.add(d);
			angle += rad;

			if (p.y > Main.W_HEIGHT+h) {
				isLive = false;
			}
		}
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
			g.pushTransform();
			g.rotate(p.x, p.y, angle);

			g.setDrawMode(g.MODE_ADD);
			g.drawImage(star, p.x - w*scale/2, p.y - h*scale/2,
					p.x + w*scale/2, p.y + h*scale/2, 0, 0, w, h, c);
			g.popTransform();
			g.setDrawMode(g.MODE_NORMAL);
		}
	}
}
