package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class LifeLine extends StatusWindow{


	private Vector2f p, size;
	private Player player;
	private float defaultLifePoint;
	private float nowLifePoint;
	private final float leftSpace = 2;
	private Image img;

	private final float redZorn = 1;

	public LifeLine(Player player) {
		this.p = new Vector2f(Main.W_WIDTH / 100, Main.W_HEIGHT / 10);
		this.size = new Vector2f(7, 18);
		this.player = player;
		this.defaultLifePoint = player.getDefaultLifePoint();
		this.nowLifePoint = this.defaultLifePoint;
		
		try {
			img = new Image("res/image/window/たいりょく.gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(this.nowLifePoint != player.getLifePoint()) {
			this.nowLifePoint = player.getLifePoint();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float sx = p.x + 80;
		float sy = p.y + 3;
		float ex = p.x + size.x;
		
		g.drawImage(img, p.x, p.y, p.x + 70, p.y + 35, 0, 0, 130,70);


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
