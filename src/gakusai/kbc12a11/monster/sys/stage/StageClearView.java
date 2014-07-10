package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StageClearView extends BasicGameState{

	int time, score, killEnemy;

	int vwTime, vwScore, vwKillEnemy;

	int timer;

	int state;
	public static final int ST_INIT = 0;
	public static final int ST_VW_SCORE = 1;
	public static final int ST_VW_TIME = 2;
	public static final int ST_VW_KILLENEMY = 3;
	public static final int ST_VW_TOTALSCORE = 4;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		SoundBank.stopAllSound();
		BgmBank.stopAllBGM();
		setState(ST_INIT);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		switch (state) {
		case ST_VW_TOTALSCORE:
		case ST_VW_TIME:
		case ST_VW_SCORE:
		case ST_INIT:
			g.setColor(Color.white);
			g.drawString("Stage Clear!", 100, 50);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

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
		timer = 0;
	}

	public void set(int time, int score, int killenemy) {
		this.time  =time;
		this.score = score;
		this.killEnemy = killenemy;
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageClearView;
	}

	private class NumberView {
		int num;
		float vwNum;//表示用数値
		float upNum;//一度に表示用に加算される数値

		int maxFrame;//このフレームに達するまで加算する
		int timer;//現在のフレーム数

		float ofx, ofy;//左上座標
		


		public NumberView(int num, int ofx, int ofy, int maxFrame) {
			this.num = num;
			this.ofx = ofx;
			this.ofy = ofy;
			this.maxFrame = maxFrame;
			vwNum = 0;
			upNum = (float)(num)/maxFrame;
			timer = 0;
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
			
		}
	}

}
