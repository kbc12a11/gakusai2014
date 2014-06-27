package gakusai.kbc12a11.monster.st1;

import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

public class St1Background extends StageBackground{
	public St1Background() throws SlickException {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	ArrayList<Star> arr;
	Color[] colors;
	int count;
	@Override
	public void init() throws SlickException {
		arr = new ArrayList<Star>();
		colors = new Color[]{Color.white, Color.blue,
				Color.red, Color.yellow};
		for (int i = 0; i < 5; i++) {
			setStar();
		}
		count = 0;
	}
	private void setStar() {
		Star st = new Star();
		st.ang = (float)Math.random()*2;
		st.scale = 20 + (float)Math.random()*200;
		st.px = (float)Math.random() * Main.W_WIDTH;
		st.py = -st.scale;
		st.dx = 0;
		st.dy = (float)Math.random()*5 + 0.1f;
		st.c = colors[(int)(Math.random()*colors.length)];
		arr.add(st);
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		count++;
		if (count%60 == 0) {
			setStar();
		}
		for (int i = arr.size()-1; i >= 0; i--) {
			Star s = arr.get(i);
			if (s.f) {
				s.ang += 0.2;
				s.py += s.dy;
			}else {
				arr.remove(i);
			}
		}
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		for (Star s : arr) {
			g.setBackground(Color.black);
			g.setColor(s.c);
			g.translate(s.px, s.py);
			g.rotate(0, 0, s.ang);
			g.scale(s.scale, s.scale);
			g.draw(s.p);
			g.resetTransform();
		}
	}

	//星型クラス
	private class Star{
		final Polygon p = new Polygon();
		float ang;
		float scale;
		float px, py;
		float dx, dy;
		Color c;
		boolean f;
		Star() {
			f = true;
			p.addPoint(0, -1);
			p.addPoint(-0.588f, 0.809f);
			p.addPoint(0.951f, -0.309f);
			p.addPoint(-0.951f, -0.309f);
			p.addPoint(0.588f, 0.809f);
			p.setClosed(true);
		}
	}
}
