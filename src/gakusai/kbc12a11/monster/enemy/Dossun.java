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

/**壁をすり抜けて空中を左右に動くだけのエネミー*/
//仕様上、敵は画面内にいるときしか更新も描画もされないので
//画面の外に出た敵はずっとその位置で止まっている状態になる
public class Dossun extends Enemy{


	//左右のどちらの方向に動くかのフラグ
	private boolean moveLeft = false;
	//時間をカウントするタイマー
	private int timer = 0;

	//ジャンプするフラグ
	private boolean jumpFlag = false;
	//ジャンプ力
	private float jumpPower = 9;

	//上昇中のフラグ
	private boolean upturnFlag = false;
	//上昇力
	private float upturnPower = 2;

	float startY;
	//表示する用の画像
	Image img;

	/**コンストラクタでステージを受け取り、初期位置を指定する*/
	public Dossun(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		//主要なプロパティ
		//p : エネミーの位置
		//d : 移動量
		//a : 加速度
		//size : エネミーの大きさ(32 * 32推奨)

		//初期位置の設定
		p.set(x, y);
		startY = p.y;
		//
		//こう書いても設定できる
		//p.x = x;
		//p.y = y;
		img = ImageBank.getInstance().getImage(ImageBank.ENEMY_DOSSUN_1);
		//大きさを指定
		size.set(64, 64);
	}

	//更新処理をここに書く
	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		//タイマーを進める
			timer++;
			//重力加速度を設定
//			a.set(Stage.GRAVITY);
			//加速度を移動量に足すことで自由落下を行う
//			d.add(a);
			if (moveLeft) {
				//左に動くよう移動量を設定
//				d.x = -speed;
			}else {
				//右に動くよう移動量を設定
//				d.x = speed;
			}
			//ジャンプする処理
			if (jumpFlag && !upturnFlag) {
				//上にジャンプするために符号を反転させる
				d.y = jumpPower;
				jumpFlag = false;
			}
			//マップとのあたり判定を行う関数
			//あたり判定を行いたい場合はp.add(d)の
			//手前にこの一行を書く。コピペでok
			int res = Collide.decideCheckOnMap(this, stg.getMap());
			//現在位置に移動量を足す事で移動する
			p.add(d);
			//移動方向の更新
			//300カウントごとに移動方向を切り替える
			if (timer%300 == 0) {
				moveLeft = !moveLeft;
			}
			//プレイヤーが近づいたらジャンプするフラグを立てる
//			//地面に触れているかの判定
//			if ((res & Collide.COL_MAP_BLOCK_DOWN) != 0) {
//				Vector2f pl = stg.getPlayerPos();//プレイヤーの現在座標を取得
//				//プレイヤーの座標とエネミーの座標が近かったらジャンプする
//				if (Math.abs(pl.x - p.x) < 60) {
//					jumpFlag = true;
//				}
//			}

			if((res & Collide.COL_MAP_BLOCK_DOWN) == 0){
				Vector2f pl = stg.getPlayerPos();//プレイヤーの現在座標を取得
				//プレイヤーの座標とエネミーの座標が近かったらジャンプする
				if (Math.abs(pl.x - p.x) < 64 && p.y + size.y < pl.y) {
					jumpFlag = true;
				}
			}else{
				d.y = -upturnPower;
				upturnFlag = true;
			}

			if(p.y <= startY || (res & Collide.COL_MAP_BLOCK_UP) != 0 || (res & Collide.COL_OUT_OF_MAP_UP) != 0){
				upturnFlag = false;
				p.y = startY;
				d.y = 0;
			}



	}

	//描画処理をここに書く
	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		//画像の表示
		//特に何もしないのであればコピペでok
		//アニメーションしたければ表示する画像を差し替えたりいろいろする
		g.drawImage(img, p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
				0, 0, 32, 32);
	}

	@Override
	public void hit(Object obj) {
		//プレイヤーとぶつかった時の処理
		//特に何かをする必要はない
	}

}
