/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 first_name last_name
 */

package mainApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListMenuControllerTest {

    @Test
    void checkIfNewListIsAdded() {
        //Call addNewList() with test list title
        //compare expected list title to title in toDoListView (ListView)
    }

    @Test
    void checkIfCorrectListIsOpened() {
        //Call openList()
        //Compare selected list title to expected title
    }

    @Test
    void checkIfListIsDeleted() {
        //Call deleteList() with test list title
        //Check toDoListView (listView) to confirm it does not contain test list title
    }

    @Test
    void checkIfTitleCanBeEdited() {
        //Add original title to menu
        //Call editTitle() with new title
        //check if title was changed
    }

    @Test
    void checkIfListsAreFormattedCorrectlyForSaving() {
        //Call saveSelectedLists()
        //Save test lists
        //Compare expected .txt file to outputted .txt file
    }

    @Test
    void checkIfYouCanOpenSavedLists() {
        //Call openPreviouslySavedData with test file
        //Compare listView object to expected listView
    }
}