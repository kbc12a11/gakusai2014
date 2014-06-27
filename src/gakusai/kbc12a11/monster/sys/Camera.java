package gakusai.kbc12a11.monster.sys;

import gakusai.kbc12a11.monster.abst.Character;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;


public class Camera {
	private int mapWidth, mapHeight;
	private float zoom;
	private Character focusChr;

	private float translateX;
	private float translateY;
	private Rectangle view;

	/**
	 *
	 * @param width マップの幅
	 * @param height マップの高さ
	 */
	public Camera(int mapWidth, int mapHeight) {
		this.mapHeight = mapHeight;
		this.mapWidth = mapWidth;
		zoom = 1.0f;
		view = new Rectangle(0, 0, Main.W_WIDTH, Main.W_HEIGHT);
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public void setFocus(Character ch) {
		focusChr = ch;
	}

	public void update() {
		setTranslate();
		view.setBounds(-translateX, -translateY, Main.W_WIDTH, Main.W_HEIGHT);
	}

	/**現在のカメラの写している範囲を返す*/
	public Rectangle getView() {
		return view;
	}

	/**オブジェクトが表示範囲内に存在するか
	 *
	 * @param p
	 * @param size
	 * @param margin 画面外に出た判定の余裕
	 * @return
	 */
	public boolean isInsideDisplay(Vector2f p, Vector2f size, float margin) {
		boolean flg = true;
		if (p.x - size.x/2 > view.getX() + view.getWidth() + margin) {
			flg = false;
		}else if (p.x + size.x/2 < view.getX() - margin) {
			flg = false;
		}else if (p.y - size.y/2 > view.getY() + view.getHeight() + margin) {
			flg = false;
		}else if (p.y + size.y/2 < view.getY() - margin) {
			flg = false;
		}
		return flg;
	}

	/**カメラのフィールド表示用X軸中央座標*/
	public float getTranslateX() {
		return translateX;
	}

	/**カメラのフィールド表示用Y軸中央座標*/
	public float getTranslateY() {
		return translateY;
	}

	public void setTranslate() {
		float x;
		float w = Main.W_WIDTH/2;
		float p;
		if (focusChr != null) {
			p = focusChr.getP().x;
		} else {
			p = Main.W_WIDTH/2;
		}
		if (p < w) {
			x = 0;
		}else if (mapWidth - w < p) {
			x = Main.W_WIDTH - mapWidth;
		}else {
			x = w - p;
		}
		translateX = x;

		float y;
		float h = Main.W_HEIGHT/2;
		if (focusChr != null) {
			p = focusChr.getP().y;
		} else {
			p = Main.W_HEIGHT/2;
		}
		if (p < h) {
			y = 0;
		}else if (mapHeight - h < p) {
			y = Main.W_HEIGHT - mapHeight;
		}else {
			y = h - p;
		}
		translateY = y;
	}
}