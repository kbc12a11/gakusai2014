package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
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
	Image bg;
	Image logo;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		bg = new Image("res/image/title/PPW_notenikakikomu500.jpg");
		logo = new Image("res/image/title/titleLogo.gif");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int ofx = bg.getWidth();
		int ofy = bg.getHeight();
		float cx = Main.W_WIDTH/2;
		float cy = Main.W_HEIGHT/2;
		//g.drawImage(bg, cx - ofx/2, cy - ofy/2);
		g.drawImage(bg, 0, 0);
		g.drawImage(logo, 100, 50);
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
