package gakusai.kbc12a11.monster.sampleStage;

import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class MitsuruStage6 extends Stage {
	
	

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		super.enter(gc, sbg);
		
		BgmBank.bgmRequest(BgmBank.BGM_STAGE_3, true);
	}

	public MitsuruStage6() throws SlickException {
		super("map/hinoStage/Stage_Hard.tmx");
		// TODO 自動生成されたコンストラクター・スタブ

		StageBackground bg = new NoteBackground();
		this.setBackground(bg);
		this.stageTime = 500;
		
	}
	
	@Override
	public void reset() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		super.reset();
		playerRespawnPoint = new Vector2f(map.calcLocateX(1), map.calcLocateY(26));
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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setItems() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_M_Stage6;
	}

}
