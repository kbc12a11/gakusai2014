package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**ＢＧＭ，ＳＥ管理クラス*/
public class BgmBank {

	/**bgm1(kjm氏お勧め)*/
	public static final int BGM_STAGE_1;
	private static final String FILE_STAGE_1 = "res/bgm/bgmStage1.ogg";

	/**ステージセレクト(kjm氏お勧め)*/
	public static final int BGM_STAGESELECT;
	private static final String FILE_STAGESELECT = "res/bgm/stageSelect.ogg";

	public static final int BGM_STAGE_2;
	private static final String FILE_STAGE_2 = "res/bgm/NormalBGM.ogg";

	public static final int BGM_STAGE_3;
	private static final String FILE_STAGE_3 = "res/bgm/HardBGM.ogg";

	/**効果音の数*/
	private static int SISE;
	//IDの設定
	static {
		SISE = 0;
		BGM_STAGE_1 = SISE++;
		BGM_STAGESELECT = SISE++;
		BGM_STAGE_2 = SISE++;
		BGM_STAGE_3 = SISE++;
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
		files[BGM_STAGESELECT] = FILE_STAGESELECT;
		files[BGM_STAGE_2] = FILE_STAGE_2;
		files[BGM_STAGE_3] = FILE_STAGE_3;

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

			if (bb.playFlag[i] && !bb.bgmList[i].playing()) {
				System.out.println("BGM id " + i + " start.");
				if (bb.loopFlag[i]) {
					bb.bgmList[i].loop(bb.pitch[i], bb.volume[i]);
				}else {
					bb.bgmList[i].play(bb.pitch[i], bb.volume[i]);
				}
			}else if (!bb.playFlag[i] && bb.bgmList[i].playing()) {
				bb.bgmList[i].stop();
			}

//			if (bb.playFlag[i]) {
//				if (bb.bgmList[i] != null) {
//					boolean flg = bb.loopFlag[i];
//					stopAllBGM();
//
//					if (flg)
//						bb.bgmList[i].loop(bb.pitch[i], bb.volume[i]);
//					else
//						bb.bgmList[i].play(bb.pitch[i], bb.volume[i]);
//
//					System.out.println("BGM id " + i + " start.");
//					bb.pitch[i] = DEFAULT_PITCH;
//					bb.volume[i] = DEFAULT_VALUME;
//				}else {
//					System.out.println("Failed to play BGM ID " + i + ".");
//				}
//				bb.playFlag[i] = false;
//			}
		}
	}

	/**デフォルトのピッチとボリュームでBGMの再生をリクエストする*/
	public static void bgmRequest(int id, boolean loopFlag) {
		bgmRequest(id, loopFlag, DEFAULT_PITCH, DEFAULT_VALUME);
	}

	/**ピッチとボリュームを指定してBGMの再生をリクエストする
	 *
	 * @param id
	 * @param pitch よくわからない
	 * @param volume 0~1の範囲のフロート
	 */
	public static void bgmRequest(int id, boolean loopFlag, float pitch, float volume) {
		for (int i = 0; i < SISE; i++) {
			bb.playFlag[i] = false;
		}
		bb.playFlag[id] = true;
		bb.pitch[id] = pitch;
		bb.volume[id] = volume;
		bb.loopFlag[id] = loopFlag;
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
