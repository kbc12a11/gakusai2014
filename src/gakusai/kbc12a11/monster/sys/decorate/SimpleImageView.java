package gakusai.kbc12a11.monster.sys.decorate;

import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SimpleImageView extends BackgroundObject{

	Image image;

	private int viewOption;

	/**Pを画像の左上に設定*/
	public static int P_TOP_LEFT = 0;
	/**Pを画像の中央上に設定*/
	public static int P_TOP_CENTER = 1;
	/**Pを画像の右上に設定*/
	public static int P_TOP_RIGHT = 2;

	/**Pを画像の中央左に設定*/
	public static int P_CENTER_LEFT = 3;
	/**Pを画像の中心に設定*/
	public static int P_CENTER_CENTER = 4;
	/**Pを画像の中央右に設定*/
	public static int P_CENTER_RIGHT = 5;

	/**Pを画像の左下に設定*/
	public static int P_BOTTOM_LEFT = 6;

	public SimpleImageView(Stage stg, Image image) {
		super(stg);
		this.image = image;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawImage(image, p.x-size.x/2, p.y-size.y/2);
	}
}
