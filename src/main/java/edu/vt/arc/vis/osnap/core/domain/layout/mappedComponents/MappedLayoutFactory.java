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



import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code MappedLayoutFactory} class provides static factory methods for the
 * creation of appropriate mappings from @{link SchemaEntry SchemaEntries} and
 * {@link GraphObjectProperty GraphObjectProperties} to {@link VisualProperty
 * VisualProperties}.
 * 
 * @see IGraphObjectBasedValueTypeContainer
 * @see GraphObjectProperty
 * @see SchemaEntry
 * @see VisualProperty
 * 
 * @see IMappedLayout
 * @see MappedColorLayout
 * @see MappedCoordinateLayout
 * @see MappedLabelTextLayout
 * @see MappedScaleLayout
 * @see MappedShapeLayout
 *
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public class MappedLayoutFactory {


    /**
     * Creates a {@link Mapping} from a {@link SchemaEntry} or
     * {@link GraphObjectProperty} to a {@link VisualProperty}.
     * 
     * @param domainKey
     *            the domain key.
     * @param visualProperty
     *            the {@link VisualProperty}.
     * @return a {@link Mapping}.
     */
    public static <DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer> Mapping<?, ?, ?, VisualProperty> createMapping(
            DOMAIN_KEY_TYPE domainKey, VisualProperty visualProperty) {

        return new Mapping<>(domainKey, visualProperty,
                domainKey.getValueType(), visualProperty.getValueType());
    }



    /**
     * Creates a {@link IMappedLayout} mapping a {@link SchemaEntry} or
     * {@link GraphObjectProperty} to a {@link VisualProperty}.
     * 
     * @param domainKey
     *            the domain key.
     * @param visualProperty
     *            the {@link VisualProperty}.
     * @return a {@link IMappedLayout Mapped Layout}.
     */
    public static <DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer> IMappedLayout<DOMAIN_KEY_TYPE, ?, ?> createMappedLayout(
            DOMAIN_KEY_TYPE domainKey, VisualProperty visualProperty) {

        Class<?> valueType = domainKey.getValueType();


        switch (visualProperty) {
            case NODE_COLOR:
            case EDGE_COLOR:
                return new MappedColorLayout<>(domainKey, visualProperty,
                        valueType);
            case NODE_X_POSITION:
            case NODE_Y_POSITION:
            case NODE_Z_POSITION:
                return new MappedCoordinateLayout<>(domainKey, visualProperty,
                        valueType);
            case NODE_SCALE:
            case EDGE_SCALE:
                return new MappedScaleLayout<>(domainKey, visualProperty,
                        valueType);
            case NODE_SHAPE:
            case EDGE_SHAPE:
                return new MappedShapeLayout<>(domainKey, visualProperty,
                        valueType);
            case NODE_LABEL_TEXT:
            case EDGE_LABEL_TEXT:
                return new MappedLabelTextLayout<>(domainKey, visualProperty,
                        valueType);
            default:
                break;

        }
        return null;
    }
}
