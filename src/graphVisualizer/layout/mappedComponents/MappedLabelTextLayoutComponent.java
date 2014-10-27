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
import graphVisualizer.layout.common.ILabelTextLayoutComponent;
import graphVisualizer.layout.common.BaseMappedLayoutComponent;
import graphVisualizer.visualization.Label;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualHyperEdge;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;


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
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "MappedLabelTextLayoutComponent")
public class MappedLabelTextLayoutComponent<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends
        BaseMappedLayoutComponent<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, String>
        implements ILabelTextLayoutComponent {


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Label Text Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Label Text based on a Mapping.";
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
     * Creates a new instance of the {@link MappedLabelTextLayoutComponent}
     * class (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedLabelTextLayoutComponent() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the <code>MappedLabelTextLayoutComponent</code>
     * .
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedLabelTextLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the <code>MappedLabelTextLayoutComponent</code>
     * .
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
    protected MappedLabelTextLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, String.class,
                MappedLabelTextLayoutComponent.name(),
                MappedLabelTextLayoutComponent.description(), true,
                serialization);

        if (!serialization) {

            switch (coDomainKey) {
                case EDGE_LABEL_TEXT:
                case NODE_LABEL_TEXT:
                case HYPEREDGE_LABEL_TEXT:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Label string provider can only"
                                    + "handle label strings, not other visual properties!");
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

        if (this.isEnabled(VisualProperty.HYPEREDGE_LABEL_TEXT)) {

            String labelString = this
                    .getCoDomainValueForGraphObject(visualHyperEdge
                            .getHyperEdge());
            Label label = new Label(visualHyperEdge.getVisualGraph()
                    .getVisualization().getPrecision());
            label.setText(labelString);
            visualHyperEdge.setLabel(label);
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

        if (this.isEnabled(VisualProperty.EDGE_LABEL_TEXT)) {

            String labelString = this.getCoDomainValueForGraphObject(visualEdge
                    .getEdge());
            Label label = new Label(visualEdge.getVisualGraph()
                    .getVisualization().getPrecision());
            label.setText(labelString);
            visualEdge.setLabel(label);
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

        if (this.isEnabled(VisualProperty.NODE_LABEL_TEXT)) {

            String labelString = this.getCoDomainValueForGraphObject(visualNode
                    .getNode());
            Label label = new Label(visualNode.getVisualGraph()
                    .getVisualization().getPrecision());
            label.setText(labelString);
            visualNode.setLabel(label);
        }

    }
}
