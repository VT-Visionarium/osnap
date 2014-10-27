/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.javafx.wizards.statusobjects;

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



import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.mappings.Mapping;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;

import java.util.ArrayList;
import java.util.List;



// -------------------------------------------------------------------------
/**
 * MappingStatus object detailing how to display graph objects.
 * 
 * @author spn2460
 * @version May 22, 2013
 */
public class MappingStatus
        extends BaseStatusObject {


    // meta data or property
    private String                              provider;
    // value being applied available meta data or available properties
    private IGraphObjectBasedValueTypeContainer key;
    private List<?>                             valuesList;


    /**
     * creates a display selection object to be used in determining how to
     * display the various properties of a graph
     * 
     * @param visualProperty
     *            the visual property
     * @param provider
     *            the property provider
     * @param key
     *            the key value
     */
    public MappingStatus(VisualProperty visualProperty, String provider,
            IGraphObjectBasedValueTypeContainer key) {

        this.setVisualProperty(visualProperty);
        this.setProvider(provider);
        this.setKey(key);
    }



    /**
     * creates an empty display selection object constructor chaining
     */
    public MappingStatus() {

        this(null, null, null);
    }

    /**
     * @return the provider i.e. meta data or property
     */
    public String getProvider() {

        return provider;
    }

    /**
     * @param provider
     *            the provider being set
     */
    public void setProvider(String provider) {

        this.provider = provider;
        setChanged();
        notifyObservers(getProvider());
    }



    /**
     * @return the key value
     */
    public IGraphObjectBasedValueTypeContainer getKey() {

        return key;
    }



    /**
     * @param key
     *            the key value being set
     */
    public void setKey(IGraphObjectBasedValueTypeContainer key) {

        this.key = key;
        setChanged();
        notifyObservers(getKey());
    }



    /**
     * @return list of values
     */
    public List<?> getValuesList() {

        return valuesList;
    }



    /**
     * @param valuesList
     *            the list being set
     */

    public void setValuesList(List<?> valuesList) {

        this.valuesList = valuesList;
        setChanged();
        notifyObservers(getValuesList());
    }

    /**
     * clearing the list, called when navigating backwards
     */
    public void clearValuesList() {

        this.valuesList = new ArrayList<>();
        setValuesList(valuesList);
    }



    /**
     * @return the mapping
     */
    @SuppressWarnings("unchecked")
    public Mapping<?, ?, ?, VisualProperty> getMapping() {

        if (this.getLayoutComponent() instanceof Mapping<?, ?, ?, ?>) {
            return (Mapping<?, ?, ?, VisualProperty>) this.getLayoutComponent();
        }
        return null;
    }


    /**
     * @param mapping
     *            the mapping.
     */
    public void setMapping(Mapping<?, ?, ?, VisualProperty> mapping) {

        if (mapping instanceof ILayoutComponent) {

            this.setLayoutComponent((ILayoutComponent) mapping);
        }
    }



}
