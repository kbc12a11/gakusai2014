package gakusai.kbc12a11.monster.sys;

import gakusai.kbc12a08.monster.enemy.EnemyFactory;
import gakusai.kbc12a11.monster.enemy.Magican;
import gakusai.kbc12a11.monster.item.Coin;
import gakusai.kbc12a11.monster.sys.block.Block;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Map {

	/**オリジナルのマップデータ*/
	private int[][] originalMapData;
	private int[][] mapData;
	/**元画像から切り出すマップチップのサイズ*/
	public Vector2f chipSize = new Vector2f();
	/**マップチップのスクリーン上のサイズ*/
	public Vector2f chipSizeOnScreen = new Vector2f();

	/**タイルを張る枚数*/
	private Vector2f chipNum = new Vector2f();
	private Image[] splites;

	Camera camera;

	/**
	 *
	 * @param mapData マップの配列
	 * @param chipNumX 配列のX方向のサイズ
	 * @param chipNumY 配列のY方向のサイズ
	 * @throws SlickException
	 */
	public Map(int[] mapData, int chipNumX, int chipNumY) throws SlickException {
		this.chipNum.set(chipNumX, chipNumY);
		this.originalMapData = new int[(int)chipNum.x][(int)chipNum.y];
		this.mapData = new int[(int)chipNum.x][(int)chipNum.y];
		for (int y = 0; y < chipNum.y; y++) {
			for (int x = 0; x < chipNum.x; x++) {
				int id = mapData[x+y*(int)chipNum.x];
				this.originalMapData[x][y] = id;

				if (id == MAP_ENPITSU || id == MAP_BOLLPEN
						|| id == MAP_BLUE || id == MAP_GREEN
						|| id == MAP_LIGHT_BLUE || id == MAP_PURPLE
						|| id == MAP_RED || id == MAP_YELLOW) {//地形データのみを保存
					this.mapData[x][y] = id;
				}
			}
		}
	}

	/**マップの画像を設定する。<br>
	 * 事前にsetChipSize()で画像を切り出すサイズを指定する必要あり*/
	public void setMapImage(String imageName, int imageSizeX, int imageSizeY) throws SlickException {
		int x = imageSizeX/(int)chipSize.x;
		int y = imageSizeY/(int)chipSize.y;
		SpriteSheet ssheet = new SpriteSheet(
				new Image(imageName), (int)chipSize.x, (int)chipSize.y);
		splites = new Image[x*y];
		for (int i = 0; i < splites.length; i++) {
			splites[i] = ssheet.getSubImage(i%x, i/x);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//デバッグ用
		Input in = gc.getInput();
		if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int x = in.getMouseX();
			int y = in.getMouseY();
			System.out.println("Map[" + (int)(x/chipSize.x) + ", " + (int)(y/chipSize.y) + "]");
		}

		Block.staticUpdate();

		int stx = 0, enx = (int)chipNum.x;
		int sty = 0, eny = (int)chipNum.y;
		if (camera != null) {
			Rectangle view = camera.getView();
			stx = (int)(view.getX()/chipSizeOnScreen.x);
			sty = (int)(view.getY()/chipSizeOnScreen.y);
			enx = (int)((view.getX()+view.getWidth())/chipSizeOnScreen.x)+1;
			eny = (int)((view.getY()+view.getHeight())/chipSizeOnScreen.y)+1;

			stx = stx < 0 ? 0 : stx; stx = stx >= chipNum.x ? (int)chipNum.x : stx;
			sty = sty < 0 ? 0 : sty; sty = sty >= chipNum.y ? (int)chipNum.y : sty;
			enx = enx < 0 ? 0 : enx; enx = enx >= chipNum.x ? (int)chipNum.x : enx;
			eny = eny < 0 ? 0 : eny; eny = eny >= chipNum.y ? (int)chipNum.y : eny;
		}

		for (int x = enx-1; x >= stx; x--){
			for (int y = eny-1; y >= sty; y--){
				Block.getBlock(mapData[x][y]).update(gc, this, x, y);
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g){
		int stx = 0, enx = (int)chipNum.x;
		int sty = 0, eny = (int)chipNum.y;
		if (camera != null) {
			Rectangle view = camera.getView();
			stx = (int)(view.getX()/chipSizeOnScreen.x);
			sty = (int)(view.getY()/chipSizeOnScreen.y);
			enx = (int)((view.getX()+view.getWidth())/chipSizeOnScreen.x)+1;
			eny = (int)((view.getY()+view.getHeight())/chipSizeOnScreen.y)+1;

			stx = stx < 0 ? 0 : stx; stx = stx >= chipNum.x ? (int)chipNum.x : stx;
			sty = sty < 0 ? 0 : sty; sty = sty >= chipNum.y ? (int)chipNum.y : sty;
			enx = enx < 0 ? 0 : enx; enx = enx >= chipNum.x ? (int)chipNum.x : enx;
			eny = eny < 0 ? 0 : eny; eny = eny >= chipNum.y ? (int)chipNum.y : eny;
		}
		g.setColor(Color.black);
		for (int x = stx; x < enx; x++) {
			for (int y = sty; y < eny; y++) {
				//				int index = mapData[x][y];
				//				if (index != 0) {
				//					int lw = enpitsuLineWidth;
				//					if (index != 1) lw = borupenLineWidth;
				//					g.setLineWidth(lw);
				//					//					g.drawImage(splites[index-1],
				//					//							x*chipSizeOnScreen.x, y*chipSizeOnScreen.y);
				//
				//					if (x-1 >= stx && mapData[x-1][y] == 0)//左端
				//						g.drawLine(x*chipSizeOnScreen.x, y*chipSizeOnScreen.y,
				//								x*chipSizeOnScreen.x, (y+1)*chipSizeOnScreen.y);
				//
				//					if (x+1 < enx && mapData[x+1][y] == 0)//右端
				//						g.drawLine((x+1)*chipSizeOnScreen.x, y*chipSizeOnScreen.y,
				//								(x+1)*chipSizeOnScreen.x, (y+1)*chipSizeOnScreen.y);
				//
				//					if (y-1 >= sty && mapData[x][y-1] == 0)//上端
				//						g.drawLine(x*chipSizeOnScreen.x, y*chipSizeOnScreen.y,
				//								(x+1)*chipSizeOnScreen.x, y*chipSizeOnScreen.y);
				//
				//					if (y+1 < eny && mapData[x][y+1] == 0)//下端
				//						g.drawLine(x*chipSizeOnScreen.x, (y+1)*chipSizeOnScreen.y,
				//								(x+1)*chipSizeOnScreen.x, (y+1)*chipSizeOnScreen.y);
				//
				//					//Block.ENPITSU.render(g, this, x, y);
				//				}
				Block.getBlock(mapData[x][y]).render(g, this, x, y);
			}
		}
	}

	/**マップの範囲外*/
	public static final int MAP_OUT_OF_MAP = -1;
	/**ブロックの無い空間*/
	public static final int MAP_EMPTY = 0;
	/**鉛筆で書かれたブロック*/
	public static final int MAP_ENPITSU = 1;
	/**ボールペンで書かれたブロック*/
	public static final int MAP_BOLLPEN = 2;
	/**黄色いブロック*/
	public static final int MAP_YELLOW = 3;
	/**赤いブロック*/
	public static final int MAP_RED = 4;
	/**緑のブロック*/
	public static final int MAP_GREEN = 5;
	/**青いブロック*/
	public static final int MAP_BLUE = 6;
	/**紫のブロック*/
	public static final int MAP_PURPLE = 7;
	/**水色のブロック*/
	public static final int MAP_LIGHT_BLUE = 8;


	/**敵：カニ*/
	public static final int ENEMY_CRAB = 21;
	/**敵：ＵＦＯ*/
	public static final int ENEMY_UFO = 22;
	/**敵：ゴースト*/
	public static final int ENEMY_GHOST = 23;
	/**敵:どっすん*/
	public static final int ENEMY_DOSSUN = 24;
	/**敵：ミサイル*/
	public static final int ENEMY_MISSILE = 25;
	/**敵:モル*/
	public static final int ENEMY_MOL = 26;
	/**敵：魔法使い*/
	public static final int ENEMY_MAGICAN = 27;
	/**敵：爆弾*/
	public static final int ENEMY_BOMB = 28;

	/**アイテム：コイン*/
	public static final int ITEM_COIN = 61;

	/**マップ上の敵データとアイテムのデータをステージに追加する。<br>
	 * このメソッドを呼ぶ前にエネミーグループとアイテムグループをクリアしておく事。*/
	public void setEnemysAndItems(Stage stg) {
		for (int x = 0; x < originalMapData.length; x++) {
			for (int y = 0; y < originalMapData[0].length; y++) {
				int id = originalMapData[x][y];
				switch(id) {
				case ENEMY_BOMB:
					break;
				case ENEMY_CRAB:
					break;
				case ENEMY_DOSSUN:
					break;
				case ENEMY_GHOST:
					break;
				case ENEMY_MAGICAN:
					stg.addEnemyFactory(new EnemyFactory(stg,
							Magican.class, calcLocateX(x), calcLocateY(y)));
					break;
				case ENEMY_MISSILE:
					break;
				case ENEMY_MOL:
					break;
				case ENEMY_UFO:
					break;
				case ITEM_COIN:
					stg.addItem(new Coin(stg, calcLocateX(x), calcLocateY(y)));
					break;
				}
			}
		}
	}

	/**マップの幅を取得*/
	public int getMapWidth() {
		return mapData.length * (int)chipSizeOnScreen.x;
	}

	/**マップの高さを取得*/
	public int getMapHeight() {
		return mapData[0].length * (int)chipSizeOnScreen.y;
	}

	public int[][] getMapData() {
		return mapData;
	}

	/**画像からチップを切り出すサイズを指定*/
	public void setChipSize(int chipSizeX, int chipSizeY) {
		chipSize.set(chipSizeX, chipSizeY);
	}
	/**チップをゲーム画面に表示するサイズを指定*/
	public void setChipSizeOnScreen(int chipSizeOnScreenX, int chipSizeOnScreenY) {
		chipSizeOnScreen.set(chipSizeOnScreenX, chipSizeOnScreenY);
	}
	/**マップ上のチップの数を指定*/
	public void setMapSize(int x, int y) {
		chipNum.set(x, y);
	}

	/**カメラをセット*/
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	/**マップ上のチップの位置から実際の座標を取得する*/
	public float calcLocateX(int x) {
		return (x+0.5f)*chipSizeOnScreen.x;
	}
	/**マップ上のチップの位置から実際の座標を取得する*/
	public float calcLocateY(int y) {
		return (y+0.5f)*chipSizeOnScreen.y;
	}

	public void erase(float x, float y, float sizex, float sizey) {
		int stx = 0, enx = (int)chipNum.x;
		int sty = 0, eny = (int)chipNum.y;
		stx = (int)((x-sizex/2)/chipSizeOnScreen.x);
		sty = (int)((y-sizey/2)/chipSizeOnScreen.y);
		enx = (int)((x+sizex/2)/chipSizeOnScreen.x)+1;
		eny = (int)((y+sizey/2)/chipSizeOnScreen.y)+1;

		stx = stx < 0 ? 0 : stx; stx = stx >= chipNum.x ? (int)chipNum.x : stx;
		sty = sty < 0 ? 0 : sty; sty = sty >= chipNum.y ? (int)chipNum.y : sty;
		enx = enx < 0 ? 0 : enx; enx = enx >= chipNum.x ? (int)chipNum.x : enx;
		eny = eny < 0 ? 0 : eny; eny = eny >= chipNum.y ? (int)chipNum.y : eny;

		for (int x0 = stx; x0 < enx; x0++) {
			for (int y0 = sty; y0 < eny; y0++) {
				if (mapData[x0][y0] == 1) {
					mapData[x0][y0] = 0;
				}
			}
		}
	}

	/**指定した座標のブロックのidを返す**/
	public int getMapBlockId(int x, int y) {
		if (x < 0 || this.mapData.length <= x) {
			return MAP_OUT_OF_MAP;
		}
		if (y < 0 || this.mapData[0].length <= y) {
			return MAP_OUT_OF_MAP;
		}

		return mapData[x][y];
	}
}
