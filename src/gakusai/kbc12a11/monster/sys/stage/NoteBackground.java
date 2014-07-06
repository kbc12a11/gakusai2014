package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class NoteBackground extends StageBackground{

	int width = 96;//決め打ち
	int height = 96;

	float offset_x;
	float offset_y;

	public NoteBackground() throws SlickException {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void init() throws SlickException {
		this.setBackGroundImage("res/image/notePink.gif");
		offset_x = 0;
		offset_y = 0;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Stage st = (Stage)sbg.getCurrentState();
		offset_y = st.camera.getTranslateY()%height;
		offset_x = st.camera.getTranslateX()%width;
	}

	 @Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		 for (int i = 0; i <= Main.W_WIDTH/width+1; i++) {
			 for (int j = 0; j <= Main.W_HEIGHT/height+1; j++) {
				 g.drawImage(backGroundImage, i*width, j*height+offset_y, new Color(255, 255, 255, 255));
			 }
		 }
	}
}
