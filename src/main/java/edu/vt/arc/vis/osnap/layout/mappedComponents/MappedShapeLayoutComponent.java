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


package edu.vt.arc.vis.osnap.layout.mappedComponents;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.layout.common.BaseMappedLayoutComponent;
import edu.vt.arc.vis.osnap.layout.common.IShapeLayoutComponent;
import edu.vt.arc.vis.osnap.visualization.Shape;
import edu.vt.arc.vis.osnap.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.visualization.VisualNode;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;


/**
 * The <code>MappedShapeLayoutComponent</code> class provides mappings from
 * properties or metadata into a shape.
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
@XmlType(name = "MappedShapeLayoutComponent")
public class MappedShapeLayoutComponent<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends
        BaseMappedLayoutComponent<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Shape>
        implements IShapeLayoutComponent {


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Shape Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Shape based on a Mapping.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return new LinkedHashSet<>();
    }


    /**
     * Creates a new instance of the {@link MappedShapeLayoutComponent} class.
     */
    @SuppressWarnings("unused")
    private MappedShapeLayoutComponent() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the <code>MappedShapeLayoutComponent</code>.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedShapeLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the <code>MappedShapeLayoutComponent</code>.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param serialization
     *            Whether or not the constructor is used during serialization.
     */
    protected MappedShapeLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Shape.class,
                MappedShapeLayoutComponent.name(), MappedShapeLayoutComponent
                        .description(), true, serialization);

        if (!serialization) {

            switch (coDomainKey) {
                case EDGE_SHAPE:
                case NODE_SHAPE:
                case HYPEREDGE_SHAPE:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Shape provider can only"
                                    + "handle shapes, not other visual properties!");
            }
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        if (this.isEnabled(VisualProperty.NODE_SHAPE)) {
            Shape shape = this.getCoDomainValueForGraphObject(visualNode
                    .getNode());
            if (shape != null) {
                visualNode.setShape(shape);
            }
            else {

            }
        }
        else {
        }

    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.isEnabled(VisualProperty.EDGE_SHAPE)) {

            Shape shape = this.getCoDomainValueForGraphObject(visualEdge
                    .getEdge());
            if (shape != null) {
                visualEdge.setShape(shape);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.isEnabled(VisualProperty.HYPEREDGE_SHAPE)) {

            Shape shape = this.getCoDomainValueForGraphObject(visualHyperEdge
                    .getHyperEdge());
            if (shape != null) {
                visualHyperEdge.setShape(shape);
            }
        }
    }
}
