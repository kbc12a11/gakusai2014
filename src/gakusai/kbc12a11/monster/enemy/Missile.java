package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Missile extends Enemy{


	private float speed = 10;
	private boolean flg_isGoLeft = false;
	Image img;
	public Missile(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);

		img = ImageBank.getInstance().getImage(ImageBank.ENEMY_MISSILE_1);
		size.set(64, 64);
		Vector2f player = stg.getPlayerPos();
		float x0 = player.x - p.x;
		if (x0 < 0) {
			speed = -speed;
			flg_isGoLeft = true;
		}else {

		}

		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

		d.set(speed, 0);
		p.add(d);
	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawImage(img, p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
				flg_isGoLeft?32:0, 0, flg_isGoLeft?0:32, 32);

	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
