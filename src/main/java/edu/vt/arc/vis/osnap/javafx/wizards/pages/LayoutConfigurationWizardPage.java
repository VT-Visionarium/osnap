package edu.vt.arc.vis.osnap.javafx.wizards.pages;


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


import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard.WizardPane;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.IConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;


/**
 * The abstract {@code LayoutConfigurationWizardPage} class provides the base
 * for pages providing values for the {@link ILayoutConfiguration} of a
 * {@link ILayout}.
 * 
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the type of the {@link IConfiguration Configuration}.
 * @param <T>
 *            the type of the {@link IConfigurationView ConfigurationView}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public abstract class LayoutConfigurationWizardPage<O extends ILayout, C extends ILayoutConfiguration<O>, T extends GridPane & ILayoutConfigurationView<O, C>>
        extends WizardPane {

    private final ValidationGroup validationGroup;

    private final GridPane        splitPane;
    private final GridPane        contentGridPane;
    private final T               configurationView;



    /**
     * Returns the {@link ValidationGroup}.
     * 
     * @return the {@link ValidationGroup}.
     */
    public ValidationGroup validationGroup() {

        return this.validationGroup;
    }


    /**
     * Returns the content {@link GridPane}.
     * 
     * @return the content {@link GridPane}.
     */
    public GridPane getContentGridPane() {

        return this.contentGridPane;
    }


    /**
     * Returns the {@link ILayoutConfigurationView}.
     * 
     * @return the {@link ILayoutConfigurationView}.
     */
    public T getConfigurationView() {

        return this.configurationView;
    }

    /**
     * Returns the {@link ILayoutConfiguration}.
     * 
     * @return the {@link ILayoutConfiguration}.
     */
    public C getConfiguration() {

        if (this.configurationView != null) {

            return this.configurationView.getConfiguration();
        }

        return null;
    }

    /**
     * Creates a new instance of the {@code LayoutConfigurationWizardPage}
     * class.
     * 
     * @param headerText
     *            the header text.
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     */
    public LayoutConfigurationWizardPage(final String headerText,
            final T configurationView) {

        super();

        this.configurationView = configurationView;

        this.splitPane = new GridPane();

        this.splitPane.setVgap(10);
        this.splitPane.setHgap(10);

        this.contentGridPane = new GridPane();
        this.contentGridPane.setVgap(10);
        this.contentGridPane.setHgap(10);

        this.validationGroup = new ValidationGroup();


        ColumnConstraints contentColumn = new ColumnConstraints();
        contentColumn.setPercentWidth(50);

        ColumnConstraints statusColumn = new ColumnConstraints();
        statusColumn.setPercentWidth(50);

        this.splitPane.getColumnConstraints().addAll(contentColumn,
                statusColumn);

        this.splitPane.add(this.contentGridPane, 0, 0);
        GridPane.setHgrow(this.splitPane, Priority.ALWAYS);
        GridPane.setHgrow(this.contentGridPane, Priority.ALWAYS);
        GridPane.setHgrow(this.configurationView, Priority.ALWAYS);


        this.setHeaderText(headerText);

        this.setContent(this.splitPane);
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        this.splitPane.add(this.getConfigurationView(), 1, 0);
        this.getConfigurationView().refreshView();
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        this.splitPane.getChildren().remove(this.getConfigurationView());
    }
}
