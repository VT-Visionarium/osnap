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


package graphVisualizer.graph.algorithms;


import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IHyperEdge;
import graphVisualizer.graph.common.INode;


/**
 * The <CODE>DefaultWeight</CODE> class provides an implementation of the
 * <CODE>IWeight</CODE> interface that always returns a weight of 1.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class DefaultWeight
        implements IWeight {


    private static DefaultWeight instance;


    /**
     * Returns the singleton instance of the class.
     * 
     * @return the singleton instance.
     */
    public static IWeight Instance() {

        if (instance == null) {
            instance = new DefaultWeight();
        }
        return instance;
    }


    private DefaultWeight() {

    }


    @Override
    public Integer getWeight(IEdge edge) {

        return 1;
    }

    @Override
    public Integer getWeight(IHyperEdge hyperedge) {

        return 1;
    }

    @Override
    public Integer getWeight(INode node) {

        return 1;
    }

}
