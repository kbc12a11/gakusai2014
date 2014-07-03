package gakusai.kbc12a11.monster.util;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.sys.wiimote.WiimoteTest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class Util {
	private Util(){}

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

	public static GameInput getGameInput(Stage stg, GameContainer gc) {
		WiimoteTest wii = Main.getWiimoteRistener();
		boolean flag = wii.isConnected();
		GameInput in = new GameInput();
		if (flag) {
			in.setX(wii.getPointingX());
			in.setY(wii.getPointingY());
			in.setA(wii.isBtn_a());
			in.setB(wii.isBtn_b());
		}else {
			Input mouse = gc.getInput();
			in.setX(mouse.getMouseX());
			in.setY(mouse.getMouseY());
			in.setA(mouse.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
			in.setB(mouse.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON));
		}

		return in;
	}
}
