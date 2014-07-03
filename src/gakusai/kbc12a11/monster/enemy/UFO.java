package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class UFO extends Enemy{

	Enemy[] enemy = new Enemy[5];
	int maxStock = 5;
	int stock = maxStock;
	private int timer = 0;
	private float speed = 1;
	private boolean release = false;
	Image[] img = new Image[2];
	public UFO(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);

		for(int i = 0; i < 5; i++){
			enemy[i] = new Mol(stg, x, y);
		}


		img[0] = ImageBank.getInstance().getImage(ImageBank.ENEMY_UFO_1);
		img[1] = ImageBank.getInstance().getImage(ImageBank.ENEMY_UFO_2);
		size.set(128, 128);
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;

		if(timer % 300 == 0 && stock > 0){
			stg.addEnemy(enemy[maxStock - stock]);
			stock--;
			release = true;
		}
		if(timer % 300 == 75 && release){
			release = false;
		}



		p.add(d);
	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(release){
			g.drawImage(img[1], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
					0, 0, 32, 32);
		}else{
			g.drawImage(img[0], p.x-size.x/2, p.y-size.y/2, p.x+size.x/2, p.y + size.y/2,
					0, 0, 32, 32);
		}
	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
