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
import graphVisualizer.layout.common.Base1DCoordinateLayoutComponent;
import graphVisualizer.layout.common.I1DCoordinateLayoutComponent;
import graphVisualizer.layout.common.BaseMappedLayoutComponent;
import graphVisualizer.layout.common.CoordinateComponent;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualHyperEdge;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;


/**
 * The <code>MappedCoordinateLayoutComponent</code> class provides mappings from
 * properties or metadata into one coordinate (x, y, or z).
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
@XmlType(name = "MappedCoordinateLayoutComponent")
public class MappedCoordinateLayoutComponent<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends
        BaseMappedLayoutComponent<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Float>
        implements I1DCoordinateLayoutComponent {


    private final I1DCoordinateLayoutComponent coordinateProvider;


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Coordinate Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Coordinate based on a Mapping.";
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
    private MappedCoordinateLayoutComponent() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the
     * <code>MappedCoordinateLayoutComponent</code> .
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedCoordinateLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the
     * <code>MappedCoordinateLayoutComponent</code> .
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
    public MappedCoordinateLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Float.class,
                MappedColorLayoutComponent.name(), MappedColorLayoutComponent
                        .description(), true, serialization);

        if (!serialization) {

            switch (coDomainKey) {
                case NODE_X_POSITION:
                case NODE_Y_POSITION:
                case NODE_Z_POSITION:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Coordinate provider can only"
                                    + "handle coordinates, not other visual properties!");
            }
        }
        this.coordinateProvider = new CoordinateProviderAdapter();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ICoordinateLayoutComponent#providesComponents
     * ()
     */
    @Override
    public Set<CoordinateComponent> providesComponents() {

        return this.coordinateProvider.providesComponents();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ICoordinateLayoutComponent#getXOutput()
     */
    @Override
    public CoordinateComponent getXOutput() {

        return this.coordinateProvider.getXOutput();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ICoordinateLayoutComponent#getYOutput()
     */
    @Override
    public CoordinateComponent getYOutput() {

        return this.coordinateProvider.getYOutput();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ICoordinateLayoutComponent#getZOutput()
     */
    @Override
    public CoordinateComponent getZOutput() {

        return this.coordinateProvider.getZOutput();
    }


    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.layout.common.ICoordinateLayoutComponent#setXOutput(
     * graphVisualizer.layout.common.CoordinateComponent)
     */
    @Override
    public void setXOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setXOutput(coordinateComponent);
    }


    @Override
    public void setYOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setYOutput(coordinateComponent);
    }


    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.layout.common.ICoordinateLayoutComponent#setZOutput(
     * graphVisualizer.layout.common.CoordinateComponent)
     */
    @Override
    public void setZOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setZOutput(coordinateComponent);
    }


    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.layout.common.ICoordinateLayoutComponent#setValue(
     * graphVisualizer.visualization.VisualNode,
     * graphVisualizer.layout.common.CoordinateComponent, java.lang.Float)
     */
    @Override
    public void setValue(VisualNode visualNode, CoordinateComponent component,
            Number value) {

        this.coordinateProvider.setValue(visualNode, component, value);

    }


    /*
     * (non-Javadoc)
     * 
     * @see graphVisualizer.layout.common.ICoordinateLayoutComponent#getValue(
     * graphVisualizer.visualization.VisualNode,
     * graphVisualizer.layout.common.CoordinateComponent)
     */
    @Override
    public Number getValue(VisualNode visualNode, CoordinateComponent component) {

        return this.coordinateProvider.getValue(visualNode, component);
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

        Float coordinatValue = this.getCoDomainValueForGraphObject(visualNode
                .getNode());


        this.setValue(visualNode, CoordinateComponent.FIRST_COMPONENT,
                coordinatValue);

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

        // Does not provide layout options for edges.
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

        // Does not provide layout options for hyperedges.

    }

    @Override
    public void enable(VisualProperty visualProperty) {

        super.enable(visualProperty);
        this.coordinateProvider.enable(visualProperty);

    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ILayoutComponent#disable(graphVisualizer
     * .visualization.VisualProperty)
     */
    @Override
    public void disable(VisualProperty visualProperty) {

        super.disable(visualProperty);
        this.coordinateProvider.disable(visualProperty);

    }

    private class CoordinateProviderAdapter
            extends Base1DCoordinateLayoutComponent {

        @Override
        public void layout(VisualNode visualNode) {

            // not used.
        }

        @Override
        public void layout(VisualEdge visualEdge) {

            // not used.
        }

        @Override
        public void layout(VisualHyperEdge visualHyperEdge) {

            // not used.
        }
    }
}
