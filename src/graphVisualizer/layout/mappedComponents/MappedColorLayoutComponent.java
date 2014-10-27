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


package graphVisualizer.layout.mappedComponents;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import graphVisualizer.graph.common.IGraphObjectBasedValueTypeContainer;
import graphVisualizer.layout.common.IColorLayoutComponent;
import graphVisualizer.layout.common.BaseMappedLayoutComponent;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualHyperEdge;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;
import javafx.scene.paint.Color;


/**
 * The <code>MappedColorLayoutComponent</code> class provides mappings from
 * properties or metadata into colors.
 * 
 * The domain key type specifies the type of the property identifier. The domain
 * value type specifies the values of the properties.
 * 
 * @param <DOMAIN_KEY_TYPE>
 *            the type of the property identifier.
 * @param <DOMAIN_VALUE_TYPE>
 *            the type of the domain values.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "MappedColorLayoutComponent")
public class MappedColorLayoutComponent<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends
        BaseMappedLayoutComponent<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Color>
        implements IColorLayoutComponent {


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Color Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Colorbased on a Mapping.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link graphVisualizer.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return new LinkedHashSet<>();
    }

    /**
     * Creates a new instance of the {@link MappedColorLayoutComponent} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedColorLayoutComponent() {

        this(null, null, null, true);
    }

    /**
     * Constructs a <code>MappedColorLayoutComponent</code> that provides a
     * mapping from the value of the property specified by the provided domain
     * key into the property specified by the co-domain key.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedColorLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Constructs a <code>MappedColorLayoutComponent</code> that provides a
     * mapping from the value of the property specified by the provided domain
     * key into the property specified by the co-domain key.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    public MappedColorLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Color.class,
                MappedColorLayoutComponent.name(), MappedColorLayoutComponent
                        .description(), true, serialization);

        if (!serialization) {

            switch (coDomainKey) {
                case EDGE_COLOR:
                case NODE_COLOR:
                case HYPEREDGE_COLOR:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Color provider can only"
                                    + "handle colors, not other visual properties!");
            }
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ILayoutComponent#layout(graphVisualizer
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        if (this.isEnabled(VisualProperty.NODE_COLOR)) {

            Color color = this.getCoDomainValueForGraphObject(visualNode
                    .getNode());
            if (color != null) {
                visualNode.setColor(color);
            }
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ILayoutComponent#layout(graphVisualizer
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.getCoDomainKey() == VisualProperty.EDGE_COLOR
                && this.isEnabled(VisualProperty.EDGE_COLOR)) {

            Color color = this.getCoDomainValueForGraphObject(visualEdge
                    .getEdge());
            if (color != null) {
                visualEdge.setColor(color);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ILayoutComponent#layout(graphVisualizer
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.getCoDomainKey() == VisualProperty.HYPEREDGE_COLOR
                && this.isEnabled(VisualProperty.HYPEREDGE_COLOR)) {

            Color color = this.getCoDomainValueForGraphObject(visualHyperEdge
                    .getHyperEdge());
            if (color != null) {
                visualHyperEdge.setColor(color);
            }
        }
    }
}
