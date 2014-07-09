package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**タイトル画面*/
public class TitleView extends BasicGameState{
	private Image bg;
	private Image[] pushAbutton;
	private Image roadof, the, pencil;
	private int timer;

	private int state;
	public static final int ST_NORMAL = 0;
	public static final int ST_SUBMIT = 1;
	public static final int ST_GONEXT = 2;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Image("res/image/title/PPW_notenikakikomu500.jpg");
		roadof = new Image("res/image/title/roadof.gif");
		the = new Image("res/image/title/the.gif");
		pencil = new Image("res/image/title/pencil.gif");
		pushAbutton = new Image[2];
		pushAbutton[0] = new Image("res/image/title/pushAbutton.gif");
		pushAbutton[1] = new Image("res/image/title/pushAbuttonSubmit.gif");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		BgmBank.bgmRequest(BgmBank.BGM_STAGE_1, true);
		setState(ST_NORMAL);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		BgmBank.stopAllBGM();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(bg, 0, 0);
		int tx = 50, ty = 40;
		float scale  =0.8f;

		int row = roadof.getWidth(), roh = roadof.getHeight();
		g.drawImage(roadof, tx, ty,
				tx + row*scale, ty + roh*scale, 0, 0, row, roh);

		int thw = the.getWidth(), thh = the.getHeight();
		g.drawImage(the, tx + (row-thw)*scale/2, ty + roh*scale,
				tx + (row+thw)*scale/2, ty + (roh+thh)*scale, 0, 0, thw, thh);
		int pew = pencil.getWidth(), peh = pencil.getHeight();
		g.drawImage(pencil, tx + (row-pew)*scale/2, ty + (roh+thh)*scale,
				tx + (row+pew)*scale/2, ty + (roh+thh+peh)*scale, 0, 0, pew, peh);


		int puw = pushAbutton[0].getWidth(), puh = pushAbutton[0].getHeight();
		tx = Main.W_WIDTH/2;
		ty = 370;
		float tmp = 0;
		int index = 0;

		if (state == ST_NORMAL) {
			tmp = (float)(Math.sin(timer*0.095)*10);
			index = 0;
		}else {
			tmp = 0;
			index = (timer/5)%2;
		}
		g.drawImage(pushAbutton[index], tx - puw/2, ty+tmp, tx + puw/2, ty+tmp+puh, 0, 0, puw, puh);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		BgmBank.update();
		SoundBank.update();
		timer++;
		GameInput in = Util.getGameInput(null, gc);
		int fadeTime = 60;
		switch (state) {
		case ST_NORMAL:
			if (timer > 30 && in.isA()) {
				setState(ST_SUBMIT);
			}
			break;
		case ST_SUBMIT:
			if (timer > 60) {
				setState(ST_GONEXT);
			}
			break;
		case ST_GONEXT:
			sbg.enterState(Main.Stage_StageSelectView,
					new FadeOutTransition(Color.black, fadeTime),
					new FadeInTransition(Color.black, fadeTime) );
			break;
		}
	}

	public void setState(int state) {
		timer = 0;
		this.state = state;
		switch (state) {
		case ST_NORMAL:
			break;
		case ST_SUBMIT:
			SoundBank.soundRequest(SoundBank.SE_CAT);
			break;
		case ST_GONEXT:
			break;
		}

	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_TitleView;
	}

}
