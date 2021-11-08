/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    //Date Validation
    @Test
    void checkIfValidDateIsAccepted() {
        ToDoList test = new ToDoList();

        assertTrue(test.validateDeadline("2021-12-12"));
    }

    @Test
    void checkIfInvalidDateFormatIsRejected() {
        ToDoList test = new ToDoList();

        assertFalse(test.validateDeadline("12-12-2022"));
    }

    @Test
    void checkIfInvalidGregorianCalendarDateIsRejected() {
        ToDoList test = new ToDoList();

        assertFalse(test.validateDeadline("32-32-2022"));
    }

    //DescriptionValidation
    @Test
    void checkIfValidDescriptionIsAccepted() {
        ToDoList test = new ToDoList();

        assertTrue(test.validateDescription("Mow the lawn"));
    }

    @Test
    void checkIfInvalidDescriptionWithZeroCharsIsRejected() {
        ToDoList test = new ToDoList();

        assertFalse(test.validateDescription(""));
    }

    @Test
    void checkIfDescriptionWithMinimumCharactersIsAccepted() {
        ToDoList test = new ToDoList();

        assertTrue(test.validateDescription("d"));
    }

    @Test
    void checkIfDescriptionWithMaximumCharactersIsAccepted() {
        ToDoList test = new ToDoList();

        assertTrue(test.validateDescription("a".repeat(256)));
    }

    @Test
    void checkIfInvalidDescriptionWithTooManyCharsIsRejected() {
        ToDoList test = new ToDoList();

        assertFalse(test.validateDescription("a".repeat(257)));
    }

    @Test
    void checkIfDescriptionOfNewTaskIsAddedWithDate() {
        ToDoList test = new ToDoList();
        Task expected = new Task("Mow the lawn", "2021-12-12");

        //Pass test deadline and description to addNewTask()
        test.addNewTask("Mow the lawn", "2021-12-12");
        Task actual = test.getTasks().get(0);

        //Compare task object to expected
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void checkIfDeadlineOfNewTaskIsAddedWithDate() {
        ToDoList test = new ToDoList();
        Task expected = new Task("Mow the lawn", "2021-12-12");

        //Pass test deadline and description to addNewTask()
        test.addNewTask("Mow the lawn", "2021-12-12");
        Task actual = test.getTasks().get(0);

        //Compare task object to expected
        assertEquals(expected.getDeadline(), actual.getDeadline());
    }

    @Test
    void checkIfDescriptionOfNewTaskIsAddedWithoutDate() {
        ToDoList test = new ToDoList();
        Task expected = new Task("Mow the lawn");

        //Pass test description to addNewTask()
        test.addNewTask("Mow the lawn", "");
        Task actual = test.getTasks().get(0);

        //Compare task object to expected
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void checkIfTaskWithDateIsDeleted() {
        ToDoList test = new ToDoList();
        Task unexpectedData = new Task("Mow the lawn", "2021-12-12");
        List<Task> unexpected = new ArrayList<>();

        //Array list that contains data that should be deleted in test
        unexpected.add(unexpectedData);

        //Pass dummy data for deadline and description to addNewTask()
        Task dummyData = test.addNewTask("Mow the lawn", "2021-12-12");

        //Call deleteTask()
        test.deleteTask(dummyData);

        //Check to see if task does not contain the dummy data previously added
        assertNotEquals(unexpected, test.getTasks());
    }

    @Test
    void checkIfTaskWithoutDateIsDeleted() {
        ToDoList test = new ToDoList();
        Task unexpectedData = new Task("Mow the lawn");
        List<Task> unexpected = new ArrayList<>();

        //Array list that contains data that should be deleted in test
        unexpected.add(unexpectedData);

        //Pass dummy data for description to addNewTask()
        Task dummyData = test.addNewTask("Mow the lawn", "");

        //Call deleteTask()
        test.deleteTask(dummyData);

        //Check to see if task does not contain the dummy data previously added
        assertNotEquals(unexpected, test.getTasks());
    }

    @Test
    void checkIfAllTasksAreCleared() {
        ToDoList test = new ToDoList();

        test.addNewTask("Mow the lawn", "2021-12-12");
        test.addNewTask("Do laundry", "2021-13-12");
        test.addNewTask("Take out the garbage", "");
        test.addNewTask("Finish math hw", "2021-12-11");

        test.clearAllTasks();

        assertTrue(test.getTasks().isEmpty());
    }

    @Test
    void checkIfDeadlineCanBeEdited() {
        ToDoList test = new ToDoList();

        //Add dummy data
        Task temp = test.addNewTask("Mow the lawn", "12-12-2021");

        //Call editDeadline() with new title
        test.editDeadline("2022-03-03", temp);

        //check if deadline was changed
        assertEquals("2022-03-03", temp.getDeadline());
    }

    @Test
    void checkIfDescriptionCanBeEdited() {
        ToDoList test = new ToDoList();

        //Add dummy data
        Task temp = test.addNewTask("Mow the lawn", "12-12-2021");

        //Call editDeadline() with new title
        test.editDescription("Clean my room", temp);

        //check if deadline was changed
        assertEquals("Clean my room", temp.getDescription());
    }

//    @Test
//    void checkIfOnlyCompletedTasksAreShown() {
//        ToDoList test = new ToDoList();
//
//        //Pass dummy data for deadline and description
//        Task temp = test.addNewTask("Mow the lawn", "2021-12-12");
//        //Mark checkboxes for half of the data
//        temp.setCompletionToChecked();
//
//        //Pass dummy data for deadline and description
//        temp = test.addNewTask("Do laundry", "2021-12-13");
//        //Mark checkboxes for half of the data
//        temp.setCompletionToChecked();
//
//        test.addNewTask("Take out the garbage", "");
//        test.addNewTask("Finish math hw", "2021-12-11");
//
//        //Call showCompletedTasks()
//        List<Task> actual = test.findCompletedTasks();
//
//        //Expected values to be returned by findCompletedTasks() method
//        List<Task> expected = new ArrayList<>();
//        expected.add(new Task("Mow the lawn", "2021-12-12"));
//        expected.add(new Task("Do laundry", "2021-12-13"));
//
//        //Compare shown data to expected
//        assertEquals(expected.get(0), actual.get(0));
//        assertEquals(expected.get(1), actual.get(1));
//    }
//
//    @Test
//    void checkIfIncompleteTasksAreShown() {
//        //Pass dummy data for deadline and description
//        //Mark checkboxes for half of the data
//        //Call showIncompleteTasks()
//        //Compare shown data to expected
//    }
//
//    @Test
//    void checkIfListsAreFormattedCorrectlyForSaving() {
//        ToDoList test = new ToDoList();
//        StringBuilder expected = new StringBuilder();
//
//        //Formatted dummy data
//        expected.append("2021-12-12\n");
//        expected.append("Mow the lawn\n");
//
//        expected.append("2021-13-12\n");
//        expected.append("Do laundry\n");
//
//        expected.append("n/a\n");
//        expected.append("Take out the garbage\n");
//
//        expected.append("2021-12-11\n");
//        expected.append("Finish math hw\n");
//
//        //Dummy data
//        test.addNewTask("Mow the lawn", "2021-12-12");
//        test.addNewTask("Do laundry", "2021-13-12");
//        test.addNewTask("Take out the garbage", "");
//        test.addNewTask("Finish math hw", "2021-12-11");
//
//
//        //Call createTextFile()
//        String actual = test.createTextFile();
//
//        //Compare expected string to outputted string
//        assertEquals(expected.toString(), actual);
//    }
//
//    @Test
//    void checkIfYouCanOpenSavedLists() {
//        //Call openPreviouslySavedData with test file
//        //Compare listView object to expected listView
//    }


}