package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.SoundBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Magican extends Enemy{

	//タイマー
	private int timer = 0;
	private int at_interval = 120;//攻撃間隔
	private int at_view = 60;//攻撃ポーズをとる時間

	private int state;
	public static final int ST_NORMAL = 0;
	public static final int ST_ATTACK = 1;

	private Image img1, img2;

	public Magican(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		p.set(x, y);
		size.set(32, 32);
		img1 = stg.getImage(ImageBank.ENEMY_MAGICAN_1);
		img2 = stg.getImage(ImageBank.ENEMY_MAGICAN_2);
		timer = (int)(Math.random()*60);//攻撃タイミングずらし
		state = ST_NORMAL;
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;

		if (state == ST_NORMAL) {
			p.add(d);
			if (timer > 100) {
				setState(ST_ATTACK);
			}
		}else if (state == ST_ATTACK) {
			if (timer == 100) {
				Vector2f pl = stg.getPlayerPos();
				double rad = Math.atan2(pl.y - p.y, pl.x - p.x);
				int n = 1;
				for (int i = 0; i < n; i++) {
//					double r = rad + 0.25*(i-n/2)*Math.PI/n;
					double dx = Math.cos(rad)*2;
					double dy = Math.sin(rad)*2;
					//magicGroup.add(new Magic(stg, p.x, p.y, (float)dx, (float)dy));
					stg.addEnemy(new Magic(stg, p.x, p.y, (float)dx, (float)dy));
				}
			}
			if (timer > 250) {
				setState(ST_NORMAL);
			}
		}

//		if (timer%at_interval == at_interval/2) {
//			Vector2f pl = stg.getPlayerPos();
//			double rad = Math.atan2(pl.y - p.y, pl.x - p.x);
//			int n = 1;
//			for (int i = 0; i < n; i++) {
////				double r = rad + 0.25*(i-n/2)*Math.PI/n;
//				double dx = Math.cos(rad)*2;
//				double dy = Math.sin(rad)*2;
//				//magicGroup.add(new Magic(stg, p.x, p.y, (float)dx, (float)dy));
//				stg.addEnemy(new Magic(stg, p.x, p.y, (float)dx, (float)dy));
//			}
//		}
//
//		if (timer % at_interval == at_interval/2 - at_view) {
//			stg.soundRequest(SoundBank.SE_MAGIC);
//		}
//
//		if (timer%180 == 0) {
//			Rectangle r = stg.getCamera().getView();
//			float x = (float)Math.random()*1 - 0.5f;
//			float y = (float)Math.random()*1 - 0.5f;
//			d.set(x, y);
//		}
//		p.add(d);

	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		Image i = null;

		if (state == ST_ATTACK) {
			i = img1;
		}else {
			i = img2;
		}
		g.drawImage(i, p.x - size.x/2, p.y - size.y/2);
	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public boolean isHit(Object obj) {
		boolean flg = super.isHit(obj);
		return flg;
	}

	public void setState(int id) {
		switch(id) {
		case ST_ATTACK:
			timer = 0;
			stg.soundRequest(SoundBank.SE_MAGIC);
			state = ST_ATTACK;
			d.set(0, 0);
			break;

		default:
		case ST_NORMAL:
			timer = 0;
			state = ST_NORMAL;
			float x = (float)Math.random()*1 - 0.5f;
			float y = (float)Math.random()*1 - 0.5f;
			d.set(x, y);
		}
	}

	private class Magic extends Enemy{
		private int timer = 0;
		public Magic(Stage stg, float px, float py, float dx, float dy) throws SlickException {
			super(stg, px, py);
			d.set(dx, dy);
			size.set(8, 8);
			isImmortal = true;//踏まれても死なないよう設定
		}

		@Override
		public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			float px = p.x, py = p.y;
			float dx = d.x, dy = d.y;
			int filter = Collide.COL_MAP_BLOCK_DOWN |
					Collide.COL_MAP_BLOCK_LEFT |
					Collide.COL_MAP_BLOCK_RIGHT |
					Collide.COL_MAP_BLOCK_UP;
			if (0 != (Collide.decideCheckOnMap(this, stg.getMap())&filter)) {
				destroy();
			}
			//p.add(d);
			d.set(dx, dy);
			p.set(px, py);
			p.add(d);
			timer++;
			//if (timer > 120) destroy();
		}

		@Override
		public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
			g.setColor(Color.black);
			g.setLineWidth(1);
			g.drawOval(p.x - size.x/2, p.y - size.y/2, size.x, size.y);
		}

		@Override
		public void hit(Object obj) {
			destroy();
		}

		@Override
		public void destroy() {
			super.destroy();
			timer = 0;
			stg.soundRequest(SoundBank.SE_BOMB, SoundBank.DEFAULT_PITCH, 0.2f);
		}

		@Override
		public boolean isHit(Object obj) {
			float dx = obj.getP().x - p.x;
			float dy = obj.getP().y - p.y;
			dx = dx < 0 ? -dx : dx;
			dy = dy < 0 ? -dy : dy;
			boolean flg = false;
			float sx = size.x/2 + obj.getSize().x/2;
			float sy = size.y/2 + obj.getSize().y/2;
			if (dx < sx && dy < sy) {
				if (dx*dx + dy*dy < sx*sx + sy*sy)
					flg = true;
			}
			return flg;
		}



		@Override
		public Shape getShape() {
			Circle c = new Circle(p.x, p.y, size.x);
			return c;
		}

		@Override
		public void renderDestroy(GameContainer gc, StateBasedGame sbg, Graphics g) {
			size.x++;
			size.y++;
			chRender(gc, sbg, g);
		}
		@Override
		protected void updateDestroy(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
			timer++;
			if (timer > 30) flg_live = false;
		}
	}
}
