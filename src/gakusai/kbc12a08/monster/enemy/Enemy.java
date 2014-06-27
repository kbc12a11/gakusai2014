package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.abst.Character;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Enemy extends Character {

//	/**エネミーが動ける状態にあるかのフラグ<br>
//	 * 主に画面内に存在するかどうかを判定する*/
//	protected boolean isActive;

	public Enemy(Stage stg, float x, float y) throws SlickException {
		super(stg);
		p.set(x, y);
		isImmortal = false;
	}

	@Override
	public final void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!stg.getCamera().isInsideDisplay(p, size, 64)){
			this.flg_live = false;//画面外にはみ出したら消失する
		}

		super.update(gc, sbg, delta);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
			super.render(gc, sbg, g);
	}
}
