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


import java.util.List;
import java.util.Set;

import graphVisualizer.graph.common.IGraphObject;
import graphVisualizer.layout.common.ILayoutComponent;
import graphVisualizer.visualization.VisualProperty;


/**
 * Status object interface
 * 
 * @author Shawn P Neuman
 * 
 */
public interface IStatus
        extends IObservable {


    /**
     * @return the visual property choice
     */
    public Set<VisualProperty> getVisualProperty();

    /**
     * @param visualProperty
     *            the visual property choice
     */
    public void setVisualProperty(VisualProperty visualProperty);

    /**
     * @param visualProperty
     *            the visual property choice
     */
    public void setVisualProperty(Set<VisualProperty> visualProperty);


    /**
     * @return the list of IGraph objects
     */
    public List<IGraphObject> getGraphObjectList();


    /**
     * @param graphObjectList
     *            the graph object list.
     */
    public void setGraphObjectList(List<IGraphObject> graphObjectList);

    /**
     * 
     * @param layoutComponent
     *            the layout component.
     */
    public void setLayoutComponent(ILayoutComponent layoutComponent);

    /**
     * @return a layout component synonymous to mapping
     */
    public ILayoutComponent getLayoutComponent();

}
