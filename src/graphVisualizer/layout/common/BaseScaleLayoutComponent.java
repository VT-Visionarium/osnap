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
 * The abstract <code>BaseScaleLayoutComponent</code> class provides common
 * functionality of all @{link IScaleLayoutComponent IScaleLayoutComponents}.
 * 
 * @see IScaleLayoutComponent
 * @see BaseLayoutComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseScaleLayoutComponent")
public abstract class BaseScaleLayoutComponent
        extends BaseLayoutComponent
        implements IScaleLayoutComponent {

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
     * {@link graphVisualizer.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_SCALE,
        // VisualProperty.HYPEREDGE_SCALE,
                VisualProperty.NODE_SCALE);
    }

    /**
     * Creates a new <code>BaseScaleLayoutComponent</code> instance.
     */
    public BaseScaleLayoutComponent() {

        this(BaseScaleLayoutComponent.capabilities());
    }

    /**
     * Creates a new <code>BaseScaleLayoutComponent</code> instance with the
     * provided capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseScaleLayoutComponent(Set<VisualProperty> capabilities) {

        this(capabilities, BaseScaleLayoutComponent.name(),
                BaseScaleLayoutComponent.description(), true);
    }

    /**
     * Creates a new <code>BaseScaleLayoutComponent</code> instance with the
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
    public BaseScaleLayoutComponent(Set<VisualProperty> capabilities,
            String name, String description, Boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }

}
