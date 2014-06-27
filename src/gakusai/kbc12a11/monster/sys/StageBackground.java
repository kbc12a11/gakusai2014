package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StageBackground {
	protected Image backGroundImage;

	public StageBackground() throws SlickException {
		init();
	}
	public StageBackground(String imageName) throws SlickException {
		setBackGroundImage(imageName);
		init();
	}
	public StageBackground(Image image) throws SlickException {
		setBackGroundImage(image);
		init();
	}

	public void setBackGroundImage(Image backGroundImage) {
		this.backGroundImage = backGroundImage;
	}
	public void setBackGroundImage(String imageName) throws SlickException {
		setBackGroundImage(new Image(imageName));
	}

	////////////////////////////////////////
	/**初期化処理
	 * @throws SlickException TODO*/
	public void init() throws SlickException {}
	/**背景の更新処理
	 * @param sbg TODO
	 * @throws SlickException TODO*/
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{}
	/**背景の描画処理
	 * @param sbg TODO*/
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
		if (backGroundImage == null) return;
		//image 表示するイメージ
		//表示する左上ｘ座標
		//表示する左上y座標
		//表示する右下x座標
		//表示する右下y座標
		//イメージから抜き出す左上x座標
		//イメージから抜き出す左上y座標
		//イメージから抜き出す右下x座標
		//イメージから抜き出す右下y座標
		//g.drawImage(image, x, y, x2, y2, srcx, srcy, srcx2, srcy2);
		g.drawImage(backGroundImage, 0, 0, Main.W_WIDTH, Main.W_HEIGHT, 0, 0, 100, 20);
	}
	////////////////////////////////////////
	public boolean isSetBackground() {
		return backGroundImage != null;
	}
}
