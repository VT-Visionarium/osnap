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


import org.authalic.saint.server.config.filters.CORSFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;


/**
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig
        extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.inMemoryAuthentication().withUser("osnap").password("0p3n$3m4nt1c")
                .roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.csrf().disable();
        http.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);
        //http.authorizeRequests().antMatchers("/**").hasRole("USER")
    //            .anyRequest().anonymous().and().httpBasic();
    }
    

    private CORSFilter corsFilter() {
        
        return new CORSFilter();
    };
}
