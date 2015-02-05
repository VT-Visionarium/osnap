package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;

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


import edu.vt.arc.vis.osnap.core.domain.layout.common.ITreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ITreeLayoutConfiguration;


/**
 * The {@link ITreeLayoutConfigurationView} provides a
 * contract for all status panes for
 * {@link ITreeLayoutConfiguration
 * TreeLayoutComponentConfigurations}.
 * 
 * @param <O>
 *            the type of the {@link ITreeLayout}.
 * @param <C>
 *            the concrete type of the
 *            {@link ITreeLayoutConfiguration}.
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface ITreeLayoutConfigurationView<O extends ITreeLayout, C extends ITreeLayoutConfiguration<O>>
        extends ICoordinateLayoutConfigurationView<O, C> {

    // tagging interface
}
