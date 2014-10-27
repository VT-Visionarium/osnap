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


package edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents;


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseLabelTextLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.Label;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The <code>SimpleLabelTextLayoutComponent</code> class provides a basic
 * implementation of the
 * {@link edu.vt.arc.vis.osnap.core.domain.layout.common.ILabelTextLayoutComponent} interface,
 * setting the label string of graph objects to their respective id.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "SimpleLabelTextLayoutComponent")
public class SimpleLabelTextLayoutComponent
        extends BaseLabelTextLayoutComponent {



    /**
     * Returns the name of this <code>ILayoutComponent</code>.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Label Text Layout Component";
    }


    /**
     * Returns the description of this <code>ILayoutComponent</code>.
     * 
     * @return the description.
     */
    public static String description() {

        return "The "
                + SimpleLabelTextLayoutComponent.name()
                + " provides the a label text for graph objects(nodes or edges)."
                + "\n\n\tThe ID is used as the label text.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty VisualProperties}
     * that can be provided) of this <code>ILayoutComponent</code>.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseLabelTextLayoutComponent.capabilities();
    }

    /**
     * Creates a new instance of the <code>SimpleLabelTextLayoutComponent</code>
     * class.
     */
    public SimpleLabelTextLayoutComponent() {

        super(SimpleLabelTextLayoutComponent.capabilities(),
                SimpleLabelTextLayoutComponent.name(),
                SimpleLabelTextLayoutComponent.description(), false);
    }



    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualNode)
     */
    @Override
    public void layout(VisualNode visualNode) {

        if (this.isEnabled(VisualProperty.NODE_LABEL_TEXT)) {

            if (visualNode.getLabel() == null) {

                visualNode.setLabel(new Label(visualNode.getVisualGraph()
                        .getVisualization().getPrecision()));

            }
            visualNode.getLabel().setText(visualNode.getNode().getId());
            visualNode.getLabel().setVisible(true);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualEdge)
     */
    @Override
    public void layout(VisualEdge visualEdge) {

        if (this.isEnabled(VisualProperty.EDGE_LABEL_TEXT)) {

            if (visualEdge.getLabel() == null) {

                visualEdge.setLabel(new Label(visualEdge.getVisualGraph()
                        .getVisualization().getPrecision()));

            }
            visualEdge.getLabel().setText(visualEdge.getEdge().getId());
            visualEdge.getLabel().setVisible(true);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent#layout(edu.vt.arc.vis.osnap.core
     * .visualization.VisualHyperEdge)
     */
    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.isEnabled(VisualProperty.HYPEREDGE_LABEL_TEXT)) {

            if (visualHyperEdge.getLabel() == null) {

                visualHyperEdge.setLabel(new Label(visualHyperEdge
                        .getVisualGraph().getVisualization().getPrecision()));

            }
            visualHyperEdge.getLabel().setText(visualHyperEdge.getHyperEdge().getId());
            visualHyperEdge.getLabel().setVisible(true);
        }

    }
}
