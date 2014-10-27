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


import graphVisualizer.graph.common.IGraphObject;
import graphVisualizer.layout.common.ILayoutComponent;
import graphVisualizer.visualization.VisualProperty;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;


/**
 * Status object to hold base layout info
 * 
 * @author Shawn P Neuman
 * 
 */
public class BaseStatusObject
        extends Observable
        implements IStatus {

    // node...shape, color, etc, or edge...shape, etc
    private Set<VisualProperty> visualPropertySet;

    private List<IGraphObject>  graphObjectList;

    private ILayoutComponent    layout;

    @Override
    public Set<VisualProperty> getVisualProperty() {

        return this.visualPropertySet;
    }

    @Override
    public void setVisualProperty(VisualProperty visualProperty) {

        if (visualProperty == null) {
            visualPropertySet = null;
        }
        else {
            this.visualPropertySet = new LinkedHashSet<>();
            this.visualPropertySet.add(visualProperty);
        }
        setChanged();
        notifyObservers(getVisualProperty());
    }

    @Override
    public void setVisualProperty(Set<VisualProperty> visualProperty) {

        if (visualProperty != null) {
            this.visualPropertySet = visualProperty;
            // setChanged();
            // notifyObservers(getVisualProperty());
        }
        setChanged();
        notifyObservers(this.visualPropertySet);

    }

    @Override
    public List<IGraphObject> getGraphObjectList() {

        return graphObjectList;
    }

    @Override
    public void setGraphObjectList(List<IGraphObject> graphObjectList) {

        this.graphObjectList = graphObjectList;
        setChanged();
        notifyObservers(getGraphObjectList());

    }

    @Override
    public void setLayoutComponent(ILayoutComponent layoutComponent) {

        this.layout = layoutComponent;
        setChanged();
        notifyObservers(layoutComponent);
    }

    @Override
    public ILayoutComponent getLayoutComponent() {

        return this.layout;
    }

    /**
     * Clears the graph object list.
     */
    public void clearGraphObjectList() {

        this.graphObjectList = new ArrayList<>();
        setGraphObjectList(graphObjectList);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#addObserver(java.util.Observer)
     */
    @Override
    public synchronized void addObserver(Observer o) {

        super.addObserver(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#clearChanged()
     */
    @Override
    public synchronized void clearChanged() {

        super.clearChanged();
    }

    @Override
    public int countObservers() {

        return super.countObservers();
    }

    @Override
    public void deleteObserver(Observer o) {

        super.deleteObserver(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#deleteObservers()
     */
    @Override
    public synchronized void deleteObservers() {

        super.deleteObservers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#hasChanged()
     */
    @Override
    public synchronized boolean hasChanged() {

        return super.hasChanged();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#notifyObservers()
     */
    @Override
    public void notifyObservers() {

        super.notifyObservers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#notifyObservers(java.lang.Object)
     */
    @Override
    public void notifyObservers(Object arg) {

        super.notifyObservers(arg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observable#setChanged()
     */
    @Override
    public synchronized void setChanged() {

        super.setChanged();
    }





}
