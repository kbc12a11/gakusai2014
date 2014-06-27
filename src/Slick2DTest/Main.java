package Slick2DTest;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
        
    public static final int WIDTH  = 800;
    public static final int HEIGHT = 600;
    private static final int FPS = 60;
    private static final String GAMENAME = "Slick2D テスト";

    public Main(String title) {
        super(GAMENAME);
        this.addState(new Menu(State.MENU));
        this.addState(new Play(State.PLAY));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(State.MENU).init(gc, this);
        this.getState(State.PLAY).init(gc, this);
        this.enterState(State.MENU);
    }
        
    public static void main(String[] args) throws SlickException {
        //エントリーポイント
        AppGameContainer app;
        try{
            app = new AppGameContainer(new Main(GAMENAME));
            app.setDisplayMode(WIDTH, HEIGHT, false);
            app.setTargetFrameRate(FPS);
            app.start();
        }catch(SlickException e){
            e.printStackTrace();
        }
    }

}
