package edu.vt.arc.vis.osnap.core.domain.layout.common;


import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;


/**
 * The {@code ITreeLayout} provides the shared contract of
 * all tree-based {@link ICoordinateLayout CoordinateLayoutComponents}.
 * 
 * @author Peter J. Radics
 * @version 1.2.3
 * @since 1.2.3
 */
public interface ITreeLayout
        extends ICoordinateLayout {

    /**
     * Returns the root {@link INode} of the tree-based
     * {@link ICoordinateLayout}.
     * 
     * @return the root {@link INode} of the tree-based
     *         {@link ICoordinateLayout}.
     */
    public abstract INode getRootNode();
}
