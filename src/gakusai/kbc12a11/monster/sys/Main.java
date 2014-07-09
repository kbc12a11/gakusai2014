package gakusai.kbc12a11.monster.sys;

import gakusai.kbc12a10.monster.stage.HinoStage;
import gakusai.kbc12a11.monster.sampleStage.SampleStage2;
import gakusai.kbc12a11.monster.st1.Stage_1;
import gakusai.kbc12a11.monster.stage.mario.MarioStage;
import gakusai.kbc12a11.monster.sys.stage.BeforeStartStage;
import gakusai.kbc12a11.monster.sys.stage.StageSelectView;
import gakusai.kbc12a11.monster.sys.stage.TitleView;
import gakusai.kbc12a11.monster.sys.wiimote.WiimoteTest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	/* 1. Main クラスまたはオブジェクトに所属するメンバー変数の宣言を書く所 */
	public static final String GAMETITLE="The Rord of the Pencil";
	public static final int FPS = 60;
	/**スクリーンのサイズ*/
	//public static final int W_WIDTH = 1366, W_HEIGHT = 768;//画面の解像度によって指定
	public static final int W_WIDTH = 640, W_HEIGHT = 480;//画面の解像度によって指定
	public static boolean FLG_FULLSCREEN = false;
	private static final boolean FLG_VSYNC = true;
	public static final boolean FLG_ANTI_ALIAS = false;

	public static boolean FLG_MOUSEHIDE = true;

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
	public static final int Stage_StageSelectView = 4;
	/**サンプルのステージ*/
	public static final int Stage_SampleStage = 1211001;
	public static final int Stage_12a11_TestStage = 1211002;
	public static final int Stage_12a11_SampleStage2 = 1211003;
	public static final int Stage_12a11_MarioStage = 1211004;

	public static final int Stage_12a10_HinoStage = 1210001;
	///ステージIDの登録ここまで

	private static WiimoteTest wiimoteListener;

	public Main(String title) {
		/* 2. コンストラクター */
		super(title);
	}

	public static void setWiimoteRistener() {
		wiimoteListener = new WiimoteTest();
		if (!wiimoteListener.isConnected()) {
			FLG_MOUSEHIDE = true;
		}
	}

	public static WiimoteTest getWiimoteRistener() {
		return wiimoteListener;
	}

	@Override
	/**作ったステージをここで追加する*/
	public void initStatesList(GameContainer gc) throws SlickException {
		//最初に追加されたステージが実行される
		addState(new TitleView());
		addState(new StageSelectView());
		addState(new HinoStage());
		addState(new MarioStage());
		addState(new SampleStage2());

		addState(new Stage_1());
		//addState(new TestStage());
		addState(new BeforeStartStage());

	}

	public static void main(String[] args) throws SlickException {
		Main main = new Main(GAMETITLE);
		setWiimoteRistener();

		AppGameContainer app = new AppGameContainer(main);
		app.setDisplayMode(W_WIDTH, W_HEIGHT, false);
		app.setTargetFrameRate(FPS);
		app.setVSync(FLG_VSYNC);
		app.setFullscreen(FLG_FULLSCREEN);
		app.setShowFPS(false);
		app.setMouseGrabbed(FLG_MOUSEHIDE);
		app.start();

	}

}