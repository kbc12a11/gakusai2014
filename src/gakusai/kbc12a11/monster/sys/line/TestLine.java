package gakusai.kbc12a11.monster.sys.line;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class TestLine extends Line{

	private int hp;
	private Color color;
	/**ラインの最低の長さ*/
	public static final int LengthMinLimit = 5;

	public TestLine(int hp, LineBit firstPoint) {
		super(firstPoint);
		this.hp = hp;
		color = Color.black;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		if (!nowDrawing) {hp--;}
		if (hp < 60) {
			color = Color.red;
		}
		if (hp < 0) {
			if (points.size() > 2) {
				points.remove(0);
			}else {
				this.live = false;
			}
		}

//		for (int i = 0; i < points.size(); i++) {
//			if (i%5 == 3) {
//				points.get(i).setLive(false);
//			}
//		}

		nowDrawing = false;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(color);
		g.setLineWidth(LineWidth);
		for (int i = 0; i < points.size()-1; i++) {
			LineBit b1 = points.get(i);
			LineBit b2 = points.get(i+1);

			if (!b1.isLive() || !b2.isLive()) continue;

			g.drawLine(b1.getX(), b1.getY(),
					b2.getX(), b2.getY());
		}
	}

	@Override
	public float lineDraw(float x, float y, float energy) {
		if (energy <= 0) return 0;
		int sz = points.size();
		if (sz < 2){
			if (sz == 0) {
				addPoint(new LineBit(x, y));
			}
			addPoint(new LineBit(x, y));
			sz = points.size();
		}
		Vector2f p0 = points.get(sz-2);
		Vector2f p1 = points.get(sz-1);

		float dx = p0.x-p1.x, dy = p0.y-p1.y;
		float length = dx*dx + dy*dy;
		if (length > LengthMinLimit*LengthMinLimit) {
			LineBit p = new LineBit(x, y);
			if (energy < length) {
				float ratio = energy/length;
				float tx = (x - p1.x)*ratio;
				float ty = (y - p1.y)*ratio;
				p.set(p1.x + tx, p1.y + ty);
			}
			addPoint(p);

			this.nowDrawing = true;
			return (float)Math.sqrt(length);
		}else {
			p1.set(x, y);
			return 0;
		}
	}
}
