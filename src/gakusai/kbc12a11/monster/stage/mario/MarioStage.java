package gakusai.kbc12a11.monster.stage.mario;

import gakusai.kbc12a11.monster.item.CureBox;
import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MarioStage extends Stage {

	public MarioStage() throws SlickException {
		super("map/mario/mario.tmx");
		// TODO 自動生成されたコンストラクター・スタブ

		StageBackground bg = new NoteBackground();
		this.setBackground(bg);
	}

	@Override
	public void reset() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		super.reset();
		playerRespawnPoint.set(map.calcLocateX(3), map.calcLocateY(10));
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.enter(gc, sbg);
		BgmBank.bgmRequest(BgmBank.BGM_STAGE_1, true);
	}



	@Override
	public void stUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void stRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setEnemys() throws SlickException {
	}

	@Override
	public void setItems() throws SlickException {
		addItem(new CureBox(this, 100, 500));
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_12a11_MarioStage;
	}

}
