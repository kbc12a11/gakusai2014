package gakusai.kbc12a11.monster.sys.window;

import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class HPWindow extends StatusWindow{

	private Player player;
	Vector2f pos, size;

	public HPWindow(Player player, Vector2f pos, Vector2f size) {
		this.player = player;
		this.pos = pos != null ? pos : new Vector2f();
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
