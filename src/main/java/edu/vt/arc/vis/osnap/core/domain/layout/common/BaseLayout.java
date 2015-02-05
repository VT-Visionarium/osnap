package edu.vt.arc.vis.osnap.core.domain.layout.common;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
//@formatter:on


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

import edu.vt.arc.vis.osnap.core.domain.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.IVisualGraphObject;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;


/**
 * The abstract {@link BaseLayout} class provides the common functionality for
 * all @{link ILayout layout components}.
 * <p/>
 * Notably, it provides the mechanics for enabling/disabling capabilities of the
 * provider.
 * 
 * @see ILayout
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BaseLayout")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ BaseColorLayout.class })
public abstract class BaseLayout
        implements ILayout {

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
     * Returns the name of this {@link ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Layout";
    }


    /**
     * Returns the description of this {@link ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Visual Property.";
    }


    /**
     * Returns the capabilities (the set of {@link VisualProperty
     * VisualProperties} that can be provided) of this {@link ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return new LinkedHashSet<>();
    }



    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public void setName(String name) {

        this.name = name;
    }


    @Override
    public String getDescription() {

        return this.description;
    }

    @Override
    public void setDescription(String description) {

        this.description = description;
    }


    @Override
    public boolean isRestricted() {

        return !this.restriction.isEmpty();
    }

    @Override
    public Set<IGraphObject> getRestriction() {

        if (restriction != null) {
            return new LinkedHashSet<>(
                    Collections.unmodifiableSet(this.restriction));
        }

        return null;
    }


    @Override
    public void setRestriction(Set<IGraphObject> restriction) {

        if (restriction != null) {

            this.clearRestriction();
            this.restriction.addAll(restriction);
        }
    }

    @Override
    public void clearRestriction() {

        if (this.restriction != null) {
            this.restriction.clear();
        }
    }

    /**
     * Creates a new instance of the {@link BaseLayout} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private BaseLayout() {

        this(null, null, null, false, true);
    }

    /**
     * Creates a new instance of the {@link BaseLayout} class. Note that by
     * default all capabilities are disabled! They are enabled by a
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet} when the
     * provider is assigned to a specific {@link VisualProperty}.
     * 
     * @param capabilities
     *            the set of {@link VisualProperty VisualProperties} that can be
     *            provided by this class.
     */
    public BaseLayout(final Set<VisualProperty> capabilities) {

        this(capabilities, BaseLayout.name(), BaseLayout.description(), true);
    }


    /**
     * Creates a new instance of the {@link BaseLayout} class. Note that by
     * default all capabilities are disabled! They are enabled by a
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet} when the
     * provider is assigned to a specific {@link VisualProperty}. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param capabilities
     *            the set of {@link VisualProperty VisualProperties} that can be
     *            provided by this class.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomizeName
     *            whether or not to append a random number to the name.
     */
    public BaseLayout(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName) {

        this(capabilities, name, description, randomizeName, false);
    }

    /**
     * Creates a new instance of the {@link BaseLayout} class. Note that by
     * default all capabilities are disabled! They are enabled by a
     * {@link edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet} when the
     * provider is assigned to a specific {@link VisualProperty}. It sets the
     * description and name to the provided values and optionally appends it
     * with a random number.
     * 
     * @param capabilities
     *            the set of {@link VisualProperty VisualProperties} that can be
     *            provided by this class.
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
    protected BaseLayout(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName, final boolean serialization) {

        if ((capabilities == null || capabilities.isEmpty()) && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create Layout without capabilities!");

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
            this.name = name + " - " + Math.abs(random.nextLong());
        }
        else {

            this.name = name;
        }
        this.description = description;
    }


    @Override
    public Set<VisualProperty> providesCapabilities() {

        return Collections.unmodifiableSet(this.capabilities.keySet());
    }

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


    @Override
    public void enable(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            this.capabilities.put(visualProperty, true);
        }
    }



    @Override
    public boolean isEnabled(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            return capabilities.get(visualProperty);
        }
        return false;
    }



    @Override
    public void disable(VisualProperty visualProperty) {

        if (this.capabilities.containsKey(visualProperty)) {

            this.capabilities.put(visualProperty, false);
        }
    }



    @Override
    public boolean modifiesVisualization() {

        for (VisualProperty visualProperty : this.capabilities.keySet()) {

            if (this.isEnabled(visualProperty)) {

                return true;
            }
        }

        return false;
    }


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


    @Override
    public void layout(Visualization visualization) {

        BaseLayout.applyLayout(this, visualization.getVisualNodes(),
                visualization.getVisualEdges(),
                visualization.getVisualHyperEdges());
    }


    @Override
    public void layout(VisualGraph graph) {

        BaseLayout.applyLayout(this, graph.getVisualNodes(),
                graph.getVisualEdges(), graph.getVisualHyperEdges());
    }


    /**
     * This method iterates over all nodes, edges, and hyperedges and applies
     * their layout (if applicable).
     * 
     * @param layout
     *            the {@link ILayout} for which to apply the layout.
     * @param nodes
     *            the nodes to lay out.
     * @param edges
     *            the edges to lay out.
     * @param hyperedges
     *            the hyperedges to lay out.
     */
    protected static void applyLayout(ILayout layout,
            Collection<VisualNode> nodes, Collection<VisualEdge> edges,
            Collection<VisualHyperEdge> hyperedges) {

        if (layout.modifiesVisualization()) {

            if (layout.modifiesVisualNodes()) {

                for (VisualNode visualNode : nodes) {

                    if (!layout.isRestricted()
                            || (layout.isRestricted() && layout
                                    .getRestriction().contains(
                                            visualNode.getNode()))) {

                        layout.layout(visualNode);
                    }
                }
            }


            if (layout.modifiesVisualEdges()) {

                for (VisualEdge visualEdge : edges) {

                    if (!layout.isRestricted()
                            || (layout.isRestricted() && layout
                                    .getRestriction().contains(
                                            visualEdge.getEdge()))) {

                        layout.layout(visualEdge);
                    }
                }
            }


            if (layout.modifiesVisualHyperEdges()) {

                for (VisualHyperEdge visualHyperEdge : hyperedges) {

                    if (!layout.isRestricted()
                            || (layout.isRestricted() && layout
                                    .getRestriction().contains(
                                            visualHyperEdge.getHyperEdge())))
                        layout.layout(visualHyperEdge);
                }
            }
        }
    }


    /**
     * Calculates the restricted set of {@link IVisualGraphObject Visual Graph
     * Objects} for a {@link ILayout LayoutSet Component} from an
     * unrestricted set.
     * 
     * @param layout
     *            the {@link ILayout LayoutSet Component}.
     * @param unrestrictedSet
     *            the unrestricted set of {@link IVisualGraphObject Visual Graph
     *            Objects}
     * @return the restricted set of {@link IVisualGraphObject Visual Graph
     *         Objects}
     */
    protected static <T extends IVisualGraphObject> Set<T> calculateRestrictedSet(
            ILayout layout, Collection<T> unrestrictedSet) {

        Set<T> restrictedSet = new LinkedHashSet<>();

        for (T visualGraphObject : unrestrictedSet) {

            if (!layout.isRestricted()
                    || layout.getRestriction().contains(
                            visualGraphObject.getGraphObject())) {

                restrictedSet.add(visualGraphObject);
            }
        }

        return restrictedSet;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ILayout) {

            ILayout other = (ILayout) obj;

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

    @Override
    public int hashCode() {

        int hash = 13;

        hash += 31 * this.getName().hashCode();
        hash += 37 * this.getDescription().hashCode();
        hash += 41 * this.getRestriction().hashCode();
        hash += 47 * this.providesCapabilities().hashCode();

        return hash;
    }

    @Override
    public String toString() {

        return this.getName();
    }
}
