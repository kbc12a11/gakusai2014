package gakusai.kbc12a11.monster.sys.decorate;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;

/**背景に表示する飾り用オブジェクト*/
public abstract class BackgroundObject extends Object{

	public BackgroundObject(Stage stg) {
		super(stg);
		isImmortal = true;
	}

	/**あたり判定は行わない*/
	@Override
	public boolean isHit(Object obj) {
		return false;
	}

	@Override
	public void hit(Object obj) {
		//何もしない
	}
}
