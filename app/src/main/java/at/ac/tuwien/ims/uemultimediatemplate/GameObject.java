package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

/**
 * Created by Bernhard on 09.12.2017.
 */

public class GameObject {
    protected FloatPoint position;
    protected float walkingSpeed;
    protected Sprite sprite;
    protected boolean visible;

    public SurfaceView ParentView;

    public GameObject(SurfaceView parentView, FloatPoint floatPoint, float walkingSpeed, Sprite sprite, boolean visible) {
        this.ParentView=parentView;
        this.position = floatPoint;
        this.walkingSpeed = walkingSpeed;
        this.sprite = sprite;
        this.visible = visible;
    }

    public GameObject() {
        this.position=new FloatPoint(0,0);
        this.walkingSpeed=0;
        this.sprite=new Sprite(new Paint(Color.WHITE));
        this.visible=true;
    }

    public  void update(double deltaRatio){}
    public void draw(Canvas canvas, float deltaRatio){}
}
