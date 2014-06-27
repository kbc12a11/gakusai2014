package gakusai.kbc12a11.monster.abst;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class StatusWindow {
	public abstract void update(GameContainer gc, int delta);
	public abstract void render(GameContainer gc, Graphics g);
}
