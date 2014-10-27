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

import org.jutility.math.geometry.Scalef;

import graphVisualizer.graph.common.IGraphObjectBasedValueTypeContainer;
import graphVisualizer.layout.common.IScaleLayoutComponent;
import graphVisualizer.layout.common.BaseMappedLayoutComponent;
import graphVisualizer.visualization.VisualEdge;
import graphVisualizer.visualization.VisualHyperEdge;
import graphVisualizer.visualization.VisualNode;
import graphVisualizer.visualization.VisualProperty;


/**
 * The <code>MappedScaleLayoutComponent</code> class provides mappings from
 * properties or metadata into one scale (x, y, or z).
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
@XmlType(name = "MappedScaleLayoutComponent")
public class MappedScaleLayoutComponent<DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer, DOMAIN_VALUE_TYPE>
        extends
        BaseMappedLayoutComponent<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, Scalef>
        implements IScaleLayoutComponent {


    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Mapped Scale Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "Provides a Scale based on a Mapping.";
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
     * Creates a new instance of the {@link MappedScaleLayoutComponent} class
     * (Serialization Constructor).
     */
    @SuppressWarnings("unused")
    private MappedScaleLayoutComponent() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the <code>MappedScaleLayoutComponent</code>.
     * 
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     */
    public MappedScaleLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType) {

        this(domainKey, coDomainKey, domainValueType, false);
    }

    /**
     * Creates a new instance of the <code>MappedScaleLayoutComponent</code>.
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
    protected MappedScaleLayoutComponent(DOMAIN_KEY_TYPE domainKey,
            VisualProperty coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            boolean serialization) {

        super(domainKey, coDomainKey, domainValueType, Scalef.class,
                MappedScaleLayoutComponent.name(), MappedShapeLayoutComponent
                        .description(), true, serialization);

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


    /*
     * (non-Javadoc)
     * 
     * @see
     * graphVisualizer.layout.common.ILayoutComponent#layout(graphVisualizer
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        // TODO: (v.2) fix Scale mappings to have x, y, z components
        if (this.getCoDomainKey() == VisualProperty.NODE_SCALE) {


            Scalef scale = null;
            Object scaleFactor = this.getCoDomainValueForGraphObject(visualNode
                    .getNode());
            if (scaleFactor != null) {
                if (scaleFactor instanceof Scalef) {
                    scale = (Scalef) scaleFactor;
                }
                else if (scaleFactor instanceof Float) {
                    Float scaling = (Float) scaleFactor;
                    scale = new Scalef(scaling, scaling, scaling);
                }
                else {
                    throw new IllegalArgumentException(
                            "Scale hast to be comprised of Floats, but provided "
                                    + "value was " + scaleFactor.getClass()
                                    + "!");
                }
            }

            if (scale != null) {

                visualNode.setScale(scale);
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

        if (this.getCoDomainKey() == VisualProperty.EDGE_SCALE) {

            Scalef scale = null;
            Object scaleFactor = this.getCoDomainValueForGraphObject(visualEdge
                    .getEdge());
            if (scaleFactor != null) {
                if (scaleFactor instanceof Scalef) {
                    scale = (Scalef) scaleFactor;
                }
                else if (scaleFactor instanceof Float) {
                    Float scaling = (Float) scaleFactor;
                    scale = new Scalef(scaling, scaling, scaling);
                }
                else {
                    throw new IllegalArgumentException(
                            "Scale hast to be comprised of Floats, but provided "
                                    + "value was " + scaleFactor.getClass()
                                    + "!");
                }
            }
            if (scale != null) {
                visualEdge.setScale(scale);
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

        // TODO: (v.2) fix Scale mappings to have x, y, z components?
        Scalef scale = null;
        Object scaleFactor = this
                .getCoDomainValueForGraphObject(visualHyperEdge.getHyperEdge());
        if (scaleFactor != null) {
            if (scaleFactor instanceof Scalef) {
                scale = (Scalef) scaleFactor;
            }
            else if (scaleFactor instanceof Float) {
                Float scaling = (Float) scaleFactor;
                scale = new Scalef(scaling, scaling, scaling);
            }
            else {
                throw new IllegalArgumentException(
                        "Scale hast to be comprised of Floats, but provided "
                                + "value was " + scaleFactor.getClass() + "!");
            }
        }
        if (scale != null) {
            visualHyperEdge.setScale(scale);
        }
    }
}
