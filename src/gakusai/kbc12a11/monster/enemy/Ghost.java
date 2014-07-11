package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Ghost extends Enemy{

	private float speed = 1.0f;
	private boolean flg_isGoLeft = true;
	private boolean flg_ogling = false;
	Image [] img = new Image[2];
	float defaultSize;

	private int state;
	public static final int ST_STOP = 0;
	public static final int ST_GO = 1;

	private float sizeChangeParam = 0.75f;

	public Ghost(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);

		img[0] = ImageBank.getInstance().getImage(ImageBank.ENEMY_GHOST_1);
		img[1] = ImageBank.getInstance().getImage(ImageBank.ENEMY_GHOST_2);
		size.set(32, 32);
		defaultSize = size.x;
		isImmortal = true;
		setState(ST_STOP);
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
//		Vector2f player = stg.getPlayerPos();
//		float x0 = player.x - p.x;
//		float y0 = player.y - p.y;
//		if ((x0 < 0 && !true) || (x0 >= 0 && !false)) {
//			flg_ogling = false;
//			if(x0 < 0){
//				d.x = -speed;
//				flg_isGoLeft = true;
//			}else{
//				d.x = speed;
//				flg_isGoLeft = false;
//			}
//
//			if(y0 < 0){
//				d.y = -speed;
//			}else{
//				d.y = speed;
//			}
//
//			size.x = size.x + 0.5f;
//			size.y = size.y + 0.5f;
//		}else {
//			flg_ogling = true;
//			d.set(0, 0);
//			if(size.x > defaultSize){
//				size.x = size.x - 0.5f;
//				size.y = size.y - 0.5f;
//			}
//		}

		Vector2f pl = stg.getPlayerPos();
		float flagx = pl.x - p.x;

		switch(state) {
		case ST_STOP:
				if (flagx > 0) {
					setState(ST_GO);
				}
				size.x -= sizeChangeParam;
				size.y -= sizeChangeParam;
				if (size.x < defaultSize) size.x = defaultSize;
				if (size.y < defaultSize) size.y = defaultSize;
				d.set(0, 0);
			break;
		case ST_GO:
			if (flagx <= 0) {
				setState(ST_STOP);
			}
			size.x += sizeChangeParam;
			size.y += sizeChangeParam;
			float dx = pl.x - p.x;
			float dy = pl.y - p.y;
			dx = Util.between(dx, -speed, speed);
			dy = Util.between(dy, -speed, speed);
			d.set(dx, dy);
			break;
		}


		p.add(d);
	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		if(state == ST_STOP){
			g.drawImage(img[1], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
					d.x < 0?32:0, 0, d.x < 0?0:32, 32);
		}else{
			g.drawImage(img[0], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
					d.x < 0?32:0, 0, d.x < 0?0:32, 32);
		}

	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}


	public void setState(int state) {
		this.state = state;
		switch(state) {
		case ST_STOP:
			break;
		case ST_GO:
			SoundBank.soundRequest(SoundBank.SE_OBAKE);
			break;
		}
	}
}
