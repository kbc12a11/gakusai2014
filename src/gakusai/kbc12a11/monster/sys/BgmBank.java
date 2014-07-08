package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**ＢＧＭ，ＳＥ管理クラス*/
public class BgmBank {

	/**bgm1(kjm氏お勧め)*/
	public static final int BGM_STAGE_1;
	private static final String FILE_STAGE_1 = "res/bgm/bgmStage1.ogg";


	/**効果音の数*/
	private static int SISE;
	//IDの設定
	static {
		SISE = 0;
		BGM_STAGE_1 = SISE++;
	}

	private Music[] bgmList;
	private boolean[] playFlag;
	private boolean[] loopFlag;
	private float[] pitch;//ピッチ
	private float[] volume;//ボリューム
	/**デフォルトのピッチ*/
	public static float DEFAULT_PITCH = 1;
	/**デフォルトのボリューム*/
	public static float DEFAULT_VALUME = 1;

	//効果音の設定
	private void setBGM(){
		System.out.println("BGM loading...");
		String[] files = new String[SISE];

		files[BGM_STAGE_1] = FILE_STAGE_1;

		for (int i = 0; i < SISE; i++) {
			pitch[i] = DEFAULT_PITCH;
			volume[i] = DEFAULT_VALUME;
			try {
				bgmList[i] = new Music(files[i]);
			} catch (SlickException | RuntimeException e) {
				System.out.println("Failed to load \"" + files[i]+"\"");
			}
		}
		System.out.println("BGM load finish.");
	}

	//シングルトン
	private static BgmBank bb = new BgmBank();
	private BgmBank(){//コンストラクタ
		bgmList = new Music[SISE];
		playFlag = new boolean[SISE];
		pitch = new float[SISE];
		volume = new float[SISE];
		loopFlag = new boolean[SISE];
		setBGM();
	}

	//更新処理
	public static void update() {
		for (int i = 0; i < bb.bgmList.length; i++) {

			if (bb.playFlag[i]) {
				if (bb.bgmList[i] != null) {
					boolean flg = bb.loopFlag[i];
					stopAllBGM();

					if (flg)
						bb.bgmList[i].loop(bb.pitch[i], bb.volume[i]);
					else
						bb.bgmList[i].play(bb.pitch[i], bb.volume[i]);

					System.out.println("BGM id " + i + " start.");
					bb.pitch[i] = DEFAULT_PITCH;
					bb.volume[i] = DEFAULT_VALUME;
				}else {
					System.out.println("Failed to play BGM ID " + i + ".");
				}
				bb.playFlag[i] = false;
			}
		}
	}

	/**デフォルトのピッチとボリュームでサウンドの再生をリクエストする*/
	public static void bgmRequest(int id, boolean loopFlag) {
		bgmRequest(id, loopFlag, DEFAULT_PITCH, DEFAULT_VALUME);
	}

	/**ピッチとボリュームを指定してサウンドの再生をリクエストする
	 *
	 * @param id
	 * @param pitch よくわからない
	 * @param volume 0~1の範囲のフロート
	 */
	public static void bgmRequest(int id, boolean loopFlag, float pitch, float volume) {
		if (!bb.playFlag[id]){
			bb.playFlag[id] = true;
			bb.pitch[id] = pitch;
			bb.volume[id] = volume;
			bb.loopFlag[id] = loopFlag;
		}else {
			bb.volume[id] = bb.volume[id] < volume ? volume : bb.volume[id];
		}
	}


	/**再生中のすべてのBGMを停止する*/
	public static void stopAllBGM() {
		for (int i = 0; i < SISE; i++) {
			stop(i);
		}
	}

	/**BGMを停止する*/
	public static void stop(int id) {
		if (id < 0 || SISE <= id) return;

		if (bb.bgmList[id] != null && bb.bgmList[id].playing()) {
			bb.bgmList[id].stop();
			bb.playFlag[id] = false;
			bb.loopFlag[id] = false;
		}
	}
}
