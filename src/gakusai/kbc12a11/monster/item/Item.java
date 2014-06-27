package gakusai.kbc12a11.monster.item;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;

public abstract class Item extends Object{

	public Item(Stage stg, float x, float y) {
		super(stg);
		p.set(x, y);
	}

	/**アイテムによって得る効果
	 *
	 * @param obj アイテムを取得したオブジェクト
	 */
	public abstract void effect(Object obj);

	@Override
	public void hit(Object obj) {
		if (isDestroy()) return;

		effect(obj);
		destroy();
	}
}
