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


package edu.vt.arc.vis.osnap.layout.common;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.vt.arc.vis.osnap.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.visualization.IVisualGraphObject;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.visualization.Visualization;


/**
 * The abstract {@link BaseLayoutComponent} class provides the common
 * functionality for all @{link ILayoutComponent layout components}.
 * <p/>
 * Notably, it provides the mechanics for enabling/disabling capabilities of the
 * provider.
 * 
 * @see ILayoutComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseLayoutComponent")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ BaseColorLayoutComponent.class })
public abstract class BaseLayoutComponent
        implements ILayoutComponent {

    @XmlElementWrapper(name = "Capabilities")
    @XmlElement(name = "VisualProperty")
    private final Map<VisualProperty, Boolean> capabilities;


    @XmlIDREF
    @XmlElementWrapper(name = "Restriction")
    @XmlElements({ @XmlElement(name = "Node", type = NodeBase.class),
            @XmlElement(name = "Edge", type = EdgeBase.class),
            @XmlElement(name = "HyperEdge", type = HyperEdgeBase.class) })
    private final Set<IGraphObject>            restriction;

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private String                             name;
    @XmlElement(name = "Description")
    private String                             description;


    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Layout Component";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Visual Property.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return new LinkedHashSet<>();
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#getName()
     */
    @Override
    public String getName() {

        return this.name;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {

        this.name = name;


    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#getDescription()
     */
    @Override
    public String getDescription() {

        return this.description;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#setDescription(java.lang
     * .String)
     */
    @Override
    public void setDescription(String description) {

        this.description = description;
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#isRestricted()
     */
    @Override
    public boolean isRestricted() {

        return !this.restriction.isEmpty();
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#getRestriction()
     */
    @Override
    public Set<IGraphObject> getRestriction() {

        if (restriction != null) {
            return new LinkedHashSet<>(
                    Collections.unmodifiableSet(this.restriction));
        }

        return null;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#setRestriction(java.util
     * .Set)
     */
    @Override
    public void setRestriction(Set<IGraphObject> restriction) {

        if (restriction != null) {
            this.clearRestriction();
            this.restriction.addAll(restriction);
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#clearRestriction()
     */
    @Override
    public void clearRestriction() {

        if (this.restriction != null) {
            this.restriction.clear();
        }
    }

    /**
     * Creates a new instance of the {@link BaseLayoutComponent} class.
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private BaseLayoutComponent() {

        this(null, null, null, false, true);
    }

    /**
     * Creates a new instance of the {@link BaseLayoutComponent} class. Note
     * that by default all capabilities are disabled! They are enabled by a
     * {@link edu.vt.arc.vis.osnap.layout.Layout} when the provider is assigned to a
     * specific {@link edu.vt.arc.vis.osnap.visualization.VisualProperty}.
     * 
     * @param capabilities
     *            the set of
     *            {@link edu.vt.arc.vis.osnap.visualization.VisualProperty
     *            VisualProperties} that can be provided by this class.
     */
    public BaseLayoutComponent(final Set<VisualProperty> capabilities) {

        this(capabilities, BaseLayoutComponent.name(), BaseLayoutComponent
                .description(), true);
    }


    /**
     * Creates a new instance of the {@link BaseLayoutComponent} class. Note
     * that by default all capabilities are disabled! The are enabled by a
     * {@link edu.vt.arc.vis.osnap.layout.Layout} when the provider is assigned to a
     * specific {@link edu.vt.arc.vis.osnap.visualization.VisualProperty}. It sets
     * the description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param capabilities
     *            the set of
     *            {@link edu.vt.arc.vis.osnap.visualization.VisualProperty
     *            VisualProperties} that can be provided by this class.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     */
    public BaseLayoutComponent(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName) {

        this(capabilities, name, description, randomizeName, false);
    }

    /**
     * Creates a new instance of the {@link BaseLayoutComponent} class. Note
     * that by default all capabilities are disabled! The are enabled by a
     * {@link edu.vt.arc.vis.osnap.layout.Layout} when the provider is assigned to a
     * specific {@link edu.vt.arc.vis.osnap.visualization.VisualProperty}. It sets
     * the description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param capabilities
     *            the set of
     *            {@link edu.vt.arc.vis.osnap.visualization.VisualProperty
     *            VisualProperties} that can be provided by this class.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected BaseLayoutComponent(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName, final boolean serialization) {

        if ((capabilities == null || capabilities.isEmpty()) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create VisualPropertyProvider without capabilities!");

        }

        this.capabilities = new LinkedHashMap<>();

        if (capabilities != null) {
            for (VisualProperty visualProperty : capabilities) {

                this.capabilities.put(visualProperty, false);
            }
        }

        this.restriction = new LinkedHashSet<>();
        if (name != null && randomizeName) {
            Random random = new Random();
            this.name = name + " - " + random.nextLong();
        }
        else {
            this.name = name;
        }
        this.description = description;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#providesCapabilities()
     */
    @Override
    public Set<VisualProperty> providesCapabilities() {

        return Collections.unmodifiableSet(this.capabilities.keySet());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#providesCapabilities()
     */
    @Override
    public Set<VisualProperty> enabledCapabilities() {

        Set<VisualProperty> enabledCapabilities = new LinkedHashSet<>();

        for (VisualProperty capability : this.providesCapabilities()) {

            if (this.isEnabled(capability)) {

                enabledCapabilities.add(capability);
            }
        }

        return enabledCapabilities;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#enable(edu.vt.arc.vis.osnap
     * .visualization.VisualProperty)
     */
    @Override
    public void enable(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            this.capabilities.put(visualProperty, true);
        }
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#isEnabled(edu.vt.arc.vis.osnap
     * .visualization.VisualProperty)
     */
    @Override
    public boolean isEnabled(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            return capabilities.get(visualProperty);
        }
        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#disable(edu.vt.arc.vis.osnap
     * .visualization.VisualProperty)
     */
    @Override
    public void disable(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            this.capabilities.put(visualProperty, false);
        }
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#modifiesVisualization()
     */
    @Override
    public boolean modifiesVisualization() {

        for (VisualProperty visualProperty : this.capabilities.keySet()) {

            if (this.isEnabled(visualProperty)) {
                return true;
            }
        }

        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#modifiesVisualNodes()
     */
    @Override
    public boolean modifiesVisualNodes() {

        for (VisualProperty visualProperty : this.capabilities.keySet()) {

            if (this.isEnabled(visualProperty)
                    && visualProperty.isNodeProperty()) {
                return true;
            }
        }
        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#modifiesVisualEdges()
     */
    @Override
    public boolean modifiesVisualEdges() {

        for (VisualProperty visualProperty : this.capabilities.keySet()) {

            if (this.isEnabled(visualProperty)
                    && visualProperty.isEdgeProperty()) {
                return true;
            }
        }
        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#modifiesVisualHyperEdges()
     */
    @Override
    public boolean modifiesVisualHyperEdges() {

        for (VisualProperty visualProperty : this.capabilities.keySet()) {

            if (this.isEnabled(visualProperty)
                    && visualProperty.isHyperedgeProperty()) {
                return true;
            }
        }
        return false;
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.Visualization)
     */
    @Override
    public void layout(Visualization visualization) {

        BaseLayoutComponent.applyLayout(this, visualization.getVisualNodes(),
                visualization.getVisualEdges(),
                visualization.getVisualHyperEdges());
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualGraph)
     */
    @Override
    public void layout(VisualGraph graph) {

        BaseLayoutComponent.applyLayout(this, graph.getVisualNodes(),
                graph.getVisualEdges(), graph.getVisualHyperEdges());
    }


    /**
     * This method iterates over all nodes, edges, and hyperedges and applies
     * their layout (if applicable).
     * 
     * @param layoutComponent
     *            the {@link ILayoutComponent} for which to apply the layout.
     * @param nodes
     *            the nodes to lay out.
     * @param edges
     *            the edges to lay out.
     * @param hyperedges
     *            the hyperedges to lay out.
     */
    protected static void applyLayout(ILayoutComponent layoutComponent,
            Collection<VisualNode> nodes, Collection<VisualEdge> edges,
            Collection<VisualHyperEdge> hyperedges) {

        if (layoutComponent.modifiesVisualization()) {

            if (layoutComponent.modifiesVisualNodes()) {

                for (VisualNode visualNode : nodes) {

                    if (!layoutComponent.isRestricted()
                            || (layoutComponent.isRestricted() && layoutComponent
                                    .getRestriction().contains(
                                            visualNode.getNode()))) {

                        layoutComponent.layout(visualNode);
                    }
                }
            }


            if (layoutComponent.modifiesVisualEdges()) {

                for (VisualEdge visualEdge : edges) {

                    if (!layoutComponent.isRestricted()
                            || (layoutComponent.isRestricted() && layoutComponent
                                    .getRestriction().contains(
                                            visualEdge.getEdge()))) {

                        layoutComponent.layout(visualEdge);
                    }
                }
            }


            if (layoutComponent.modifiesVisualHyperEdges()) {

                for (VisualHyperEdge visualHyperEdge : hyperedges) {

                    if (!layoutComponent.isRestricted()
                            || (layoutComponent.isRestricted() && layoutComponent
                                    .getRestriction().contains(
                                            visualHyperEdge.getHyperEdge())))
                        layoutComponent.layout(visualHyperEdge);
                }
            }
        }
    }


    /**
     * Calculates the restricted set of {@link IVisualGraphObject Visual Graph
     * Objects} for a {@link ILayoutComponent Layout Component} from an
     * unrestricted set.
     * 
     * @param layoutComponent
     *            the {@link ILayoutComponent Layout Component}.
     * @param unrestrictedSet
     *            the unrestricted set of {@link IVisualGraphObject Visual Graph
     *            Objects}
     * @return the restricted set of {@link IVisualGraphObject Visual Graph
     *         Objects}
     */
    protected static <T extends IVisualGraphObject> Set<T> calculateRestrictedSet(
            ILayoutComponent layoutComponent, Collection<T> unrestrictedSet) {

        Set<T> restrictedSet = new LinkedHashSet<>();

        for (T visualGraphObject : unrestrictedSet) {

            if (!layoutComponent.isRestricted()
                    || layoutComponent.getRestriction().contains(
                            visualGraphObject.getGraphObject())) {

                restrictedSet.add(visualGraphObject);
            }
        }

        return restrictedSet;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ILayoutComponent) {

            ILayoutComponent other = (ILayoutComponent) obj;

            boolean sameName = this.getName().equals(other.getName());
            boolean sameDescription = this.getDescription().equals(
                    other.getDescription());

            boolean sameCapabilities = true;

            for (VisualProperty capability : this.providesCapabilities()) {

                if (!other.providesCapabilities().contains(capability)) {
                    sameCapabilities = false;
                    break;
                }
                if (this.isEnabled(capability) != other.isEnabled(capability)) {

                    sameCapabilities = false;
                    break;
                }
            }

            boolean sameRestriction = true;
            for (IGraphObject graphObject : this.getRestriction()) {
                if (other.getRestriction() == null
                        || !other.getRestriction().contains(graphObject)) {
                    sameRestriction = false;
                    break;
                }
            }


            return sameName && sameDescription && sameCapabilities
                    && sameRestriction;

        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hash = 13;

        hash += 31 * this.getName().hashCode();
        hash += 37 * this.getDescription().hashCode();
        hash += 41 * this.getRestriction().hashCode();
        hash += 47 * this.providesCapabilities().hashCode();

        return hash;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return this.getName();
    }
}
