package at.ac.tuwien.ims.uemultimediatemplate;

/**
 * Created by Bernhard on 10.12.2017.
 */

public class FloatPoint {
    public float x,y;

    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Generates a new floatPoint with the same coordinates as floatPoint
     */
    public FloatPoint(FloatPoint floatPoint) {
        this.x = floatPoint.x;
        this.y = floatPoint.y;
    }

    /**
     * Add x and y to the objects x and y. The Parameters may be negative.
     */
    public void offset(float x, float y){
        this.x+=x;
        this.y+=y;
    }

    /**
     * Add the point to this. The Parameters may be negative.
     */
    public void offset(FloatPoint offset){
        this.x+=offset.x;
        this.y+=offset.y;
    }

    /**
     * Multiply the object's x and y by mult.
     * @param mult The scalar to multiply by.
     */
    public void multiply(float mult){
        this.multiply(mult, mult);
    }

    /**
     * Multiply x and y by xMult and yMult
     */
    public void multiply(float xMult, float yMult){
        this.x*=xMult;
        this.y*=yMult;
    }

    /**
     * Multiply the Object's x by floatPoint.x and y by floatPoint.y
     * @param floatPoint The other FloatPoint to multiply element-wise by.
     */
    public void multiply(FloatPoint floatPoint){
        this.x*= floatPoint.x;
        this.y*= floatPoint.y;
    }

    /**
     * Normalize this object.
     */
    public void normalize(){
        float len=this.length();
        this.x/=len;
        this.y/=len;
    }

    public float length(){
        return (float) Math.sqrt((this.x*this.x)+(this.y*this.y));
    }

    /**
     * Generate a new normalized FloatPoint from the given floatPoint
     * @param floatPoint the floatPoint to normalize
     * @return the normalized floatPoint
     */
    public static FloatPoint normalize(FloatPoint floatPoint){
        FloatPoint result=new FloatPoint(floatPoint);
        result.normalize();
        return result;
    }

    /**
     * Get the dot-Product of two floatPoints
     */
    public static float dot(FloatPoint p1, FloatPoint p2){
        return p1.x*p2.x+p1.y*p2.y;
    }

    /**
     * Substracts a FloatPoint from another and return the result
     */
    public static FloatPoint Substract(FloatPoint p1, FloatPoint p2){
        return new FloatPoint(p1.x-p2.x, p1.y-p2.y);
    }


    /**
     * @return fp[x,y]
     */
    @Override
    public String toString() {
        return "fp["+x+","+y+"]";
    }
}
