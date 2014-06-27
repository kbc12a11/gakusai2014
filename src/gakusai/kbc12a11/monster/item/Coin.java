package gakusai.kbc12a11.monster.item;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Coin extends Item{

	private float viewAng = 0;
	private float rad = 0.01f;
	private int score = 100;

	public Coin(Stage stg, float x, float y) {
		super(stg, x, y);
		size.set(16, 16);
	}

	@Override
	public void effect(Object obj) {
		stg.addScore(score);
		flg_live = false;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		viewAng += rad;
		if (viewAng > 2*Math.PI) viewAng = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		float a = (float)Math.sin(viewAng)*size.x/2;
		g.setLineWidth(2);
		g.setColor(Color.yellow);
		g.fillOval(p.x - a, p.y - size.y/2, a*2, size.y);
		g.setColor(Color.black);
		g.drawOval(p.x - a, p.y - size.y/2, a*2, size.y);
		g.drawLine(p.x, p.y - size.y/4, p.x, p.y + size.y/4);
	}
}
