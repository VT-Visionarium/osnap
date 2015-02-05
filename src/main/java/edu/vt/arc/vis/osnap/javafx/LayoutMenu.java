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
import edu.vt.arc.vis.osnap.core.domain.layout.LayoutSet;


/**
 * The {@code LayoutMenu} class provides the options pertaining to
 * {@link LayoutSet LayoutSets}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public class LayoutMenu
        extends Menu {

    private final ObjectProperty<LayoutSet> layoutSet;
    private final ObjectProperty<Universe>  universe;

    private final Action                    createDefaultMI;
    private final Action                    createEmptyMI;
    private final Action                    closeMI;

    /**
     * Returns the layoutSet property.
     * 
     * @return the layoutSet property.
     */
    public ObjectProperty<LayoutSet> layoutSet() {

        return this.layoutSet;
    }

    /**
     * Returns the layoutSet.
     * 
     * @return the layoutSet.
     */
    public LayoutSet getLayout() {

        return this.layoutSet.get();
    }

    /**
     * Sets the layoutSet.
     * 
     * @param layoutSet
     *            the layoutSet.
     */
    public void setLayout(LayoutSet layoutSet) {

        this.layoutSet.set(layoutSet);
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

        this.layoutSet = new SimpleObjectProperty<>();
        this.universe = new SimpleObjectProperty<>();



        this.createDefaultMI = new Action("Create Layout with Default Values",
                actionEvent -> {


                    this.setLayout(LayoutSet.defaultLayout(LayoutMenu.this
                            .getUniverse()));
                });


        this.createEmptyMI = new Action("Create Empty Layout", actionEvent -> {


            this.setLayout(new LayoutSet(LayoutMenu.this.getUniverse()));
        });


        this.closeMI = new Action("Clear Layout", actionEvent -> {

            this.layoutSet.set(null);
        });

        this.closeMI.disabledProperty().set(true);

        this.getItems().addAll(ActionUtils.createMenuItem(createDefaultMI),
                ActionUtils.createMenuItem(createEmptyMI),
                ActionUtils.createMenuItem(closeMI));


        this.layoutSet.addListener((observable, oldValue, newValue) -> {

            boolean requiresLayout = (newValue == null);

            this.closeMI.disabledProperty().set(requiresLayout);
        });
    }
}
