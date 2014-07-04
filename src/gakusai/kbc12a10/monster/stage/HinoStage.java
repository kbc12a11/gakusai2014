package gakusai.kbc12a10.monster.stage;

import gakusai.kbc12a08.monster.enemy.EnemyFactory;
import gakusai.kbc12a11.monster.enemy.Bomb;
import gakusai.kbc12a11.monster.enemy.Ghost;
import gakusai.kbc12a11.monster.enemy.Magican;
import gakusai.kbc12a11.monster.enemy.Missile;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class HinoStage extends Stage {

	public HinoStage() throws SlickException {
		super("map/hinoStage/hinoStage.tmx");
		// TODO 自動生成されたコンストラクター・スタブ

		StageBackground bg = new NoteBackground();
		this.setBackground(bg);
	}

	@Override
	public void stUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void stRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void setEnemys() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		//敵の追加
		addEnemyFactory(new EnemyFactory(this, Magican.class, 200, 505));

		EnemyFactory[] bomb = {
				new EnemyFactory(this, Bomb.class, 2200, 600),
				new EnemyFactory(this, Bomb.class, 4500, 450),
				new EnemyFactory(this, Bomb.class, 7000, 500),
		};

		addEnemyFactory(bomb);

		EnemyFactory[] missile = {
				new EnemyFactory(this, Missile.class, 2100, 300),
				new EnemyFactory(this, Missile.class, 2300, 500),
				new EnemyFactory(this, Missile.class, 2600, 600),
				new EnemyFactory(this, Missile.class, 2600, 270),
				new EnemyFactory(this, Missile.class, 2200, 700),
				new EnemyFactory(this, Missile.class, 4600, 700),
				new EnemyFactory(this, Missile.class, 4600, 740),
				new EnemyFactory(this, Missile.class, 7000, 100),
				new EnemyFactory(this, Missile.class, 6540, 740),
				new EnemyFactory(this, Missile.class, 7000, 980),
		};

		addEnemyFactory(missile);

		EnemyFactory[] ghost = {
				new EnemyFactory(this, Ghost.class, 3120, 100),
				new EnemyFactory(this, Ghost.class, 4800, 450),
		};
		addEnemyFactory(ghost);

	}

	@Override
	public void setItems() throws SlickException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_12a10_HinoStage;
	}

}
