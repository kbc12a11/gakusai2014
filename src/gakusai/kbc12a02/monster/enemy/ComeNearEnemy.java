package gakusai.kbc12a02.monster.enemy;
import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**壁をすり抜けて空中を左右に動くだけのエネミー*/
//仕様上、敵は画面内にいるときしか更新も描画もされないので
//画面の外に出た敵はずっとその位置で止まっている状態になる
public class ComeNearEnemy extends Enemy{

	//時間をカウントするタイマー
	private int timer = 0;
	//接触判定
	private boolean atack = false;

	//表示する用の画像
	Image img;

	/**コンストラクタでステージを受け取り、初期位置を指定する*/
	public ComeNearEnemy(Stage stg, float x, float y) throws SlickException {
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
		img = new Image("res/image/mol/mol1.gif");
		//大きさを指定
		size.set(32, 32);
	}

	//更新処理をここに書く
	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//タイマーを進める
		timer++;

		//接触判定
		if(stg.getPlayerPos().x - p.getX() > -25 && stg.getPlayerPos().x  - p.getX() < 25){
			if(stg.getPlayerPos().y - p.getY() > -25 && stg.getPlayerPos().y  - p.getY() < 25){
				atack = true;
			}
		}else{
			if(stg.getPlayerPos().x - p.getX() < -50 || stg.getPlayerPos().x  - p.getX() > 50){
				if(stg.getPlayerPos().y - p.getY() < -50 || stg.getPlayerPos().y  - p.getY() > 50){
					atack = false;
				}
			}
		}


		//接触してないときプレイヤーに接近 else 離れる
			if(!atack){
				if(stg.getPlayerPos().x < p.x){
					if(stg.getPlayerPos().y < p.y){
						d.set(-2, -2);
					}else{
						d.set(-2, 2);
					}
				}else{
					if(stg.getPlayerPos().y < p.y){
						d.set(2,-2);
					}else{
						d.set(2, 2);
					}
				}
			}else{
				d.set(- 2 *  d.getX(),  - 2 * d.getY());
			}

		//現在位置に移動量を足す事で移動する
		p.add(d);
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
