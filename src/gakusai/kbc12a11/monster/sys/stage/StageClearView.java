package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.Main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StageClearView extends BasicGameState{

	int time, score, killEnemy;

	int state;
	public static final int ST_INIT = 0;
	public static final int ST_VW_SCORE = 1;
	public static final int ST_VW_TIME = 2;
	public static final int ST_VW_KILLENEMY = 3;
	public static final int ST_VW_TOTALSCORE = 4;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

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

}
