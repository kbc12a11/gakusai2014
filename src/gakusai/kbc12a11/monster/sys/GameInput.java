package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.geom.Vector2f;


public class GameInput {
	private float x, y;
	private boolean a, b;
	private boolean c, z;
	private boolean up, down, left, right;
	private Vector2f joyInput;

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public boolean isA() {
		return a;
	}
	public void setA(boolean a) {
		this.a = a;
	}
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}

	public void setC(boolean c) {
		this.c = c;
	}

	public boolean isC() {
		return c;
	}

	public void setZ(boolean z) {
		this.z = z;
	}

	public boolean isZ() {
		return z;
	}

	public void setJoyInput(Vector2f joyInput) {
		this.joyInput = joyInput;
	}

	public Vector2f getJoyInput() {
		return joyInput;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}

}
