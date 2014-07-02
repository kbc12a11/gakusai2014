package gakusai.kbc12a11.monster.sys.wiimote;

import gakusai.kbc12a11.monster.sys.Main;
import wiiusej.wiiusejevents.utils.WiimoteListener;

public class WiimoteInput {

	private float x, y;
	private boolean button_A, button_B;

	private WiimoteListener lestener;

	public WiimoteInput() {
		lestener = Main.getWiimoteRistener();
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean isButtonAHold() {
		return button_A;
	}
	public boolean isButtonBHold() {
		return button_B;
	}

	public void update() {
	}
}
