package at.ac.tuwien.ims.uemultimediatemplate;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * The game logic is in this class. Here is where the magic happens.
 * It utilizes a {@link GameLoop}. So far it is only capable of drawing a rectangle and a line.
 * @author Bernhard Aschl
 *
 */
public class GameSurfaceview extends SurfaceView implements SurfaceHolder.Callback{

    public float DeltaRatio =1f;

    private GameLoop loop;
    private Thread loopThread;

    private final ArrayList<GameObject> enemies=new ArrayList<>();
    private final ArrayList<GameObject> oldies=new ArrayList<>();
    public GameObject Base;
    private Sprite enemySprite;


    //todo: enforce panorama

    /**
     * Pretty much standard constructor of this class.
     * Three versions of this constructor have to be implemented to avoid exceptions.
     * @param context
     */
    public GameSurfaceview(Context context) {
        super(context);

        Initialize();

        getHolder().addCallback(this);
        setFocusable(true);
    }

    //this one is used
    public GameSurfaceview(Context context, AttributeSet attrs) {
        super(context, attrs);

        Initialize();

        getHolder().addCallback(this);
        setFocusable(true);
    }

    public GameSurfaceview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs,defStyle);

        Initialize();

        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldWidth, int oldHeight){
        if(this.Base != null) {
            this.Base.position = new FloatPoint(newWidth / 2f, newHeight);
        }
    }

    private void Initialize(){
        Base=new GameObject(this,
                new FloatPoint(this.getWidth()/2f, this.getHeight()),
                0f,
                new Sprite(new Paint(Color.WHITE)),
                true);

        enemySprite=new Sprite(new Paint(Color.WHITE));

        Oldie ol=new Oldie();
        ol.position.offset(100f, 100f);
        ol.ParentView=this;

        oldies.add(ol);
    }

    /**
     * Is called after the surface is created.
     * The Gameloop is initialized here and started here.
     * @param surfaceHolder the surfaceHolder the game takes place in
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        loop = new GameLoop(surfaceHolder, this);
        loop.setRunning(true);
        loopThread =new Thread(loop);
        loopThread.start();

    }

    /**
     * Isn't used right now. Is called when something is changed on the surface.
     * @param surfaceHolder
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }


    /**
     * Is called after the surface is destroyed (game closed, back button...)
     * The gameloop is stopped here.
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        loop.setRunning(false);
        try {
            loopThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Here every gameobject will be drawn.
     * @param c The canvas to draw on.
     */
    @Override
    public void draw(Canvas c){

        if(c==null){
            Log.d("GameSurfaceview/draw", "Canvas is null");
            return;
        }

        super.draw(c);

        if(oldies!=null && enemies !=null) {

            for (GameObject oldie : oldies) {
                oldie.draw(c, DeltaRatio);
            }

            for(GameObject enemy : enemies){
                enemy.draw(c, DeltaRatio);
            }
        }


    }

    public void update(double dltRatio) {
        //spawn enemies randomly
        SpawnEnemies(dltRatio);

        if(oldies!=null && enemies != null){
            for (GameObject oldie : oldies){
                oldie.update(dltRatio);
            }
            for (GameObject enemy : enemies){
                enemy.update(dltRatio);
            }
        }

        this.invalidate();
    }

    private double elapsedTimeSinceLastSpawn=0;
    private void SpawnEnemies(double dltRatio) {

        elapsedTimeSinceLastSpawn+=dltRatio;

        while(elapsedTimeSinceLastSpawn>60){
            elapsedTimeSinceLastSpawn-=60;


            FloatPoint startPostion =
                    new FloatPoint((float) Math.random()*this.getWidth(), -10f);

            Enemy spawn = new Enemy(
                    this,
                    startPostion,
                    2,
                    enemySprite,
                    true,
                    100
            );
            enemies.add(spawn);

        }

    }
}
