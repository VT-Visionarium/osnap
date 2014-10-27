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


package edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents;


import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The <code>VisualPropertyMappingFactory</code> class provides static factory
 * methods for the creation of appropriate mappings from @{link SchemaEntry
 * SchemaEntries} and {@link GraphObjectProperty GraphObjectProperties} to
 * {@link VisualProperty VisualProperties}.
 * 
 * @see IGraphObjectBasedValueTypeContainer
 * @see GraphObjectProperty
 * @see SchemaEntry
 * @see VisualProperty
 * 
 * @see IMappedLayoutComponent
 * @see MappedColorLayoutComponent
 * @see MappedCoordinateLayoutComponent
 * @see MappedLabelTextLayoutComponent
 * @see MappedScaleLayoutComponent
 * @see MappedShapeLayoutComponent
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class VisualPropertyMappingFactory {


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
     * Creates a {@link IMappedLayoutComponent} mapping a {@link SchemaEntry} or
     * {@link GraphObjectProperty} to a {@link VisualProperty}.
     * 
     * @param domainKey
     *            the domain key.
     * @param visualProperty
     *            the <code>VisualProperty</code>.
     * @return a <code>MappedVisualPropertyProvider</code>.
     */
    public static <DOMAIN_KEY_TYPE extends IGraphObjectBasedValueTypeContainer> Mapping<?, ?, ?, VisualProperty> createMappedVisualPropertyProvider(
            DOMAIN_KEY_TYPE domainKey, VisualProperty visualProperty) {

        Class<?> valueType = domainKey.getValueType();


        switch (visualProperty) {
            case NODE_COLOR:
            case EDGE_COLOR:
                return new MappedColorLayoutComponent<>(domainKey,
                        visualProperty, valueType);
            case NODE_X_POSITION:
            case NODE_Y_POSITION:
            case NODE_Z_POSITION:
                return new MappedCoordinateLayoutComponent<>(domainKey,
                        visualProperty, valueType);
            case NODE_SCALE:
            case EDGE_SCALE:
                return new MappedScaleLayoutComponent<>(domainKey,
                        visualProperty, valueType);
            case NODE_SHAPE:
            case EDGE_SHAPE:
                return new MappedShapeLayoutComponent<>(domainKey,
                        visualProperty, valueType);
            case NODE_LABEL_TEXT:
            case EDGE_LABEL_TEXT:
                return new MappedLabelTextLayoutComponent<>(domainKey,
                        visualProperty, valueType);
            default:
                break;

        }
        return null;
    }
}
