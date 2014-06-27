package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class LifeLine extends StatusWindow{


	private Vector2f p, size;
	private Player player;
	private float defaultLifePoint;
	private float nowLifePoint;
	private final float leftSpace = 2;

	private final float redZorn = 1;

	public LifeLine(Player player) {
		this.p = new Vector2f(100, 70);
		this.size = new Vector2f(15, 35);
		this.player = player;
		this.defaultLifePoint = player.getDefaultLifePoint();
		this.nowLifePoint = this.defaultLifePoint;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(this.nowLifePoint != player.getLifePoint()) {
			this.nowLifePoint = player.getLifePoint();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float sx = p.x;
		float sy = p.y;
		float ex = p.x + size.x;

		if(sx != ex) {
			if(this.nowLifePoint <= redZorn) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.black);
			}
			for(int a = 0; a < this.nowLifePoint; a ++) {
				g.fillRect(leftSpace * a * size.x + sx, sy, size.x, size.y);
			}
		}
	}

}
