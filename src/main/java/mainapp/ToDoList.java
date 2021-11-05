package mainapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ToDoList {
    ObservableList<Task> tasks = FXCollections.observableArrayList();

    public void addNewTask(String description, String deadline) {
        Task newTask = new Task(description, deadline);
        this.tasks.add(newTask);
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
                //If deadline is inputted, check to see if its in Gregorian Calendar
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

    public void deleteTask(Task selectedTask) {
        //Delete selected task from table
        this.tasks.remove(selectedTask);
    }

    public void clearAllTasks() {
        this.tasks.clear();
    }

    public void editDeadline(TableColumn.CellEditEvent<Task, String> editedCell, Task selectedTask) {
        selectedTask.setDeadline(editedCell.getNewValue());
    }

    public void editDescription(TableColumn.CellEditEvent<Task, String> editedCell, Task selectedTask) {
        selectedTask.setDescription(editedCell.getNewValue());
    }

    public ObservableList<Task> findCompletedTasks() {
        ObservableList<Task> completedTasks = FXCollections.observableArrayList();

        for(Task task: this.tasks) {
            //Show only tasks that are checked off
            if(task.getCompletion().isSelected())
                completedTasks.add(task);
        }

        return completedTasks;
    }

    public ObservableList<Task> findIncompleteTasks() {
        ObservableList<Task> incompleteTasks = FXCollections.observableArrayList();

        for(Task task: this.tasks) {
            //Show only tasks that are checked off
            if(!task.getCompletion().isSelected())
                incompleteTasks.add(task);
        }

        return incompleteTasks;
    }

    public File createTextFile() {
        return null;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}
