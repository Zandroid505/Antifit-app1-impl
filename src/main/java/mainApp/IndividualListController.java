/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class IndividualListController implements Initializable {

    @FXML private TextField descriptionTextField;
    @FXML private TextField deadlineTextField;

    //Table that holds tasks
    @FXML private TableView<Task> listTableView;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, String> deadlineColumn;
    @FXML private TableColumn<Task, CheckBox> completionColumn;

    ObservableList<Task> listOfTasks = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //If deadline is inputted, check to see if its in Gregorian Calendar

        //Set cell values for tableView
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        completionColumn.setCellValueFactory(new PropertyValueFactory<>("completion"));

        //Set items
        listTableView.setItems(listOfTasks);

        //Make description editable
        //Make deadline editable
        //Deadline is editable
    }

    public void newTaskButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listOfTasks;
        String newDescription = descriptionTextField.getText();
        String newDeadline = deadlineTextField.getText();

        //Validate description and deadline
        if(validateDeadline(newDeadline) && validateDescription(newDescription)) {
            addNewTask(newDescription, newDeadline, allTasks);
        }

        //Add due date and descriptions to current list
        //Task newTask = new Task("", "");

        //Get all items from the table as a list
        //Add new description and dueDate to tableView
    }

    public void addNewTask(String description, String deadline, ObservableList<Task> allTasks) {
        Task newTask = new Task(description, deadline);
        allTasks.add(newTask);
    }

    public boolean validateDescription(String description) {
        return description.length() >= 1 && description.length() <= 256;
            //Add dialog box stating description length requirement if false
    }

    public boolean validateDeadline(String deadline) {
        if(deadline.isEmpty()) {
            return true;
        }
        else if(deadline.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                dateFormat.parse(deadline);

                return true;
            } catch (ParseException e) {
                return false;
                //Add dialog box stating deadline format requirement if false
            }
        }
        else
            return false;
            //Add dialog box stating deadline format requirement if false
    }

    public void deleteTaskButtonPressed(ActionEvent delTaskButtonPressed) {
        Task selectedTask = listTableView.getSelectionModel().getSelectedItem();
        ObservableList<Task> allTasks = listTableView.getItems();

        deleteTask(selectedTask, allTasks);

        //Description and deadline/dueDate
    }

    public void deleteTask(Task selectedTask, ObservableList<Task> allTasks) {
        //Delete selected task from table
        allTasks.remove(selectedTask);
    }

    public void clearButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listTableView.getItems();

        clearAllTasks(allTasks);
    }

    public void clearAllTasks(ObservableList<Task> allTasks) {
        allTasks.clear();
    }

    public void editList(ActionEvent event) {
        //Don't make double click to edit

    }

    public void completedButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listOfTasks;

        ObservableList<Task> completedTasks = findCompletedTasks(allTasks);
        displayCompletedTasks(completedTasks);
    }

    public ObservableList<Task> findCompletedTasks(ObservableList<Task> allTasks) {
        ObservableList<Task> completedTasks = FXCollections.observableArrayList();

        for(Task task: allTasks) {
            //Show only tasks that are checked off
            if(task.getCompletion().isSelected())
                completedTasks.add(task);
        }

        return completedTasks;
    }

    public void displayCompletedTasks(ObservableList<Task> completedTasks) {
        //Clear table of original tasks
        listTableView.refresh();

        //Set tasks in table to completed ones
        listTableView.setItems(completedTasks);
    }

    public void incompleteButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listOfTasks;

        ObservableList<Task> incompleteTasks = findIncompleteTasks(allTasks);
        displayIncompleteTasks(incompleteTasks);

    }

    public ObservableList<Task> findIncompleteTasks(ObservableList<Task> allTasks) {
        ObservableList<Task> incompleteTasks = FXCollections.observableArrayList();

        for(Task task: allTasks) {
            //Show only tasks that are checked off
            if(!task.getCompletion().isSelected())
                incompleteTasks.add(task);
        }

        return incompleteTasks;
    }

    public void displayIncompleteTasks(ObservableList<Task> incompleteTasks) {

        //Clear table of original tasks
        listTableView.refresh();

        //Show only tasks that are not checked off
        listTableView.setItems(incompleteTasks);
    }

    public void allButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listOfTasks;

        displayAllTasks(allTasks);
    }

    public void displayAllTasks(ObservableList<Task> allTasks) {
        //Clear table of tasks
        listTableView.refresh();

        //Show all tasks in table
        listTableView.setItems(allTasks);
    }

    public void saveList(ActionEvent event) {
        //if(one or more lists are selected)
        //String temp = createOutputListString(selected lists)
        //Write temp to new file

        //Open file explorer
        //Let user choose where to save to do list(s)
        //Save to location
    }

    public void openSavedList(ActionEvent openSavedFileButtonPressed) {
        //Open file explorer
        //Let user open .txt file
        //Populate listView with .txt file
    }

    private void refresh() {
        //Clear description and deadline field after adding list
    }

}
