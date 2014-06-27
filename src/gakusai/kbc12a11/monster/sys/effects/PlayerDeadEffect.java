package gakusai.kbc12a11.monster.sys.effects;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class PlayerDeadEffect extends Effect{

	private int time = 300;
	Color[] c = {Color.black, Color.white};
	int size = 28;

	public PlayerDeadEffect(Stage stg, Vector2f pos) {
		super(stg, pos);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		time--;
		if (time < 0) {
			flg_live = false;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		int num = 12;
		g.setLineWidth(2);
		g.setColor(Color.black);
		for (int i = 0; i < num; i++) {
			float px = p.x;
			float py = p.y;
			double cos = Math.cos(2*Math.PI*i/num);
			double sin = Math.sin(2*Math.PI*i/num);

			for (int j = 0; j < 2; j++) {
				px = px + (float)cos*((300-time)/(j+1));
				py = py + (float)sin*((300-time)/(j+1));

				g.drawOval(px - size/2, py - size/2, size, size);
			}
		}
	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
