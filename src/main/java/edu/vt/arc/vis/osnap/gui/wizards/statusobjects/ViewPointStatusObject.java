/**
 * 
 */
package edu.vt.arc.vis.osnap.gui.wizards.statusobjects;

import org.jutility.math.vectorAlgebra.Vector4;

import edu.vt.arc.vis.osnap.layout.mappedComponents.MappedViewpointLayoutComponent;


/**
 * @author Shawn P Neuman
 *
 */
public class ViewPointStatusObject extends BaseStatusObject{

    

    private float               cameraX = 0f;
    private float               cameraY = 0f;
    private float               cameraZ = 20f;
    
    /**
     * 
     */
    public ViewPointStatusObject() {

        
    }
    
    
    /**
     * @param cameraX
     *            the cameraX to set
     */
    public void setCameraX(float cameraX) {

        this.cameraX = cameraX;
        setChanged();
        notifyObservers(cameraX);
    }


    /**
     * @param cameraY
     *            the cameraY to set
     */
    public void setCameraY(float cameraY) {

        this.cameraY = cameraY;
        setChanged();
        notifyObservers(cameraY);
    }



    /**
     * @param cameraZ
     *            the cameraZ to set
     */
    public void setCameraZ(float cameraZ) {

        this.cameraZ = cameraZ;
        setChanged();
        notifyObservers(cameraZ);
    }


    /**
     * @return camera offset in x direction
     */
    public float getCameraX() {

        return cameraX;
    }


    /**
     * @return camera offset in y direction
     */
    public float getCameraY() {

        return cameraY;
    }


    /**
     * @return camera offset in z direction
     */
    public float getCameraZ() {

        return cameraZ;
    }


    /**
     * @param offX
     * @param offY
     * @param offZ
     */
    public void setVector(float offX, float offY, float offZ) {

        Vector4<Float> vect = new Vector4<>(offX, offY, offZ, Float.class);
        ((MappedViewpointLayoutComponent)this.getLayoutComponent()).setOffset(vect);
        setChanged();
        notifyObservers();

        
    }
    
    

}
