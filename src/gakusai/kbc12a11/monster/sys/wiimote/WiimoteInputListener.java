package gakusai.kbc12a11.monster.sys.wiimote;

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

public class WiimoteInputListener implements WiimoteListener{


	@Override
	public void onButtonsEvent(WiimoteButtonsEvent arg0) {
		button_A = arg0.isButtonAHeld();
		button_B = arg0.isButtonBHeld();
	}
	@Override
	public void onIrEvent(IREvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		System.out.println("x : " + x + "  y : " + y);
	}

	private float x, y;
	private boolean button_A, button_B;

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

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0) {
	}
	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0) {
	}
	@Override
	public void onDisconnectionEvent(DisconnectionEvent arg0) {
	}
	@Override
	public void onExpansionEvent(ExpansionEvent arg0) {
	}
	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
	}
	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
	}
	@Override
	public void onMotionSensingEvent(MotionSensingEvent arg0) {
	}
	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
	}
	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
	}
	@Override
	public void onStatusEvent(StatusEvent arg0) {
	}

}
