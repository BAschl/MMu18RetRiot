package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * The GameLoop-Thread the Game runs on.
 * @author Bernhard Aschl
 */

class GameLoop implements Runnable {

    private boolean running;
    private float dltRatio;

    private SurfaceHolder holder;
    private GameSurfaceview view;

    /**
     * The constructor for this GameLoop.
     * @param surfaceHolder
     * @param gameSurfaceview The GameSurfaceView this Gameloop is for.
     */
    public GameLoop(SurfaceHolder surfaceHolder, GameSurfaceview gameSurfaceview) {
        holder=surfaceHolder;
        view=gameSurfaceview;
        dltRatio =1;
        Log.v("GameLoop", "gameloop created");
    }

    /**
     * Sets the running state of the thread.
     * @param running If running is true, the thread starts running (as soon as started) If running is false it stops.
     */
    public void setRunning(boolean running){
        this.running=running;
    }

    /**
     * Here the actual gameloop is implemented. Doesn't create a thread when run is called.
     * Frame-independence is implemented here with DeltaRatio.
     */
    @Override
    public void run(){

        Log.d("GameLoop/run", "gameloop has started, running is "+running);

        long lastFrameTime= System.currentTimeMillis();


        while (running){

            int deltaTime=(int)(System.currentTimeMillis()-lastFrameTime);
            lastFrameTime= System.currentTimeMillis();
            dltRatio=(deltaTime/(1f/60f))/1000;

            //frameunabhänigkeit wird nicht mit framedropping und delaying, sondern DeltaRatio implementiert.
            //dabei wird errechnet, wie viel Zeit in Relation zu 1/60tel einer Sekunde vergangen ist
            //und alle Movements usw. werden mit dem Wert (dltRatio) multipliziert.

            //update

            view.update(dltRatio);

            Canvas c;
            c=holder.lockCanvas();

            try {

                synchronized (holder) {
                    if(view!=null) {

                        view.DeltaRatio =dltRatio;
                        view.draw(c);

                    }
                }

            }finally{
                if(c!=null){
                    holder.unlockCanvasAndPost(c);
                }
            }
        }

    }


}
