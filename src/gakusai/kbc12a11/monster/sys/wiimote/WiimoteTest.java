package gakusai.kbc12a11.monster.sys.wiimote;

import gakusai.kbc12a11.monster.sys.Main;

import org.newdawn.slick.geom.Vector2f;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
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

	/**ポインタの座標*/
	private float pointingX, pointingY;
	private boolean btn_a, btn_b;

	//ヌンチャク関係
	/**傾ける強さ(0-1)*/
	private float magnitude;
	/**傾けた角度(0-360(上が0,たまにNaN))*/
	private float angle;
	private boolean btn_c, btn_z;

	/**wiiリモコンが接続されているか*/
	private boolean isConnected;

	private float scale = 2;

	public boolean isBtnCPressed() {
		return btn_c;
	}
	public boolean isBtnZPressed() {
		return btn_z;
	}


	Wiimote wiimote;
	Vector2f centerPoint;
	Vector2f virtualScreenSize;
	Vector2f realScreenSize;

	public WiimoteTest() {
		realScreenSize = new Vector2f(Main.W_WIDTH, Main.W_HEIGHT);
		virtualScreenSize = new Vector2f((int)(Main.W_WIDTH*scale), (int)(Main.W_HEIGHT*scale));
		createConnection();
	}

	/**wiiリモコンとの接続を確立する*/
	public void createConnection() {
		System.out.print("Connect to Wii remote\t:");
		if (isConnected) {
			System.out.println();
			System.out.println("Had already been conected to Wii remote!");
			return;
		}
		try {
			Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
			wiimote = wiimotes[0];
			setIRTracking(true);
			wiimote.setVirtualResolution((int)virtualScreenSize.x, (int)virtualScreenSize.y);
			wiimote.activateSmoothing();
			wiimote.setAlphaSmoothingValue(0.001f);
			wiimote.setOrientationThreshold((float)(1.0*Math.PI));
			wiimote.addWiiMoteEventListeners(this);
			System.out.println("Success!");
			isConnected = true;
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Failed.");
			isConnected = false;
		}
	}

	/**ポインタのx座標*/
	public float getPointingX() {
		return pointingX - virtualScreenSize.x/2 + realScreenSize.x/2;
	}

	/**ポインタのy座標*/
	public float getPointingY() {
		return pointingY - virtualScreenSize.y/2 + realScreenSize.y/2;
	}

	public boolean isBtnAPushed() {
		return btn_a;
	}

	public boolean isBtnBPushed() {
		return btn_b;
	}

	/**Wiiリモコンと接続されているかどうか*/
	public boolean isConnected() {
		return isConnected;
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
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
		isConnected = false;
		wiimote = null;
	}

	/**ヌンチャクのイベント*/
	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
		//ヌンチャクのjoystickeventのパラメータ
		//Max[0] = 225, [1] = 225
		//Min[0] = 30 , [1] = 32
		//Center[0] = 127, [1] = 131
		if (arg0 instanceof NunchukEvent) {
			NunchukEvent nunchuk = (NunchukEvent)arg0;
			JoystickEvent joystick = nunchuk.getNunchukJoystickEvent();
			NunchukButtonsEvent buttons = nunchuk.getButtonsEvent();
			btn_c = buttons.isButtonCHeld();
			btn_z = buttons.isButtonZeHeld();
			angle = joystick.getAngle();
			magnitude = joystick.getMagnitude();
		}
	}

	/**ヌンチャクのジョイスティックの入力値をベクトルに変換して返す*/
	public Vector2f getJoystickInput() {
		Vector2f d = new Vector2f();
		double a = Double.isNaN(angle) ? 0 : angle;
		float m = magnitude;

		a = (a+90)*Math.PI/180;//ラジアンに変換+90度右に回転
		float x = (float)Math.cos(a)*m;
		float y = (float)Math.sin(a)*m;
		d.set(-x, -y);//右,下を正にする
		return d;
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
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onIrEvent(IREvent arg0) {
		pointingX = arg0.getX();
		pointingY = arg0.getY();
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
