package gakusai.kbc12a11.monster.sys;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageBank {

	/**プレイヤーの画像1*/
	public static final int PLAYER_1;
	private static final String FILE_PLAYER_1 = "res/image/player/player.gif";

	/**プレイヤーの画像2*/
	public static final int PLAYER_2;
	private static final String FILE_PLAYER_2 = "res/image/player/player2.gif";

	/**プレイヤーがダメージを受けた時の画像*/
	public static final int PLAYER_DAMAGED;
	public static final String FILE_PLAYER_DAMAGED = "res/image/player/player_damage.gif";

	/**爆弾の画像1*/
	public static final int ENEMY_BOMB_1;
	private static final String FILE_ENEMY_BOMB_1 = "res/image/enemy/bomb/bomb1.gif";

	/**爆弾の画像2*/
	public static final int ENEMY_BOMB_2;
	private static final String FILE_ENEMY_BOMB_2 = "res/image/enemy/bomb/bomb2.gif";

	/**爆弾の画像3*/
	public static final int ENEMY_BOMB_3;
	private static final String FILE_ENEMY_BOMB_3 = "res/image/enemy/bomb/bomb3.gif";

	/**爆弾の画像3*/
	public static final int ENEMY_BOMB_4;
	private static final String FILE_ENEMY_BOMB_4 = "res/image/enemy/bomb/bomb4.gif";


	/**蟹の画像1*/
	public static final int ENEMY_CRAB_1;
	private static final String FILE_ENEMY_CRAB_1 = "res/image/enemy/crab/crab1.gif";

	/**どっすんの画像1*/
	public static final int ENEMY_DOSSUN_1;
	private static final String FILE_ENEMY_DOSSUN_1 = "res/image/enemy/dossun/dossun1.gif";

	/**ゴーストの画像1*/
	public static final int ENEMY_GHOST_1;
	private static final String FILE_ENEMY_GHOST_1 = "res/image/enemy/ghost/ghost1.gif";

	/**ゴーストの画像2*/
	public static final int ENEMY_GHOST_2;
	private static final String FILE_ENEMY_GHOST_2 = "res/image/enemy/ghost/ghost2.gif";

	/**魔法使いの画像1*/
	public static final int ENEMY_MAGICAN_1;
	private static final String FILE_ENEMY_MAGICAN_1 = "res/image/enemy/magican/mahou1.gif";

	/**魔法使いの画像2*/
	public static final int ENEMY_MAGICAN_2;
	private static final String FILE_ENEMY_MAGICAN_2 = "res/image/enemy/magican/mahou2.gif";

	/**ミサイルの画像1*/
	public static final int ENEMY_MISSILE_1;
	private static final String FILE_ENEMY_MISSILE_1 = "res/image/enemy/missile/missile1.gif";

	/**モルの画像1*/
	public static final int ENEMY_MOL_1;
	private static final String FILE_ENEMY_MOL_1 = "res/image/enemy/mol/mol1.gif";

	/**モルの画像2*/
	public static final int ENEMY_MOL_2;
	private static final String FILE_ENEMY_MOL_2 = "res/image/enemy/mol/mol2.gif";

	/**モルの画像3*/
	public static final int ENEMY_MOL_3;
	private static final String FILE_ENEMY_MOL_3 = "res/image/enemy/mol/mol3.gif";

	/**モルの画像4*/
	public static final int ENEMY_MOL_4;
	private static final String FILE_ENEMY_MOL_4 = "res/image/enemy/mol/mol4.gif";

	/**モルの画像5*/
	public static final int ENEMY_MOL_5;
	private static final String FILE_ENEMY_MOL_5 = "res/image/enemy/mol/mol5.gif";

	/**UFOの画像1*/
	public static final int ENEMY_UFO_1;
	private static final String FILE_ENEMY_UFO_1 = "res/image/enemy/UFO/UFO1.gif";

	/**UFOの画像2*/
	public static final int ENEMY_UFO_2;
	private static final String FILE_ENEMY_UFO_2 = "res/image/enemy/UFO/UFO2.gif";


	////////////////////////////////////////背景素材
	/**へのへのもへじ*/
	public static final int BG_HENOHENO;
	private static final String FILE_BG_HENOHENO = "res/image/background/henoheno.png";

	/**イメージの数*/
	private static int IMAGE_SIZE;

	static {
		IMAGE_SIZE = 0;

		PLAYER_1 = IMAGE_SIZE++;
		PLAYER_2 = IMAGE_SIZE++;
		PLAYER_DAMAGED = IMAGE_SIZE++;

		//enemy
		ENEMY_BOMB_1 = IMAGE_SIZE++;
		ENEMY_BOMB_2 = IMAGE_SIZE++;
		ENEMY_BOMB_3 = IMAGE_SIZE++;
		ENEMY_BOMB_4 = IMAGE_SIZE++;

		ENEMY_CRAB_1 = IMAGE_SIZE++;

		ENEMY_DOSSUN_1 = IMAGE_SIZE++;

		ENEMY_GHOST_1 = IMAGE_SIZE++;
		ENEMY_GHOST_2 = IMAGE_SIZE++;

		ENEMY_MAGICAN_1 = IMAGE_SIZE++;
		ENEMY_MAGICAN_2 = IMAGE_SIZE++;

		ENEMY_MISSILE_1 = IMAGE_SIZE++;

		ENEMY_MOL_1 = IMAGE_SIZE++;
		ENEMY_MOL_2 = IMAGE_SIZE++;
		ENEMY_MOL_3 = IMAGE_SIZE++;
		ENEMY_MOL_4 = IMAGE_SIZE++;
		ENEMY_MOL_5 = IMAGE_SIZE++;

		ENEMY_UFO_1 = IMAGE_SIZE++;
		ENEMY_UFO_2 = IMAGE_SIZE++;



		//background
		BG_HENOHENO = IMAGE_SIZE++;
	}

	private Image[] imageList;

	private static ImageBank instance = new ImageBank();

	private ImageBank() {
		imageList = new Image[IMAGE_SIZE];
		setImages();
	}

	/**インスタンスを取得する*/
	public static ImageBank getInstance() {
		return instance;
	}

	/**イメージを取得する*/
	public Image getImage(int id) {
		return imageList[id];
	}

	private void setImages() {
		String[] files = new String[IMAGE_SIZE];

		files[PLAYER_1] = FILE_PLAYER_1;
		files[PLAYER_2] = FILE_PLAYER_2;
		files[PLAYER_DAMAGED] = FILE_PLAYER_DAMAGED;

		//enemy
		files[ENEMY_BOMB_1] = FILE_ENEMY_BOMB_1;
		files[ENEMY_BOMB_2] = FILE_ENEMY_BOMB_2;
		files[ENEMY_BOMB_3] = FILE_ENEMY_BOMB_3;
		files[ENEMY_BOMB_4] = FILE_ENEMY_BOMB_4;

		files[ENEMY_CRAB_1] = FILE_ENEMY_CRAB_1;

		files[ENEMY_DOSSUN_1] = FILE_ENEMY_DOSSUN_1;

		files[ENEMY_GHOST_1] = FILE_ENEMY_GHOST_1;
		files[ENEMY_GHOST_2] = FILE_ENEMY_GHOST_2;

		files[ENEMY_MAGICAN_1] = FILE_ENEMY_MAGICAN_1;
		files[ENEMY_MAGICAN_2] = FILE_ENEMY_MAGICAN_2;

		files[ENEMY_MISSILE_1] = FILE_ENEMY_MISSILE_1;

		files[ENEMY_MOL_1] = FILE_ENEMY_MOL_1;
		files[ENEMY_MOL_2] = FILE_ENEMY_MOL_2;
		files[ENEMY_MOL_3] = FILE_ENEMY_MOL_3;
		files[ENEMY_MOL_4] = FILE_ENEMY_MOL_4;
		files[ENEMY_MOL_5] = FILE_ENEMY_MOL_5;

		files[ENEMY_UFO_1] = FILE_ENEMY_UFO_1;
		files[ENEMY_UFO_2] = FILE_ENEMY_UFO_2;

		//background
		files[BG_HENOHENO] = FILE_BG_HENOHENO;

		for (int i = 0; i < IMAGE_SIZE; i++) {
			try {
				imageList[i] = new Image(files[i]);
			} catch (SlickException | RuntimeException e) {
				System.out.println("Image not found! : " + files[i]);
			}
		}
	}
}
