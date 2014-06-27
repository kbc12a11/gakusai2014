package gakusai.kbc12a08.monster.enemy;

import gakusai.kbc12a11.monster.sys.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.newdawn.slick.geom.Vector2f;

/**敵を固定の位置に毎回出現させるためのクラス*/
public class EnemyFactory {
	/**出現位置*/
	private Vector2f rp;
	/**エネミーの大きさ(デフォルト32*32)*/
	private Vector2f size;
	private Stage stg;

	/**リスポーン可能状態フラグ*/
	private boolean flg_resp = true;

	/**このEnemyFactoryでリスポーンさせたエネミーのインスタンス<br>
	 * フィールド上には１体だけしか出現させないようにするために保持*/
	private Enemy respawnedEnemy;

	//クラス
	//private Class<? extends Enemy> enemyClass;
	//コンストラクタ
	Constructor<? extends Enemy> constructor;

	/**
	 * デフォルトの大きさ(32*32)の敵の出現ポイントを設定する
	 * @param stg
	 * @param enemyClass 出現させたい敵のクラス
	 * @param x 出現させたい位置のx座標
	 * @param y 出現させたい位置のy座標
	 */
	public EnemyFactory(Stage stg, Class<? extends Enemy> enemyClass, float x, float y) {
		this(stg, enemyClass, x, y, 32, 32);
	}

	/**
	 * 敵の出現ポイントを設定する
	 * @param stg
	 * @param enemyClass 出現させたい敵のクラス
	 * @param x 出現させたい位置のx座標
	 * @param y 出現させたい位置のy座標
	 * @param sizex 敵の大きさ
	 * @param sizey 敵の大きさ
	 */
	public EnemyFactory(Stage stg, Class<? extends Enemy> enemyClass, float x, float y, float sizex, float sizey) {
		this.stg = stg;
		rp = new Vector2f(x, y);
		size = new Vector2f(sizex, sizey);
		try {
			this.constructor = enemyClass.getConstructor(Stage.class, float.class, float.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void init() {
		respawnedEnemy = null;
		flg_resp = true;
	}

	/**更新処理*/
	public void update() {
		if (respawnedEnemy != null) {
			if (!respawnedEnemy.isLive()) {
				respawnedEnemy = null;//エネミーが死んでいたらGCするために参照を破棄
			}else {
				return;//エネミーが出現中で生存していたら何もしない
			}
		}

		//↓エネミーが存在していないときの処理
		boolean isInsideView = stg.getCamera().isInsideDisplay(rp, size, 32);
		if (!isInsideView) {
			flg_resp = true;
			return;//画面外なら何もしない
		}
		else if (flg_resp){
			flg_resp = false;
			respawn();//画面内にリスポーンポイントが来たらリスポーンする
		}
	}

	/**敵を生成する*/
	public void respawn() {
		Enemy enemy = null;
		try {
			enemy = constructor.newInstance(stg, rp.x, rp.y);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (enemy != null) {
			stg.addEnemy(enemy);
			respawnedEnemy = enemy;
		}
	}

	/**敵の出現ポイントを返す*/
	public Vector2f getRespawnPoint() {
		return rp;
	}
}
