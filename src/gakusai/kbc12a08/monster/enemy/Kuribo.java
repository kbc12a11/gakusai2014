package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Kuribo extends Enemy {
	Image img;
	int count = 0;
	public Kuribo(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		// TODO 自動生成されたコンストラクター・スタブ

		SpriteSheet ssheet = new SpriteSheet(new Image("haniwa.png"), 32, 32);
		img = ssheet.getSubImage(6, 0);
		size.set(32, 32);
		this.p.set(x, y);
		a.set(0, 0.5f);

	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

		count++;
		d.add(a);

		d.set(1, d.y);
		if (count%500 >= 150) {
			d.set(-1, d.y);
		}


		Collide.decideCheckOnMap(this, stg.getMap());
		if (d.x * d.x  < 2) {
			if (d.x < stg.getMap().getMapWidth()-250)
				d.set(4, 10);
			else
				d.set(-4, 10);
		}
		p.add(d);




	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawImage(img, p.x - size.x/2, p.y - size.y/2);

	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
