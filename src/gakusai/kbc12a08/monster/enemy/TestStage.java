package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestStage extends Stage{

	public TestStage() throws SlickException {
		super("map/toyama/toyama.tmx");
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_TestStage;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		super.init(gc, sbg);

		StageBackground sb = new NoteBackground();
		this.setBackground(sb);
	}


	@Override
	public void stUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO 自動生成されたメソッド・スタブ
		Input in = gc.getInput();
		if (in.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
			sbg.enterState(1, new FadeOutTransition(Color.black, 50), new FadeInTransition(Color.black, 50) );
		}
	}



	@Override
	public void stRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setEnemys() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setItems() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
