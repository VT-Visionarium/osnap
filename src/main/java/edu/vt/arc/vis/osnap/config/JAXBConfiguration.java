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


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


/**
 * Configuration of the Java XML Binding for Spring.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
@Configuration
public class JAXBConfiguration {


    /**
     * Returns the JAXB2 Marshaller.
     * 
     * @return the JAXB2 Marshaller.
     */
    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setPackagesToScan("edu.vt.arc.vis.osnap.core.domain");

        return marshaller;
    }

}
