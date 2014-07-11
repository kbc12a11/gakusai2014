package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOverView extends BasicGameState{
	int timer;
	GameInput gameInput;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		timer = 0;
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		BgmBank.stopAllBGM();
		SoundBank.stopAllSound();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);

		g.setColor(Color.white);
		String gomess1 = "Game Over.";
		String gomess2 = "push A button.";
		Font f = g.getFont();
		g.drawString(gomess1, Main.W_WIDTH/2 - f.getWidth(gomess1)/2,
				Main.W_HEIGHT/2 - f.getLineHeight());
		if (timer > 30) {
			g.drawString(gomess2, Main.W_WIDTH/2 - f.getWidth(gomess2)/2,
					Main.W_HEIGHT/2 + f.getLineHeight());
		}
		Util.drawPointer(g, gameInput, gc);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer ++;
		gameInput = Util.getGameInput(gameInput, gc);
		if (timer > 30) {
			if (gameInput.isA()) {
				int fadeOutTime = 600;
				int fadeInTime = 60;
				sbg.enterState(Main.Stage_TitleView,
						new FadeOutTransition(Color.black, fadeOutTime),
						new FadeInTransition(Color.black, fadeInTime) );
			}
		}
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_GameOverView;
	}

}
