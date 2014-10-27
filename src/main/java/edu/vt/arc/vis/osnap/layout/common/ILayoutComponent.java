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


package edu.vt.arc.vis.osnap.layout.common;


import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;

import java.util.Set;


/**
 * The <code>ILayoutComponent</code> interface provides a contract for classes
 * that provide layout options for {@link VisualProperty VisualProperties}.
 * 
 * @see VisualProperty
 * @see Visualization
 * @see VisualGraph
 * @see VisualNode
 * @see VisualEdge
 * @see VisualHyperEdge
 * @see IGraphObject
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public interface ILayoutComponent {

    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public abstract String getName();

    /**
     * Sets the name of this {@link ILayoutComponent}.
     * 
     * @param name
     *            the name.
     */
    public abstract void setName(String name);

    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public abstract String getDescription();

    /**
     * Sets the description of this {@link ILayoutComponent}.
     * 
     * @param description
     *            the description.
     */
    public abstract void setDescription(String description);


    /**
     * Returns the set of {@link VisualProperty VisualProperties} that this
     * {@link ILayoutComponent} can provide.
     * 
     * @return the set of visual properties provided by the layout component.
     */
    public abstract Set<VisualProperty> providesCapabilities();

    /**
     * Returns the set of {@link VisualProperty VisualProperties} that are
     * enabled.
     * 
     * @return the set of visual properties provided by the layout component.
     */
    public abstract Set<VisualProperty> enabledCapabilities();


    /**
     * Enables the output of the provided {@link VisualProperty} (if
     * applicable).
     * 
     * @param visualProperty
     *            the VisualProperty to enable.
     */
    public abstract void enable(VisualProperty visualProperty);


    /**
     * Disables the output of the provided {@link VisualProperty} (if
     * applicable).
     * 
     * @param visualProperty
     *            the VisualProperty to disable.
     */
    public abstract void disable(VisualProperty visualProperty);


    /**
     * Returns whether or not a {@link VisualProperty} is to be applied or not.
     * 
     * @param visualProperty
     *            the VisualProperty in question.
     * @return <code>true</code>, if the {@link VisualProperty} is to be
     *         applied; <code>false</code> otherwise.
     */
    public abstract boolean isEnabled(VisualProperty visualProperty);


    /**
     * Returns whether or not this {@link ILayoutComponent} will make any
     * changes to the {@link Visualization} when calling layout.
     * 
     * @return <code>true</code>, if the this {@link ILayoutComponent} will make
     *         changes to the {@link Visualization}; <code>false</code>
     *         otherwise.
     */
    public abstract boolean modifiesVisualization();


    /**
     * Returns whether or not this {@link ILayoutComponent} will make any
     * changes to {@link VisualNode VisualNodes} when calling layout.
     * 
     * @return <code>true</code>, if the provider will make changes;
     *         <code>false</code> otherwise.
     */
    public abstract boolean modifiesVisualNodes();


    /**
     * Returns whether or not this {@link ILayoutComponent} will make any
     * changes to {@link VisualEdge VisualEdges} when calling layout.
     * 
     * @return <code>true</code>, if the provider will make changes;
     *         <code>false</code> otherwise.
     */
    public abstract boolean modifiesVisualEdges();


    /**
     * Returns whether or not this {@link ILayoutComponent} will make any
     * changes to {@link VisualHyperEdge VisualHyperEdges} when calling layout.
     * 
     * @return <code>true</code>, if the provider will make changes;
     *         <code>false</code> otherwise.
     */
    public abstract boolean modifiesVisualHyperEdges();


    /**
     * Applies this {@link ILayoutComponent ILayoutComponent'scale}
     * {@link VisualProperty VisualProperties} to the provided
     * {@link Visualization}.
     * 
     * @param visualization
     *            the visualization to be laid out.
     */
    public abstract void layout(Visualization visualization);


    /**
     * Applies this {@link ILayoutComponent ILayoutComponent'scale}
     * {@link VisualProperty VisualProperties} to the provided
     * {@link VisualGraph}.
     * 
     * @param graph
     *            the graph to be laid out.
     */
    public abstract void layout(VisualGraph graph);


    /**
     * Applies this {@link ILayoutComponent ILayoutComponent'scale}
     * {@link VisualProperty VisualProperties} to the provided
     * {@link VisualNode}.
     * 
     * @param visualNode
     *            the visual node to lay out.
     */
    public abstract void layout(VisualNode visualNode);


    /**
     * Applies this {@link ILayoutComponent ILayoutComponent'scale}
     * {@link VisualProperty VisualProperties} to the provided
     * {@link VisualEdge}.
     * 
     * @param visualEdge
     *            the visual edge to lay out.
     */
    public abstract void layout(VisualEdge visualEdge);

    /**
     * Applies this {@link ILayoutComponent ILayoutComponent'scale}
     * {@link VisualProperty VisualProperties} to the provided
     * {@link VisualHyperEdge}.
     * 
     * @param visualHyperEdge
     *            the visual hyperedge to lay out.
     */
    public abstract void layout(VisualHyperEdge visualHyperEdge);


    /**
     * Returns whether or not this {@link ILayoutComponent} is restricted to a
     * certain set of {@link IGraphObject IGraphObjects}.
     * 
     * @return <code>true</code>, if this {@link ILayoutComponent} is
     *         restricted; <code>false</code> otherwise.
     */
    public abstract boolean isRestricted();


    /**
     * Returns the restriction of this {@link ILayoutComponent}, i.e., the set
     * of {@link IGraphObject IGraphObjects} it applies to.
     * 
     * @return the restriction.
     */
    public abstract Set<IGraphObject> getRestriction();


    /**
     * Sets the restriction of this {@link ILayoutComponent}, i.e., the set of
     * {@link IGraphObject IGraphObjects} it applies to.
     * 
     * @param restriction
     *            the restriction.
     * 
     */
    public abstract void setRestriction(Set<IGraphObject> restriction);


    /**
     * Clears the restriction of this {@link ILayoutComponent}, i.e., the set of
     * {@link IGraphObject IGraphObjects} it applies to.
     */
    public abstract void clearRestriction();
}
