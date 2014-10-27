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


package edu.vt.arc.vis.osnap.core.domain.layout.common;


import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract <code>BaseShapeLayoutComponent</code> class provides common
 * functionality of all @{link IShapeLayoutComponent IShapeLayoutComponents}.
 * 
 * @see IShapeLayoutComponent
 * @see BaseLayoutComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseShapeLayoutComponent")
public abstract class BaseShapeLayoutComponent
        extends BaseLayoutComponent
        implements IShapeLayoutComponent {

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Scale Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseScaleLayoutComponent.name()
                + " provides the scale for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_SHAPE,
        // VisualProperty.HYPEREDGE_SHAPE,
                VisualProperty.NODE_SHAPE);
    }

    /**
     * Creates a new <code>BaseShapeLayoutComponent</code> instance.
     */
    public BaseShapeLayoutComponent() {

        this(BaseShapeLayoutComponent.capabilities());
    }

    /**
     * Creates a new <code>BaseShapeLayoutComponent</code> instance with the
     * provided capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseShapeLayoutComponent(Set<VisualProperty> capabilities) {

        this(capabilities, BaseShapeLayoutComponent.name(),
                BaseShapeLayoutComponent.description(), true);
    }

    /**
     * Creates a new <code>BaseShapeLayoutComponent</code> instance with the
     * provided capabilities, (randomized) name, and description.
     * 
     * @param capabilities
     *            the capabilities.
     * @param name
     *            the name.
     * @param description
     *            the description.
     * @param randomizeName
     *            whether or not to randomize the name.
     */
    public BaseShapeLayoutComponent(Set<VisualProperty> capabilities,
            String name, String description, Boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }

}
