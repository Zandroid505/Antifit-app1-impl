/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

    public void editList(ActionEvent event) {
        //Don't make double click to edit
    }

    public void newTaskButtonPressed(ActionEvent event) {
        String newDescription = descriptionTextField.getText();
        String newDeadline = deadlineTextField.getText();

        //Validate description and deadline
        if(validateDeadline(newDeadline) && validateDescription(newDescription)) {
            addNewTask(newDescription, newDeadline);
        }

        //Add due date and descriptions to current list
        //Task newTask = new Task("", "");

        //Get all items from the table as a list
        //Add new description and dueDate to tableView
    }

    public void addNewTask(String description, String deadline) {
        Task newTask = new Task(description, deadline);
        listOfTasks.add(newTask);
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

    public void completedButtonPressed(ActionEvent event) {
        ObservableList<Task> allTasks = listTableView.getItems();

        showCompletedTasks(allTasks);
    }

    public void showCompletedTasks(ObservableList<Task> allTasks) {
        FilteredList<Task> filteredTasks = new FilteredList<>(allTasks);
        ObservableList<String> completedTasks = FXCollections.observableArrayList("Completed", "Incomplete");

        ChoiceBox<String> choiceBoxCol = new ChoiceBox<>();
        choiceBoxCol.setItems(completedTasks);

        //Show only tasks that are checked off
        choiceBoxCol.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredTasks.setPredicate(newValue == null ? null : (Task e) -> newValue.equals(e.getCompletion()));
            }
        });
    }

    public void incompleteButtonPressed(ActionEvent event) {
        //Show only tasks that are not checked off
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
