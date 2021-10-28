/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 first_name last_name
 */

package mainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IndividualListController implements Initializable {

    @FXML private Label titleLabel;
    @FXML private TextField descriptionTextField;
    @FXML private TextField deadlineTextField;
    @FXML private TableView<Task> listTableView;
    @FXML private TableColumn<Task, String> descriptionsColumn;
    @FXML private TableColumn<Task, LocalDate> deadlineColumn;
    @FXML private TableColumn<Task, CheckBox> taskCompletion;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set dueDate default to current day
        //Assign title name of file to Label

        //Set cell values to data stored in current toDoList Object
        //Set cell value of taskCompletion to checkboxes

        //Make description editable
        //Make dueDate editable
    }

    public void editList(ActionEvent event) {
        //Don't make double click to edit
    }

    public void addNewTask(ActionEvent event) {
        //Add due date and descriptions to current to do list object

        //Get all items from the table as a list
        //Add new description and dueDate to tableView
    }

    public void deleteTask(ActionEvent delTaskButtonPressed) {
        //Delete selected task from table
        //Description and deadline/dueDate
    }

    public void showCompletedTasks(ActionEvent completeTasksButtonPressed) {
        //Show only tasks that are checked off
    }

    public void showIncompleteTasks(ActionEvent incompleteTasksButtonPressed) {
        //Show only tasks that are not checked off
    }

    public void saveList(ActionEvent saveButtonPressed) {
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
