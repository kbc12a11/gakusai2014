package gakusai.kbc12a11.monster.util;

import gakusai.kbc12a11.monster.abst.Character;
import gakusai.kbc12a11.monster.sys.Map;
import gakusai.kbc12a11.monster.sys.block.Block;
import gakusai.kbc12a11.monster.sys.player.Player;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

public class Collide {
	private Collide(){}

	/**マップの上端に触れた*/
	public static int COL_OUT_OF_MAP_UP = 1;
	/**マップの左端に触れた*/
	public static int COL_OUT_OF_MAP_LEFT = 2;
	/**マップの下端に触れた*/
	public static int COL_OUT_OF_MAP_DOWN = 4;
	/**マップの右端に触れた*/
	public static int COL_OUT_OF_MAP_RIGHT = 8;

	/**キャラクタの上にあるマップのブロックに当たった*/
	public static int COL_MAP_BLOCK_UP = 16;
	/**キャラクタの左にあるマップのブロックに当たった*/
	public static int COL_MAP_BLOCK_LEFT = 32;
	/**キャラクタの下にあるマップのブロックに当たった*/
	public static int COL_MAP_BLOCK_DOWN = 64;
	/**キャラクタの右にあるマップのブロックに当たった*/
	public static int COL_MAP_BLOCK_RIGHT = 128;


	/**キャラクターとMapのあたり判定を行い、キャラクターの移動量を制御する。
	 * @return 当たっていればtrue*/
	public static int decideCheckOnMap(Character ch, Map map) {
		float px = ch.getP().x, py = ch.getP().y;
		float dx = ch.getD().x, dy = ch.getD().y;
		float sx = ch.getSize().x/2, sy = ch.getSize().y/2;
		int mlw = map.getMapData().length;
		int mlh = map.getMapData()[0].length;
		float chipX = map.chipSizeOnScreen.x;
		float chipY = map.chipSizeOnScreen.y;

		int res = 0;
		if (dx == 0 && dy == 0) return res;

		int effx = 0, effy = 0;//係数
		if (dx < 0) effx = -1;
		if (dx > 0) effx = 1;
		if (dy < 0) effy = -1;
		if (dy > 0) effy = 1;

		//float x0 = px + dx, y0 = py + dy;
		int tx, ty;
		int hitxx = -1, hitxy = -1, hityx = -1, hityy = -1;

		Block[] hitBlocks = new Block[6];
		int blocksIndex = 0;

		//X方向のチェック
		tx = (px + dx + sx*effx + effx) >= 0 ? (int)((px + dx + (sx*effx) + effx)/chipX) : -1;
		boolean flagx = true;
		if (tx < 0 || mlw <= tx) {
			flagx = false;
		}
		if (tx < 0) {
			res = res|COL_OUT_OF_MAP_LEFT;
		}
		if (mlw <= tx) {
			res = res|COL_OUT_OF_MAP_RIGHT;
		}

		for (int i : new int[]{0, 1, -1}) {
			float ty0 = (py + sy*(i));
			ty = (ty0 >= 0 || map.getMapHeight() >= ty0) ?
					(int)(ty0/chipY) : -1;
					if (ty < 0) {
						flagx = false;
						res = res|COL_OUT_OF_MAP_UP;
					}
					if (mlh <= ty) {
						res = res|COL_OUT_OF_MAP_DOWN;
						break;
					}
					if (flagx && map.getMapData()[tx][ty] != 0) {
						Block b = Block.getBlock(map.getMapBlockId(tx, ty));
						if (!b.isPassedBlock()){
							if (dx < 0) {
								res = res|COL_MAP_BLOCK_LEFT;

							}else {
								res = res|COL_MAP_BLOCK_RIGHT;
							}
							flagx = false;
						}
						if (hitxx != tx || hitxy != ty) {
							hitBlocks[blocksIndex++] = b;
						}
						hitxx = tx;
						hitxy = ty;
					}
					if (!flagx) break;
		}
		if (!flagx){
			px = (tx + (effx<0 ? 1 : 0)) * chipX - sx*effx - effx;
			dx = 0;
		}

		//Y方向のチェック
		ty = (py + dy + sy*effy + effy) >= 0 ? (int)((py + dy + (sy*effy)+effy)/chipY) : -1;
		boolean flagy = true;
		if (ty < 0) {
			flagy = false;
		}

		if (ty < 0) {
			res = res|COL_OUT_OF_MAP_UP;
		}
		if (mlh <= ty) {
			res = res|COL_OUT_OF_MAP_DOWN;
		}
		if (mlh > ty) {
			for (int i : new int[]{0, 1, -1}) {
				float tx0 = (px + sx*(i));
				tx = (tx0 >= 0 || map.getMapWidth() >= tx0) ?
						(int)(tx0/chipX) : -1;
						if (tx < 0) {
							flagy = false;
							res = res|COL_OUT_OF_MAP_LEFT;
						}
						if (mlw <= tx) {
							res = res|COL_OUT_OF_MAP_RIGHT;
							flagy = false;
						}
						if (flagy && map.getMapData()[tx][ty] != 0) {
							Block b = Block.getBlock(map.getMapBlockId(tx, ty));
							if (!b.isPassedBlock()){
								if (dy < 0) {
									res = res|COL_MAP_BLOCK_UP;
								}else {
									res = res|COL_MAP_BLOCK_DOWN;
								}
								flagy = false;
							}
							if (hityx != tx || hityy != ty) {
								hitBlocks[blocksIndex++] = b;
							}
							hityx = tx;
							hityy = ty;
						}
						if (!flagy) break;
			}
		}else if (py-sy > mlh*chipY) {
			ch.fallOfMap();
		}
		if (!flagy) {
			py = (ty + (effy<0 ? 1 : 0)) * chipY - sy*effy - effy;
			dy = 0;
		}

		ch.getD().set(dx, dy);
		ch.getP().set(px, py);

		//		//ブロックに当たった時のエフェクト
		//		if ((res & (COL_MAP_BLOCK_DOWN | COL_MAP_BLOCK_UP)) != 0) {
		//			Block b = Block.getBlock(map.getMapBlockId(hityx, hityy));
		//			b.effect(ch);
		//			ch.onBlock(b);
		//		}
		//
		//		if ((res & (COL_MAP_BLOCK_LEFT | COL_MAP_BLOCK_RIGHT)) != 0) {
		//			Block b = Block.getBlock(map.getMapBlockId(hitxx, hitxy));
		//			b.effect(ch);
		//			ch.onBlock(b);
		//		}
		for (Block b : hitBlocks) {
			if (b != null){
				b.effect(ch);
				ch.onBlock(b);
			}
		}
		return res;
	}

	public static DecideCheckResult decideCheckOnLine(Player ch, Vector2f A, Vector2f B,
			float size, boolean isFirstLine){
		DecideCheckResult res = null;
		float dist = new Line(A, B).distance(new Vector2f(ch.getP()).add(ch.getD()));

		float abx = B.x - A.x, aby = B.y - A.y;
		float apx = ch.getP().x - A.x, apy = ch.getP().y - A.y;

		float bax = -abx, bay = -aby;
		float bpx = ch.getP().x - B.x, bpy = ch.getP().y - B.y;
		if (dist <= size) {
			Vector2f baseN = gakusai.kbc12a11.monster.sys.line.Line.getNormalVector(A, B);
			if (isFirstLine) {
				ch.rideLineFlag = true;
				res = new DecideCheckResult(true, dist, 0, baseN);
			}else if ((abx*apx + aby*apy)*(bax*bpx + bay*bpy) >= 0){
				ch.rideLineFlag = true;
				//外積値を求める
				float closs = abx * apy - aby * apx;
				res = new DecideCheckResult(true, dist, closs, baseN);
			}else {
				ch.rideLineFlag = false;
				res = new DecideCheckResult(false, 0, 0, null);
			}
		}else {
			ch.rideLineFlag = false;
			res = new DecideCheckResult(false, 0, 0, null);
		}
		return res;
	}

	public static class DecideCheckResult {
		/**直線との距離*/
		public float dist;
		/**0より大きいなら線の右側。0より小さいなら線の左側*/
		public float closs;
		/**直線に乗っているか*/
		public boolean isRide;
		/**当たった直線の正規化法線ベクトル*/
		public Vector2f N;

		public DecideCheckResult(boolean isRide, float dist, float closs, Vector2f N) {
			this.dist = dist;
			this.closs = closs;
			this.isRide = isRide;
			this.N = N;
		}
	}

	/**太さlwの直線ABと大きさsizeの点Pとのあたり判定を行う
	 *
	 * @param A 直線ABの始点
	 * @param B 直線ABの終点
	 * @param lw 直線ABの太さ
	 * @param P あたり判定を行う点P
	 * @param size 点Pの大きさ
	 * @return
	 */
	public static boolean isHitLineToCircle(Vector2f A, Vector2f B, float lw,
			Vector2f P, float size) {

		//AとBの座標が同じなら点と点であたり判定を行う
		if (A.equals(B)) return isHitCircleToCircle(A, lw, P, size);
		float a, b, c;
		//直線AB
		a = A.y - B.y;
		b = B.x - A.x;
		c = -(a*A.x + b*A.y);

		float h = a*P.x + b*P.y + c;
		h = h*h/(a*a + b*b);
		//距離が一定上なら何もしない
		if (h > (size+lw)*(size+lw)) return false;

		//d0がABの線分の範囲内かチェック
		Vector2f ad = new Vector2f(P.x - A.x, P.y - A.y);
		Vector2f bd = new Vector2f(P.x - B.x, P.y - B.y);
		Vector2f ab = new Vector2f(B.x - A.x, B.y - A.y);
		//内積の取得
		float dotADAB = ad.dot(ab);
		float dotBDBA = bd.x*-ab.x + bd.y*-ab.y;
		if (dotADAB*dotBDBA < 0) return false;
		return true;
	}

	public static float getDistanceLineToPoint(Vector2f A, Vector2f B, Vector2f P) {
		org.newdawn.slick.geom.Line l = new
				org.newdawn.slick.geom.Line(A, B);

		return l.distance(P);
	}

	/**
	 * 円と円のあたり判定を行う
	 * @param P1 円1の中心
	 * @param r1  円1の半径
	 * @param P2 円2の中心
	 * @param r2 円2の半径
	 * @return
	 */
	public static boolean isHitCircleToCircle(Vector2f P1, float r1,
			Vector2f P2, float r2) {
		float c = r1 + r2;
		float a = P1.x - P2.x;
		if (c < a) return false;
		float b = P1.y - P2.y;
		if (c < b) return false;
		if (a*a + b*b > c*c) {
			return false;
		}else {
			return true;
		}
	}

	/**キャラクターがジャンプ可能か(キャラクターの足元にブロックが存在するか)*/
	public static boolean canJump(Character ch, Map map) {
		float px = ch.getP().x, py = ch.getP().y;
		float dx = ch.getD().x, dy = ch.getD().y;
		float sx = ch.getSize().x/2, sy = ch.getSize().y/2;
		int mlw = map.getMapData().length;
		int mlh = map.getMapData()[0].length;
		float chipX = map.chipSizeOnScreen.x;
		float chipY = map.chipSizeOnScreen.y;

		int tx = (int)((px+sx)/chipX);
		int ty = (int)((py+sy)/chipY);
		boolean res = false;
		if (ty+1 < mlh){
			int[][] md = map.getMapData();
			if (!Block.getBlock(md[tx][ty+1]).isPassedBlock()) {
				res = true;
			}
		}
		return res;
	}
}
