package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.Main;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class StageSelectView extends BasicGameState{

	ArrayList<GameState> stageList;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		stageList = new ArrayList<GameState>();
		stageList.add(sbg.getState(Main.Stage_12a10_HinoStage));
		stageList.add(sbg.getState(Main.Stage_12a11_MarioStage));
		stageList.add(sbg.getState(Main.Stage_12a11_SampleStage2));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("Stage Select.", 100, 50);

		for (int i = 0; i < stageList.size(); i++) {
			g.drawString(stageList.get(i).toString(), 120, 80 + 30*i);
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageSelectView;
	}

}
