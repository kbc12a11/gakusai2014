package gakusai.kbc12a11.monster.sys.block;

import gakusai.kbc12a11.monster.abst.Character;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public enum Block {
	/**マップの範囲外を意味する特殊ブロック*/
	OUT_OF_MAP(Map.MAP_OUT_OF_MAP) {
		@Override
		public void render(Graphics g, Map map, int x, int y) {
		}
		@Override
		public boolean isPassedBlock() {
			return false;
		}
	},
	/**空のブロック*/
	EMPTY(Map.MAP_EMPTY) {
		@Override
		public void render(Graphics g, Map map, int x, int y) {
		}
		@Override
		public boolean isPassedBlock() {
			return true;
		}
	},
	/**鉛筆ブロック*/
	ENPITSU(Map.MAP_ENPITSU) {
		int[] blockList = {Map.MAP_EMPTY};
		Color c = new Color(0, 0, 0, 100);
		@Override
		public void render(Graphics g, Map map, int x, int y) {
//			renderFrame(g, map, x, y, 1, 0, Color.black,
//					blockList, false);
			//fillRect(g, x, y, map, c);
			renderTexture(g, x, y, map, c, ImageBank.getImage(ImageBank.TX_BOLLPEN));
		}
	},
	/**ボールペン*/
	BOLLPEN(Map.MAP_BOLLPEN) {
		int[] blockList = {Map.MAP_EMPTY};
		Color c = new Color(0, 0, 0, 255);
		@Override
		public void render(Graphics g, Map map, int x, int y) {
			renderTexture(g, x, y, map, c, ImageBank.getImage(ImageBank.TX_BOLLPEN));
		}
	},
	/**黄色*/
	YELLOW(Map.MAP_YELLOW) {

		@Override
		public void render(Graphics g, Map map, int x, int y) {
			// TODO 自動生成されたメソッド・スタブ

		}
	},
	/**赤*/
	RED(Map.MAP_RED) {
		int[] blockList = {Map.MAP_RED};
		Color c = new Color(255, 0, 0, 255);
		@Override
		public void render(Graphics g, Map map, int x, int y) {
//			renderFrame(g, map, x, y, 3, 0, Color.red,
//					blockList, true);

			//fillRect(g, x, y, map, c);
			renderTexture(g, x, y, map, c, ImageBank.getImage(ImageBank.TX_BOLLPEN));

			if (map.getMapBlockId(x, y-1) == Map.MAP_EMPTY) {
				Vector2f[] p = new Vector2f[(int)map.chipSizeOnScreen.x+1];

				float cx = map.chipSizeOnScreen.x;
				float cy = map.chipSizeOnScreen.y;

				for (int i = 0; i < p.length; i++) {
					double y0 = Math.random()*cy/6-cy/4;
					p[i] = new Vector2f(i, (float)y0);
				}

				if (map.getMapBlockId(x-1, y) != Map.MAP_RED) p[0].y = 0;
				if (map.getMapBlockId(x+1, y) != Map.MAP_RED) p[p.length-1].y = 0;
				g.setLineWidth(3);
				g.setColor(Color.red);
				for (int i = 1; i < p.length; i++) {
					g.drawLine(p[i].x+x*cx, p[i].y+y*cy, p[i-1].x+x*cx, p[i-1].y+y*cy);
				}
			}
		}
	},
	/**緑*/
	GREEN(Map.MAP_GREEN) {

		@Override
		public void render(Graphics g, Map map, int x, int y) {
			// TODO 自動生成されたメソッド・スタブ

		}
	},
	/**青*/
	BLUE(Map.MAP_BLUE) {
		int[] blockList = {Map.MAP_BLUE};
		Color c = new Color(0, 0, 255, 10);
		@Override
		public void render(Graphics g, Map map, int x, int y) {
			renderFrame(g, map, x, y, 1, 0, Color.blue,
					blockList, true);
			fillRect(g, x, y, map, c);
		}



		@Override
		public boolean isPassedBlock() {//通り抜け可能ブロック
			return true;
		}

		@Override
		public void update(GameContainer gc, Map map, int x, int y) throws SlickException {
			if (timer%120 == 0) {
				int underIndex = map.getMapBlockId(x, y+1);
				switch (underIndex) {
				case Map.MAP_EMPTY :
					map.getMapData()[x][y+1] = Map.MAP_BLUE;
					break;
				case Map.MAP_RED :
					map.getMapData()[x][y+1] = Map.MAP_EMPTY;
					break;
				}
			}
		}
	},
	/**紫*/
	PURPLE(Map.MAP_PURPLE) {

		@Override
		public void render(Graphics g, Map map, int x, int y) {
			// TODO 自動生成されたメソッド・スタブ

		}
	},
	LITE_BLUE(Map.MAP_LIGHT_BLUE) {

		@Override
		public void render(Graphics g, Map map, int x, int y) {
			// TODO 自動生成されたメソッド・スタブ

		}
	}
	;

	private final int id;
	private Block(int id) {
		this.id = id;
	}

	/**ブロックにキャラクターが接触した時の効果*/
	public void effect(Character ch) {
	}
	/**ブロックの描画
	 * @param map TODO
	 * @param x ブロックのx座標
	 * @param y ブロックのy座標*/
	public abstract void render(Graphics g, Map map, int x, int y);
	/**ブロックの更新
	 *
	 * @param gc
	 * @param map
	 * @param x ブロックのx座標
	 * @param y ブロックのy座標
	 * @throws SlickException
	 */
	public void update(GameContainer gc, Map map, int x, int y) throws SlickException{}

	/**指定したIDのブロックを返す*/
	public static Block getBlock(int id) {
		Block b = EMPTY;
		switch (id) {
		case Map.MAP_ENPITSU:
			b = ENPITSU;
			break;
		case Map.MAP_BOLLPEN:
			b = BOLLPEN;
			break;
		case Map.MAP_YELLOW:
			b = YELLOW;
			break;
		case Map.MAP_RED:
			b = RED;
			break;
		case Map.MAP_GREEN:
			b = GREEN;
			break;
		case Map.MAP_BLUE:
			b = BLUE;
			break;
		case Map.MAP_PURPLE:
			b = PURPLE;
			break;
		case Map.MAP_LIGHT_BLUE:
			b = LITE_BLUE;
			break;
		}
		return b;
	}

	public int getId() {
		return id;
	}

	/**指定した色で指定した座標に指定した太さとパディングで枠線を書く
	 *
	 * @param g
	 * @param map
	 * @param x
	 * @param y
	 * @param lineWidth
	 * @param padding
	 * @param c
	 * @param blockList くっつくブロックのリスト
	 * @param isWhiteList ブラックリストかホワイトリストかのフラグ<br>trueならホワイトリスト
	 */
	private static void renderFrame(Graphics g, Map map, int x, int y,
			int lineWidth, int padding, Color c,
			int[] blockList, boolean isWhiteList) {

		int[][] md = map.getMapData();
		if (x < 0 || md.length <= x
				|| y < 0 || md[0].length <= y) return;
		boolean l, r, t, b = l = r = t = true;

		if (blockList != null) {

			int lb = map.getMapBlockId(x-1, y);
			int rb = map.getMapBlockId(x+1, y);
			int tb = map.getMapBlockId(x, y-1);
			int bb = map.getMapBlockId(x, y+1);

			for (int block : blockList) {
				if (isWhiteList) {
					if (lb == block) l = false;
					if (rb == block) r = false;
					if (tb == block) t = false;
					if (bb == block) b = false;
				}else {
					if (lb != block) l = false;
					if (rb != block) r = false;
					if (tb != block) t = false;
					if (bb != block) b = false;
				}
			}
		}

		renderFrame(g, map, x, y, c, lineWidth, padding,
				t, b, l, r);
	}

	/**
	 * 指定した色で指定した場所に枠線を書く
	 * @param g
	 * @param map
	 * @param x
	 * @param y
	 * @param c
	 * @param lineWidth
	 * @param padding
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 */
	private static void renderFrame(Graphics g, Map map, int x, int y,
			Color c, int lineWidth, int padding,
			boolean top, boolean bottom, boolean left, boolean right){

		g.setLineWidth(lineWidth);
		g.setColor(c);
		Vector2f chip = map.chipSizeOnScreen;
		if (left)//左端
			g.drawLine(x*chip.x+padding, y*chip.y+padding,
					x*chip.x+padding, (y+1)*chip.y-padding);

		if (right)//右端
			g.drawLine((x+1)*chip.x-padding, y*chip.y+padding,
					(x+1)*chip.x-padding, (y+1)*chip.y-padding);

		if (top)//上端
			g.drawLine(x*chip.x+padding, y*chip.y+padding,
					(x+1)*chip.x-padding, y*chip.y+padding);

		if (bottom)//下端
			g.drawLine(x*chip.x+padding, (y+1)*chip.y-padding,
					(x+1)*chip.x-padding, (y+1)*chip.y-padding);
	}

	private static void fillRect(Graphics g, int x, int y, Map map, Color c) {
		float sx = map.chipSizeOnScreen.x;
		float sy = map.chipSizeOnScreen.y;
		g.setColor(c);
		g.fillRect(x*sx, y*sy, sx, sy);
	}

	/**タイマー*/
	private static long timer = 0;
	/**ブロック全体の更新*/
	public static void staticUpdate() {
		timer++;
		if (timer >= Long.MAX_VALUE-1) {
			timer = 0;
		}
	}

	/**ブロックを通り抜けることができるかの判定*/
	public boolean isPassedBlock() {
		return false;//デフォルトでは通り抜けできない
	}

	/***
	 * テクスチャを張る<br>
	 * テクスチャのサイズは縦横2^nピクセルにすること
	 * @param g
	 * @param x
	 * @param y
	 * @param map
	 * @param c
	 */
	public static void renderTexture(Graphics g, int x, int y, Map map, Color c, Image texture) {
		Vector2f cp = map.chipSizeOnScreen;
		Rectangle r = new Rectangle(
				x*cp.x,
				y*cp.y, cp.x,cp.y);
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.setColor(new Color(255, 255, 255, c.a));
		g.texture(r, texture);
		g.setDrawMode(Graphics.MODE_SCREEN);
		g.setColor(c);
		g.fill(r);
		g.setDrawMode(Graphics.MODE_NORMAL);
	}

	public static void renderTexture(Shape s, Graphics g, int x, int y, Map map,
			Color c, Image texture) {
		g.setDrawMode(Graphics.MODE_NORMAL);
		g.setColor(new Color(255, 255, 255, c.a));
		g.texture(s, texture);
		g.setDrawMode(Graphics.MODE_SCREEN);
		g.setColor(c);
		g.fill(s);
		g.setDrawMode(Graphics.MODE_NORMAL);
	}
}
