package gakusai.kbc12a11.monster.sys.player;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Character;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.item.Item;
import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.block.Block;
import gakusai.kbc12a11.monster.sys.effects.Effect;
import gakusai.kbc12a11.monster.sys.effects.PlayerDeadEffect;
import gakusai.kbc12a11.monster.sys.line.Line;
import gakusai.kbc12a11.monster.sys.line.LineBit;
import gakusai.kbc12a11.monster.sys.stage.ObjectGroup;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;
import gakusai.kbc12a11.monster.util.Util;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Character{

	/**ステータス*/
	private int playerState = STATE_NORMAL;
	/**通常状態*/
	public static final int STATE_NORMAL = 1;
	/**インビジブル(無敵)状態*/
	public static final int STATE_INVISIBLE = 2;
	/**死亡状態*/
	public static final int STATE_DEAD = 4;
	/**ゲームをクリアした状態*/
	public static final int STATE_CLEAR = 8;
	/**ダメージを受けた状態*/
	public static final int STATE_DAMAGED = 16;



	/**イメージ１と２を切り替えるフラグ*/
	private boolean flg_image = false;
	private int timer;
	private  Image img1, img2, imgDamaged;

	/**プレイヤーの残機(デフォルト値)*/
	private final int defaultStock = 3;
	/**プレイヤーの残機*/
	private int stock = defaultStock;

	/**プレイヤーのライフ(デフォルト値)*/
	private final int defaultLifePoint = 3;
	/**プレイヤーのライフポイント*/
	private int lifePoint = defaultLifePoint;

	/**ジャンプ可能フラグ*/
	private boolean flg_jump = false;

	/**死亡エフェクト*/
	private Effect deadEffect;

	///攻撃関係
	private ObjectGroup attackBits;
	private float bulletSpeed = 5.0f;
	private int attackDefaultInterval = 100;
	private int attackInterval;


	/**状態異常が治るまでのカウント*/
	private int stateCount = 0;
	private final int stCntInvisible = 180;//インビジブル状態のデフォルト継続時間
	private final int stCntDead = 120;//死亡状態のデフォルト継続時間
	private final int stCntCrear = 120;//クリア状態の継続時間
	private final int stCntDamaged = 60;//ダメージを受けた状態のデフォルト継続時間

	//線に乗っているかのフラグ
	public boolean rideLineFlag = false;
	//最高速度
	private float maxSpeed = 5;
	//移動スピード
	private float moveSpeed = 3;

	/**水中にいるフラグ*/
	private boolean flg_in_water = false;

	/**左側を向いているかのフラグ*/
	private boolean flg_isGoLeft = false;

	public Player(Stage stg) throws SlickException {
		super(stg);
		p.set(20, 0);
		d.set(0, 0);
		a.set(0, 0.5f);

		size.set(28, 28);
		//size.set(32, 32);
		//		SpriteSheet ssheet = new SpriteSheet(new Image("haniwa.png"), 32, 32);
		//		img = ssheet.getSubImage(13, 0);
		img1 = stg.getImage(ImageBank.PLAYER_1);
		img2 = stg.getImage(ImageBank.PLAYER_2);
		imgDamaged = stg.getImage(ImageBank.PLAYER_DAMAGED);

		attackBits = new ObjectGroup(stg);
		attackInterval = 0;
	}

	/**リスポーン*/
	public void respawn(Vector2f playerRespawn) {
		stateCount = 0;
		a.set(0, 0);
		d.set(0, 0);
		p.set(playerRespawn);
		setState(STATE_NORMAL);
		deadEffect = null;
		flg_live = true;
		flg_destroy = false;
		lifePoint = defaultLifePoint;
		attackBits.clear();
		timer = 0;
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		stateCheck();
		flg_in_water = false;
		if (getState() != STATE_CLEAR) {
			a.set(Stage.GRAVITY);
			//ライン
			for (Line l : stg.getLineGroup().getLines()) {
				ArrayList<LineBit> arr = l.getPoints();
				for (int i = 0; i < arr.size() - 1; i++) {
					LineBit b1 = arr.get(i);
					LineBit b2 = arr.get(i+1);
					if (!b1.isLive()) continue;
					if (!b2.isLive()) continue;
					Collide.DecideCheckResult res =
							Collide.decideCheckOnLine(this, b1, b2, 21, i == 0);
					if (res != null && res.isRide) {
						lineCollidAction(res);
						flg_jump = true;
						//a.add(l.getForce(i, i+1));
					}
				}
			}

			Block[] blocks = new Block[6];
			Collide.getOnBlock(p, size, stg.getMap(), blocks);
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i] != null) {
					onBlock(blocks[i]);
				}
			}
			//joystickの入力
			GameInput in = stg.getGameInput();
			Vector2f joyInput = in.getJoyInput();
			if (joyInput.x < -0.05 || 0.05 < joyInput.x) {
				d.x = Util.between(joyInput.x*delta, -moveSpeed, moveSpeed);
			}else {
				d.x = 0;
			}
			if (flg_in_water) {
				if (joyInput.y < -0.05 || 0.05 < joyInput.y) {
					d.y = Util.between(joyInput.y*delta, -moveSpeed, moveSpeed);
				}
			}
			if ((flg_jump)
					&& in.isC()) {
				d.y = -5;
				flg_jump = false;
			}
			d.add(a);
			//速度調整
			if (d.x > maxSpeed) d.x = maxSpeed;
			if (d.x < -maxSpeed) d.x = -maxSpeed;
			if (d.y > maxSpeed) d.y = maxSpeed;
			if (d.y < -maxSpeed) d.y = -maxSpeed;

			if (flg_in_water) {
				d.x = d.x * 0.7f;
				d.y = d.y * 0.7f;
			}
			Vector2f nextp = new Vector2f(p);
			Vector2f nextd = new Vector2f(d);
			int r = Collide.decideCheckOnMap(this, stg.getMap(), nextp, nextd, blocks);
			//int r = Collide.decideCheckOnMap(this, stg.getMap());
			p.set(nextp);
			d.set(nextd);
			if ((r&Collide.COL_OUT_OF_MAP_RIGHT) == Collide.COL_OUT_OF_MAP_RIGHT) {
				setState(STATE_CLEAR);
			}
			if ((r&Collide.COL_MAP_BLOCK_DOWN) != 0) {
				flg_jump = true;
			}else {
				flg_jump = false;
			}
		}else if (getState() == STATE_CLEAR) {
			d.set(1, 0);
		}
		p.add(d);

		if (d.x > 0) {
			flg_isGoLeft = false;
		}
		if (d.x < 0) {
			flg_isGoLeft = true;
		}

		updateAttack(gc, sbg, delta);//攻撃

		//画像切り替えフラグ
		timer++;
		if (timer % 60 == 0) {
			flg_image = !flg_image;
		}
	}

	//攻撃
	private void updateAttack(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameInput in = stg.getGameInput();
		float ax = 0, ay = 0;
		if (in.isUp()) {
			ay = -bulletSpeed;
		}
		if (in.isDown()) {
			ay = bulletSpeed;
		}
		if (in.isLeft()) {
			ax = -bulletSpeed;
		}
		if (in.isRight()) {
			ax = bulletSpeed;
		}
		attackInterval--;
		if ((ax != 0 || ay != 0 ) && attackInterval < 0) {
			Vector2f atc = new Vector2f(ax, ay);
			atc.normalise().scale(bulletSpeed);
			attackBits.add(new PlayerAttackBit(stg, p.x, p.y, atc.x, atc.y));
			attackInterval = attackDefaultInterval;
		}
		attackBits.update(gc, sbg, delta);
	}

	//ステータスのチェック
	private void stateCheck() {
		if (playerState == STATE_NORMAL) {
			return;
		}

		stateCount--;
		if (stateCount < 0) {
			switch (playerState) {

			case STATE_INVISIBLE:
				setState(STATE_NORMAL);
				break;

			case STATE_DEAD:
				flg_live = false;
				break;

			case STATE_CLEAR:
				stg.setStageState(Stage.STATE_CLEAR);
				break;

			case STATE_DAMAGED:
				setState(STATE_INVISIBLE);
				break;
			}
		}
	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ
		//g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);

		Image i = null;
		if (flg_image) {
			i = img1;
		}else {
			i = img2;
		}
		if (getState() == STATE_DAMAGED) {
			i = imgDamaged;
		}
		float alpha = 1;
		if (getState() == STATE_INVISIBLE || getState() == STATE_DAMAGED) {
			alpha = stateCount%2 == 0 ? 0.7f : 0.25f;
		}

		i.setAlpha(alpha);
		g.drawImage(i, p.x - size.x/2, p.y - size.y/2,
				flg_isGoLeft?32:0, 0, flg_isGoLeft?0:32, 32);

		attackBits.render(gc, sbg, g);
	}

	@Override
	public void hit(Object obj) {
		if (obj instanceof Item) {
			return;
		}

		if (obj instanceof Enemy) {
			if (!obj.isImmortal() && obj.getP().y > p.y + size.y/3) {
				obj.destroy();
				d.set(d.x, -5);
			}else {
				setState(STATE_DAMAGED);
				int eff = 1;
				if (obj.getP().x > p.x) {
					eff = -1;
				}
				d.set(eff * 2, -2);
				damage(1);
			}
			stg.soundRequest(SoundBank.SE_CAT);
		}
	}

	@Override
	protected void updateDestroy(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		stateCheck();
		deadEffect.update(gc, sbg, delta);
	}

	@Override
	public void renderDestroy(GameContainer gc, StateBasedGame sbg, Graphics g) {
		deadEffect.render(gc, sbg, g);
	}

	protected void lineCollidAction(Collide.DecideCheckResult res) {
		//移動量の変更
		Vector2f N = res.N.copy();
		float a0 = -N.dot(d);
		d.x += N.x * a0;
		d.y += N.y * a0;
		//加速度の変更
		N = res.N.copy().copy();
		a0 = -N.dot(a);
		a.x += N.x * a0;
		a.y += N.y * a0;

		if (res.dist < 20) {
			//			int eff = -1;
			//			if (res.dist < 5) {
			//				eff = - 10;
			//			}
			//			if (res.closs < 0) {
			//				eff *= -1;
			//			}
			d.y += -1;;
		}
	}

	@Override
	public void fallOfMap() {
		setState(STATE_DEAD);
	}

	/**damageの量のダメージを受ける*/
	public void damage(int damage) {
		lifePoint -= damage;
		if (lifePoint > defaultLifePoint) {
			lifePoint = defaultLifePoint;
		}
		if (lifePoint <= 0) {
			setState(STATE_DEAD);
		}
	}

	/**現在のライフポイントを取得する*/
	public int getLifePoint() {
		return lifePoint;
	}

	/**デフォルトのライフポイントを取得する*/
	public int getDefaultLifePoint() {
		return defaultLifePoint;
	}

	/**残機を返す*/
	public int getStock() {
		return stock;
	}

	/**残機アップ*/
	public void extend(int s) {
		stock += s;
	}

	/**プレイヤーのステータス*/
	public int getState() {
		return playerState;
	}

	/**ステータスを設定する*/
	public void setState(int state) {
		playerState = state;
		switch (state) {
		case STATE_INVISIBLE:
			stateCount = stCntInvisible;
			break;

		case STATE_DEAD:
			stock--;
			lifePoint = 0;
			stateCount = stCntDead;
			deadEffect = new PlayerDeadEffect(stg, p);
			destroy();
			break;

		case STATE_DAMAGED:
			stateCount = stCntDamaged;
			break;

		case STATE_CLEAR :
			stateCount = stCntCrear;
			break;
		default ://定義されていないステータスの場合、通常状態になる
			playerState = STATE_NORMAL;
		}
	}

	/**攻撃*/
	public ObjectGroup getAttackBits() {
		return attackBits;
	}

	@Override
	public void destroy() {
		super.destroy();
		stg.soundRequest(SoundBank.SE_ENDAAAAAAAAAAAAAAAA);
		BgmBank.stopAllBGM();
	}
	@Override
	public void onBlock(Block b) {
		switch(b) {

		case RED://赤ブロックにぶつかった時の処理
			if (getState() == STATE_NORMAL) {
				setState(STATE_DAMAGED);
				int eff = 1;
				if (d.x > 0) {
					eff = -1;
				}
				d.set(eff * 2, -2);
				damage(1);
				stg.soundRequest(SoundBank.SE_CAT);
			}
			break;
		case BLUE:
		case LITE_BLUE:
			flg_in_water = true;
			break;

		default:
			break;
		}
	}
}
