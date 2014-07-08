package gakusai.kbc12a11.monster.enemy;

import gakusai.kbc12a08.monster.enemy.Enemy;
import gakusai.kbc12a11.monster.abst.Object;
import gakusai.kbc12a11.monster.sys.ImageBank;
import gakusai.kbc12a11.monster.sys.stage.Stage;
import gakusai.kbc12a11.monster.util.Collide;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Mol extends Enemy{

	//左右のどちらの方向に動くかのフラグ
	private boolean moveLeft;
	//プレイヤーが左にいるか
	private boolean isPlayerPosLeft;
	private int timer = 0;
	private float speed = 3;

	float digheight = 8;

	private int state = ST_NORMAL;
	/**通常(立ち)状態*/
	public static final int ST_NORMAL = 0;
	/**潜る途中*/
	public static final int ST_TO_DIG = 1;
	/**潜り状態*/
	public static final int ST_DIG = 2;
	/**出てくる途中*/
	public static final int ST_TO_NORMAL = 3;

	public Mol(Stage stg, float x, float y) throws SlickException {
		super(stg, x, y);
		size.set(32, 32);
		setState(ST_NORMAL);
	}

	@Override
	public void chUpdate(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;
		a.set(Stage.GRAVITY);
		d.add(a);
		if (stg.getPlayerPos().x < p.x) {
			isPlayerPosLeft = true;
		}else {
			isPlayerPosLeft = false;
		}
		if (state == ST_NORMAL) {
			updateStNormal(gc, sbg, delta);
		}else if (state == ST_TO_DIG) {
			updateStToDig(gc, sbg, delta);
		}else if (state == ST_DIG) {
			updateStDig(gc, sbg, delta);
		}else if (state == ST_TO_NORMAL) {
			updateStToNormal(gc, sbg, delta);
		}

		int res = Collide.decideCheckOnMap(this, stg.getMap());
		if ((res & Collide.COL_MAP_BLOCK_DOWN) == 0) setState(ST_NORMAL);
		else if (state == ST_NORMAL) d.x = 0;
		p.add(d);

	}

	@Override
	public void chRender(GameContainer gc, StateBasedGame sbg, Graphics g) {
		Image img = ImageBank.getImage(ImageBank.ENEMY_MOL_1);
		if(state == ST_NORMAL){

		}else if (state == ST_TO_DIG || state == ST_TO_NORMAL){
			img = ImageBank.getImage(ImageBank.ENEMY_MOL_3);
		}else if (state == ST_DIG) {
			img = ImageBank.getImage(ImageBank.ENEMY_MOL_5);
		}
		g.drawImage(img, p.x-32/2, p.y-32/2, p.x+32/2, p.y + 32/2,
				isPlayerPosLeft?32:0, 0, isPlayerPosLeft?0:32, 32);

	}

	private void updateStNormal(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (timer > 120) setState(ST_TO_DIG);
	}
	private void updateStToDig(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (timer > 10) setState(ST_DIG);
		if (isPlayerPosLeft) {
			moveLeft = true;
		}else {
			moveLeft = false;
		}
	}
	private void updateStDig(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (timer > 90) setState(ST_TO_NORMAL);
		if (moveLeft) {
			d.x = -speed;
		}else {
			d.x = speed;
		}
	}
	private void updateStToNormal(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (timer > 10) setState(ST_NORMAL);
		d.x = 0;
	}

	public void setState(int state) {
		if (state == this.state) return;
		timer = 0;
		this.state = state;
		switch(state) {
		case ST_NORMAL:
			System.out.println("Mol state change to NORMAL");
			break;
		case ST_TO_DIG:
			break;
		case ST_DIG:
			System.out.println("Mol state change to DIG");
			break;
		case ST_TO_NORMAL:
			break;
		}
	}

	@Override
	public boolean isHit(Object obj) {
		if (state != ST_NORMAL) {
			float ty = p.y;
			float sx = size.x, sy = size.y;
			p.y = p.y + (sy-digheight)/2;
			size.set(digheight, digheight);
			boolean flag = super.isHit(obj);
			p.y = ty;
			size.set(sx, sy);
			return flag;
		}else {
			return super.isHit(obj);
		}
	}

	@Override
	public void hit(Object obj) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
