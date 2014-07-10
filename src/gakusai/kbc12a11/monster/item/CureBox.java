package gakusai.kbc12a11.monster.item;

import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.effects.Effect;
import gakusai.kbc12a11.monster.sys.player.Player;
import gakusai.kbc12a11.monster.sys.stage.Stage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class CureBox extends Item {

	private Image img;
	private int timer;

	public CureBox(Stage stg, float x, float y) {
		super(stg, x, y);
		timer = 0;
		img = ImageBank.getImage(ImageBank.ITEM_CUREBOX);
		size.set(32, 32);
	}

	@Override
	public void effect(Object obj) {
		if (obj instanceof Player) {
			((Player)obj).damage(-1);
			flg_live = false;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.drawImage(img, p.x - size.x/2, p.y - size.y/2);
	}

	@Override
	public void hit(Object obj) {
		if (obj instanceof Player) {
			stg.soundRequest(SoundBank.SE_CUREBOX);
		}
		super.hit(obj);
	}


	//回復エフェクト
	public class CureEffect extends Effect {

		public CureEffect(Stage stg, Vector2f pos) {
			super(stg, pos);
			// TODO 自動生成されたコンストラクター・スタブ
		}

		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int delta)
				throws SlickException {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void hit(Object obj) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}
}
