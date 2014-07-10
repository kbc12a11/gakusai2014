package gakusai.kbc12a06.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class StockWindow extends StatusWindow {
	
	private int stock;
	private Vector2f imgp, nump;
	private Player player;
	private Image img, num;
	
	public StockWindow(Player player) {
		this.player = player;
		imgp = new Vector2f(Main.W_WIDTH / 30, Main.W_HEIGHT / 30);
		nump = new Vector2f(imgp.x + 50, imgp.y - 15);
		img = ImageBank.getImage(ImageBank.WD_STOCK);
		num = ImageBank.getImage(ImageBank.WD_NUM);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		if(stock != player.getStock() && player.getStock() >= 0) {
			stock = player.getStock();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		
		g.drawImage(img, imgp.x, imgp.y, imgp.x + 50, imgp.y + 40, 0, 0, 280,250);
		g.setColor(Color.black);
		g.drawImage(num, nump.x, nump.y, nump.x + 40, nump.y + 40, 100 * stock, 0, 95 + 100 * stock,130);
	//	g.drawImage(num, nump.x, nump.y, nump.x + 40, nump.y + 40, 100, 0, 95 + 100,130);
	//	g.drawImage(num, nump.x, nump.y, nump.x + 40, nump.y + 40, 200, 0, 95 + 200,130);
		//g.drawString(Integer.toString(stock), p.x + 45, p.y + 9);
	}

}
