/**
 * 
 */
package edu.vt.arc.vis.osnap.config.filters;

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

/*
 * --
 * Saint-Server
 * -
 * Copyright (C) 2014 Peter J. Radics, Brian Farrell, Andrew Foy, Peter Sforza
 * -
 * This program is licensed under the terms of the SA-INT License (the "License").
 * You may not use this file except in compliance with the License.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * You should have received a copy of the License along with this program.
 * --
 */


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * Basic filter for Cross-Origin Resource Sharing (CORS). Allows access from
 * any domain.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @since 0.1
 */
@Component
public class CORSFilter
        extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String originHeader = request.getHeader("Origin");
        if (request.getHeader("Access-Control-Request-Method") != null
                && "OPTIONS".equals(request.getMethod())) {


            response.addHeader("Access-Control-Allow-Origin", originHeader);


            response.setHeader("Access-Control-Allow-Methods",
                    "POST, GET, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers",
                    "Content-Type, X-Requested-With, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");
        }
        else {
            
            response.addHeader("Access-Control-Allow-Origin", originHeader);
        }

        filterChain.doFilter(request, response);
    }

}
