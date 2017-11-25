package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

/**
 * Created by Bernhard on 09.11.2017.
 */

class GameLoop implements Runnable {
    private SurfaceHolder holder;
    private GameSurfaceview view;
    private boolean running;
    private double dltRatio;

    public GameLoop(SurfaceHolder surfaceHolder, GameSurfaceview gameSurfaceview) {
        this.holder=surfaceHolder;
        this.view=gameSurfaceview;
        this.dltRatio =1;
        Log.v("GameLoop", "gameloop created");
    }

    public void setRunning(boolean running){
        this.running=running;
    }

    @Override
    public void run(){

        Log.d("GameLoop/run", "gameloop has started, running is "+running);
        //standard would be 60fps
        long lastFrameTime= System.currentTimeMillis();
        while (running){
            int deltaTime=(int)(lastFrameTime- System.currentTimeMillis());
            lastFrameTime= System.currentTimeMillis();
            dltRatio=deltaTime/(1f/60f);
            //frameunabhänigkeit wird nicht mit framedropping und delaying, sondern deltaRatio implementiert.
            //dabei wird errechnet, wie viel Zeit in Relation zu 1/60tel einer Sekunde vergangen ist
            //und alle Movements usw. werden mit dem Wert (dltRatio) multipliziert.

            //update

            Canvas c;
            c=holder.lockCanvas();

            try {
                synchronized (holder) {
                    if(view!=null)
                        view.draw(c);
                }
            }finally{
                if(c!=null){
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
    }


}
