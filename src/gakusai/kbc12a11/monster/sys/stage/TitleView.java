package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
/**タイトル画面*/
public class TitleView extends BasicGameState{

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		g.setColor(Color.white);
		g.drawString("Rord of the Pencil", 100, 100);
		g.drawString("push A button.", 100, 130);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameInput in = Util.getGameInput(null, gc);
		int fadeTime = 60;
		if (in.isA()) {
			sbg.enterState(Main.Stage_StageSelectView,
					new FadeOutTransition(Color.black, fadeTime),
					new FadeInTransition(Color.black, fadeTime) );
		}
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_TitleView;
	}

}
