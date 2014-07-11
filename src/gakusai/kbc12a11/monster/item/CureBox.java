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
	Effect effect = new CureEffect(stg, p);
	boolean flgGet;

	public CureBox(Stage stg, float x, float y) {
		super(stg, x, y);
		timer = 0;
		img = ImageBank.getImage(ImageBank.ITEM_CUREBOX);
		size.set(32, 32);
	}

	@Override
	public void effect(Object obj) {
		if (obj instanceof Player) {
	//		((Player)obj).damage(-1);
			flg_live = false;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {


	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {

		if(flgGet){
			effect.render(gc, sbg, g);
		}else{
			g.drawImage(img, p.x - size.x/2, p.y - size.y/2);
		}

	}

	@Override
	public void hit(Object obj) {
		if (obj instanceof Player) {

			if(!flgGet){
				stg.soundRequest(SoundBank.SE_CUREBOX);
				((Player)obj).damage(-1);
			}

			flgGet = true;



		}
		if(timer >= 50){
			super.hit(obj);
		}




	}


	//回復エフェクト
	public class CureEffect extends Effect {

		Vector2f player = stg.getPlayerPos();

		private Image cureEffect = ImageBank.getImage(ImageBank.EF_CURE);
		int x0 = 32, y0 = 32;
		public CureEffect(Stage stg, Vector2f pos) {
			super(stg, pos);
			stg.getMap().erase(p.x-x0/2, p.y-y0/2, x0, y0);
		}

		@Override
		public void update(GameContainer gc, StateBasedGame sbg, int delta)
				throws SlickException {
			if(flgGet && timer < 50){


			}

			System.out.println(timer);


		}

		@Override
		public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
			// TODO 自動生成されたメソッド・スタブ
			player = stg.getPlayerPos();

			if(timer <= 50){
				timer++;
				int x = timer / 5;
				int y = 0;
				int cureX = 120;
				int cureY = 120;
				g.drawImage(cureEffect, player.x - x0, player.y - y0*1.5f, player.x+x0, player.y+y0/2, x * cureX, y * cureY, (x + 1) * cureX, (y + 1) * cureY);
			}


		}

		@Override
		public void hit(Object obj) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}
}
