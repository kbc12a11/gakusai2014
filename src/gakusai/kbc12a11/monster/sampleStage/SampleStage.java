package gakusai.kbc12a11.monster.sampleStage;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a08.monster.enemy.EnemyFactory;
import gakusai.kbc12a11.monster.enemy.Magican;
import gakusai.kbc12a11.monster.item.Coin;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.stage.NoteBackground;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//Stageクラスを継承する
public class SampleStage extends Stage{

	public SampleStage() throws SlickException {
		//使用するマップデータをスーパークラスのコンストラクタに渡す
		super("map/st3/enpitsuborupentest.tmx");

		//背景の設定
		//基本的には下2行をコピペでok
		StageBackground bg = new NoteBackground();
		this.setBackground(bg);
	}

	//必ずMainで設定したidを返すように指定する
	@Override
	public int getID() {
		return Main.Stage_SampleStage;
	}

	//敵の設定情報を書く
	@Override
	public void setEnemys() throws SlickException {

		//////敵の追加///////
		//基本は敵インスタンスを生成してステージに追加する
		//敵のインスタンスの生成
		//第一引数は必ずthisを指定する
		Enemy sampleEnemy1 = new SampleEnemy1(this, 300, 200);
		//敵の追加
		addEnemy(sampleEnemy1);

		//配列で敵を宣言してまとめて追加することもできる
		Enemy[] sampleEnemy2s = {
				new SampleEnemy2(this, 100, 300),
				new SampleEnemy2(this, 200, 100),
				new SampleEnemy2(this, 300, 150),
				new SampleEnemy2(this, 400, 350)
		};
		addEnemy(sampleEnemy2s);

		//複数種類の敵をまとめて追加することもできる
		Enemy[] enemys = {
				new SampleEnemy1(this, 500, 300),
				new SampleEnemy1(this, 500, 100),
				new SampleEnemy1(this, 500, 200),
				new RoundEnemy(this, 500, 300, 0, 0.01f, 100),
				new RoundEnemy(this, 600, 200, 0.5f, 0.03f, 50),
				new RoundEnemy(this, 400, 100, 1.5f, 0.1f, 90),
		};
		addEnemy(enemys);

		//固定位置に出現する敵の追加
		EnemyFactory ef = new EnemyFactory(this, Magican.class, 300, 300);
		addEnemyFactory(ef);

		EnemyFactory[] efs = {
				new EnemyFactory(this, Magican.class, 1300, 70),
				new EnemyFactory(this, Magican.class, 200, 400),
				new EnemyFactory(this, Magican.class, 100, 200),
				new EnemyFactory(this, Magican.class, 900, 100),
		};
		addEnemyFactory(efs);
	}

	@Override
	public void setItems() throws SlickException {
		addItem(new Coin(this, 100, 100));
		addItem(new Coin(this, 100, 200));
		addItem(new Coin(this, 100, 300));
		addItem(new Coin(this, 100, 400));
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
