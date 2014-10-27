package edu.vt.arc.vis.osnap.config;

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

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * Web Application Initializer.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
@Order(2)
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {


    // {!begin addToRootContext}
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[] { SecurityConfig.class, JPAConfiguration.class, MVCConfig.class, CoreConfig.class };
    }

    // {!end addToRootContext}

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[] { MVCConfig.class };
    }


    @Override
    protected String[] getServletMappings() {

        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[] { characterEncodingFilter };
    }


}