package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**ＢＧＭ，ＳＥ管理クラス*/
public class SoundBank {
	//曲のIDを登録
	/**プレイヤーが死亡した時の効果音*/
	public static final int SE_PLAYER_DEAD;
	private static final String FILE_PLAYER_DEAD = "res/se/thiunthiun.wma";
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
	private static final String FILE_LINE = "res/se/line.wav";

	/**効果音の数*/
	private static int SE_SIZE;
	//IDの設定
	static {
		SE_SIZE = 0;
		SE_PLAYER_DEAD = SE_SIZE++;
		SE_BOMB = SE_SIZE++;
		SE_CAT = SE_SIZE++;
		SE_ENDAAAAAAAAAAAAAAAA = SE_SIZE++;
		SE_LINE = SE_SIZE ++;
	}

	private Sound[] soundList;
	private boolean[] playFlag;
	private int baseWaitTime = 0;
	private int[] waitTime;
	private float[] pitch;//ピッチ
	private float[] volume;//ボリューム
	/**デフォルトのピッチ*/
	public static float DEFAULT_PITCH = 1;
	/**デフォルトのボリューム*/
	public static float DEFAULT_VALUME = 1;

	//効果音の設定
	private void setSE(){
		String[] files = new String[SE_SIZE];
		files[SE_PLAYER_DEAD] = FILE_PLAYER_DEAD;
		files[SE_BOMB] = FILE_BOMB;
		files[SE_CAT] = FILE_CAT;
		files[SE_ENDAAAAAAAAAAAAAAAA] = FILE_ENDAAAAAAAAAAAAAAAA;
		files[SE_LINE] = FILE_LINE;

		for (int i = 0; i < SE_SIZE; i++) {
			pitch[i] = DEFAULT_PITCH;
			volume[i] = DEFAULT_VALUME;
			try {
				soundList[i] = new Sound(files[i]);
			} catch (SlickException | RuntimeException e) {
				System.out.println("Failed to load \"" + files[i]+"\"");
			}
		}
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
	public void update() {
		for (int i = 0; i < soundList.length; i++) {

			waitTime[i]--;
			if (waitTime[i] < -1) {
				waitTime[i] = -1;
			}else {
				playFlag[i] = false;
			}

			if (playFlag[i]) {
				if (soundList[i] != null) {
					//					if (soundList[i].playing()) {
					//						soundList[i].stop();
					//					}
					soundList[i].play(pitch[i], volume[i]);
					waitTime[i] = baseWaitTime;
					pitch[i] = DEFAULT_PITCH;
					volume[i] = DEFAULT_VALUME;
				}else {
					System.out.println("Failed to sound ID " + i + ".");
				}
				playFlag[i] = false;
			}
		}
	}

	/**デフォルトのピッチとボリュームでサウンドの再生をリクエストする*/
	public void soundRequest(int id) {
		soundRequest(id, DEFAULT_PITCH, DEFAULT_VALUME);
	}

	/**ピッチとボリュームを指定してサウンドの再生をリクエストする
	 *
	 * @param id
	 * @param pitch よくわからない
	 * @param volume 0~1の範囲のフロート
	 */
	public void soundRequest(int id, float pitch, float volume) {
		if (!playFlag[id]){
			playFlag[id] = true;
			this.pitch[id] = pitch;
			this.volume[id] = volume;
		}else {
			this.volume[id] = this.volume[id] < volume ? volume : this.volume[id];
		}
	}

	/**再生中のすべてのサウンドを停止する*/
	public void stopAllSound() {
		for (int i = 0; i < SE_SIZE; i++) {
			if (soundList[i] != null &&
					soundList[i].playing()) {
				soundList[i].stop();
			}
		}
	}
}
