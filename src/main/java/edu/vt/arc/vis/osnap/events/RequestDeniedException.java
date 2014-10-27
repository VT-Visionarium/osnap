/**
 * 
 */
package edu.vt.arc.vis.osnap.events;

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



/**
 * @author Willy Lund
 *
 */
public class RequestDeniedException
        extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8979985895255728771L;

    /**
     * 
     */
    public RequestDeniedException() {

        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public RequestDeniedException(String message) {

        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public RequestDeniedException(Throwable cause) {

        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public RequestDeniedException(String message, Throwable cause) {

        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public RequestDeniedException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
