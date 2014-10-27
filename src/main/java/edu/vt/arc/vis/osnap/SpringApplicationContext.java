package edu.vt.arc.vis.osnap;

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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import edu.vt.arc.vis.osnap.config.CoreConfig;
import edu.vt.arc.vis.osnap.config.JPAConfiguration;


/**
 * Wrapper to always return a reference to the Spring Application Context from
 * within non-Spring enabled beans. Unlike Spring MVC's
 * WebApplicationContextUtils we do not need a reference to the Servlet context
 * for this. All we need is for this bean to be initialized during application
 * startup.
 */
public class SpringApplicationContext {

    private static ApplicationContext instance;

    /**
     * This method is called from within the ApplicationContext once it is done
     * starting up, it will stick a reference to itself into this bean.
     * 
     * @param instance
     *            a reference to the ApplicationContext.
     */
    public static void setApplicationContext(ApplicationContext instance) {

        SpringApplicationContext.instance = instance;
    }

    /**
     * Returns the Singleton Instance of the application context.
     * 
     * @return the Singleton Instance of the application context.
     */
    public static ApplicationContext Instance() {

        if (SpringApplicationContext.instance == null) {
            
            SpringApplicationContext
            .setApplicationContext(new AnnotationConfigApplicationContext(
                    JPAConfiguration.class, CoreConfig.class));
        }
        return SpringApplicationContext.instance;
    }
}
