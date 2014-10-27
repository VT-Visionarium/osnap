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


package edu.vt.arc.vis.osnap.gui.wizards.content;


import edu.vt.arc.vis.osnap.visualization.VisualProperty;


/**
 * capabilities object stores visual property and boolean value for each vpp
 * capability designed for easier use of capabilities table
 * 
 * @author Shawn P Neuman
 */
public class CapabilitiesObject {


    private VisualProperty vp;
    private Boolean        enabled;

    /**
     * @param vp
     * @param enabled
     * 
     */
    public CapabilitiesObject(VisualProperty vp, Boolean enabled) {

        setVisualProperty(vp);
        setEnabled(enabled);
    }

    /**
     * @return this visual property
     */
    public VisualProperty getVisualProperty() {

        return this.vp;

    }

    /**
     * @param vp
     */
    public void setVisualProperty(VisualProperty vp) {

        this.vp = vp;
    }

    /**
     * @return if enabled true, else false
     */
    public Boolean isEnabled() {

        return this.enabled;
    }

    /**
     * @param enabled
     *            true or false
     */
    public void setEnabled(Boolean enabled) {

        this.enabled = enabled;
    }

}
