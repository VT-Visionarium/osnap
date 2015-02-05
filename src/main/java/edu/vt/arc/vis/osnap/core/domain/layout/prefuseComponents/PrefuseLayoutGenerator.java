package edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents;


//@formatter:off
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
//@formatter:on

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.controlsfx.dialog.ExceptionDialog;
import org.jutility.io.ConversionException;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IUniverse;
import edu.vt.arc.vis.osnap.io.prefuse.PrefuseFormatConverter;
import edu.vt.arc.vis.osnap.io.prefuse.PrefuseVisualizationEngine;
import static edu.vt.arc.vis.osnap.io.prefuse.Constants.PREFUSE_GROUP;
import prefuse.Visualization;
import prefuse.action.layout.Layout;
import prefuse.action.layout.graph.TreeLayout;
import prefuse.activity.Activity;
import prefuse.activity.ActivityListener;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.expression.Predicate;
// import prefuse.data.expression.parser.ExpressionParser;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;


/**
 * The {@code PrefuseLayoutGenerator} class provides factory methods for the
 * various 2D graph visualization layouts available through the prefuse
 * visualization toolkit.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseLayoutGenerator {


    private static PrefuseLayoutGenerator s_Instance;


    /**
     * Returns the singleton instance of the {@link PrefuseLayoutGenerator}
     * class.
     * 
     * @return the singleton instance.
     */
    public static PrefuseLayoutGenerator Instance() {

        if (PrefuseLayoutGenerator.s_Instance == null) {

            PrefuseLayoutGenerator.s_Instance = new PrefuseLayoutGenerator();
        }

        return PrefuseLayoutGenerator.s_Instance;
    }


    private final Map<String, prefuse.data.Graph> graphMap;
    private final Lock                            progressLock;
    private final Condition                       layoutFinished;
    private boolean                               continueLayout;


    /**
     * Creates a new {@code PrefuseLayoutGenerator} instance.
     */
    protected PrefuseLayoutGenerator() {

        this.graphMap = new LinkedHashMap<>();
        this.progressLock = new ReentrantLock();

        this.layoutFinished = progressLock.newCondition();
        this.continueLayout = false;
    }


    /**
     * Runs the layout of the provided {@link IPrefuseLayout} and applies it to
     * the {@link Visualization}.
     * 
     * @param layoutComponent
     *            the layout component to run.
     * @param visualization
     *            the visualization to modify.
     */
    public void layout(
            IPrefuseLayout layoutComponent,
            edu.vt.arc.vis.osnap.core.domain.visualization.Visualization visualization) {

        try {

            Graph graph = null;

            graph = this.obtainGraph(visualization.getUniverse());

            if (graph != null) {

                Predicate filter = this.createFilterPredicate(layoutComponent);

                Visualization vis = this.createAndRunVisualization(graph,
                        layoutComponent, filter);

                this.progressLock.lock();
                try {

                    this.layoutFinished.await();
                }
                catch (InterruptedException e) {

                    ExceptionDialog edlg = new ExceptionDialog(e);

                    edlg.setTitle("Layout interrupted!");

                    edlg.setHeaderText("Layout " + layoutComponent.getName()
                            + " was interrupted during layout!");

                    edlg.showAndWait();
                }
                finally {

                    this.progressLock.unlock();
                }

                if (this.continueLayout) {

                    PrefuseVisualizationEngine engine = new PrefuseVisualizationEngine(
                            layoutComponent, visualization);

                    engine.convert(
                            vis,
                            edu.vt.arc.vis.osnap.core.domain.visualization.Visualization.class);

                }
                else {
                    // TODOCUMENT
                }
            }
        }
        catch (ConversionException e) {

            ExceptionDialog edlg = new ExceptionDialog(e);

            edlg.setTitle("Conversion failed!");

            edlg.setHeaderText("Conversion of internal graph format to native prefuse format failed!");

            edlg.showAndWait();
        }
    }

    /**
     * Creates or retrieves the Prefuse {@link Graph} associated with a provided
     * {@link Universe}.
     * 
     * @param universe
     *            the universe queried.
     * @return the Prefuse graph representing the universe.
     * @throws ConversionException
     *             if the conversion of the {@link Universe} fails.
     */
    private Graph obtainGraph(IUniverse universe)
            throws ConversionException {

        Graph graph = null;
        if (!this.graphMap.containsKey(universe.getId())) {

            graph = PrefuseFormatConverter.Instance().convert(universe,
                    prefuse.data.Graph.class);
            this.graphMap.put(universe.getId(), graph);

        }
        else {
            graph = this.graphMap.get(universe.getId());
        }

        return graph;
    }


    /**
     * Creates and runs the Prefuse {@link Visualization} using the provided
     * Prefuse {@link Graph}, {@link IPrefuseLayout}, and Prefuse
     * {@link Predicate Filter Predicate}.
     * 
     * @param graph
     *            the Prefuse graph to use for visualization.
     * @param layoutComponent
     *            the layout component.
     * @param filter
     *            the filter predicate.
     * @return a visualization.
     */
    private Visualization createAndRunVisualization(Graph graph,
            IPrefuseLayout layoutComponent, Predicate filter) {

        // -- 2. the visualization --------------------------------------------

        // add the graph to the visualization as the data group "graph"
        // nodes and edges are accessible as "graph.nodes" and "graph.edges"

        Visualization vis = new Visualization();
        if (filter == null) {

            vis.add(PREFUSE_GROUP, graph);
        }
        else {

            vis.add(PREFUSE_GROUP, graph, filter);

        }

        if (layoutComponent instanceof IPrefuseTreeLayout
                && layoutComponent.getLayout() instanceof TreeLayout) {

            IPrefuseTreeLayout treeLayoutComponent = (IPrefuseTreeLayout) layoutComponent;

            TreeLayout treeLayout = (TreeLayout) treeLayoutComponent
                    .getLayout();

            String rootNodeID = treeLayoutComponent.getRootNode().getId();

            Iterator<?> it = graph.nodes();
            boolean set = false;

            while (it.hasNext()) {
                Object object = it.next();
                if (object instanceof Node) {
                    Node node = (Node) object;

                    if (node.canGetString(edu.vt.arc.vis.osnap.io.prefuse.Constants.ID)) {
                        String id = node
                                .getString(edu.vt.arc.vis.osnap.io.prefuse.Constants.ID);

                        if (id.equals(rootNodeID)) {
                            VisualItem visualItem = vis.getVisualItem(
                                    PREFUSE_GROUP, node);
                            treeLayout.setLayoutRoot((NodeItem) visualItem);
                            set = true;
                            break;
                        }
                    }
                }
            }
            if (!set) {

                throw new RuntimeException("LayoutRoot never set");
            }

        }


        vis.setInteractive(PREFUSE_GROUP + ".edges", null, false);
        vis.setInteractive(PREFUSE_GROUP + ".nodes", null, false);

        // -- 3. the renderers and renderer factory ---------------------------


        // -- 4. the processing actions ---------------------------------------

        Layout layout = layoutComponent.getLayout();

        // add the actions to the visualization
        vis.putAction("layout", layout);

        layout.addActivityListener(new ActivityListener() {

            @Override
            public void activityScheduled(Activity a) {

                // Nothing to be done
            }

            @Override
            public void activityStarted(Activity a) {

                // TODO: add functionality for progress bar
            }

            @Override
            public void activityStepped(Activity a) {

                // TODO: add functionality for progress bar
            }

            @Override
            public void activityFinished(Activity a) {

                // TODO: add functionality for progress bar

                PrefuseLayoutGenerator.this.progressLock.lock();
                PrefuseLayoutGenerator.this.continueLayout = true;
                PrefuseLayoutGenerator.this.layoutFinished.signalAll();
                PrefuseLayoutGenerator.this.progressLock.unlock();

            }

            @Override
            public void activityCancelled(Activity a) {

                // TODO: add functionality for progress bar

                PrefuseLayoutGenerator.this.progressLock.lock();
                PrefuseLayoutGenerator.this.layoutFinished.signalAll();
                PrefuseLayoutGenerator.this.progressLock.unlock();
            }

        });
        // -- 5. the display and interactive controls -------------------------

        layoutComponent.applyParameters();

        // -- 6. launch the visualization -------------------------------------

        // start up the animated layout
        vis.run("layout");
        return vis;
    }

    /**
     * Creates a {@link Predicate PredicateFilter} based on the restriction
     * contained in the {@link IPrefuseLayout}.
     * 
     * @param layoutComponent
     *            the layout componen on which to base the restriction.
     * @return the filter predicate.
     */
    private Predicate createFilterPredicate(IPrefuseLayout layoutComponent) {

        Predicate filter = null;

        // TODO: uncomment once filters are implemented

        // String expression = this.createRestrictionExpression(layoutComponent
        // .getRestriction());
        // ExpressionParser.predicate(expression);

        return filter;

    }


    /**
     * Creates the restriction expression of a restriction to use in the
     * creation of an equivalent {@link Predicate PredicateFilter}.
     * 
     * @param restriction
     *            the restriction of a {@link IPrefuseLayout}.
     * @return the restriction expression.
     */
    String createRestrictionExpression(Collection<IGraphObject> restriction) {

        if (restriction.isEmpty()) {
            return null;
        }
        StringBuilder expression = new StringBuilder();

        Set<String> nodeNames = new LinkedHashSet<>();
        Set<String> edgeNames = new LinkedHashSet<>();
        for (IGraphObject graphObject : restriction) {

            if (graphObject instanceof INode) {

                nodeNames.add(graphObject.getId());
            }
            else if (graphObject instanceof IEdge) {

                edgeNames.add(graphObject.getId());
            }
        }

        if (nodeNames.isEmpty() && !edgeNames.isEmpty()) {

            // Calculate which nodes are contained.
            for (IGraphObject graphObject : restriction) {

                if (graphObject instanceof IEdge) {
                    IEdge edge = (IEdge) graphObject;

                    nodeNames.add(edge.getSource().getId());
                    nodeNames.add(edge.getTarget().getId());

                }
            }
        }

        // At this point, there always will be nodes.
        // -> Create restriction for node names first.
        expression.append("[graphViz:type]=='graphViz:node' && (");
        expression.append(this.subExpression(nodeNames));
        expression.append(")");

        // If edges are specified
        // -> Create restriction for edge names.
        if (!edgeNames.isEmpty()) {

            expression.append(" || ");

            expression.append("[graphViz:type]=='graphViz:edge' && (");
            expression.append(this.subExpression(edgeNames));
            expression.append(")");
        }


        // TODO: fix implementation once Filters are implemented
        // return expression.toString();
        return null;


    }

    /**
     * Creates a sub-expression for a collection of names.
     * 
     * @param names
     *            a collection of names.
     * @return the sub-expression.
     */
    private String subExpression(Collection<String> names) {

        StringBuilder expression = new StringBuilder();

        int i = 0;
        for (String name : names) {

            if (i > 0) {

                expression.append(" || ");
            }

            expression.append("[graphViz:ID] == \'");
            expression.append(name);
            expression.append("\'");
            i++;
        }

        return expression.toString();
    }
}
