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
import org.newdawn.slick.state.StateBasedGame;

public class Crab extends Enemy{

	//左右のどちらの方向に動くかのフラグ
	private boolean moveLeft = true;

	private int timer = 1;

	private float speed = 3;
	Image[] img = new Image[3];
	private int view = 0;
	private boolean flgAddView = true;
	private boolean fly = true;

	public Crab(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		img[0] = ImageBank.getInstance().getImage(ImageBank.ENEMY_CRAB_1);
		img[1] = ImageBank.getInstance().getImage(ImageBank.ENEMY_CRAB_2);
		img[2] = ImageBank.getInstance().getImage(ImageBank.ENEMY_CRAB_3);
		size.set(32, 32);
		System.out.println("createCrab : " + p);
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//マップとのあたり判定を行う関数
		//あたり判定を行いたい場合はp.add(d)の
		//手前にこの一行を書く。コピペでok
		int res = Collide.decideCheckOnMap(this, stg.getMap());
		if((res & Collide.COL_MAP_BLOCK_DOWN) != 0){
			fly = false;
			timer++;
		}




		//重力加速度を設定
		a.set(Stage.GRAVITY);
		//加速度を移動量に足すことで自由落下を行う


		d.add(a);



		//移動方向の更新
		if ((res & Collide.COL_MAP_BLOCK_LEFT) != 0 || (res & Collide.COL_MAP_BLOCK_RIGHT) != 0 ||
				(res & Collide.COL_OUT_OF_MAP_LEFT) != 0 || ( res & Collide.COL_OUT_OF_MAP_RIGHT) != 0
		) {
			moveLeft = !moveLeft;
		}


		if (moveLeft) {
			//左に動くよう移動量を設定
			d.x = -speed;
		}else {
			//右に動くよう移動量を設定
			d.x = speed;
		}

		if((res & Collide.COL_MAP_BLOCK_DOWN) == 0){
			d.x = 0;
		}


		//現在位置に移動量を足す事で移動する

		p.add(d);

		//地面から落ちる場合折り返す
		if((res & Collide.COL_MAP_BLOCK_DOWN) == 0 && !fly){
			if (!moveLeft) {
				d.x = -speed;
			}else {
				d.x = speed;
			}
			d.y = -a.y;
			p.add(d);
			moveLeft = !moveLeft;
			fly = true;
		}


		if(timer%8 == 0){
			if(flgAddView){
				view++;
			}else{
				view--;
			}
		}

		if(view == 0){
			flgAddView = true;
		}else if(view == 2){
			flgAddView = false;
		}

	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawImage(img[view], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
				!moveLeft?32:0, 0, !moveLeft?0:32, 32);

	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
