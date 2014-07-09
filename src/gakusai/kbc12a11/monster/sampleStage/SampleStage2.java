package gakusai.kbc12a11.monster.sampleStage;

import gakusai.kbc12a08.monster.enemy.EnemyFactory;
import gakusai.kbc12a11.monster.item.Coin;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.decorate.SimpleImageView;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//Stageクラスを継承する
public class SampleStage2 extends Stage{

	public SampleStage2() throws SlickException {
		//使用するマップデータをスーパークラスのコンストラクタに渡す
		super("map/sampleStage/SampleStage2.tmx");

		//背景の設定
		//基本的には下2行をコピペでok
		StageBackground bg = new NoteBackground();
		this.setBackground(bg);
	}

	@Override
	public void reset() throws SlickException {
		super.reset();
		//マップ上の(1,27)の位置にプレイヤーの初期位置を設定
		int mx = (int)map.chipSizeOnScreen.x;
		int my = (int)map.chipSizeOnScreen.y;
		this.playerRespawnPoint.set(1*mx, 27*my);
	}

	//必ずMainで設定したidを返すように指定する
	@Override
	public int getID() {
		return Main.Stage_12a11_SampleStage2;
	}

	//敵の設定情報を書く
	@Override
	public void setEnemys() throws SlickException {
		addEnemyFactory(new EnemyFactory(this, SampleEnemy1.class,
				map.calcLocateX(25), map.calcLocateY(18)));

		addEnemyFactory(new EnemyFactory(this, SampleEnemy2.class,
				map.calcLocateX(4), map.calcLocateY(18)));
	}

	@Override
	public void setItems() throws SlickException {
		for (int i = 0; i < 4; i++) {
			addItem(new Coin(this, map.calcLocateX(4+i), map.calcLocateY(25)));
			addItem(new Coin(this, map.calcLocateX(20+i), map.calcLocateY(20-i)));
		}

		for (int i = 0; i < 17; i++) {
			addItem(new Coin(this, map.calcLocateX(38+i), map.calcLocateY(27)));
		}
	}

	@Override
	public void setBackgroundObject() throws SlickException {
		addBackgroundObject(
				new SimpleImageView(this,
						ImageBank.getInstance().
						getImage(ImageBank.BG_HENOHENO),
						map.calcLocateX(10), map.calcLocateY(10)));
	}

	@Override
	public void stUpdate(GameContainer gc, StateBasedGame sbg, int delta) {
		//特に何もしなくてok
	}

	@Override
	public void stRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		//特に何もしなくてok
	}




}
