package gakusai.kbc12a11.monster.st1;

import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;

public class Stage_1 extends Stage{
	public static Line line2 = new Line(1000, 200, 0, 200);
	public Stage_1() throws SlickException {
		super("map/st3/enpitsuborupentest.tmx");

//		Enemy[] enemy = {new Raven(this, 500, 100),
//				new Raven(this, 400, 500),
//				new Raven(this,1000,300),
//				new Raven(this,1000,870),
//				new Demon(this,100,100),
//				new Demon(this,1200,100),
//				new Kuribo(this,1000,800)};
//
//		addEnemy(enemy);

		//StageBackground bg = new St1Background();
		//StageBackground bg = new StageBackground("/map/st1/background.png");
		StageBackground bg = new NoteBackground();
		this.setBackground(bg);

//		Music openingMenuMusic = new Music("map/st1/st1.ogg");
//		openingMenuMusic.loop();
	}

	@Override
	public void stUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO 自動生成されたメソッド・スタブ

//		Input in = gc.getInput();
//		if (in.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
//			//sbg.enterState(2);
//			sbg.enterState(2, new FadeOutTransition(Color.black, 50), new FadeInTransition(Color.black, 50) );
//		}
	}

	@Override
	public void stRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_Stage_1;
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
