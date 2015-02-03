package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


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
// @formatter:on


import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedLayoutFactory;
import edu.vt.arc.vis.osnap.core.domain.mappings.IValueMapping;

import java.util.ArrayList;
import java.util.List;



/**
 * The abstract {@code MappedLayoutConfiguration} class provides the minimum
 * {@link IMappedLayoutConfiguration} for {@link IMappedLayout MappedLayouts}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class MappedLayoutConfiguration
        extends
        LayoutConfiguration<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>>
        implements IMappedLayoutConfiguration {


    private IGraphObjectBasedValueTypeContainer key;
    private final List<Object>                  domainValues;
    private final List<IValueMapping<?, ?>>     valueMappings;
    private Class<?>                            domainValueType;



    /**
     * Creates a new instance of the {@code MappedLayoutConfiguration} class
     * class with the provided defaults for name and description.
     */
    public MappedLayoutConfiguration() {

        super(BaseMappedLayout.name(), BaseMappedLayout.description());

        this.domainValues = new ArrayList<>();
        this.valueMappings = new ArrayList<>();
    }



    @Override
    public IGraphObjectBasedValueTypeContainer getDomainKey() {

        return key;
    }

    @Override
    public void setKey(final IGraphObjectBasedValueTypeContainer key) {

        this.key = key;
    }

    @Override
    public List<?> getDomainValues() {

        return this.domainValues;
    }

    @Override
    public void setDomainValues(final List<?> domainValues) {

        this.domainValues.clear();
        this.domainValues.addAll(domainValues);
    }

    @Override
    public List<IValueMapping<?, ?>> getValueMappings() {

        return valueMappings;
    }

    @Override
    public void setValueMappings(final List<IValueMapping<?, ?>> valueMappings) {

        this.valueMappings.clear();
        this.valueMappings.addAll(valueMappings);
    }

    @Override
    public Class<?> getDomainValueType() {

        return this.domainValueType;
    }

    @Override
    public void setDomainValueType(final Class<?> domainValueType) {

        this.domainValueType = domainValueType;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?> createConfiguredObject() {

        IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?> layout = MappedLayoutFactory
                .createMappedLayout(this.getDomainKey(),
                        this.getEnabledVisualProperty());

        for (IValueMapping<?, ?> valueMapping : this.getValueMappings()) {

            layout.addValueMapping((IValueMapping) valueMapping);
        }


        return layout;
    }
}
