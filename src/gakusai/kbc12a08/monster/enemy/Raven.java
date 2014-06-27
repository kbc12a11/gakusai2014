package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Raven extends Enemy {

	Image img;
	int count = 0;
	public Raven(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		// TODO 自動生成されたコンストラクター・スタブ

		//SpriteSheet ssheet = new SpriteSheet(new Image("haniwa.png"), 32, 32);
		img = new Image("res/image/UFO.gif");
		size.set(32, 32);
		this.p.set(x, y);
		isImmortal = true;
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

		count++;

		d.set(3, 0);
		if (count%300 >= 200) {
			d.set(-3, 0);
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
