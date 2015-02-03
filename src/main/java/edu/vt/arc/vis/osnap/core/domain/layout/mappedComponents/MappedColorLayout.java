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
import edu.vt.arc.vis.osnap.core.domain.layout.common.IColorLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import javafx.scene.paint.Color;


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
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "MappedColorLayout")
public class MappedColorLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends BaseMappedLayout<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Color>
        implements IColorLayout {


    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Color Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Color based on a Mapping.";
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
     * Creates a new instance of the {@link MappedColorLayout} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedColorLayout() {

        this(null, null, null, true);
    }

    /**
     * Constructs a {@code MappedColorLayout} that provides a mapping from the
     * value of the property specified by the provided domain key into the
     * property specified by the co-domain key.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedColorLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Constructs a {@code MappedColorLayout} that provides a mapping from the
     * value of the property specified by the provided domain key into the
     * property specified by the co-domain key.
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
    public MappedColorLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Color.class,
                MappedColorLayout.name(), MappedColorLayout.description(),
                true, serialization);

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
