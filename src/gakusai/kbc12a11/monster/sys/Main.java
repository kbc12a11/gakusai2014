package gakusai.kbc12a11.monster.sys;

import gakusai.kbc12a11.monster.sampleStage.SampleStage2;
import gakusai.kbc12a11.monster.st1.Stage_1;
import gakusai.kbc12a11.monster.sys.stage.BeforeStartStage;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	/* 1. Main クラスまたはオブジェクトに所属するメンバー変数の宣言を書く所 */
	public static final String GAMETITLE="The Rord of the Pencil";
	public static final int FPS = 60;
	/**スクリーンのサイズ*/
	public static final int W_WIDTH = 1366, W_HEIGHT = 768;//画面の解像度によって指定
	//public static final int W_WIDTH = 640, W_HEIGHT = 480;//画面の解像度によって指定
	public static boolean FLG_FULLSCREEN = false;
	private static final boolean FLG_VSYNC = true;
	public static final boolean FLG_ANTI_ALIAS = false;

	//
	//ステージIDの登録
	//他のステージと重複しないような数字を付けてください
	//例)1211001
	//
	//ここから
	public static final int Stage_TitleView = 0;
	public static final int Stage_BeforeStartStage = 1;
	public static final int Stage_Stage_1 = 2;
	public static final int Stage_TestStage = 3;
	/**サンプルのステージ*/
	public static final int Stage_SampleStage = 1211001;
	public static final int Stage_12a11_TestStage = 1211002;
	public static final int Stage_12a11_SampleStage2 = 1211003;
	///ステージIDの登録ここまで

	//Stage st;

	public Main(String title) {
		/* 2. コンストラクター */
		super(title);
	}

	@Override
	/**作ったステージをここで追加する*/
	public void initStatesList(GameContainer gc) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		//最初に追加されたステージが実行される
		addState(new SampleStage2());

		addState(new Stage_1());
		//addState(new TestStage());
		addState(new BeforeStartStage());

	}

//	@Override
//	public void init(GameContainer gc) throws SlickException {
//		st = new Stage_1();
//		Music openingMenuMusic = new Music("map/st1/st1.ogg");
//		openingMenuMusic.loop();
//	}
//
//	@SuppressWarnings("static-access")
//	@Override
//	public void update(GameContainer gc, int delta) throws SlickException {
//		st.update(gc, delta);
//	}
//	@Override
//	public void render(GameContainer gc, Graphics g) throws SlickException {
//		g.setAntiAlias(true);
//		st.render(gc, g);
//	}

	public static void main(String[] args) throws SlickException {
		/* 6. JVM 側がこの Main クラスを実体化するための、
		いわば着火メソッド。便宜上、このクラスに埋め込まれているだけで、
		ゲームプログラム本体とは基本的に関係がない部分 */
		AppGameContainer app = new AppGameContainer(new Main(GAMETITLE));
		app.setDisplayMode(W_WIDTH, W_HEIGHT, false);
		app.setTargetFrameRate(FPS);
		app.setVSync(FLG_VSYNC);
		app.setFullscreen(FLG_FULLSCREEN);
		app.setShowFPS(false);
		app.start();
	}

}