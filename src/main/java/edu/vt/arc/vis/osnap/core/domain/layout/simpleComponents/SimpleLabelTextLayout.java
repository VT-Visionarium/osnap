package edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents;


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


import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseLabelTextLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Label;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.Set;

import javax.xml.bind.annotation.XmlType;


/**
 * The {@code SimpleLabelTextLayout} class provides a basic implementation of
 * the {@link edu.vt.arc.vis.osnap.core.domain.layout.common.ILabelTextLayout}
 * interface, setting the label string of graph objects to their respective id.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlType(name = "SimpleLabelTextLayout")
public class SimpleLabelTextLayout
        extends BaseLabelTextLayout {



    /**
     * Returns the name of this {@code ILayout}.
     * 
     * @return the name.
     */
    public static String name() {

        return "Simple Label Text Layout";
    }


    /**
     * Returns the description of this {@code ILayout}.
     * 
     * @return the description.
     */
    public static String description() {

        return "The "
                + SimpleLabelTextLayout.name()
                + " provides the a label text for graph objects(nodes or edges)."
                + "\n\n\tThe ID is used as the label text.";
    }


    /**
     * Returns the capabilities (the set of
     * {@link edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty
     * VisualProperties} that can be provided) of this {@code ILayout}.
     * 
     * @return the capabilities.
     */
    public static Set<VisualProperty> capabilities() {

        return BaseLabelTextLayout.capabilities();
    }

    /**
     * Creates a new instance of the {@code SimpleLabelTextLayout} class.
     */
    public SimpleLabelTextLayout() {

        super(SimpleLabelTextLayout.capabilities(), SimpleLabelTextLayout
                .name(), SimpleLabelTextLayout.description(), false);
    }



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

    @Override
    public void layout(VisualHyperEdge visualHyperEdge) {

        if (this.isEnabled(VisualProperty.HYPEREDGE_LABEL_TEXT)) {

            if (visualHyperEdge.getLabel() == null) {

                visualHyperEdge.setLabel(new Label(visualHyperEdge
                        .getVisualGraph().getVisualization().getPrecision()));

            }
            visualHyperEdge.getLabel().setText(
                    visualHyperEdge.getHyperEdge().getId());
            visualHyperEdge.getLabel().setVisible(true);
        }

    }
}
