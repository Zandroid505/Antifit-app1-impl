/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class IndividualListController implements Initializable {
    private ToDoList toDoList = new ToDoList();
    private FileChooser fileChooser = new FileChooser();

    @FXML private TextField descriptionTextField;
    @FXML private TextField deadlineTextField;

    //Table that holds tasks
    @FXML private TableView<Task> listTableView;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, String> deadlineColumn;
    @FXML private TableColumn<Task, CheckBox> completionColumn;

    @FXML private ToggleButton allButton;
    @FXML private ToggleGroup completionStatus;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set initial directory

        //Set cell values for tableView
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        completionColumn.setCellValueFactory(new PropertyValueFactory<>("completion"));

        //Set items
        listTableView.setItems(toDoList.getTasks());

        listTableView.setEditable(true);

        //Make description editable
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //Make deadline editable
        deadlineColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        completionStatus.selectToggle(allButton);
    }

    public void newTaskButtonPressed() {
        String newDescription = descriptionTextField.getText();
        String newDeadline = deadlineTextField.getText();

        //Validate description and deadline
        if(toDoList.validateDeadline(newDeadline) && toDoList.validateDescription(newDescription)) {
            Task temp = toDoList.addNewTask(newDescription, newDeadline);

            //Initialize checkbox
            temp.initCompletion();

            //Clear description and deadline field after adding task
            descriptionTextField.clear();
            deadlineTextField.clear();
        }
        else
            inputInvalidMessage();
    }

    public void deleteTaskButtonPressed() {
        Task selectedTask = listTableView.getSelectionModel().getSelectedItem();

        //Remove description and deadline from list
        toDoList.deleteTask(selectedTask);
    }

    public void clearButtonPressed() {
        toDoList.clearAllTasks();
    }

    public void editDeadlineCellEvent(TableColumn.CellEditEvent<Task, String> editedCell) {
        //Double click to edit
        if(toDoList.validateDeadline(editedCell.getNewValue())) {
            Task selectedTask = listTableView.getSelectionModel().getSelectedItem();
            toDoList.editDeadline(editedCell.getNewValue(), selectedTask);
        } else
            inputInvalidMessage();
    }

    public void editDescriptionCellEvent(TableColumn.CellEditEvent<Task, String> editedCell) {
        //Double click to edit
        if(toDoList.validateDescription((editedCell.getNewValue()))) {
            Task selectedTask = listTableView.getSelectionModel().getSelectedItem();
            toDoList.editDescription(editedCell.getNewValue(), selectedTask);
        } else
            inputInvalidMessage();

    }

    private void inputInvalidMessage() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        errorAlert.setTitle("Input not valid");
        errorAlert.setHeaderText("Your deadline, description, or both are invalid!");
        errorAlert.setContentText("Deadline format: YYYY-MM-DD or leave blank\nDescription: 1 to 256 characters");
        errorAlert.showAndWait();
    }

    public void completeButtonPressed() {
        ObservableList<Task> completedTasks = toDoList.findCompletedTasks();
        displayCompletedTasks(completedTasks);
    }

    private void displayCompletedTasks(ObservableList<Task> completedTasks) {
        //Clear table of original tasks
        listTableView.refresh();

        //Set tasks in table to completed ones
        listTableView.setItems(completedTasks);
    }

    public void incompleteButtonPressed() {
        ObservableList<Task> incompleteTasks = toDoList.findIncompleteTasks();
        displayIncompleteTasks(incompleteTasks);
    }

    private void displayIncompleteTasks(ObservableList<Task> incompleteTasks) {
        //Clear table of original tasks
        listTableView.refresh();

        //Show only tasks that are not checked off
        listTableView.setItems(incompleteTasks);
    }

    public void allButtonPressed() {
        displayAllTasks();
    }

    private void displayAllTasks() {
        //Clear table of tasks
        listTableView.refresh();

        //Show all tasks in table
        listTableView.setItems(toDoList.getTasks());
    }

    public void saveList() {
       fileChooser.setTitle("Save Dialog");

       fileChooser.setInitialFileName("newList");
       fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));

        //Open file explorer
        //Let user choose where to save to do list(s)
        //Save to location
       File listFile = fileChooser.showSaveDialog(new Stage());
       if(listFile != null) {
           saveSystem(listFile, toDoList.createTextFile());
           fileChooser.setInitialDirectory(listFile.getParentFile());
       }
    }

    private void saveSystem(File outFile, String listText) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        try {
            PrintWriter printWriter = new PrintWriter(outFile);
            printWriter.write(listText);
            printWriter.close();
        } catch(FileNotFoundException e) {
            errorAlert.setTitle("Error saving file");
            errorAlert.setHeaderText("Couldn't save file");
            errorAlert.showAndWait();
        }
    }

    public void loadListToTable() {
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));

        //Open file explorer
        //Let user open .txt file
        //Populate tableView with .txt file
        File listFile = fileChooser.showOpenDialog(new Stage());
        if(listFile != null) {
            fileChooser.setInitialDirectory(listFile.getParentFile());
            toDoList.clearAllTasks();
            if(!toDoList.openList(listFile))
                errorOpeningListMessage();
        }

    }

    private void errorOpeningListMessage() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        errorAlert.setTitle("Error opening file");
        errorAlert.setHeaderText("Couldn't open file");
        errorAlert.showAndWait();
    }
}
