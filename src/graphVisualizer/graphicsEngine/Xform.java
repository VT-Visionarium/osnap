package graphVisualizer.graphicsEngine;


import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;


/**
 * Simple Transform class.
 * 
 * @author William H. Lund
 * @version 1.0
 * @since 1.0
 *
 */
public class Xform
        extends Group {

    private Translate translation  = new Translate();

    private Translate pivot        = new Translate();
    private Translate inversePivot = new Translate();
    private Rotate    rotationX    = new Rotate();
    {
        rotationX.setAxis(Rotate.X_AXIS);
    }
    private Rotate    rotationY    = new Rotate();
    {
        rotationY.setAxis(Rotate.Y_AXIS);
    }
    private Rotate    rotationZ    = new Rotate();
    {
        rotationZ.setAxis(Rotate.Z_AXIS);
    }
    private Scale     scale        = new Scale();



    /**
     * Returns the translation.
     * 
     * @return the translation.
     */
    public Translate getTranslation() {

        return translation;
    }


    /**
     * Sets the translation.
     * 
     * @param translation
     *            the translation.
     */
    public void setTranslation(Translate translation) {

        this.translation = translation;
    }


    /**
     * Returns the pivot.
     * 
     * @return the pivot.
     */
    public Translate getPivot() {

        return pivot;
    }


    /**
     * Sets the pivot.
     * 
     * @param pivot
     *            the pivot.
     */
    public void setPivot(Translate pivot) {

        this.pivot = pivot;
    }


    /**
     * Returns the inverse pivot.
     * 
     * @return the inverse pivot.
     */
    public Translate getInversePivot() {

        return inversePivot;
    }


    /**
     * Sets the inverse pivot.
     * 
     * @param inversePivot
     *            the inverse pivot.
     */
    public void setInversePivot(Translate inversePivot) {

        this.inversePivot = inversePivot;
    }


    /**
     * Returns the rotationX.
     * 
     * @return the rotationX.
     */
    public Rotate getRotationX() {

        return rotationX;
    }


    /**
     * Sets the rotationX.
     * 
     * @param rotationX
     *            the rotationX.
     */
    public void setRotationX(Rotate rotationX) {

        this.rotationX = rotationX;
    }


    /**
     * Returns the rotationY.
     * 
     * @return the rotationY.
     */
    public Rotate getRotationY() {

        return rotationY;
    }


    /**
     * Sets the rotationY.
     * 
     * @param rotationY
     *            the rotationY.
     */
    public void setRotationY(Rotate rotationY) {

        this.rotationY = rotationY;
    }


    /**
     * Returns the rotationZ.
     * 
     * @return the rotationZ.
     */
    public Rotate getRotationZ() {

        return rotationZ;
    }


    /**
     * Sets the rotationZ.
     * 
     * @param rotationZ
     *            the rotationZ.
     */
    public void setRotationZ(Rotate rotationZ) {

        this.rotationZ = rotationZ;
    }


    /**
     * Returns the scale.
     * 
     * @return the scale.
     */
    public Scale getScale() {

        return scale;
    }


    /**
     * Sets the scale.
     * 
     * @param scale
     *            the scale.
     */
    public void setScale(Scale scale) {

        this.scale = scale;
    }

    /**
     * Creates a new instance of the {@link Xform} class.
     */
    public Xform() {

        super();
        getTransforms().addAll(translation, rotationZ, rotationY, rotationX,
                scale);
    }

    /**
     * Creates a new instance of the {@link Xform} class with the provided
     * rotation order.
     * 
     * @param rotateOrder
     *            the rotation order.
     */
    public Xform(RotateOrder rotateOrder) {

        super();
        // choose the order of rotations based on the rotateOrder
        switch (rotateOrder) {
            case XYZ:
                getTransforms().addAll(translation, pivot, rotationZ,
                        rotationY, rotationX, scale, inversePivot);
                break;
            case XZY:
                getTransforms().addAll(translation, pivot, rotationY,
                        rotationZ, rotationX, scale, inversePivot);
                break;
            case YXZ:
                getTransforms().addAll(translation, pivot, rotationZ,
                        rotationX, rotationY, scale, inversePivot);
                break;
            case YZX:
                getTransforms().addAll(translation, pivot, rotationX,
                        rotationZ, rotationY, scale, inversePivot); // For
                // Camera
                break;
            case ZXY:
                getTransforms().addAll(translation, pivot, rotationY,
                        rotationX, rotationZ, scale, inversePivot);
                break;
            case ZYX:
                getTransforms().addAll(translation, pivot, rotationX,
                        rotationY, rotationZ, scale, inversePivot);
                break;
        }
    }

    /**
     * Sets the translation.
     * 
     * @param x
     *            the x translation.
     * @param y
     *            the y translation.
     * @param z
     *            the z translation.
     */
    public void setTranslate(double x, double y, double z) {

        translation.setX(x);
        translation.setY(y);
        translation.setZ(z);
    }

    /**
     * Sets the translation.
     * 
     * @param x
     *            the x translation.
     * @param y
     *            the y translation.
     */
    public void setTranslate(double x, double y) {

        translation.setX(x);
        translation.setY(y);
    }

    // Cannot override these methods as they are final:
    // public void setTranslateX(double x) { t.setX(x); }
    // public void setTranslateY(double y) { t.setY(y); }
    // public void setTranslateZ(double z) { t.setZ(z); }
    // Use these methods instead:
    /**
     * Sets the x translation.
     * 
     * @param x
     *            the x translation.
     */
    public void setTranslationX(double x) {

        translation.setX(x);
    }

    /**
     * Sets the y translation.
     * 
     * @param y
     *            the y translation.
     */
    public void setTranslationY(double y) {

        translation.setY(y);
    }

    /**
     * Sets the z translation.
     * 
     * @param z
     *            the z translation.
     */
    public void setTranslationZ(double z) {

        translation.setZ(z);
    }

    /**
     * Sets the rotation angles around each axis.
     * 
     * @param x
     *            the angle of rotation around the x axis.
     * @param y
     *            the angle of rotation around the y axis.
     * @param z
     *            the angle of rotation around the z axis.
     */
    public void setRotate(double x, double y, double z) {

        rotationX.setAngle(x);
        rotationY.setAngle(y);
        rotationZ.setAngle(z);
    }

    /**
     * Sets the rotation around the x axis.
     * 
     * @param x
     *            the rotation around the x axis.
     */
    public void setRotateX(double x) {

        rotationX.setAngle(x);
    }

    /**
     * Sets the rotation around the y axis.
     * 
     * @param y
     *            the rotation around the y axis.
     */
    public void setRotateY(double y) {

        rotationY.setAngle(y);
    }

    /**
     * Sets the rotation around the z axis.
     * 
     * @param z
     *            the rotation around the z axis.
     */
    public void setRotateZ(double z) {

        rotationZ.setAngle(z);
    }


    /**
     * Sets the uniform scaling factor.
     * 
     * @param scaleFactor
     *            the uniform scaling factor.
     */
    public void setScale(double scaleFactor) {

        scale.setX(scaleFactor);
        scale.setY(scaleFactor);
        scale.setZ(scaleFactor);
    }

    /**
     * Sets the scaling
     * 
     * @param x
     *            the scaling of the x dimension.
     * @param y
     *            the scaling of the y dimension.
     * @param z
     *            the scaling of the z dimension.
     */
    public void setScale(double x, double y, double z) {

        scale.setX(x);
        scale.setY(y);
        scale.setZ(z);
    }

    /**
     * Sets the scaling of the x dimension.
     * 
     * @param x
     *            the scaling.
     */
    public void setScalingX(double x) {

        scale.setX(x);
    }

    /**
     * Sets the scaling of the y dimension.
     * 
     * @param y
     *            the scaling.
     */
    public void setScalingY(double y) {

        scale.setY(y);
    }

    /**
     * Sets the scaling of the z dimension.
     * 
     * @param z
     *            the scaling.
     */
    public void setScalingZ(double z) {

        scale.setZ(z);
    }

    /**
     * Sets the pivot to the provided values.
     * 
     * @param x
     *            the x value.
     * @param y
     *            the y value.
     * @param z
     *            the z value.
     */
    public void setPivot(double x, double y, double z) {

        pivot.setX(x);
        pivot.setY(y);
        pivot.setZ(z);
        inversePivot.setX(-x);
        inversePivot.setY(-y);
        inversePivot.setZ(-z);
    }

    /**
     * Resets the translation, rotation, scale, and pivot.
     */
    public void reset() {

        translation.setX(0.0);
        translation.setY(0.0);
        translation.setZ(0.0);
        rotationX.setAngle(0.0);
        rotationY.setAngle(0.0);
        rotationZ.setAngle(0.0);
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
        pivot.setX(0.0);
        pivot.setY(0.0);
        pivot.setZ(0.0);
        inversePivot.setX(0.0);
        inversePivot.setY(0.0);
        inversePivot.setZ(0.0);
    }

    /**
     * Resets the translation, scale, and pivot.
     */
    public void resetTranslationScaleAndPivot() {

        translation.setX(0.0);
        translation.setY(0.0);
        translation.setZ(0.0);
        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
        pivot.setX(0.0);
        pivot.setY(0.0);
        pivot.setZ(0.0);
        inversePivot.setX(0.0);
        inversePivot.setY(0.0);
        inversePivot.setZ(0.0);
    }

    /**
     * Prints debug output.
     */
    public void debug() {

        System.out.println("t = (" + translation.getX() + ", "
                + translation.getY() + ", " + translation.getZ() + ")  "
                + "r = (" + rotationX.getAngle() + ", " + rotationY.getAngle()
                + ", " + rotationZ.getAngle() + ")  " + "scale = ("
                + scale.getX() + ", " + scale.getY() + ", " + scale.getZ()
                + ")  " + "pivot = (" + pivot.getX() + ", " + pivot.getY()
                + ", " + pivot.getZ() + ")  " + "inversePivot = ("
                + inversePivot.getX() + ", " + inversePivot.getY() + ", "
                + inversePivot.getZ() + ")");
    }



    /**
     * Order of rotation around axes.
     * 
     * @author William H. Lund
     * @version 1.0
     * @since 1.0
     *
     */
    public static enum RotateOrder {
        /**
         * x, then y, then z.
         */
        XYZ,
        /**
         * x, then z, then y.
         */
        XZY,
        /**
         * y, then x, then z.
         */
        YXZ,
        /**
         * y, then z, then x.
         */
        YZX,
        /**
         * z, then x, then y.
         */
        ZXY,
        /**
         * z, then y, then x.
         */
        ZYX
    }
}