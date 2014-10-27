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


package edu.vt.arc.vis.osnap.gui.widgets;


import edu.vt.arc.vis.osnap.gui.wizards.content.GraphObjectTreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * consolidates a tree view with search panel into one class
 * 
 * @author Shawn P Neuman
 * 
 */
public class TreeViewWithSearchPanel
        extends VBox {

    private SearchPanel         searchPanel;
    private GraphObjectTreeView tree;
    private Text                title;


    /**
     * creates a search-able tree
     * 
     * @param label
     */
    public TreeViewWithSearchPanel(String label) {

        this.title = new Text(label);
        tree = new GraphObjectTreeView();
        searchPanel = new SearchPanel();

        this.getChildren().addAll(title, tree, searchPanel);
    }


}
