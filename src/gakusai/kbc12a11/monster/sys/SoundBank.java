package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**ＢＧＭ，ＳＥ管理クラス*/
public class SoundBank {
	//曲のIDを登録
	/**爆発音*/
	public static final int SE_BOMB;
	private static final String FILE_BOMB = "res/se/bomb.wav";

	/**猫の鳴き声*/
	public static final int SE_CAT;
	private static final String FILE_CAT = "res/se/Cat.wav";
	/**エンダアアアアアアアアアアア*/
	public static final int SE_ENDAAAAAAAAAAAAAAAA;
	private static final String FILE_ENDAAAAAAAAAAAAAAAA = "res/se/Endaaaaaa.wav";
	/**線を引く音*/
	public static final int SE_LINE;
	private static final String FILE_LINE = "res/se/ta_ta_warekie02.wav";

	/**お化けの登場音*/
	public static final int SE_OBAKE;
	private static final String FILE_OBAKE = "res/se/ta_ta_obake01.wav";

	/**魔法発射音*/
	public static final int SE_MAGIC;
	private static final String FILE_MAGIC = "res/se/ta_ta_maho12.wav";

	public static final int SE_GET_COIN;
	private static final String FILE_GET_COIN = "res/se/crrect_answer3.wav";

	/**爆発音*/
	public static final int SE_BURST;
	private static final String FILE_BURST = "res/se/burst.wav";

	/**爆発音*/
	public static final int SE_CRUSH;
	private static final String FILE_CRUSH = "res/se/crush.wav";

	/**爆発音*/
	public static final int SE_UFO;
	private static final String FILE_UFO = "res/se/ufo.wav";

	/**爆発音*/
	public static final int SE_MISSILE;
	private static final String FILE_MISSILE = "res/se/missile.wav";

	/**爆発音*/
	public static final int SE_GHOST;
	private static final String FILE_GHOST = "res/se/ghost.wav";

	/**爆発音*/
	public static final int SE_MOL;
	private static final String FILE_MOL = "res/se/mol.wav";

	public static final int SE_CUREBOX;
	private static final String FILE_CUREBOX = "rex/se/curebox.wav";

	/**効果音の数*/
	private static int SE_SIZE;
	//IDの設定
	static {
		SE_SIZE = 0;
		SE_BOMB = SE_SIZE++;
		SE_CAT = SE_SIZE++;
		SE_ENDAAAAAAAAAAAAAAAA = SE_SIZE++;
		SE_LINE = SE_SIZE ++;
		SE_OBAKE = SE_SIZE++;
		SE_MAGIC = SE_SIZE++;
		SE_GET_COIN = SE_SIZE++;
		SE_BURST = SE_SIZE++;
		SE_CRUSH = SE_SIZE++;
		SE_UFO = SE_SIZE++;
		SE_MISSILE = SE_SIZE++;
		SE_GHOST = SE_SIZE++;
		SE_MOL = SE_SIZE++;
		SE_CUREBOX = SE_SIZE++;
	}

	private Sound[] soundList;
	private boolean[] playFlag;
	private int baseWaitTime = 4;//待ち時間
	private int[] waitTime;
	private float[] pitch;//ピッチ
	private float[] volume;//ボリューム
	/**デフォルトのピッチ*/
	public static float DEFAULT_PITCH = 1;
	/**デフォルトのボリューム*/
	public static float DEFAULT_VALUME = 1;

	//効果音の設定
	private void setSE(){
		System.out.println("SE loading...");
		String[] files = new String[SE_SIZE];
		files[SE_BOMB] = FILE_BOMB;
		files[SE_CAT] = FILE_CAT;
		files[SE_ENDAAAAAAAAAAAAAAAA] = FILE_ENDAAAAAAAAAAAAAAAA;
		files[SE_LINE] = FILE_LINE;
		files[SE_OBAKE] = FILE_OBAKE;
		files[SE_MAGIC] = FILE_MAGIC;
		files[SE_GET_COIN] = FILE_GET_COIN;
		files[SE_BURST] = FILE_BURST;
		files[SE_CRUSH] = FILE_CRUSH;
		files[SE_UFO] = FILE_UFO;
		files[SE_MISSILE] = FILE_MISSILE;
		files[SE_GHOST] = FILE_GHOST;
		files[SE_MOL] = FILE_MOL;
		files[SE_CUREBOX] = FILE_CUREBOX;

		for (int i = 0; i < SE_SIZE; i++) {
			pitch[i] = DEFAULT_PITCH;
			volume[i] = DEFAULT_VALUME;
			try {
				soundList[i] = new Sound(files[i]);
			} catch (SlickException | RuntimeException e) {
				System.out.println("Failed to load \"" + files[i]+"\"");
			}
		}
		System.out.println("SE load finish.");
	}

	//シングルトン
	private static SoundBank sb = new SoundBank();
	private SoundBank(){//コンストラクタ
		soundList = new Sound[SE_SIZE];
		playFlag = new boolean[SE_SIZE];
		waitTime = new int[SE_SIZE];
		pitch = new float[SE_SIZE];
		volume = new float[SE_SIZE];
		setSE();
	}

	public static SoundBank getSoundBank() {
		return sb;
	}


	//更新処理
	public static void update() {
		for (int i = 0; i < sb.soundList.length; i++) {

			sb.waitTime[i]--;
			if (sb.waitTime[i] < -1) {
				sb.waitTime[i] = -1;
			}else {
				sb.playFlag[i] = false;
			}

			if (sb.playFlag[i]) {
				if (sb.soundList[i] != null) {
//					if (sb.soundList[i].playing()) {
//						sb.soundList[i].stop();
//					}
					sb.soundList[i].play(sb.pitch[i], sb.volume[i]);
					sb.waitTime[i] = sb.baseWaitTime;
					sb.pitch[i] = DEFAULT_PITCH;
					sb.volume[i] = DEFAULT_VALUME;
					//System.out.println("Sound SE ID " + i);
				}else {
					System.out.println("Failed to sound SE ID " + i + ".");
				}
				sb.playFlag[i] = false;
			}
		}
	}

	/**デフォルトのピッチとボリュームでサウンドの再生をリクエストする*/
	public static void soundRequest(int id) {
		soundRequest(id, DEFAULT_PITCH, DEFAULT_VALUME);
	}

	/**ピッチとボリュームを指定してサウンドの再生をリクエストする
	 *
	 * @param id
	 * @param pitch よくわからない
	 * @param volume 0~1の範囲のフロート
	 */
	public static void soundRequest(int id, float pitch, float volume) {
		if (!sb.playFlag[id]){
			sb.playFlag[id] = true;
			sb.pitch[id] = pitch;
			sb.volume[id] = volume;
		}else {
			sb.volume[id] = sb.volume[id] < volume ? volume : sb.volume[id];
		}
	}


	/**再生中のすべてのサウンドを停止する*/
	public static void stopAllSound() {
		for (int i = 0; i < SE_SIZE; i++) {
			if (sb.soundList[i] != null &&
					sb.soundList[i].playing()) {
				sb.soundList[i].stop();
				sb.playFlag[i] = false;
			}
		}
	}
}
