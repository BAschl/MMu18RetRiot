package at.ac.tuwien.ims.uemultimediatemplate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Bernhard on 09.12.2017.
 */
public class Sprite {
    private Bitmap sprite;
    private int frameWidth;
    private int frameHeight;
    private int totalFrames;
    private int currentFrame;
    private boolean endlessAnimation;
    private int timePerFrameMillis;
    private long startTime;
    public Paint NoBitmapPaint;

    public Sprite(File file) {
        try {
            JSONObject obj=new JSONObject((new Scanner(file)).useDelimiter("\\A").next());
            this.sprite= BitmapFactory.decodeFile(obj.getString("BitmapFilePath"));
            this.totalFrames=obj.getInt("FramesAmount");
            this.endlessAnimation=obj.getBoolean("EndlessAnimation");
            this.timePerFrameMillis=Math.round(1000f/obj.getInt("FramesPerSecond"));

            this.frameHeight = this.sprite.getHeight()/this.totalFrames;
            this.frameWidth =  this.sprite.getWidth();
            this.currentFrame=0;
            //hasn't started yet - set to curTime if draw or start is called
            this.startTime=0;
        } catch (JSONException e) {
            Log.e("Sprite|Sprite(File)", "There was a Problem with the JSON of the Sprite with the directory "+file.getAbsolutePath());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Log.e("Sprite|Sprite(File)", "The File doesn't seem to exist. Filepath: "+file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public Sprite(Paint noBitmapPaint){
        this.NoBitmapPaint=noBitmapPaint;
        this.frameHeight=20;
        this.frameWidth=20;
    }

    // Prüfen, ob es Zeit für den nächsten Frame ist
    public void update() {
        long curTime=System.currentTimeMillis();
        //todo
    }

    public void draw(Canvas canvas, float x, float y, float scale){
        this.draw(canvas, x,y,this.frameHeight*scale, this.frameWidth*scale);
    }

    public void draw(Canvas canvas, float x, float y) {
        this.draw(canvas, x,y,this.frameHeight, this.frameWidth);
    }

    // Zeichnen des aktuellen Frames auf dem Canvas
    public void draw(Canvas canvas, float x, float y, float height, float width) {
        RectF dst=new RectF(x,y,x+width,y+height);
        if(sprite!=null){
            Rect src=new Rect(frameWidth*currentFrame, 0, frameWidth*(currentFrame+1),frameHeight);
            canvas.drawBitmap(sprite, src,dst,null);
        }
        else {
            canvas.drawRect(dst, NoBitmapPaint);
        }
    }
}
