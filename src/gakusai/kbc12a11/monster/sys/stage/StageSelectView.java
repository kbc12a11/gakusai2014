package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.util.Util;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class StageSelectView extends BasicGameState{

	ArrayList<GameState> stageList;
	int nowSelectIndex = 0;
	int wait = 10;
	int timer;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		stageList = new ArrayList<GameState>();
		stageList.add(sbg.getState(Main.Stage_12a10_HinoStage));
		stageList.add(sbg.getState(Main.Stage_12a11_MarioStage));
		stageList.add(sbg.getState(Main.Stage_12a11_SampleStage2));
		timer = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
//
//		for (int i = 0; i < stageList.size(); i++) {
//			if (nowSelectIndex != i)
//				g.drawString(stageList.get(i).toString(), 120, 80 + 30*i);
//			else
//				g.drawString("> " + stageList.get(i).toString(), 120, 80 + 30*i);
//		}

		int     width = 500;
		int     height = 200;
		int     depth = 100;

		SGL gl = Renderer.get();
		gl.glEnable(SGL.GL_TEXTURE_2D);

		//  次に指定する４つの座標を、描く四角形の頂点として認識させる
		gl.glBegin(SGL.GL_QUADS);

		//  OpenGL では頂点が左回りになっているのがポリゴンの表となる
		//  今は表のみ表示する設定にしているので、頂点の方向を反対にすると裏側となり、表示されなくなる

		float a = 1.0f;

		gl.glColor4f(1.0f, 0.5f, 0.5f, a);            //  次に指定する座標に RGB で色を設定する
		gl.glVertex3f(width - 50, height- 50, 0);  //  1 つめの座標を指定する

		gl.glColor4f(0.5f, 1.0f, 0.5f, a);
		gl.glVertex3f(50, height - 50, 1);      // 2 つめの座標を指定する

		gl.glColor4f(0.5f, 0.5f, 1.0f, a);
		gl.glVertex3f(50, 50, 1);                //    3 つめの座標を指定する

		gl.glColor4f(1.0f, 1.0f, 1.0f, a);
		gl.glVertex3f(width - 50, 50, 1);        //    4 つめの座標を指定する

		gl.glEnd();
		//g.drawString("Stage Select.", 100, 550);
//		g.drawImage(ImageBank.getImage(ImageBank.ENEMY_BOMB_1).dr, 100, 500);
		ImageBank.getImage(ImageBank.ENEMY_BOMB_1).draw(100, 100, new Color(255, 255, 255,255));
		float x = 100, y = 100;
		float angle = 0;
		gl.glTranslatef(x, y, 0);

        gl.glBegin(SGL.GL_QUADS);
            drawEmbedded(0,0,width,height);
        gl.glEnd();

        gl.glTranslatef(-x, -y, 0);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		GameInput in = Util.getGameInput(null, gc);
		Vector2f joy = in.getJoyInput();

		timer--;

		if (timer < 0 && joy.y > 0.5) {
			nowSelectIndex = (nowSelectIndex + 1)%stageList.size();
			timer = wait;
		}
		if (timer < 0 && joy.y < -0.5) {
			nowSelectIndex--;
			if (nowSelectIndex < 0) nowSelectIndex = stageList.size()-1;
			timer = wait;
		}
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_StageSelectView;
	}

}
