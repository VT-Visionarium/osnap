/**
 * 
 */
package edu.vt.arc.vis.osnap.javafx.wizards.statusobjects;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */


import org.jutility.math.vectorAlgebra.Vector4;

import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedViewpointLayoutComponent;


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
