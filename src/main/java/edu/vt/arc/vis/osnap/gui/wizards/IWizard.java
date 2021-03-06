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


package edu.vt.arc.vis.osnap.gui.wizards;


import edu.vt.arc.vis.osnap.gui.wizards.pages.WizardPage;
import javafx.stage.Stage;


/**
 * Wizard interface
 * 
 * @author Shawn P Neuman
 * 
 */
public interface IWizard {


    /**
     * Sets the owner of the wizard.
     * 
     * @param owner
     *            the owner.
     */
    public void setOwner(Stage owner);

    /**
     * Returns the number of pages in this wizard.
     * 
     * @return the number of pages.
     */
    public int numberOfPages();

    /**
     * gets the page at a given index
     * 
     * @param index
     *            the integer value of a page
     * @return the Wizard page at the given index
     */
    public WizardPage getPage(int index);

    /**
     * removes a page form the stack
     * 
     * @param index
     *            the index of the page to be removed
     */
    public void removePage(int index);

    /**
     * adds a page to the stack. used for dynamic adding
     * 
     * @param page
     *            the page to be added
     */
    public void addPage(WizardPage page);

    /**
     * adds a page at a specific index
     * 
     * @param index
     *            the index of the page to be added
     * @param page
     *            the page to be added
     */
    public void addPage(int index, WizardPage page);

    /**
     * navigate to next page if it exists
     */
    public void nextPage();

    /**
     * navigate to previous page if it exists
     */
    public void priorPage();

    /**
     * @return true if next page exists
     */
    public boolean hasNextPage();

    /**
     * @return true if previous page exists
     */
    public boolean hasPriorPage();

    /**
     * navigate to page and push page on stack
     * 
     * @param nextPageIdx
     *            index of next page
     * @param pushHistory
     *            add page to stack
     */
    void navTo(int nextPageIdx, boolean pushHistory);

    /**
     * navigate to next page with this index
     * 
     * @param nextPageIdx
     *            index to navigate to
     */
    public void navTo(int nextPageIdx);

    /**
     * navigate to a page with a specific string id
     * 
     * @param id
     *            string value of page
     */
    public void navTo(String id);

    /**
     * gets the index value of a given page
     * 
     * @return index of this page
     */
    public int getCurrentPageIndex();

    /**
     * close the wizard
     */
    public void finish();

    /**
     * cancel the wizard
     */
    public void cancel();
}
