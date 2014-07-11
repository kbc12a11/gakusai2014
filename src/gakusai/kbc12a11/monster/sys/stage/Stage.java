package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a06.monster.sys.window.StockWindow;
import gakusai.kbc12a06.monster.sys.window.TimeWindow;
import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a08.monster.enemy.EnemyFactory;
import gakusai.kbc12a11.monster.abst.StatusWindow;
import gakusai.kbc12a11.monster.item.Item;
import gakusai.kbc12a11.monster.sys.BgmBank;
import gakusai.kbc12a11.monster.sys.Camera;
import gakusai.kbc12a11.monster.sys.GameInput;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Main;
import gakusai.kbc12a11.monster.sys.Map;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.StageBackground;
import gakusai.kbc12a11.monster.sys.StopWatch;
import gakusai.kbc12a11.monster.sys.decorate.BackgroundObject;
import gakusai.kbc12a11.monster.sys.eraser.Eraser;
import gakusai.kbc12a11.monster.sys.line.LineGroup;
import gakusai.kbc12a11.monster.sys.player.Player;
import gakusai.kbc12a11.monster.sys.player.PlayerAttackBit;
import gakusai.kbc12a11.monster.sys.window.LifeLine;
import gakusai.kbc12a11.monster.sys.window.LineEnergyWindow;
import gakusai.kbc12a11.monster.sys.window.ScoreWindow;
import gakusai.kbc12a11.monster.util.Util;
import gakusai.kbc12a11.monster.util.XmlParse;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public abstract class Stage extends BasicGameState{

	/**ステージにかかっている重力*/
	public static Vector2f GRAVITY = new Vector2f(0, 0.1f);

	/**プレイヤーのリスポーン地点*/
	protected Vector2f playerRespawnPoint;

	protected Map map;
	protected Camera camera;
	//ウインドウ
	protected StatusWindow leWindow;
	protected LifeLine lifeWindow;
	protected ScoreWindow scoreWindow;
	protected TimeWindow timeWindow;
	protected StockWindow stockWindow;

	protected Eraser eraser;//消しゴム
	protected SoundBank soundBank;//効果音
	protected ImageBank imageBank;//画像セット

	protected Player player;
	protected LineGroup lg;
	protected ObjectGroup enemyGroup;//敵
	protected ObjectGroup itemGroup;//アイテム
	protected ObjectGroup backgroundObjectGroup;//背景オブジェクト
	/**固定位置に敵を生成する*/
	protected ArrayList<EnemyFactory> enemyFactorys;
	protected GameInput gameInput;//入力

	protected StageBackground bg;

	/**ステージの状態*/
	protected int stageState;
	/**通常状態*/
	public static final int STATE_NORMAL = 1;
	/**ゲームをクリアした状態*/
	public static final int STATE_CLEAR = 2;
	/**ポーズ*/
	public static final int STATE_PAUSE = 3;
	/**タイムアップ*/
	public static final int STATE_TIMEUP = 4;
	/**ゲームオーバー*/
	public static final int STATE_GAMEOVER = 5;
	/**プレイヤーの死亡*/
	public static final int STATE_PLAYER_DEAD = 6;
	/**違うステージから遷移*/
	public static final int STATE_IN_STAGE = 7;

	/**ステージの特殊状態が継続する時間*/
	private int timer;
	private final int stCntClear = 120;
	private final int stCntTimeup = 120;

	///////////////////////
	//パラメータ
	/**スコア*/
	protected int score;
	protected StopWatch stopWatch;
	/**ステージの制限時間(デフォルト120秒)*/
	protected int stageTime = 120;
	///////////////////////

	///ステージの遷移
	protected int nextStage = Main.Stage_StageClearView;


	public Stage (String mapName) throws SlickException {
		soundBank = SoundBank.getSoundBank();
		imageBank = ImageBank.getInstance();

		map = XmlParse.getMap(mapName);
		reset();
	}

	/**初期化*/
	public void reset() throws SlickException {
		player = new Player(this);
		playerRespawnPoint = new Vector2f(20, 20);//リスポーン地点の設定
		lg = new LineGroup(this);
		enemyGroup = new ObjectGroup(this);
		enemyFactorys = new ArrayList<EnemyFactory>();
		itemGroup = new ObjectGroup(this);
		backgroundObjectGroup = new ObjectGroup(this);

		camera = new Camera(map.getMapWidth(), map.getMapHeight());
		camera.setFocus(player);
		map.setCamera(camera);

		eraser = new Eraser(this);
		stopWatch = new StopWatch();

		leWindow = new LineEnergyWindow(lg, null, null);
		lifeWindow = new LifeLine(player);
		scoreWindow = new ScoreWindow(this);
		timeWindow = new TimeWindow(this, stageTime);
		stockWindow = new StockWindow(player);

		gameInput = new GameInput();
	}

	/**初期化*/
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		player.respawn(playerRespawnPoint);
		//カメラの初期化
		camera.setFocus(player);
		camera.update();

		lg.getLines().clear();
		gc.getGraphics().setBackground(Color.white);
		BgmBank.stopAllBGM();
		SoundBank.stopAllSound();
		enemyGroup.clear();
		enemyFactorys.clear();//エネミーファクトリーのクリア
		itemGroup.clear();//アイテムグループのクリア
		backgroundObjectGroup.clear();
		setEnemys();
		setItems();
		setBackgroundObject();
		map.setEnemysAndItems(this);
		map.resetMapData();
		//エネミーファクトリーの初期化
		for (EnemyFactory ef : enemyFactorys) {
			ef.init();
		}

		score = 0;//得点の初期化
		setStageState(STATE_NORMAL);
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		setStageState(STATE_PAUSE);
	}

	//更新
	@Override
	public final void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		gameInput = Util.getGameInput(gameInput, gc);

		if (bg != null) {
			bg.update(gc, sbg, delta);//背景
		}
		eraser.update(gc, sbg, delta);
		map.update(gc, sbg, delta);

		lg.update(gc, sbg, delta, camera);//ライン

		for (EnemyFactory ef : enemyFactorys) {
			ef.update();
		}

		stUpdate(gc, sbg, delta);//各ステージ
		enemyGroup.update(gc, sbg, delta);//敵
		itemGroup.update(gc, sbg, delta);//アイテム
		backgroundObjectGroup.update(gc, sbg, delta);

		player.update(gc, sbg, delta);//プレイヤー
		camera.update();
		//敵とのあたり判定
		if (player.getState() == Player.STATE_NORMAL ||
				player.getState() == Player.STATE_INVISIBLE) {
			enemyGroup.hitCheck(player);
		}
		//アイテムとのあたり判定
		itemGroup.hitCheck(player);
		//プレイヤーの弾と敵とのあたり判定
		ObjectGroup patk = player.getAttackBits();
		for (int i = 0; i < patk.objects.size(); i++) {
			PlayerAttackBit b = (PlayerAttackBit)patk.objects.get(i);
			enemyGroup.hitCheck(b);
		}


		leWindow.update(gc, delta);//線の残量ウィンドウ
		lifeWindow.update(gc, delta);//体力ウインドウ
		scoreWindow.update(gc, delta);//スコア
		timeWindow.update(gc, delta);//タイム
		stockWindow.update(gc, delta);//残機

		BgmBank.update();
		SoundBank.update();//効果音
		stageStateCheck(sbg);
	}

	/**ステージのステータスの更新
	 * @param sbg TODO*/
	private void stageStateCheck(StateBasedGame sbg) {
		timer--;
		GameState gbs;
		switch (stageState) {
		case STATE_NORMAL:
			if (timer < 0) timer = 0;
			if (timeWindow.getTime() < 0) {
				setStageState(STATE_TIMEUP);
			}
			if (!player.isLive()) {
				if (player.getStock() < 0) {
					setStageState(STATE_GAMEOVER);
				}else {
					setStageState(STATE_PLAYER_DEAD);
				}
			}
			if (player.getState() == Player.STATE_CLEAR) {
				setStageState(STATE_CLEAR);
			}
			break;
		case STATE_CLEAR:
			if (timer < 0) {
				if (nextStage == Main.Stage_StageClearView) {
					gbs = sbg.getState(Main.Stage_StageClearView);
					((StageClearView)gbs).set(timeWindow.getTime(), score, 0);
					sbg.enterState(gbs.getID(), new FadeOutTransition(Color.black, 120), new FadeInTransition(Color.black, 120) );
				}else {

					gbs = sbg.getState(nextStage);
					sbg.enterState(gbs.getID(), new FadeOutTransition(Color.black, 120), new FadeInTransition(Color.black, 120) );
				}
			}
			break;
		case STATE_TIMEUP:
			if (timer < 0) {
				setStageState(STATE_GAMEOVER);
			}
			break;
		case STATE_GAMEOVER:
			sbg.enterState(Main.Stage_GameOverView, new FadeOutTransition(Color.black, 120), new FadeInTransition(Color.black, 120) );
			break;
		case STATE_PLAYER_DEAD:
			gbs = sbg.getState(Main.Stage_BeforeStartStage);
			((BeforeStartStage)gbs).set(player.getStock(), this.getID(), getStageState());
			sbg.enterState(gbs.getID(), new FadeOutTransition(Color.black, 120), new FadeInTransition(Color.black, 120) );
			break;
		}
	}

	//描画
	@Override
	public final void render(GameContainer gc, StateBasedGame sbg, Graphics g){
		g.setAntiAlias(Main.FLG_ANTI_ALIAS);
		if (bg != null) {
			bg.render(gc, sbg, g);
		}
		g.translate(camera.getTranslateX(), camera.getTranslateY());
		backgroundObjectGroup.render(gc, sbg, g);
		g.resetTransform();

		eraser.render(gc, sbg, g);


		g.translate(camera.getTranslateX(), camera.getTranslateY());
		map.render(gc, sbg, g);
		stRender(gc, sbg, g);
		lg.render(gc, sbg, g);
		itemGroup.render(gc, sbg, g);
		enemyGroup.render(gc, sbg, g);
		player.render(gc, sbg, g);
		g.resetTransform();

		Util.drawPointer(g, gameInput, gc);

		leWindow.render(gc, g);
		lifeWindow.render(gc, g);
		scoreWindow.render(gc, g);
		timeWindow.render(gc, g);
		stockWindow.render(gc, g);

		if (stageState == STATE_TIMEUP) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Main.W_WIDTH, Main.W_HEIGHT);
			Image img = ImageBank.getImage(ImageBank.WD_TIMEUP);
			float w = img.getWidth(), h = img.getHeight();
			g.drawImage(img, Main.W_WIDTH/2 - w/2, Main.W_HEIGHT/2 - h/2);
		}
	}


	/**ステージごとに固有の更新処理
	 * @param sbg TODO*/
	public abstract void stUpdate(GameContainer gc, StateBasedGame sbg, int delta);
	/**ステージごとに固有の描画処理
	 * @param sbg TODO*/
	public abstract void stRender(GameContainer gc, StateBasedGame sbg, Graphics g);

	public Map getMap() {
		return map;
	}
	public LineGroup getLineGroup() {
		return lg;
	}

	/////
	//エネミーグループに関連するメソッドここから
	public ObjectGroup getEnemyGroup() {
		return enemyGroup;
	}
	/**ステージに敵を追加する*/
	public void addEnemy(Enemy enemy) {
		enemyGroup.add(enemy);
	}
	/**ステージに敵を追加する*/
	public void addEnemy(Enemy[] enemys) {
		enemyGroup.add(enemys);
	}
	/**ステージ上の敵の数を返す*/
	public int getEnemyNums() {
		return enemyGroup.getSize();
	}

	/**固定位置に出現する敵を追加する*/
	public void addEnemyFactory(EnemyFactory ef) {
		enemyFactorys.add(ef);
	}

	public void addEnemyFactory(EnemyFactory[] efs) {
		for (EnemyFactory ef : efs) {
			addEnemyFactory(ef);
		}
	}

	/**ステージ上の敵をセットする*/
	public abstract void setEnemys() throws SlickException;
	//エネミーグループに関連するメソッドここまで
	/////
	//アイテムグループに関連するメソッド
	public ObjectGroup getItemGroup() {
		return itemGroup;
	}

	public void addItem(Item item) {
		itemGroup.add(item);
	}
	public void addItem(Item[] items) {
		itemGroup.add(items);
	}

	/**アイテムの数*/
	public int getItemNums() {
		return itemGroup.getSize();
	}
	/**ステージ上のアイテムをセットする*/
	public abstract void setItems() throws SlickException;
	////アイテムグループに関するメソッドここまで

	//背景オブジェクトに関連するメソッド
	public ObjectGroup getBackgroundObjectGroup() {
		return backgroundObjectGroup;
	}

	public void addBackgroundObject(BackgroundObject bgobj) {
		backgroundObjectGroup.add(bgobj);
	}
	public void addBackgroundObject(BackgroundObject[] bgobj) {
		backgroundObjectGroup.add(bgobj);
	}

	/**背景オブジェクトの数*/
	public int getBackgroundObjectsNums() {
		return backgroundObjectGroup.getSize();
	}
	/**背景オブジェクトをセットする*/
	public void setBackgroundObject() throws SlickException{}
	////背景オブジェクトに関するメソッドここまで

	/**得点を取得*/
	public int getScore() {
		return score;
	}

	/**得点を加算*/
	public void addScore(int score) {
		this.score += score;
	}

	/**プレイヤーの座標を取得*/
	public Vector2f getPlayerPos() {
		return player.getP().copy();
	}

	/**ゲームの状態を設定する*/
	public void setStageState(int stageState) {
		this.stageState = stageState;

		switch(stageState) {
		case STATE_CLEAR:
			if (nextStage == Main.Stage_StageClearView) {
				SoundBank.soundRequest(SoundBank.SE_FANFARE, 1.0f, 1);
			}
			this.timer = stCntClear;
			stopWatch.pause();
			break;
		case STATE_PAUSE:
			this.stopWatch.pause();
			break;
		case STATE_TIMEUP:
			SoundBank.soundRequest(SoundBank.SE_GONG);
			this.timer = stCntTimeup;
			break;
		case STATE_GAMEOVER:
			break;
		case STATE_PLAYER_DEAD:
			break;
		case STATE_NORMAL:
			stopWatch.start();
			break;
		}
	}



	/**ステージのステータスを取得*/
	public int getStageState() {
		return stageState;
	}

	/**カメラの取得*/
	public Camera getCamera() {
		return camera;
	}

	/**デフォルトのピッチとボリュームでサウンドの再生をリクエストする*/
	public void soundRequest(int id) {
		soundBank.soundRequest(id);
	}
	/**ピッチとボリュームを指定してサウンドの再生をリクエストする*/
	public void soundRequest(int id, float pitch, float volume) {
		soundBank.soundRequest(id, pitch, volume);
	}

	/**イメージバンクから画像を取得する*/
	public Image getImage(int id) {
		return imageBank.getImage(id);
	}

	//背景の設定
	public void setBackGroundImage(Image backGroundImage) throws SlickException {
		if (bg == null) bg = new StageBackground();
		bg.setBackGroundImage(backGroundImage);
	}
	public void setBackGroundImage(String imageName) throws SlickException {
		setBackGroundImage(new Image(imageName));
	}
	public void setBackground(StageBackground background) {
		this.bg = background;
	}

	public GameInput getGameInput() {
		return gameInput;
	}

	@Override
	public String toString() {
		return "Stage " + getID();
	}

	/**プレイヤーの残機を取得する*/
	public int getPlayerStock() {
		return player.getStock();
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}
}
