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
import edu.vt.arc.vis.osnap.core.domain.layout.common.Base1DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I1DCoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code MappedCoordinateLayout} class provides mappings from properties or
 * metadata into one coordinate (x, y, or z).
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
@XmlType(name = "MappedCoordinateLayout")
public class MappedCoordinateLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends BaseMappedLayout<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Float>
        implements I1DCoordinateLayout {


    private final I1DCoordinateLayout coordinateProvider;


    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Coordinate Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Coordinate based on a Mapping.";
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
     * Returns the coordinate components (the set of {@link CoordinateComponent
     * CoordinateComponents} that can be provided) by this
     * {@link ICoordinateLayout}.
     * 
     * @return the components.
     */
    public static Set<CoordinateComponent> components() {

        return Base1DCoordinateLayout.components();
    }

    /**
     * Creates a new instance of the {@code MappedCoordinateLayout} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedCoordinateLayout() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code MappedCoordinateLayout} .
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedCoordinateLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the {@code MappedCoordinateLayout} .
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
    public MappedCoordinateLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Float.class,
                MappedColorLayout.name(), MappedColorLayout.description(),
                true, serialization);

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

    @Override
    public Set<CoordinateComponent> providesComponents() {

        return this.coordinateProvider.providesComponents();
    }


    @Override
    public CoordinateComponent getXOutput() {

        return this.coordinateProvider.getXOutput();
    }

    @Override
    public void setXOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setXOutput(coordinateComponent);
    }


    @Override
    public CoordinateComponent getYOutput() {

        return this.coordinateProvider.getYOutput();
    }

    @Override
    public void setYOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setYOutput(coordinateComponent);
    }


    @Override
    public CoordinateComponent getZOutput() {

        return this.coordinateProvider.getZOutput();
    }

    @Override
    public void setZOutput(CoordinateComponent coordinateComponent) {

        this.coordinateProvider.setZOutput(coordinateComponent);
    }


    @Override
    public float getFirstComponentScale() {

        return this.coordinateProvider.getFirstComponentScale();
    }

    @Override
    public void setFirstComponentScale(float value) {

        this.coordinateProvider.setFirstComponentScale(value);
    }


    @Override
    public float getSecondComponentScale() {

        return this.coordinateProvider.getSecondComponentScale();
    }

    @Override
    public void setSecondComponentScale(float value) {

        this.coordinateProvider.setSecondComponentScale(value);
    }


    @Override
    public float getThirdComponentScale() {

        return this.coordinateProvider.getThirdComponentScale();
    }

    @Override
    public void setThirdComponentScale(float value) {

        this.coordinateProvider.setThirdComponentScale(value);
    }


    @Override
    public void setValue(VisualNode visualNode, CoordinateComponent component,
            Number value) {

        this.coordinateProvider.setValue(visualNode, component, value);

    }

    @Override
    public Number getValue(VisualNode visualNode, CoordinateComponent component) {

        return this.coordinateProvider.getValue(visualNode, component);
    }


    @Override
    public void layout(VisualNode visualNode) {

        Float coordinatValue = this.getCoDomainValueForGraphObject(visualNode
                .getNode());


        this.setValue(visualNode, CoordinateComponent.FIRST_COMPONENT,
                coordinatValue);

    }


    @Override
    public void layout(VisualEdge visualEdge) {

        // Does not provide layout options for edges.
    }


    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // Does not provide layout options for hyperedges.

    }

    @Override
    public void enable(VisualProperty visualProperty) {

        super.enable(visualProperty);
        this.coordinateProvider.enable(visualProperty);
    }


    @Override
    public void disable(VisualProperty visualProperty) {

        super.disable(visualProperty);
        this.coordinateProvider.disable(visualProperty);

    }

    private static class CoordinateProviderAdapter
            extends Base1DCoordinateLayout {

        /**
         * Creates a new instance of the {@code CoordinateProviderAdapter}
         * class.
         */
        public CoordinateProviderAdapter() {

            // $no-op$
        }

        @Override
        public void layout(VisualNode visualNode) {

            // $no-op$
        }

        @Override
        public void layout(VisualEdge visualEdge) {

            // $no-op$
        }

        @Override
        public void layout(VisualHyperEdge visualHyperEdge) {

            // $no-op$
        }
    }
}
