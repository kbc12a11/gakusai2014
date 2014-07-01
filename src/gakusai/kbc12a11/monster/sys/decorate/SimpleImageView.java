package gakusai.kbc12a11.monster.sys.decorate;

import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Util;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SimpleImageView extends BackgroundObject{

	Image image;

	private int viewOption;

	public SimpleImageView(Stage stg, Image image, float x, float y) {
		super(stg);
		this.image = image;
		viewOption = Util.DRAWIMAGE_CENTER_CENTER;
		p.set(x, y);
		size.set(image.getWidth(), image.getHeight());
	}

	/**UtilのDRAWIMAGEオプションを設定する*/
	public void setViewOption(int option) {
		this.viewOption = option;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		Util.drawImage(g, image, p, size, viewOption);
	}
}
