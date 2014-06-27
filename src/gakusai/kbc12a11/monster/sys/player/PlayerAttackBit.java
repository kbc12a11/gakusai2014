package gakusai.kbc12a11.monster.sys.player;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.Camera;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerAttackBit extends Object{

	public PlayerAttackBit(Stage stg, float x, float y, float dx, float dy) {
		super(stg);
		p.set(x, y);
		d.set(dx, dy);
		size.set(8, 8);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		p.add(d);
		Camera c = stg.getCamera();
		Rectangle r = c.getView();
		if (p.x < r.getX() || r.getX() + r.getWidth() < p.x ||
				p.y < r.getY() || r.getY() + r.getHeight() < p.y) {
			this.destroy();
			flg_live = false;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.black);
		g.fillOval(p.x - size.x/2, p.y - size.y/2, size.x, size.y);
	}

	@Override
	public void hit(Object obj) {
		destroy();
		flg_live = false;
		obj.destroy();
	}


}
