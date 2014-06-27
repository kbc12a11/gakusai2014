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

/**中心点の周りをぐるぐる回るだけのエネミー*/
//仕様上、敵は画面内にいるときしか更新も描画もされないので
//画面の外に出た敵はずっとその位置で止まっている状態になる
public class RoundEnemy extends Enemy{

	//回転した角度の現在値
	private float angle;
	//回転する速度
	private float rad;
	//回転半径
	private float r;

	//回転の中心座標
	private float centerX, centerY;

	//表示する用の画像
	Image img;

	/**コンストラクタでステージを受け取り、初期位置を指定する*/
	public RoundEnemy(Stage stg, float centerX, float centerY,
			float angle, float rad, float r) throws SlickException {
		super(stg, centerX, centerY);
		this.centerX = centerX;
		this.centerY = centerY;
		this.angle = angle;
		this.rad = rad;
		this.r = r;

		//初期位置設定
		setPosition();

		//画像を指定
		img = stg.getImage(ImageBank.ENEMY_UFO_1);
		//大きさを指定
		size.set(32, 32);
	}

	//エネミーの位置を設定する関数
	private void setPosition() {
		//現在の角度と回転半径と中心点の座標からエネミーの位置を求める
		float x = (float)Math.cos(angle) * r + centerX;
		float y = (float)Math.sin(angle) * r + centerY;
		p.set(x, y);
	}

	//更新処理をここに書く
	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//角度を更新
		angle += rad;
		//位置を設定
		setPosition();
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
