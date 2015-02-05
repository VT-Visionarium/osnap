package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
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

import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;


/**
 * The abstract {@code ConfigurationView} class provides the base for all status
 * panes for {@link IConfiguration Configurations}.
 *
 * @param <O>
 *            the type of the configured object.
 * @param <C>
 *            the type of the {@link IConfiguration Configuration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class ConfigurationView<O, C extends IConfiguration<O>>
        extends GridPane
        implements IConfigurationView<O, C> {

    private final ObjectProperty<C> configurationProperty;

    private final Label             title;


    /**
     * Returns the {@link ILayoutConfiguration} property.
     * 
     * @return the {@link ILayoutConfiguration} property.
     */
    @Override
    public ObjectProperty<C> configurationProperty() {

        return this.configurationProperty;
    }

    /**
     * Returns the value of the {@link ILayoutConfiguration} property.
     * 
     * @return the value of the {@link ILayoutConfiguration} property.
     */
    @Override
    public C getConfiguration() {

        return this.configurationProperty.get();
    }

    /**
     * Sets the value of the {@link ILayoutConfiguration} property.
     * 
     * @param value
     *            the value of the {@link ILayoutConfiguration} property.
     */
    @Override
    public void setConfiguration(final C value) {

        this.configurationProperty.set(value);
    }

    /**
     * Creates a new instance of the {@code ConfigurationView} class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public ConfigurationView(final String title) {

        this(title, null);
    }

    /**
     * Creates a new instance of the {@code ConfigurationView} class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public ConfigurationView(final String title, final C defaultConfiguration) {

        super();

        Objects.nonNull(defaultConfiguration);

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 0, 25));
        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(50);
        ColumnConstraints valueColumn = new ColumnConstraints();
        valueColumn.setPercentWidth(50);
        this.getColumnConstraints().addAll(labelColumn, valueColumn);

        this.configurationProperty = new SimpleObjectProperty<>(
                defaultConfiguration);

        this.title = new Label(title);
        this.title.setFont(Font.font("Verdana", 16));
        this.title.styleProperty().setValue(
                "-fx-font-weight: bold; -fx-padding: 0 0 5 0;");

        this.add(this.title, 0, 0);

        this.setupEventHandlers();
    }



    private void setupEventHandlers() {

        this.configurationProperty.addListener(observable -> {

            this.refreshView();
        });
    }

    /**
     * Returns the number of columns used by this view.
     * 
     * @return the number of columns used by this view.
     */
    protected int rowsUsed() {

        return 1;
    }

    /**
     * Refreshes the view.
     */
    @Override
    public abstract void refreshView();
}
