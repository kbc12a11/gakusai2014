package gakusai.kbc12a11.monster.sys.stage;

import gakusai.kbc12a11.monster.sys.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**ステージ開始前の表示*/
public class BeforeStartStage extends BasicGameState{
	private int timer = 0;
	//待機時間
	private int waitTime = 200;
	//フェードアウト時間
	private int fadeTime = 100;

	private boolean isGameOver;
	private String gomess1 = "Game Over.";
	private String gomess2 = "Push A button.";


	/**この画面に来たステージのステータス*/
	private int beforeStageState;
	private String gcmess1 = "Game Clear.";
	private String gcmess2 = "Push A button.";

	/**残機*/
	private int stock = -1;
	/**次のステージ*/
	private int nextStage = 0;
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
		super.enter(container, game);
		timer = waitTime;

		if (stock < 0) {
			isGameOver = true;
			timer /= 2;
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		g.setColor(Color.white);
		String s = "";

		if (beforeStageState == Stage.STATE_CLEAR){
			Font f = g.getFont();
			g.drawString(gcmess1, Main.W_WIDTH/2 - f.getWidth(gomess1)/2,
					Main.W_HEIGHT/2 - f.getLineHeight());
			if (timer < 0) {
				g.drawString(gcmess2, Main.W_WIDTH/2 - f.getWidth(gomess2)/2,
						Main.W_HEIGHT/2 + f.getLineHeight());
			}
		}else if (!isGameOver) {
			s = "" + stock;
			g.drawString(s, Main.W_WIDTH/2 - g.getFont().getWidth(s)/2,
					Main.W_HEIGHT/2);
		}else {
			Font f = g.getFont();
			g.drawString(gomess1, Main.W_WIDTH/2 - f.getWidth(gomess1)/2,
					Main.W_HEIGHT/2 - f.getLineHeight());
			if (timer < 0) {
				g.drawString(gomess2, Main.W_WIDTH/2 - f.getWidth(gomess2)/2,
						Main.W_HEIGHT/2 + f.getLineHeight());
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer--;
		if (!isGameOver) {
			if (timer < 0) {
				sbg.enterState(nextStage,
						new FadeOutTransition(Color.black, fadeTime),
						new FadeInTransition(Color.black, fadeTime) );
			}
		}else {
			if (timer < 0) {
				Input in = gc.getInput();
				if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					sbg.enterState(nextStage,
							new FadeOutTransition(Color.black, fadeTime),
							new FadeInTransition(Color.black, fadeTime) );
				}
 			}
		}
	}
	/***
	 * パラメータをセットする
	 * @param stock 残機
	 * @param nextStage 次のステージ
	 * @param stageState 現在のステージのステータス
	 */
	public void set(int stock, int nextStage, int stageState) {
		this.stock = stock;
		this.nextStage = nextStage;
		this.beforeStageState = stageState;
	}

	@Override
	public int getID() {
		// TODO 自動生成されたメソッド・スタブ
		return Main.Stage_BeforeStartStage;
	}

}
