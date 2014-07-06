package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.line.LineGroup;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class LineEnergyWindow extends StatusWindow {

	private LineGroup lg;
	private float ratio;
	private final float limRatio;
	private Vector2f p, size;
	private Image img;
	private float penSize;
	private float penX, penY;

	public LineEnergyWindow(LineGroup lg, Vector2f p, Vector2f size) {
		this.lg = lg;
		this.p = p != null ? p : new Vector2f(700, 20);
		this.size = size != null ? size : new Vector2f(600, 5);
		ratio = 1;
		limRatio = lg.getLineDrawEnergyMinLimit() / lg.getLineDrawEnergyMax();

		try {
			img = new Image("res/image/pen.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		penSize = 24;
		penY = 12;
		penX = 350;

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float sx = p.x -size.x/2;
		float sy = p.y;
		float ex = sx + (ratio > 0 ? size.x * ratio : 0);
		float ey = p.y;

		g.setColor(Color.black);
		g.setLineWidth(30);
		g.drawLine(350, p.y, sx + size.x, p.y);

		g.setColor(ratio > limRatio ? Color.white : Color.red);
		g.setLineWidth(size.y);
		g.drawLine(sx, sy, ex, ey);

		g.drawImage(img, penX, p.y - penY, penX + penSize, p.y -penY + penSize, 0, 0, 323, 323);


	}

	@Override
	public void update(GameContainer gc, int delta) {
		ratio = lg.getLineDrawEnergyNow() / lg.getLineDrawEnergyMax();
		if (ratio < 0) ratio = 0;
		if (ratio > 1) ratio = 1;
	}

}
