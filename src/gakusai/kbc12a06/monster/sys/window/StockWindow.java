package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class StockWindow extends StatusWindow {
	
	private int stock;
	private Vector2f p;
	private Player player;
	private Image img;
	
	public StockWindow(Player player) {
		this.player = player;
		p = new Vector2f(Main.W_WIDTH / 30, Main.W_HEIGHT / 60);
		try {
			img = new Image("res/image/window/残気.gif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		stock = player.getStock();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(img, p.x, p.y, p.x + 50, p.y + 40, 0, 0, 100,70);
		g.setColor(Color.black);
		g.drawString(Integer.toString(stock), p.x + 45, p.y + 9);
	}

}
