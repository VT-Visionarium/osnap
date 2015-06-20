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

import org.jutility.math.geometry.ScaleFactor;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IScaleLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code MappedScaleLayout} class provides mappings from properties or
 * metadata into one scale (x, y, or z).
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
@SuppressWarnings("rawtypes")
@XmlType(name = "MappedScaleLayout")
public class MappedScaleLayout<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends BaseMappedLayout<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, ScaleFactor>
        implements IScaleLayout {


    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Scale Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Scale based on a Mapping.";
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
     * Creates a new instance of the {@link MappedScaleLayout} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedScaleLayout() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@code MappedScaleLayout}.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedScaleLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the {@code MappedScaleLayout}.
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
    protected MappedScaleLayout(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType,  ScaleFactor.class,
                MappedScaleLayout.name(), MappedScaleLayout.description(),
                true, serialization);

        if (!serialization) {

            switch (coDomainKey) {
                case EDGE_SCALE:
                case NODE_SCALE:
                case HYPEREDGE_SCALE:
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Scale provider can only"
                                    + "handle scales, not other visual properties!");
            }
        }
    }


    @Override
    public void layout(VisualNode visualNode) {

        // TODO: (v.2) fix Scale mappings to have x, y, z components
        if (this.getCoDomainKey() == VisualProperty.NODE_SCALE) {


            ScaleFactor<?> scale = null;
            Object scaleFactor = this.getCoDomainValueForGraphObject(visualNode
                    .getNode());
            if (scaleFactor != null) {
                if (scaleFactor instanceof ScaleFactor<?>) {
                    scale = (ScaleFactor<?>) scaleFactor;
                }
                else if (scaleFactor instanceof Number) {
                    Number scaling = (Number) scaleFactor;
                    scale = new ScaleFactor<>(scaling, scaling, scaling, scaling.getClass());
                }
                else {
                    throw new IllegalArgumentException(
                            "Scale hast to be comprised of Numbers, but provided "
                                    + "value was " + scaleFactor.getClass()
                                    + "!");
                }
            }

            if (scale != null) {

                visualNode.setScale(scale);
            }
        }
    }

    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.getCoDomainKey() == VisualProperty.EDGE_SCALE) {

            ScaleFactor<?> scale = null;
            Object scaleFactor = this.getCoDomainValueForGraphObject(visualEdge
                    .getEdge());
            if (scaleFactor != null) {
                if (scaleFactor instanceof ScaleFactor<?> ) {
                    scale = (ScaleFactor<?>) scaleFactor;
                }
                else if (scaleFactor instanceof Number) {
                    Number scaling = (Number) scaleFactor;
                    scale = new ScaleFactor<>(scaling, scaling, scaling, scaling.getClass());
                }
                else {
                    throw new IllegalArgumentException(
                            "Scale hast to be comprised of Numbers, but provided "
                                    + "value was " + scaleFactor.getClass()
                                    + "!");
                }
            }
            if (scale != null) {
                visualEdge.setScale(scale);
            }
        }
    }

    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        // TODO: (v.2) fix Scale mappings to have x, y, z components?
        ScaleFactor<?> scale = null;
        Object scaleFactor = this
                .getCoDomainValueForGraphObject(visualHyperEdge.getHyperEdge());
        if (scaleFactor != null) {
            if (scaleFactor instanceof ScaleFactor<?>) {
                scale = (ScaleFactor<?>) scaleFactor;
            }
            else if (scaleFactor instanceof Number) {
                
                Number scaling = (Number)scaleFactor;
                scale = new ScaleFactor<>(scaling, scaling, scaling, scaling.getClass());
            }
            else {
                throw new IllegalArgumentException(
                        "Scale hast to be comprised of Numbers, but provided "
                                + "value was " + scaleFactor.getClass() + "!");
            }
        }
        if (scale != null) {
            visualHyperEdge.setScale(scale);
        }
    }
}
