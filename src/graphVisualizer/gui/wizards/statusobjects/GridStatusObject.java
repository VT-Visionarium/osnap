/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


/**
 * 
 */
package graphVisualizer.gui.wizards.statusobjects;


/**
 * @author Shawn P Neuman
 *
 */
public class GridStatusObject
        extends BaseStatusObject {


    private float xScale = 1;
    private float yScale = 1;

    /**
     * constructor for 2d grid status object
     */
    public GridStatusObject() {

        // empty by design
    }

    /**
     * @param x
     */
    public void setxScale(float x) {

        this.xScale = x;
        setChanged();
        notifyObservers(getxScale());
        // System.out.println("setting x scale to: " + getxScale());

    }

    /**
     * @param y
     */
    public void setyScale(float y) {

        this.yScale = y;
        setChanged();
        notifyObservers(getyScale());
        // System.out.println("setting y scale to: " + getyScale());
    }

    /**
     * @return the y distance scale
     */
    public float getyScale() {

        return this.yScale;
    }

    /**
     * @return the x distance scale
     */
    public float getxScale() {

        return this.xScale;
    }



}
