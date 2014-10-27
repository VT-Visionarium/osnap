/**
 * 
 */
package graphVisualizer.gui.wizards.statuspanes;


import graphVisualizer.gui.wizards.statusobjects.ViewPointStatusObject;
import graphVisualizer.layout.mappedComponents.MappedViewpointLayoutComponent;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class ViewPointStatusPane
        extends BaseStatusPane {

    private Text cameraOffset;
    private Text xOffset;
    private Text yOffset;
    private Text zOffset;

    /**
     * @param lbl
     */
    public ViewPointStatusPane(String lbl) {

        super(lbl);


        cameraOffset = new Text("Viewpoint Camera Offsets: x, y, z");
        xOffset = new Text("0.0");
        yOffset = new Text("0.0");
        zOffset = new Text("20.0");

        getGrid().add(cameraOffset, 0, 2);
        getGrid().add(xOffset, 0, 3);
        getGrid().add(yOffset, 1, 3);
        getGrid().add(zOffset, 2, 3);

    }

    @Override
    public void update(Observable arg0, Object arg1) {

//        System.out.println("update called in view point status pane");
        ViewPointStatusObject status = (ViewPointStatusObject) arg0;

        super.update(arg0, arg1);

        xOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getX());
        yOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getY());
        zOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getZ());
    }

}
