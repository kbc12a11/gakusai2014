package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class LifeLine extends StatusWindow{


	private Vector2f imgp, size;
	private Player player;
	private float defaultLifePoint;
	private float nowLifePoint;
	private final float leftSpace = 2;

	private final float redZorn = 1;
	Image img;

	public LifeLine(Player player) {

		this.imgp = new Vector2f(Main.W_WIDTH / 7, Main.W_HEIGHT / 8);
		this.size = new Vector2f(7, 18);
		this.player = player;
		this.defaultLifePoint = player.getDefaultLifePoint();
		this.nowLifePoint = this.defaultLifePoint;
		img = ImageBank.getImage(ImageBank.WD_LIFE);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(this.nowLifePoint != player.getLifePoint()) {
			this.nowLifePoint = player.getLifePoint();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		float sx = imgp.x + 20;
		float sy = imgp.y;
		float ex = imgp.x + size.x;

		float w = img.getWidth(), h = img.getHeight();
		float sc = 0.2f;
		g.drawImage(img, imgp.x - w*sc, imgp.y - h*sc/2, imgp.x, imgp.y + h*sc/2, 0, 0, w,h);

		//g.drawImage(ImageBank.getImage(ImageBank.WD_LIFE), imgp.x, imgp.y, imgp.x + 70, imgp.y + 35, 0, 0, 430,250);


		if(sx != ex) {
			if(this.nowLifePoint <= redZorn) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.black);
			}
			for(int a = 0; a < this.nowLifePoint; a ++) {
				g.fillRect(leftSpace * a * size.x + sx, sy-size.y/2,
						size.x, size.y);
			}
		}
	}

}
