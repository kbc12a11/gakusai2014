package gakusai.kbc12a11.monster.abst;

import gakusai.kbc12a11.monster.sys.block.Block;
import gakusai.kbc12a11.monster.sys.effects.Effect;
import gakusai.kbc12a11.monster.sys.stage.ObjectGroup;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Character extends Object{

	ObjectGroup effects;

	public Character(Stage stg) {
		super(stg);
		effects = new ObjectGroup(stg);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!flg_destroy) {
			chUpdate(gc, null, delta);
		}else {
			updateDestroy(gc, sbg, delta);
		}
		effects.update(gc, sbg, delta);
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if (!flg_destroy) {
			chRender(gc, null, g);
		}else {
			renderDestroy(gc, sbg, g);
		}
		effects.render(gc, sbg, g);
	}

	public abstract void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;
	public abstract void chRender(GameContainer gc, StateBasedGame sbg, Graphics g);


	/**死亡時の行動
	 * @param sbg TODO
	 * @throws SlickException TODO*/
	protected void updateDestroy(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		d.add(a);
		p.add(d);

		if (p.y > stg.getMap().getMapHeight()) {
			flg_live = false;
		}
	}
	/**死亡時の描画
	 * @param sbg TODO*/
	public void renderDestroy(GameContainer gc, StateBasedGame sbg, Graphics g) {
		chRender(gc, sbg, g);
	}

	@Override
	public void destroy() {
		super.destroy();
		d.set(2, -12);
		a.set(0, 2);
	}

	/**マップから落下した時の処理*/
	public void fallOfMap() {
		super.destroy();
	}

	/**ブロックに触れた時の処理*/
	public void onBlock(Block b) {
	}

	public void addEffect(Effect e) {
		effects.add(e);
	}

	public void removeEffect(Effect e) {
		effects.remove(e);
	}
}
