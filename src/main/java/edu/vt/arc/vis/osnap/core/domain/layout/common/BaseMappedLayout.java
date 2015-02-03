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


import edu.vt.arc.vis.osnap.core.domain.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.base.NodeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualGraph;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.core.domain.visualization.Visualization;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The {@code BaseMappedLayout} class provides mappings of properties of a
 * domain to visual properties.
 * 
 * The 'from' part of the mapping provides the identifier for which the mapping
 * is valid along with the datatype of domain objects. The 'to' part of the
 * mapping specifies what values a certain visual property expects.
 * 
 * Currently, only {@link SchemaEntry SchemaEntries} and @{link
 * GraphObjectProperty GraphObjectProperties} are supported as domain keys.
 * 
 * @param <DOMAIN_KEY_TYPE>
 *            The type of the identifier of the domain property. Has to extend
 *            {@link IGraphObjectBasedValueTypeContainer}.
 * @param <DOMAIN_VALUE_TYPE>
 *            The type of the domain values.
 * @param <CODOMAIN_VALUE_TYPE>
 *            The datatype of the visual property mapped to.
 * 
 * @see SchemaEntry
 * @see GraphObjectProperty
 * @see IGraphObjectBasedValueTypeContainer
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "BaseMappedLayout")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseMappedLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>
        extends
        Mapping<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE, VisualProperty>
        implements
        IMappedLayout<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> {

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
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Visual Property based on a Mapping.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
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

        return Collections.unmodifiableSet(this.restriction);
    }

    @Override
    public void setRestriction(Set<IGraphObject> restriction) {

        this.clearRestriction();
        this.restriction.addAll(restriction);
    }

    @Override
    public void clearRestriction() {

        this.restriction.clear();
    }


    /**
     * Creates a new instance of the {@link BaseMappedLayout} class.
     * (Serialization Constructor)
     */
    protected BaseMappedLayout() {


        this(null, null, null, null, null, null, true, true);
    }

    /**
     * Constructs a {@code BaseMappedLayout} for the provided domain key and the
     * provided co-domain key.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     */
    public BaseMappedLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType) {

        this(domainKey, coDomainKey, domainValueType, coDomainValueType,
                BaseMappedLayout.name(), BaseMappedLayout.description(), true);
    }

    /**
     * Constructs a {@code BaseMappedLayout} for the provided domain key and the
     * provided co-domain key. It sets the description and name to the provided
     * values and optionally appends it with a random number.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     */
    public BaseMappedLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType,
            String name, String description, boolean randomize) {

        this(domainKey, coDomainKey, domainValueType, coDomainValueType, name,
                description, randomize, false);
    }

    /**
     * Constructs a {@code BaseMappedLayout} for the provided domain key and the
     * provided co-domain key. It sets the description and name to the provided
     * values and optionally appends it with a random number.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     * @param name
     *            the name of the layout component.
     * @param description
     *            the description of the layout component.
     * @param randomize
     *            whether or not to append a random number to the name.
     * @param serialization
     *            whether or not the constructor is used during serialization.
     */
    public BaseMappedLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType,
            String name, String description, boolean randomize,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, coDomainValueType,
                serialization);

        if (!serialization
                && !this.getDomainValueType().isAssignableFrom(
                        domainKey.getValueType())) {

            throw new IllegalArgumentException(
                    "Provided domain value type does not correspond to domain key'scale value type!");
        }
        if (!serialization
                && !this.getCoDomainValueType().isAssignableFrom(
                        coDomainKey.getValueType())) {

            throw new IllegalArgumentException(
                    "Provided co-domain value type does not correspond to co-domain key'scale value type!");
        }

        this.capabilities = new LinkedHashMap<>();

        if (this.getCoDomainKey() != null) {

            this.capabilities.put(this.getCoDomainKey(), false);
        }

        this.restriction = new LinkedHashSet<>();
        if (randomize) {

            Random random = new Random();
            this.name = name + " - " + random.nextLong();
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

        this.layout(visualization.getVisualNodes(),
                visualization.getVisualEdges(),
                visualization.getVisualHyperEdges());
    }


    @Override
    public void layout(VisualGraph graph) {

        this.layout(graph.getVisualNodes(), graph.getVisualEdges(),
                graph.getVisualHyperEdges());
    }


    /**
     * This method iterates over all nodes, edges, and hyperedges and applies
     * their layout (if applicable).
     * 
     * @param nodes
     *            the nodes to lay out.
     * @param edges
     *            the edges to lay out.
     * @param hyperedges
     *            the hyperedges to lay out.
     */
    private void layout(Collection<VisualNode> nodes,
            Collection<VisualEdge> edges, Collection<VisualHyperEdge> hyperedges) {

        if (this.modifiesVisualization()) {

            if (this.modifiesVisualNodes()) {

                for (VisualNode visualNode : nodes) {

                    if (!this.isRestricted()
                            || (this.isRestricted() && this.restriction
                                    .contains(visualNode.getNode()))) {

                        this.layout(visualNode);
                    }
                }
            }


            if (this.modifiesVisualEdges()) {

                for (VisualEdge visualEdge : edges) {

                    if (!this.isRestricted()
                            || (this.isRestricted() && this.restriction
                                    .contains(visualEdge.getEdge()))) {

                        this.layout(visualEdge);
                    }
                }
            }


            if (this.modifiesVisualHyperEdges()) {

                for (VisualHyperEdge visualHyperEdge : hyperedges) {

                    if (!this.isRestricted()
                            || (this.isRestricted() && this.restriction
                                    .contains(visualHyperEdge.getHyperEdge())))
                        this.layout(visualHyperEdge);
                }
            }
        }
    }


    /**
     * Returns the co-domain value for a graph object.
     * 
     * @param graphObject
     *            the graph object.
     * 
     * @return the co-domain value.
     */
    protected CODOMAIN_VALUE_TYPE getCoDomainValueForGraphObject(
            IGraphObject graphObject) {

        DOMAIN_VALUE_TYPE domainValueS = null;

        IGraphObjectBasedValueTypeContainer domainKey = this.getDomainKey();

        Object domainValue = domainKey.getValueForGraphObject(graphObject);

        Collection<?> domainValueCollection = null;

        if (!(domainValue instanceof Collection<?>)) {

            List<Object> objectList = new LinkedList<>();
            objectList.add(domainValue);
            domainValueCollection = objectList;

        }
        else {

            domainValueCollection = (Collection<?>) domainValue;
        }

        for (Object value : domainValueCollection) {

            // Attempting to cast the alleged domain value to the domain value
            // type.
            domainValueS = this.castValue(this.getDomainValueType(), value);

            // If we were able to retrieve the domain value, we attempt to
            // retrieve its image in the co-domain.
            if (domainValueS != null) {

                CODOMAIN_VALUE_TYPE coDomainValue = this
                        .getImageForValue(domainValueS);

                if (coDomainValue != null) {

                    return coDomainValue;
                }
            }
        }



        return null;
    }

    @Override
    public String toString() {

        return this.getName();
    }

    /**
     * Attempts to cast a raw object value to the provided class type.
     * 
     * @param clazz
     *            the type to cast to.
     * @param rawValue
     *            the raw value to cast.
     * @return the value in the type if castable; {@code null} otherwise.
     */
    private <T> T castValue(Class<T> clazz, Object rawValue) {

        if (clazz.isAssignableFrom(rawValue.getClass())) {
            return clazz.cast(rawValue);
        }

        return null;
    }
}
