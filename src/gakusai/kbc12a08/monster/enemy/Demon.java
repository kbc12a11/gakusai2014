package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Demon extends Enemy {
	Image img;
	int count = 0;
	public Demon(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		// TODO 自動生成されたコンストラクター・スタブ

		//SpriteSheet ssheet = new SpriteSheet(new Image("res/image/mol/mol1.gif"), 32, 32);
		img = new Image("res/image/mol/mol1.gif");
		size.set(32, 32);
		this.p.set(x, y);
		a.set(0, 2);

	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

		count++;
		d.add(a);

		d.set(4, d.y);
		if (count%300 >= 150) {
			d.set(-4, d.y);
		}

		if (count % 120 == 0) {
			d.set(d.x, -20);
		}

		Collide.decideCheckOnMap(this, stg.getMap());
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
