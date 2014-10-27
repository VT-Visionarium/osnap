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


package graphVisualizer.gui.wizards;


import graphVisualizer.gui.wizards.statusobjects.IStatus;


/**
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IWizardWithStatus
        extends IWizard {

    /**
     * Returns the status object.
     * 
     * @return the status object.
     */
    public IStatus getStatusObject();
}
