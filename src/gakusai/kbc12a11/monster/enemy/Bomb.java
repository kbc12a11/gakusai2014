package gakusai.kbc12a11.monster.enemy;


import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.effects.Effect;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bomb extends Enemy {

	private int timer = 0;
	Animation ani;

	/**ステータス*/
	private int state;
	/**待機*/
	public static final int STATE_WAIT = 0;
	/**アクティブ*/
	public static final int STATE_ACTIVE = 1;
	/**爆発中*/
	public static final int STATE_BOMB = 2;


	/**動き出す距離*/
	private static final int limitDist = 120;

	private int countDown = 180;

	private float speed = 5;

	Effect effect;

	private boolean flg_isGoLeft = true;

	//表示する用の画像
	Image [] img = new Image[4];

	Image  burst = stg.getImage(ImageBank.EF_BOMB_BURST);



	public Bomb(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		p.set(x, y);

		img[0] = ImageBank.getInstance().getImage(ImageBank.ENEMY_BOMB_1);
		img[1] = ImageBank.getInstance().getImage(ImageBank.ENEMY_BOMB_2);
		img[2] = ImageBank.getInstance().getImage(ImageBank.ENEMY_BOMB_3);
		img[3] = ImageBank.getInstance().getImage(ImageBank.ENEMY_BOMB_4);

		ani = new Animation(img, 300);


		size.set(32, 32);
		speed = 2;
		this.state = STATE_WAIT;
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		a.set(Stage.GRAVITY);
		d.add(a);

		Vector2f player = stg.getPlayerPos();


		float x0 = player.x - p.x;
		float y0 = player.y - p.y;


		if (state==STATE_WAIT && x0*x0 + y0*y0 < limitDist*limitDist) {
			setState(STATE_ACTIVE);
		}

		if (state == STATE_ACTIVE) {
			countDown--;
			if (x0 < 0) {
				flg_isGoLeft = true;
				d.set(-speed, d.y);
			}else {
				flg_isGoLeft = false;
				d.set(speed, d.y);
			}
			if (countDown < 0) {
				setState(STATE_BOMB);
			}
		}
		if (state != STATE_BOMB) {
			Collide.decideCheckOnMap(this, stg.getMap());
			p.add(d);
		}

		if (state == STATE_BOMB) {
			timer++;
			if (timer > 59) flg_live = false;

			try {
				effect.update(gc, sbg, delta);
			} catch (SlickException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		switch (state) {
		case STATE_WAIT:
			g.drawImage(img[0], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
					32, 0, 0, 32);
			break;
		case STATE_ACTIVE:
			System.out.println(timer);
			if(countDown % 16 < 8){
				g.drawImage(img[2], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
						flg_isGoLeft?32:0, 0, flg_isGoLeft?0:32, 32);
			}else{
				g.drawImage(img[3], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
						flg_isGoLeft?32:0, 0, flg_isGoLeft?0:32, 32);
			}

			break;
		case STATE_BOMB:
			effect.render(gc, sbg, g);
			break;
		}
	}

	//プレイヤーにぶつかったときの処理
	@Override
	public void hit(Object obj) {
		if (state == STATE_ACTIVE) {
			setState(STATE_BOMB);
		}
	}

	private void setState(int state) {
		switch (state) {
		case STATE_ACTIVE:
			this.state = state;
			break;
		case STATE_BOMB:
			this.state = state;
			timer=0;
			state = STATE_BOMB;
			effect = new BombBurstEffect(stg, p);
			isImmortal = true;
			break;
		}
	}

	public class BombBurstEffect extends Effect {
		//private int burstSize;

		int x0 = 512, y0 = 512;

		public BombBurstEffect(Stage stg, Vector2f pos) {
			super(stg, pos);
			// TODO 自動生成されたコンストラクター・スタブ
			//burstSize = 10;
			stg.getMap().erase(p.x-x0/2, p.y-y0/2, x0, y0);
		}

		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			//burstSize++;
		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
//			g.setColor(Color.red);
//			g.fillOval(p.x-burstSize/2, p.y-burstSize/2, burstSize, burstSize);
//			g.setColor(Color.yellow);
//			float tmp = burstSize*0.7f;
//			g.fillOval(p.x-tmp/2, p.y-tmp/2, tmp, tmp);

			//img[2] = ImageBank.getInstance().getImage(ImageBank.BURST);
			int x = timer % 8;
			int y = timer / 8;
			int burstX = 128;
			int burstY = 128;
			g.drawImage(burst, p.x - x0, p.y - y0, p.x+x0, p.y+y0, x * burstX, y * burstY, (x + 1) * burstX, (y + 1) * burstY);
		}

		@Override
		public void hit(Object obj) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}
}
