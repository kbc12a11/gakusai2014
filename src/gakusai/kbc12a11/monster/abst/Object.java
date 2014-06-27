package gakusai.kbc12a11.monster.abst;

import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Object {
	/**オブジェクトの生存フラグ<br>
	 * 生存フラグが消えるとオブジェクトが消滅する*/
	protected boolean flg_live;

	/**死亡フラグ<br>
	 * 死亡フラグが立ち、死亡アクションを実行して生存フラグが消滅する*/
	protected boolean flg_destroy;

	/**オブジェクトの大きさ*/
	protected Vector2f size;
	/**質量*/
	protected float mass;

	/**現在位置*/
	protected Vector2f p;
	/**移動量*/
	protected Vector2f d;
	/**加速度*/
	protected Vector2f a;

	/**所属しているステージ*/
	protected Stage stg;

	/***
	 * オブジェクトが不死であるフラグ<br>
	 * プレイヤーがこのオブジェクトを殺すことができるかを判定する
	 */
	protected boolean isImmortal;


	public Object(Stage stg) {
		mass = 5;//デフォルト値
		p = new Vector2f();
		d = new Vector2f();
		a = new Vector2f();
		size = new Vector2f();
		flg_live = true;
		flg_destroy = false;

		this.stg = stg;
	}

	/**更新処理
	 * @param sbg TODO
	 * @throws SlickException TODO*/
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;
	/**描画処理
	 * @param sbg TODO*/
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g);


	/**他のオブジェクトとのあたり判定を行う*/
	public boolean isHit(Object obj) {
		//		Shape s1 = this.getShape();
		//		Shape s2 = obj.getShape();
		//		return s1.intersects(s2);
		float dx = obj.getP().x - p.x;
		float dy = obj.getP().y - p.y;
		dx = dx < 0 ? -dx : dx;
		dy = dy < 0 ? -dy : dy;
		boolean flg = false;
		float sx = size.x/2 + obj.getSize().x/2;
		float sy = size.y/2 + obj.getSize().y/2;
		if (dx < sx && dy < sy) {
			if (dx*dx + dy*dy < sx*sx + sy*sy) {
				flg = true;
			}
		}
		return flg;
	}
	/**他のオブジェクトと当たった際の処理*/
	public abstract void hit(Object obj);

	/**オブジェクトの形を返す*/
	public Shape getShape() {
		return new Rectangle(p.x - size.x/2, p.y - size.y/2, size.x, size.y);
	}

	public boolean isLive() {
		return flg_live;
	}

	/**死亡フラグを立てる*/
	public void destroy() {
		flg_destroy = true;
	}

	public boolean isDestroy() {
		return flg_destroy;
	}

	public Vector2f getA() {
		return a;
	}
	public Vector2f getD() {
		return d;
	}
	public Vector2f getP() {
		return p;
	}
	public Vector2f getSize() {
		return size;
	}
	public float getMass() {
		return mass;
	}

	/**オブジェクトが不死か
	 * @return 不死ならtrue*/
	public boolean isImmortal() {
		return isImmortal;
	}
}
