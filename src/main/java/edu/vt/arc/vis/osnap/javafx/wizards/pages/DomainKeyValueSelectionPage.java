package edu.vt.arc.vis.osnap.javafx.wizards.pages;


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



import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IMappedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.IConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.MappedLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.content.DomainKeyValueSelectionGridPane;



/**
 * The {@code DomainKeyValueSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the domain key and values
 * for a {@link IMappedLayout Mapped Layout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class DomainKeyValueSelectionPage
        extends
        LayoutConfigurationWizardPage<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>, IMappedLayoutConfiguration, MappedLayoutConfigurationView> {

    private final DomainKeyValueSelectionGridPane selectionGridPane;


    /**
     * Creates a new instance of the {@code DomainKeyValueSelectionPage} class.
     * 
     * @param configurationView
     *            the {@link IConfigurationView} of this page.
     */
    public DomainKeyValueSelectionPage(
            MappedLayoutConfigurationView configurationView) {

        super("Select Domain Key and Values", configurationView);


        this.selectionGridPane = new DomainKeyValueSelectionGridPane();

        this.getContentGridPane().add(this.selectionGridPane, 0, 0);
    }


    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        this.selectionGridPane.setRestriction(this.getConfiguration()
                .getRestriction());
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfiguration().setDomainValues(
                this.selectionGridPane.getDomainValues());
        this.getConfiguration().setKey(this.selectionGridPane.getDomainKey());
        this.getConfiguration().setDomainValueType(
                this.selectionGridPane.getDomainKey().getValueType());
    }
}
