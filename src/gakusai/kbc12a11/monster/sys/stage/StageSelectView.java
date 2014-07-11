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
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StageSelectView extends BasicGameState{

	ArrayList<GameState> stageList;

	private int state;
	public static final int ST_NORMAL = 0;
	public static final int ST_SUBMIT = 1;
	public static final int ST_GONEXT = 2;

	int nowSelectIndex = 0;
	int wait = 10;
	int timer;
	int stTimer;
	float gscale;
	float agscale = 0.01f;
	float dgscale;
	Image bg;
	Image nyanpus;
	Image easy, normal, hard;
	Image title;

	GameInput gameInput;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		stageList = new ArrayList<GameState>();
		stageList.add(sbg.getState(Main.Stage_12a11_MarioStage));
		stageList.add(sbg.getState(Main.Stage_12a10_Stage5));
		stageList.add(sbg.getState(Main.Stage_M_Stage6));

		nyanpus = new Image("res/image/stageSelect/nyampus.gif");
		easy = new Image("res/image/stageSelect/easy.gif");
		normal = new Image("res/image/stageSelect/normal.gif");
		hard = new Image("res/image/stageSelect/hard.gif");
		bg = new Image("res/image/title/PPW_notenikakikomu500.jpg");
		title = new Image("res/image/window/stageSelect.png");
		timer = 0;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		gscale = 1.0f;
		dgscale = agscale;
		setState(ST_NORMAL);
		BgmBank.bgmRequest(BgmBank.BGM_STAGESELECT, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		float sscale = 0.5f;
		float lscale = 0.7f;
		Image[] img = {easy, normal, hard};

		int ww = Main.W_WIDTH*(nowSelectIndex+1)/4, wh = Main.W_HEIGHT/2;

		if (state != ST_NORMAL) {
			gscale += dgscale;
			dgscale += agscale;
			ww = ww + nyanpus.getWidth()/2 - img[nowSelectIndex].getWidth()/2;
			wh = wh - 60;
			g.translate( ww-(ww)*gscale, wh-(wh)*gscale);
			g.scale(gscale, gscale);
		}

		g.drawImage(bg, 0, 0);

		float fontScale = 0.9f;
		int nw = nyanpus.getWidth(), nh = nyanpus.getHeight();
		Color[] c = {Color.gray, Color.blue, Color.green};

		for (int i = 0; i < 3; i++) {
			int ofx = (Main.W_WIDTH*(i+1))/4;
			int ofy = 270;
			float scale;
			if (nowSelectIndex == i) {
				scale = lscale;
			}else {
				scale = sscale;
			}
			g.drawImage(nyanpus, ofx-(nw/2)*scale, ofy-(nh/2)*scale,
					ofx+(nw/2)*scale, ofy+(nh/2)*scale,
					0, 0, nw, nh);
			g.setDrawMode(g.MODE_SCREEN);
			g.setColor(c[i]);
			g.fillRect(ofx-(nw/2)*scale, ofy-(nh/2)*scale,
					nw*scale, nh*scale);
			g.setDrawMode(g.MODE_NORMAL);
			g.setColor(Color.black);

			int pdx = 10, pdy = 60;
			int tx = img[i].getWidth(), ty = img[i].getHeight();
			g.drawImage(img[i],
					ofx+(nw/2 - tx*fontScale-pdx)*scale, ofy-(nh/2-pdy)*scale,
					ofx + (nw/2-pdx)*scale, ofy+(-nh/2+ty*fontScale+pdy)*scale,
					0, 0, tx, ty);
			g.drawImage(title, 30, -40, 600, 200, 0, 0, 700,300);

		}

		Util.drawPointer(g, gameInput, gc);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		BgmBank.update();
		SoundBank.update();
		gameInput = Util.getGameInput(null, gc);
		Vector2f joy = gameInput.getJoyInput();
		stTimer++;
		timer--;

		if (state == ST_NORMAL) {
			if (timer < 0 && joy.x > 0.5) {
				nowSelectIndex = (nowSelectIndex + 1)%stageList.size();
				timer = wait;
			}
			if (timer < 0 && joy.x < -0.5) {
				nowSelectIndex--;
				if (nowSelectIndex < 0) nowSelectIndex = stageList.size()-1;
				timer = wait;
			}

			if (gameInput.isA()) {
				setState(ST_SUBMIT);
			}
		}else if(state == ST_SUBMIT) {
			if (stTimer > 60) {
				setState(ST_GONEXT);
			}
		}else if (state == ST_GONEXT) {
			int fadeOutTime = 2000;
			int fadeInTime = 200;
			Stage stg = (Stage)stageList.get(nowSelectIndex);
			stg.reset();
			GameState bef = sbg.getState(Main.Stage_BeforeStartStage);
			((BeforeStartStage)bef).set(stg.getPlayerStock(),
					stg.getID(), Stage.STATE_NORMAL);
			sbg.enterState(bef.getID(),
					new FadeOutTransition(Color.black, fadeOutTime),
					new FadeInTransition(Color.black, fadeInTime) );
		}
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageSelectView;
	}

	public void setState(int state) {

		this.state = state;
		switch (state) {
		case ST_NORMAL:
			stTimer = 0;
			break;
		case ST_SUBMIT:
			stTimer = 0;
			SoundBank.soundRequest(SoundBank.SE_CAT);
			break;
		case ST_GONEXT:
			break;
		}
	}
}
