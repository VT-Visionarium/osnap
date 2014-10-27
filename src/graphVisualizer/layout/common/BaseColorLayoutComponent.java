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


package graphVisualizer.layout.common;


import graphVisualizer.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract <code>BaseColorLayoutComponent</code> class provides common
 * functionality of all @{link IColorLayoutComponent IColorLayoutComponents}.
 * 
 * @see IColorLayoutComponent
 * @see BaseLayoutComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseColorLayoutComponent")
public abstract class BaseColorLayoutComponent
        extends BaseLayoutComponent
        implements IColorLayoutComponent {

    /**
     * Returns the name of this {@link ILayoutComponent}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Color Layout Component";
    }


    /**
     * Returns the description of this {@link ILayoutComponent}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseColorLayoutComponent.name()
                + " provides the color for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link graphVisualizer.visualization.VisualProperty VisualProperties}
     * that can be provided) of this {@link ILayoutComponent}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_COLOR,
        // VisualProperty.HYPEREDGE_COLOR,
                VisualProperty.NODE_COLOR);
    }


    /**
     * Creates a new instance of the {@link BaseColorLayoutComponent} class.
     */
    protected BaseColorLayoutComponent() {

        super(BaseColorLayoutComponent.capabilities());
    }


    /**
     * Creates a new instance of the {@link BaseColorLayoutComponent} class with
     * the provided capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseColorLayoutComponent(Set<VisualProperty> capabilities) {

        this(capabilities, BaseColorLayoutComponent.name(),
                BaseColorLayoutComponent.description(), true);
    }


    /**
     * Creates a new instance of the {@link BaseColorLayoutComponent} class with
     * the provided capabilities, (randomized) name, and description.
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
    public BaseColorLayoutComponent(final Set<VisualProperty> capabilities,
            final String name, final String description,
            final boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }
}
