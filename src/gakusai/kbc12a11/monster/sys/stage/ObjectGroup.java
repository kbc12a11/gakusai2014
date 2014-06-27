package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.abst.Object;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ObjectGroup {
	ArrayList<Object> objects;
	private Stage stg;

	public ObjectGroup(Stage stg) {
		objects = new ArrayList<Object>();
		this.stg = stg;
	}

	public void add(Object o) {
		objects.add(o);
	}

	public void add(Object[] o) {
		for (int i = 0; i < o.length; i++) {
			add(o[i]);
		}
	}

	public int getSize() {
		return objects.size();
	}

	public void remove(int index) {
		objects.remove(index);
	}
	public void remove(Object o) {
		objects.remove(o);
	}
	public void clear() {
		objects.clear();
	}

	/**オブジェクトグループの更新
	 * @param sbg TODO
	 * @throws SlickException TODO*/
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		for (int i = objects.size()-1; i >= 0; i--) {
			Object o = objects.get(i);
			o.update(gc, sbg, delta);

			if (!o.isLive()) {
				remove(i);
			}
		}
	}
	/**オブジェクトグループの描画
	 * @param sbg TODO*/
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for (Object o : objects) {
			if (stg.getCamera().isInsideDisplay(o.getP(), o.getSize(), 0)){
				o.render(gc, sbg, g);
			}
		}
	}

	/**あたり判定を行い、それぞれのオブジェクトのコールバック関数を呼び出す*/
	public void hitCheck(Object obj) {
		if (obj == null || obj.isDestroy() || !obj.isLive()) {
			return;
		}
		for (Object o : objects) {
			if (o == null || o == obj || o.isDestroy() || !o.isLive()) {
				continue;
			}
			if (o.isHit(obj)) {
				o.hit(obj);
				obj.hit(o);
			}
		}
	}
}
