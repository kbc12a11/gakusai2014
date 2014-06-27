package gakusai.kbc12a11.monster.sys.line;


import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Line {
	protected ArrayList<LineBit> points;
	protected boolean live;
	protected boolean nowDrawing = false;

	protected int LineWidth = 5;

	private float force = 0.2f;//線の方向に移動させる力の大きさ

	public Line(LineBit firstPoint) {
		points = new ArrayList<LineBit>();
		addPoint(firstPoint);
		live = true;
	}

	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta);
	public abstract void render(GameContainer gc, StateBasedGame sbg, Graphics g);

	/**x,yの位置にラインを描く
	 * @param x
	 * @param y
	 * @return 新しく描いた長さ
	 */
	public abstract float lineDraw(float x, float y, float energy);

	public void addPoint(LineBit p) {
		points.add(p);
	}

	public ArrayList<LineBit> getPoints() {
		return points;
	}

	public boolean isLive() {
		return live;
	}

	public boolean isDrawing() {
		return nowDrawing;
	}

	public void setDrawingFlag(boolean nowDrawing) {
		this.nowDrawing = nowDrawing;
	}

	public int getLineWidth() {
		return LineWidth;
	}

	/**
	 * 直線ABの法線ベクトルを返す
	 * */
	public static Vector2f getNormalVector(Vector2f A, Vector2f B){
		float a, b;
//		//AとBの座標が同じなら何もしない
		if (A.equals(B)) return new Vector2f();
//		//直線AB
		a = A.y - B.y;
		b = B.x - A.x;
		Vector2f N;
		if (a != 0)
			N = new Vector2f(1, b/a).normalise();
		else
			N = new Vector2f(0, 1);
		return N;
	}
	/**
	 * 	線に乗っているキャラを移動させる力を返す
	 * @param index1 始点インデックス
	 * @param index2 終点インデックス
	 * @return 移動力ベクトル
	 */
	public Vector2f getForce(int index1, int index2) {
		Vector2f result = new Vector2f();
		if (index1 < 0 || points.size() <= index1) {
			return result;
		}
		if (index2 < 0 || points.size() <= index2) {
			return result;
		}
		if (index1 == index2) {
			return result;
		}
		Vector2f s = points.get(index1);
		Vector2f e = points.get(index2);
		float x = e.x - s.x;
		float y = e.y - s.y;
		result.set(x, y).normalise();
		result.scale(force);
		return result;
	}

	public void erase(float x, float y, float sizex, float sizey) {
		x = x - sizex/2;
		y = y - sizey/2;
		for (LineBit b : points) {
			if (x <= b.x && b.x <= x+sizex) {
				if (y <= b.y && b.y <= y+sizey) {
					b.setLive(false);
				}
			}
		}
	}
}
