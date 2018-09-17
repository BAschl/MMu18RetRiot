package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Canvas;
import android.view.SurfaceView;


public class Enemy extends GameObject {
    //DirectionVector
    private FloatPoint lookingDirection;
    private EnemyStatus status;
    private float hp;

    public Enemy(SurfaceView parentView, FloatPoint position, float walkingSpeed, Sprite sprite, boolean visible, float hp) {
        super(parentView, position, walkingSpeed, sprite, visible);
        this.status=EnemyStatus.NORMAL;
        this.lookingDirection=new FloatPoint(position);
        this.lookingDirection.multiply(-1);
        this.hp=hp;
    }

    @Override
    public void update(double deltaRatio) {
        //default behavior: run to base
        if(status==EnemyStatus.NORMAL)
        {
            if(this.ParentView instanceof GameSurfaceview) {
                GameSurfaceview gsv = (GameSurfaceview)ParentView;

                FloatPoint step = FloatPoint.normalize(FloatPoint.Substract(gsv.Base.position, this.position));
                step.multiply(this.walkingSpeed*(float)deltaRatio);

                this.position.offset(step);

            }
            //this.position.offset(this.lookingDirection.x*this.walkingSpeed*(float)deltaRatio, this.lookingDirection.y*this.walkingSpeed*(float)deltaRatio);
        }
    }

    @Override
    public void draw(Canvas canvas, float deltaRatio) {
        this.sprite.draw(canvas, position.x, position.y);
    }
}
