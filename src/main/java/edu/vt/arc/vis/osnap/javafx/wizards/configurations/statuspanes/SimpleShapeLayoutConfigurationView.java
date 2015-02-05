package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


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


import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleShapeLayoutConfiguration;


/**
 * The {@code SimpleShapeLayoutConfigurationView} class provides a status panes
 * for {@link SimpleShapeLayoutConfiguration ShapeLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleShapeLayoutConfigurationView
        extends
        LayoutConfigurationView<SimpleShapeLayout, SimpleShapeLayoutConfiguration> {

    private final Text shape;
    private final Text shapeTF;



    /**
     * Creates a new instance of the {@code SimpleShapeLayoutConfigurationView}
     * class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public SimpleShapeLayoutConfigurationView(final String title) {

        super(title, new SimpleShapeLayoutConfiguration());

        this.shape = new Text("Shape:");
        this.shapeTF = new Text();
        this.shape.setStyle("-fx-font-weight: bold");

        this.add(this.shape, 0, 3);
        this.add(this.shapeTF, 1, 3);
    }



    @Override
    public void refreshView() {

        super.refreshView();

        this.shapeTF.setText("");
        if (this.getConfiguration() != null) {

            if (this.getConfiguration().getShape() != null) {

                this.shapeTF.setText(this.getConfiguration().getShape()
                        .toString());
            }
        }
    }

}
