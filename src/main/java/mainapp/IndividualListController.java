/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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
        //fileChooser.setInitialDirectory(new File("C:\\temp"));

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
            toDoList.addNewTask(newDescription, newDeadline);

            //Clear description and deadline field after adding task
            descriptionTextField.clear();
            deadlineTextField.clear();
        }

        //Add due date and descriptions to current list
        //Task newTask = new Task("", "");

        //Get all items from the table as a list
        //Add new description and dueDate to tableView
    }

    public void deleteTaskButtonPressed() {
        Task selectedTask = listTableView.getSelectionModel().getSelectedItem();

        //Remove description and deadline from list
        toDoList.deleteTask(selectedTask);
    }

    public void clearButtonPressed() {
        toDoList.clearAllTasks();
    }

    public void editDeadlineCellEvent(TableColumn.CellEditEvent<Task, String>editedCell) {
        //Double click to edit
        Task selectedTask = listTableView.getSelectionModel().getSelectedItem();
        toDoList.editDeadline(editedCell, selectedTask);
    }

    public void editDescriptionCellEvent(TableColumn.CellEditEvent<Task, String> editedCell) {
        //Double click to edit
        Task selectedTask = listTableView.getSelectionModel().getSelectedItem();
        toDoList.editDescription(editedCell, selectedTask);
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
       }
    }

    private void saveSystem(File outFile, String listText) {
        try {
            PrintWriter printWriter = new PrintWriter(outFile);
            printWriter.write(listText);
            printWriter.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            //Error saving file
        }
    }

    public void openList() {
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", "*.txt"));

        //Open file explorer
        //Let user open .txt file
        //Populate tableView with .txt file
        File listFile = fileChooser.showOpenDialog(new Stage());
        if(listFile != null) {
            listTableView.refresh();
            toDoList.loadList(listFile);
        }

    }
}
