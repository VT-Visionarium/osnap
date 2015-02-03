package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
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

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseTreeLayoutComponent;


/**
 * The {@code IPrefuseTreeLayoutConfiguration} interface provides the
 * contract for {@link IConfiguration Configurations} of
 * {@link IPrefuseTreeLayoutComponent Prefuse TreeLayoutComponents}.
 * 
 * @param <T>
 *            the type of the {@link IPrefuseTreeLayoutComponent}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.2.0
 */
public interface IPrefuseTreeLayoutConfiguration<T extends IPrefuseTreeLayoutComponent>
        extends IPrefuseLayoutConfiguration<T>, ITreeLayoutConfiguration<T> {

    // tagging interface
}