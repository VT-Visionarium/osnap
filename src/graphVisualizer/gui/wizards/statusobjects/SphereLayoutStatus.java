/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


/**
 * 
 */
package graphVisualizer.gui.wizards.statusobjects;


/**
 * @author Shawn P Neuman
 *
 */
public class SphereLayoutStatus extends BaseStatusObject {
    
    
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;
    private float xScale;
    private float yScale;
    private float zScale;
    
    
    /**
     * Sphere layout status object
     * contains booleans to detect scaling on x, y or z axis
     */
    public SphereLayoutStatus() {
        // empty constructor by design
        // parent class is implemented
    }


    /**
     * @return the x
     */
    public boolean isX() {

        return x;
    }


    /**
     * @param x the x to set
     */
    public void setX(boolean x) {

        this.x = x;
    }


    /**
     * @return the y
     */
    public boolean isY() {

        return y;
    }


    /**
     * @param y the y to set
     */
    public void setY(boolean y) {

        this.y = y;
    }


    /**
     * @return the z
     */
    public boolean isZ() {

        return z;
    }


    /**
     * @param z the z to set
     */
    public void setZ(boolean z) {

        this.z = z;
    }


    /**
     * @return the xScale
     */
    public float getxScale() {

        return xScale;
    }


    /**
     * @param xScale the xScale to set
     */
    public void setxScale(float xScale) {

        this.xScale = xScale;
        setChanged();
        notifyObservers(this.xScale);
    }


    /**
     * @return the yScale
     */
    public float getyScale() {

        return yScale;
    }


    /**
     * @param yScale the yScale to set
     */
    public void setyScale(float yScale) {

        this.yScale = yScale;
        setChanged();
        notifyObservers(this.yScale);
    }


    /**
     * @return the zScale
     */
    public float getzScale() {

        return zScale;
    }


    /**
     * @param zScale the zScale to set
     */
    public void setzScale(float zScale) {

        this.zScale = zScale;
        setChanged();
        notifyObservers(this.zScale);
    }

}
