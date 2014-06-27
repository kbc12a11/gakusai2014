package gakusai.kbc12a11.monster.sys.effects;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.geom.Vector2f;

public abstract class Effect extends Object{

	public Effect(Stage stg, Vector2f pos) {
		super(stg);
		p.set(pos);
	}

	public Effect(Stage stg) {
		super(stg);
	}
}
