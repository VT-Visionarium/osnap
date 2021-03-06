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


package edu.vt.arc.vis.osnap.layout.common;


import edu.vt.arc.vis.osnap.visualization.VisualProperty;

import java.util.EnumSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The abstract <code>BaseLabelTextLayoutComponent</code> class provides common
 * functionality of all @{link ILabelTextLayoutComponent
 * ILabelTextLayoutComponents}.
 * 
 * @see ILabelTextLayoutComponent
 * @see BaseLayoutComponent
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "BaseLabelTextLayoutComponent")
public abstract class BaseLabelTextLayoutComponent
        extends BaseLayoutComponent
        implements ILabelTextLayoutComponent {

    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Label Text Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The " + BaseLabelTextLayoutComponent.name()
                + " provides the label text for nodes and edges.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return EnumSet.of(VisualProperty.EDGE_LABEL_TEXT,
        // VisualProperty.HYPEREDGE_LABEL_TEXT,
                VisualProperty.NODE_LABEL_TEXT);
    }

    /**
     * Creates a new <code>BaseLabelTextLayoutComponent</code> instance.
     */
    public BaseLabelTextLayoutComponent() {

        this(BaseLabelTextLayoutComponent.capabilities());
    }

    /**
     * Creates a new <code>BaseLabelTextLayoutComponent</code> instance with the
     * provided capabilities.
     * 
     * @param capabilities
     *            the capabilities.
     */
    public BaseLabelTextLayoutComponent(Set<VisualProperty> capabilities) {

        this(capabilities, BaseLabelTextLayoutComponent.name(),
                BaseLabelTextLayoutComponent.description(), true);
    }

    /**
     * Creates a new <code>BaseLabelTextLayoutComponent</code> instance with the
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
    public BaseLabelTextLayoutComponent(Set<VisualProperty> capabilities,
            String name, String description, Boolean randomizeName) {

        super(capabilities, name, description, randomizeName);
    }
}
