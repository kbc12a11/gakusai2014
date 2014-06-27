package Slick2DTest;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play extends BasicGameState {
    
    private int state;
    TrueTypeFont font;
    private int cnt = 0;
    
    public Play(int state){
        this.state = state;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Font awtFont = new Font("MS gothic", Font.BOLD, 20);
        font = new TrueTypeFont(awtFont, false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 800, 600);
        
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("STATE = " + this.state, 100, 100);
       
        g.drawString("CNT = " + this.cnt, 100, 150);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
        if(gc.getInput().isKeyDown(Input.KEY_SPACE)){
            sbg.enterState(State.MENU,
                new FadeOutTransition(Color.black, 1000),
                new FadeInTransition(Color.black, 1000)
            );
        }
        
        if(gc.getInput().isKeyDown(Input.KEY_Z)){
            cnt++;
        }
    }

    @Override
    public int getID(){
       return this.state;
    }
}
