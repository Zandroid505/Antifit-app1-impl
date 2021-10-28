/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 first_name last_name
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
import java.util.ResourceBundle;

public class IndividualListController implements Initializable {

    @FXML private TextField descriptionTextField;
    @FXML private TextField deadlineTextField;

    //Table that holds tasks
    @FXML private TableView<Task> listTableView;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, String> deadlineColumn;
    @FXML private TableColumn<Task, CheckBox> completionColumn;

    ObservableList<Task> listOfTasks = FXCollections.observableArrayList(
            new Task("Do the laundry", "2021-12-21")
    );

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

    public void editList(ActionEvent event) {
        //Don't make double click to edit
    }

    public void addNewTask(ActionEvent event) {
        //Add due date and descriptions to current list
        //Task newTask = new Task("", "");

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
