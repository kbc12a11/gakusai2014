package lwjgltest;


import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class DrawMonolith {
    private int         width = 300;
    private int         height = 200;
    private int         depth = 200;

    private float           xAngle = 0;
    private float           yAngle = 0;
    private Direction       input = null;

    public void start() {
        try {
            //  ウインドウを生成する
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("monolith");
            Display.create();
        } catch(LWJGLException e) {
            e.printStackTrace();
            return;
        }

        try {
            //  OpenGL の初期設定

            //  ポリゴンの表示面を表のみに設定する
            glEnable(GL_CULL_FACE);
            glCullFace(GL_BACK);

            //  カメラ用の設定変更を宣言する
            glMatrixMode(GL_PROJECTION);
            //  設定を初期化する
            glLoadIdentity();
            //  視体積（目に見える範囲）を定義する
            glOrtho(0, width, 0, height, 0, depth);

            //  物体モデル用の設定変更を宣言する
            glMatrixMode(GL_MODELVIEW);

            while (!Display.isCloseRequested()) {
                //  オフスクリーンを初期化する
                glClear(GL_COLOR_BUFFER_BIT);

                //  キー入力を処理する
                update();

                //  オフスクリーンに描画する
                render();

                //  オフスクリーンをスクリーンに反映する
                Display.update();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Display.destroy();
        }
    }

    private void update() {
        input = Direction.getPressing();

        if (input != null) {
            xAngle = input.rotateXAngle(xAngle);
            yAngle = input.rotateYAngle(yAngle);
        }
    }

    private void render() {
        //  設定を初期化する
        glLoadIdentity();

        //  画面中央の座標を (0, 0, 0) とするよう座標系を移動する
        glTranslatef(width / 2f, height / 2f, depth / -2f);

        //  座標のスケールを指定する
        //  ここで指定した x, y, z 毎の係数が、次に指定する座標にそれぞれ掛けられる
        glScalef(20f, 20f, 20f);

        glRotatef(xAngle, 0, 1, 0); //  y 軸を中心に xAngle 度回転させる
        glRotatef(yAngle, 1, 0, 0); //  x 軸を中心に yAngle 度回転させる

        glBegin(GL_QUADS);

        for (Face face: Face.values()) {
            face.draw();
        }

        glEnd();
    }

    public static void main(String[] args) {
        new DrawMonolith().start();
    }

}