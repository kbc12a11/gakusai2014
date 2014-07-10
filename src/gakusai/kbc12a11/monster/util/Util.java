package gakusai.kbc12a11.monster.util;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.wiimote.WiimoteTest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Util {
	/**数字画像の横幅*/
	private static final int NUMIMG_W;
	/**数字画像の縦幅*/
	private static final int NUMIMG_H;
	static {
		Image img = ImageBank.getImage(ImageBank.WD_NUM);
		NUMIMG_W = img.getWidth()/10;
		NUMIMG_H = img.getHeight();
	}
	private Util(){
	}

	/**数値を範囲内に丸める*/
	public static float between(float num, float min, float max) {
		float res = num;
		if (res < min) res = min;
		if (res > max) res = max;
		return res;
	}

	/**Pを画像の左上に設定*/
	public static final int DRAWIMAGE_TOP_LEFT = 0;
	/**Pを画像の中央上に設定*/
	public static final int DRAWIMAGE_TOP_CENTER = 1;
	/**Pを画像の右上に設定*/
	public static final int DRAWIMAGE_TOP_RIGHT = 2;

	/**Pを画像の中央左に設定*/
	public static final int DRAWIMAGE_CENTER_LEFT = 3;
	/**Pを画像の中心に設定*/
	public static final int DRAWIMAGE_CENTER_CENTER = 4;
	/**Pを画像の中央右に設定*/
	public static final int DRAWIMAGE_CENTER_RIGHT = 5;

	/**Pを画像の左下に設定*/
	public static final int DRAWIMAGE_BOTTOM_LEFT = 6;
	/**Pを画像の中央下に設定*/
	public static final int DRAWIMAGE_BOTTOM_CENTER = 7;
	/**Pを画像の右下に設定*/
	public static final int DRAWIMAGE_BOTTOM_RIGHT = 8;

	public static void drawImage(Graphics g, Image image, Vector2f p, Vector2f size, int option) {
		float x0 = 0;
		float y0 = 0;
		switch (option) {
		case DRAWIMAGE_TOP_LEFT :
			x0 = 0;
			y0 = 0;
			break;
		case DRAWIMAGE_TOP_CENTER:
			x0 = size.x/2;
			y0 = 0;
			break;
		case DRAWIMAGE_TOP_RIGHT:
			x0 = size.x;
			y0 = 0;
			break;

		case DRAWIMAGE_CENTER_LEFT :
			x0 = 0;
			y0 = size.y/2;
			break;
		case DRAWIMAGE_CENTER_CENTER:
			x0 = size.x/2;
			y0 = size.y/2;
			break;
		case DRAWIMAGE_CENTER_RIGHT:
			x0 = size.x;
			y0 = size.y/2;
			break;

		case DRAWIMAGE_BOTTOM_LEFT :
			x0 = 0;
			y0 = size.y;
			break;
		case DRAWIMAGE_BOTTOM_CENTER:
			x0 = size.x/2;
			y0 = size.y;
			break;
		case DRAWIMAGE_BOTTOM_RIGHT:
			x0 = size.x;
			y0 = size.y;
			break;

		default:
			x0 = size.x/2;
			y0 = size.y/2;
		}

		g.drawImage(image, p.x-x0, p.y-y0);
	}

	public static GameInput getGameInput(GameInput in, GameContainer gc) {
		WiimoteTest wii = Main.getWiimoteRistener();
		boolean flag = wii.isConnected();
		if (in == null) {
			in = new GameInput();
		}
		if (flag) {
			in.setX(wii.getPointingX());
			in.setY(wii.getPointingY());
			in.setA(wii.isBtnAPushed());
			in.setB(wii.isBtnBPushed());
			in.setC(wii.isBtnCPressed());
			in.setZ(wii.isBtnZPressed());

			in.setUp(wii.isUp());
			in.setDown(wii.isDown());
			in.setLeft(wii.isLeft());
			in.setRight(wii.isRight());

			in.setJoyInput(wii.getJoystickInput());
		}else {
			Input mouse = gc.getInput();
			in.setX(mouse.getMouseX());
			in.setY(mouse.getMouseY());
			in.setA(mouse.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
			in.setB(mouse.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON));
			in.setC(mouse.isKeyDown(Input.KEY_SPACE));
			in.setZ(mouse.isKeyDown(Input.KEY_Z));

			in.setUp(mouse.isKeyDown(Input.KEY_UP));
			in.setDown(mouse.isKeyDown(Input.KEY_DOWN));
			in.setLeft(mouse.isKeyDown(Input.KEY_LEFT));
			in.setRight(mouse.isKeyDown(Input.KEY_RIGHT));

			Vector2f v = new Vector2f();

			if (mouse.isKeyDown(Input.KEY_W)) {
				v.y = -1;
			}
			if (mouse.isKeyDown(Input.KEY_S)) {
				v.y = 1;
			}
			if (mouse.isKeyDown(Input.KEY_A)) {
				v.x = -1;
			}
			if (mouse.isKeyDown(Input.KEY_D)) {
				v.x = 1;
			}

			in.setJoyInput(v);
		}

		return in;
	}

	/**
	 *数値を描画する
	 * @param g
	 * @param num 描画する数値
	 * @param x 右端の座標
	 * @param y 中心の座標
	 * @param scale 各縮率
	 */
	public static void drawNumber(Graphics g, int num,
			float x, float y, float scale){

		int n = num;
		int i = 0;
		float w = NUMIMG_W*scale;
		while (true) {
			int r = n%10;
			_drawNumber(g, r, x-i*w, y, scale);
			i++;
			n = n/10;
			if (n == 0)break;
		}
 	}

	private static void _drawNumber(Graphics g, int index, float x, float y,
			float scale) {
		Image img = ImageBank.getImage(ImageBank.WD_NUM);
		float w = NUMIMG_W;
		float h = NUMIMG_H;
		float sw = w*scale;
		float sh = h*scale;
		g.drawImage(img, x - sw/2, y - sh/2, x + sw/2, y + sh/2,
				w*(index), 0, w*(index+1), h);
	}
}
