package gakusai.kbc12a11.monster.sys.wiimote;

import gakusai.kbc12a11.monster.sys.Main;

import org.newdawn.slick.geom.Vector2f;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

public class WiimoteTest implements WiimoteListener{

	private float x, y;
	private boolean btn_a, btn_b;

	private float scale = 2;

	Wiimote wiimote;
	Vector2f centerPoint;
	Vector2f virtualScreenSize;
	Vector2f realScreenSize;

	public WiimoteTest() {
		realScreenSize = new Vector2f(Main.W_WIDTH, Main.W_HEIGHT);
		virtualScreenSize = new Vector2f((int)(Main.W_WIDTH*scale), (int)(Main.W_HEIGHT*scale));
		getWiimote();
	}

	/**Wiiリモコンの取得とリスナーの設定*/
	public void getWiimote() {
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
        wiimote = wiimotes[0];
        setIRTracking(true);
        wiimote.setVirtualResolution((int)virtualScreenSize.x, (int)virtualScreenSize.y);
        wiimote.activateSmoothing();
        wiimote.setAlphaSmoothingValue(0.001f);
        wiimote.addWiiMoteEventListeners(this);
	}

	public float getX() {
		return x - virtualScreenSize.x/2 + realScreenSize.x/2;
	}

	public float getY() {
		return y - virtualScreenSize.y/2 + realScreenSize.y/2;
	}

	public boolean isBtn_a() {
		return btn_a;
	}

	public boolean isBtn_b() {
		return btn_b;
	}

	/**IRトラッキングを行うかを設定<br>
	 * trueでIRトラッキングを行う*/
	public void setIRTracking(boolean flag) {
		if (wiimote != null) {
			if (flag) {
				wiimote.activateIRTRacking();
			}else {
				wiimote.deactivateIRTRacking();
			}
		}
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		btn_a = arg0.isButtonAHeld();
		btn_b = arg0.isButtonBHeld();
	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onIrEvent(IREvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onStatusEvent(StatusEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
