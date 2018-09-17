package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by Bernhard on 09.12.2017.
 */
//just a plain old Oldie, doing damage to one enemy.
public class Oldie extends GameObject {
    private int dmg;
    private int range;
    private float lookingDirection;
    private Sprite attackAnimation;


    private GameObject currentTarget;

    public Oldie(SurfaceView parentView, FloatPoint position, float walkingSpeed, Sprite sprite, boolean visible, int dmg, int range, Sprite attackAnimation) {
        super(parentView, position, walkingSpeed, sprite, visible);
        this.dmg=dmg;
        this.range=range;
        this.attackAnimation=attackAnimation;
        this.lookingDirection=0.5f; //up
    }

    public Oldie() {
        super();
        this.dmg=0;
        this.lookingDirection=0.5f; //up
    }

    @Override
    public void update(double deltaRatio) {
        //this.position.offset((float)deltaRatio,(float)deltaRatio);
        //if target!=Null => hit else
        //determine if something is in range
        //if yes, trigger hit
    }

    @Override
    public void draw(Canvas canvas, float deltaRatio) {
        this.sprite.draw(canvas, position.x, position.y);
    }
}
