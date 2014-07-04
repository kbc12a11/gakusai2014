package gakusai.kbc12a11.monster.sys.eraser;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Eraser extends Object{

	public Eraser(Stage stg) {
		super(stg);
		this.size.set(64, 64);
		isErase = false;
	}

	private boolean isErase;

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameInput in = Util.getGameInput(stg, gc);
		isErase = false;
		if (in.isB()) {
			p.set(in.getX(), in.getY());
			float x = p.x - stg.getCamera().getTranslateX();
			float y = p.y- stg.getCamera().getTranslateY();
			float sizex = size.x;
			float sizey = size.y;
			stg.getMap().erase(x, y, sizex, sizey);

			stg.getLineGroup().lineErase(x, y, sizex, sizey);
			isErase = true;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if (!isErase) return;
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.setColor(Color.black);
		g.setLineWidth(1);
		g.drawRect(p.x - size.x/2, p.y - size.y/2, size.x, size.y);
	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}
	@Override
	public Shape getShape() {
		Rectangle rect = new Rectangle(p.x - stg.getCamera().getTranslateX(),
				p.y- stg.getCamera().getTranslateY(), size.x, size.y);

		return rect;
	}
}
