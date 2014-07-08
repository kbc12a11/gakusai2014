package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.util.Util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StageSelectView extends BasicGameState{

	ArrayList<GameState> stageList;
	int nowSelectIndex = 0;
	int wait = 10;
	int timer;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		stageList = new ArrayList<GameState>();
		stageList.add(sbg.getState(Main.Stage_12a10_HinoStage));
		stageList.add(sbg.getState(Main.Stage_12a11_MarioStage));
		stageList.add(sbg.getState(Main.Stage_12a11_SampleStage2));
		timer = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);

		for (int i = 0; i < stageList.size(); i++) {
			if (nowSelectIndex != i)
				g.drawString(stageList.get(i).toString(), 120, 80 + 30*i);
			else
				g.drawString("> " + stageList.get(i).toString(), 120, 80 + 30*i);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameInput in = Util.getGameInput(null, gc);
		Vector2f joy = in.getJoyInput();

		timer--;

		if (timer < 0 && joy.y > 0.5) {
			nowSelectIndex = (nowSelectIndex + 1)%stageList.size();
			timer = wait;
		}
		if (timer < 0 && joy.y < -0.5) {
			nowSelectIndex--;
			if (nowSelectIndex < 0) nowSelectIndex = stageList.size()-1;
			timer = wait;
		}

		int fadeTime = 60;
		if (in.isA()) {
			sbg.enterState(stageList.get(nowSelectIndex).getID(),
					new FadeOutTransition(Color.black, fadeTime),
					new FadeInTransition(Color.black, fadeTime) );
		}
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageSelectView;
	}

}
