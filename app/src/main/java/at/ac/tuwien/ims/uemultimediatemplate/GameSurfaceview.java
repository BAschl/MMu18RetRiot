package at.ac.tuwien.ims.uemultimediatemplate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * TODO: document your custom view class.
 */
public class GameSurfaceview extends SurfaceView implements SurfaceHolder.Callback{


    private GameLoop loop;
    private Thread loopThread;

    public GameSurfaceview(Context context) {
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);
    }
    public GameSurfaceview(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        setFocusable(true);
    }
    public GameSurfaceview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs,defStyle);

        getHolder().addCallback(this);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        loop = new GameLoop(surfaceHolder, this);
        loop.setRunning(true);
        loopThread =new Thread(loop);
        loopThread.start();

    }



    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        loop.setRunning(false);
        try {
            loopThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Canvas c){
        if(c==null){
            Log.d("GameSurfaceview/draw", "Canvas is null");
            return;
        }
        super.draw(c);

        Paint p= new Paint();
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        c.drawRect(new Rect(50, 30, 100, 70), p);
        Paint p2=new Paint();
        p2.setColor(Color.WHITE);
        p2.setStyle(Paint.Style.FILL);
        c.drawLine(0f, 0f, 100f, 100f, p2);
    }
}
