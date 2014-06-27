package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class StockWindow extends StatusWindow {
	
	private int stock;
	private Vector2f p;
	private Player player;

	
	public StockWindow(Player player) {
		this.player = player;
		p = new Vector2f(20, 20);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		stock = player.getStock();
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.black);
		g.drawString(Integer.toString(stock), p.x, p.y);
	}

}
