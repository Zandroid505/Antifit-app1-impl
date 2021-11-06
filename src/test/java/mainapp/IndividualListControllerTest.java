/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import org.junit.jupiter.api.Test;

class IndividualListControllerTest {

    @Test
    void checkIfNewTaskIsAdded() {
        //Pass test deadline and description to addNewTask()
        //Compare task object to expected
    }

    @Test
    void checkIfTaskIsDeleted() {
        //Pass dummy data for deadline and description to addNewTask()
        //Call deleteTask()
        //Check to see if task does not contain the dummy data previously added
    }

    @Test
    void checkIfOnlyCompletedTasksAreShown() {
        //Pass dummy data for deadline and description
        //Mark checkboxes for half of the data
        //Call showCompletedTasks()
        //Compare shown data to expected
    }

    @Test
    void checkIfIncompleteTasksAreShown() {
        //Pass dummy data for deadline and description
        //Mark checkboxes for half of the data
        //Call showIncompleteTasks()
        //Compare shown data to expected
    }
}