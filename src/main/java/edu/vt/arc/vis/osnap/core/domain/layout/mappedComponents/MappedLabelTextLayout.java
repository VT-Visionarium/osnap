package edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents;


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


import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILabelTextLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Label;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code MappedColorLayout} class provides mappings from properties or
 * metadata into colors.
 * 
 * The domain key type specifies the type of the property identifier. The domain
 * value type specifies the values of the properties.
 * 
 * @param <DOMAIN_KEY_TYPE>
 *            the type of the property identifier.
 * @param <DOMAIN_VALUE_TYPE>
 *            the type of the domain values.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "MappedLabelTextLayout")
public class MappedLabelTextLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends BaseMappedLayout<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, String>
        implements ILabelTextLayout {


    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Label Text Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Label Text based on a Mapping.";
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


    /**
     * Creates a new instance of the {@link MappedLabelTextLayout} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedLabelTextLayout() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code MappedLabelTextLayout} .
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedLabelTextLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the {@code MappedLabelTextLayout} .
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
    protected MappedLabelTextLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, String.class,
                MappedLabelTextLayout.name(), MappedLabelTextLayout
                        .description(), true, serialization);

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
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout#layout(edu.vt.
     * arc.vis.osnap.core .visualization.VisualHyperEdge)
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
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout#layout(edu.vt.
     * arc.vis.osnap.core .visualization.VisualEdge)
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
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout#layout(edu.vt.
     * arc.vis.osnap.core .visualization.VisualNode)
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
