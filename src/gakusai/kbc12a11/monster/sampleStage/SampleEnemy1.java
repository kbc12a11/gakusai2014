package gakusai.kbc12a11.monster.sampleStage;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**壁をすり抜けて空中を左右に動くだけのエネミー*/
//仕様上、敵は画面内にいるときしか更新も描画もされないので
//画面の外に出た敵はずっとその位置で止まっている状態になる
public class SampleEnemy1 extends Enemy{

	//左右のどちらの方向に動くかのフラグ
	private boolean moveLeft = true;
	//時間をカウントするタイマー
	private int timer = 0;
	//移動スピード
	private float speed = 0.5f;

	//表示する用の画像
	Image img;

	/**コンストラクタでステージを受け取り、初期位置を指定する*/
	public SampleEnemy1(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		//主要なプロパティ
		//p : エネミーの位置
		//d : 移動量
		//a : 加速度
		//size : エネミーの大きさ(32 * 32推奨)

		//初期位置の設定
		p.set(x, y);
		//
		//こう書いても設定できる
		//p.x = x;
		//p.y = y;

		//画像を指定
		img = stg.getImage(ImageBank.ENEMY_MOL_1);
		//大きさを指定
		size.set(32, 32);
	}

	//更新処理をここに書く
	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//タイマーを進める
		timer++;

		if (moveLeft) {
			//左に動くよう移動量を設定
			d.set(-speed, 0);
		}else {
			//右に動くよう移動量を設定
			d.set(speed, 0);
		}

		//現在位置に移動量を足す事で移動する
		p.add(d);

		//移動方向の更新
		//300カウントごとに移動方向を切り替える
		if (timer%300 == 0) {
			moveLeft = !moveLeft;
		}
	}

	//描画処理をここに書く
	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		//画像の表示
		//特に何もしないのであればコピペでok
		//アニメーションしたければ表示する画像を差し替えたりいろいろする
		g.drawImage(img, p.x - size.x/2, p.y - size.y/2);
	}

	@Override
	public void hit(Object obj) {
		//プレイヤーとぶつかった時の処理
		//特に何かをする必要はない
	}

}
