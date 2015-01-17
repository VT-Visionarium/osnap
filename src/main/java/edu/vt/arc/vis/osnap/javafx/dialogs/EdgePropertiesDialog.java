package edu.vt.arc.vis.osnap.javafx.dialogs;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
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


import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import org.jutility.javafx.control.labeled.LabeledTextField;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;


/**
 * The {@code EdgePropertiesDialog} provides a dialog box for the display of
 * {@link IEdge Edge} properties.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class EdgePropertiesDialog
        extends Dialog<ButtonType> {


    /**
     * Creates a new instance of the {@code EdgePropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code EdgePropertiesDialog}.
     * @param edge
     *            the edge.
     */
    public EdgePropertiesDialog(final Node owner, final IEdge edge) {

        this(owner == null ? null : owner.getScene().getWindow(), edge);
    }

    /**
     * Creates a new instance of the {@code EdgePropertiesDialog} class.
     * 
     * @param owner
     *            the owner of this {@code EdgePropertiesDialog}.
     * @param edge
     *            the edge.
     */
    public EdgePropertiesDialog(final Window owner, final IEdge edge) {

        super();
        this.initOwner(owner);
        this.setTitle("Edge Properties");

        LabeledTextField isDirectedTF = new LabeledTextField("Directed ?");
        isDirectedTF.setHgap(10);
        isDirectedTF.setText(edge.isDirected().toString());
        isDirectedTF.setEditable(false);

        this.getDialogPane().setContent(isDirectedTF);

        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    }
}
