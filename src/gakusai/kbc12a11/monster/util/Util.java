package gakusai.kbc12a11.monster.util;

public class Util {
	private Util(){}

	/**数値を範囲内に丸める*/
	public static float between(float num, float min, float max) {
		float res = num;
		if (res < min) res = min;
		if (res > max) res = max;
		return res;
	}
}
