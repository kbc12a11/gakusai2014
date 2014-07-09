package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.Main;
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
	private Vector2f p, size, pen, penSize;
	private Image img;

	public LineEnergyWindow(LineGroup lg, Vector2f p, Vector2f size) {
		this.lg = lg;
		this.p = p != null ? p : new Vector2f(Main.W_WIDTH / 3, 20);
		this.size = size != null ? size : new Vector2f(Main.W_WIDTH / 3, 5);
		this.pen = new Vector2f(Main.W_WIDTH * 2 / 7, Main.W_HEIGHT / 70);
		this.penSize = new Vector2f(25, 11);
		ratio = 1;
		limRatio = lg.getLineDrawEnergyMinLimit() / lg.getLineDrawEnergyMax();

		try {
			img = new Image("res/image/pen.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float sx = pen.x + penSize.x + 15;
		float sy = p.y;
		float ex = (ratio > 0 ? sx + size.x * ratio : sx);
		float ey = p.y;

		g.setColor(Color.black);
		g.setLineWidth(28);
		g.drawLine(pen.x - 1, p.y, pen.x + size.x + 40, p.y);

		g.setColor(ratio > limRatio ? Color.white : Color.red);
		g.setLineWidth(size.y);
		g.drawLine(sx, sy, ex, ey);
		

		g.drawImage(img, pen.x, pen.y + 2, pen.x + penSize.x, p.y + penSize.y, 0, 0, 323, 323);


	}

	@Override
	public void update(GameContainer gc, int delta) {
		ratio = lg.getLineDrawEnergyNow() / lg.getLineDrawEnergyMax();
		if (ratio < 0) ratio = 0;
		if (ratio > 1) ratio = 1;
	}

}
