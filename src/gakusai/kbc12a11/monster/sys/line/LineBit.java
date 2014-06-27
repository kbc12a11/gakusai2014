package gakusai.kbc12a11.monster.sys.line;

import org.newdawn.slick.geom.Vector2f;

public class LineBit extends Vector2f{
	private boolean isLive;

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public boolean isLive() {
		return isLive;
	}

	public LineBit() {
		super();
		setLive(true);
	}

	public LineBit(float x, float y) {
		super(x, y);
		setLive(true);
	}

	public LineBit(Vector2f p) {
		super(p);
		setLive(true);
	}
}
