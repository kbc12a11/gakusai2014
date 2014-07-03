package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Mol extends Enemy{

	private float speed = 1;
	Image img;
	private boolean flg_isGoLeft = false;
	public Mol(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		img = ImageBank.getInstance().getImage(ImageBank.ENEMY_MOL_2);
		size.set(32, 32);
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Vector2f player = stg.getPlayerPos();
		float x0 = player.x - p.x;
		if (x0 < 0) {
			flg_isGoLeft = true;
		}else {

		}

		//重力加速度を設定

		a.set(Stage.GRAVITY);
		//加速度を移動量に足すことで自由落下を行う
		d.add(a);
		int res = Collide.decideCheckOnMap(this, stg.getMap());
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
