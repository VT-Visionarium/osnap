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
 * Status object to hold info for the layout matching its name
 * @author Shawn P Neuman
 * 
 */
public class LabelStatus
        extends BaseStatusObject {
    
    private String label;

    /**
     * 
     */
    public LabelStatus() {

        
    }

    /**
     * @param label the label assigned to the restrictions
     */
    public void setLabelText(String label){
        this.label = label;
        setChanged();
        notifyObservers(getLabelText());
    }
    
    /**
     * @return the label for the restrictions
     */
    public String getLabelText(){
        return this.label;
    }
}
