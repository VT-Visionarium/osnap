package edu.vt.arc.vis.osnap.javafx;


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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.Layout;


/**
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 */

public class LayoutMenu
        extends Menu {

    private final ObjectProperty<Layout>   layout;
    private final ObjectProperty<Universe> universe;

    private final Action                   createDefaultMI;
    private final Action                   createEmptyMI;
    private final Action                   closeMI;

    /**
     * Returns the layout property.
     * 
     * @return the layout property.
     */
    public ObjectProperty<Layout> layout() {

        return this.layout;
    }

    /**
     * Returns the layout.
     * 
     * @return the layout.
     */
    public Layout getLayout() {

        return this.layout.get();
    }

    /**
     * Sets the layout.
     * 
     * @param layout
     *            the layout.
     */
    public void setLayout(Layout layout) {

        this.layout.set(layout);
    }

    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Universe> universe() {

        return this.universe;
    }

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe.get();
    }

    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe.set(universe);
    }

    /**
     * Creates a new instance of the {@link LayoutMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     */
    public LayoutMenu(Stage stage) {

        this(stage, null);
    }

    /**
     * Creates a new instance of the {@link LayoutMenu} class.
     * 
     * @param stage
     *            the stage of this menu.
     * 
     * @param graphic
     *            the graphic of the file menu.
     */
    public LayoutMenu(Stage stage, Node graphic) {

        super("Layout", graphic);

        this.layout = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();



        this.createDefaultMI = new Action("Create Layout with Default Values",
                actionEvent -> {


                    this.setLayout(Layout.defaultLayout(LayoutMenu.this
                            .getUniverse()));
                });


        this.createEmptyMI = new Action("Create Empty Layout", actionEvent -> {


            this.setLayout(new Layout(LayoutMenu.this.getUniverse()));
        });


        this.closeMI = new Action("Clear Layout", actionEvent -> {

            this.layout.set(null);
        });

        this.closeMI.disabledProperty().set(true);

        this.getItems().addAll(ActionUtils.createMenuItem(createDefaultMI),
                ActionUtils.createMenuItem(createEmptyMI),
                ActionUtils.createMenuItem(closeMI));


        this.layout.addListener((observable, oldValue, newValue) -> {

            boolean requiresLayout = (newValue == null);

            this.closeMI.disabledProperty().set(requiresLayout);
        });
    }
}
