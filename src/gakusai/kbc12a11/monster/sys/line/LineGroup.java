package gakusai.kbc12a11.monster.sys.line;

import gakusai.kbc12a11.monster.sys.Camera;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class LineGroup {
	private ArrayList<Line> lines;
	private boolean lineCreateFlag = false;

	//ラインエネルギーの最大値
	private final float lineDrawEnergy_Max = 1000;
	//ラインエネルギーの現在地
	private float lineDrawEnergy_Now = lineDrawEnergy_Max;
	//ラインエネルギーの自然回復量
	private float lineDrawEnergy_recovery = 5;
	//新しくラインを書き始めるためのラインエネルギーの下限値
	private final float lineDrawEnergy_minLimit = lineDrawEnergy_Max * 0.2f;

	private final Stage stg;


	/**デフォルトのhp*/
	private int defLineHp = 200;

	public LineGroup(Stage stg) {
		lines = new ArrayList<Line>();
		this.stg = stg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta, Camera camera){
		createLine(gc, delta, camera);
		updateLine(gc, sbg, delta);

		if (!lineCreateFlag)lineDrawEnergy_Now += lineDrawEnergy_recovery;
		if (lineDrawEnergy_Now > lineDrawEnergy_Max) {
			lineDrawEnergy_Now = lineDrawEnergy_Max;
		}
		if (lineDrawEnergy_Now <= 0) {
			if (lineDrawEnergy_Now < -1000)lineDrawEnergy_Now = -1000;
			lineCreateFlag = false;
		}
	}

	private void createLine(GameContainer gc, int delta, Camera camera) {
		if (lineDrawEnergy_Now <= 0) return;
//		boolean isClick = in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
//		float mx = in.getMouseX() - camera.getTranslateX();
//		float my = in.getMouseY() - camera.getTranslateY();
		GameInput in = stg.getGameInput();
		boolean isClick = in.isA();
		float mx = in.getX() - camera.getTranslateX();
		float my = in.getY() - camera.getTranslateY();

		if (!isClick) {
			lineCreateFlag = false;
			return;
		}
		if (lineCreateFlag) {
			if (lines.size() == 0) lineCreateFlag = false;
		}

		if (!lineCreateFlag) {
			if (lineDrawEnergy_Now < lineDrawEnergy_minLimit) return;
			lines.add(new TestLine(defLineHp, new LineBit(mx, my)));
			lineCreateFlag = true;
			LineBit p = new LineBit(mx, my);
			lines.get(lines.size()-1).addPoint(p);
		}
		int index = lines.size()-1;
		Line l = lines.get(index);
		lineDrawEnergy_Now -= l.lineDraw(mx, my, lineDrawEnergy_Now);

		if(lineCreateFlag){
			stg.soundRequest(SoundBank.SE_LINE);
		}
	}

	private void updateLine(GameContainer gc, StateBasedGame sbg, int delta) {
		for (int i = lines.size()-1; i >= 0; i--) {
			lines.get(i).update(gc, sbg, delta);
			if (!lines.get(i).isLive()) {
				lines.remove(i);
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for (Line l : lines) {
			l.render(gc, sbg, g);
		}
	}

	public ArrayList<Line> getLines(){
		return lines;
	}

	public float getLineDrawEnergyNow() {
		return lineDrawEnergy_Now;
	}
	public float getLineDrawEnergyMax() {
		return lineDrawEnergy_Max;
	}
	public float getLineDrawEnergyMinLimit() {
		return lineDrawEnergy_minLimit;
	}

	public void lineErase(float x, float y, float sizex, float sizey) {
		for (Line l : lines) {
			l.erase(x, y, sizex, sizey);
		}
	}
}
