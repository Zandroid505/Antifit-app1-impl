/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ToDoList {
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public boolean validateDescription(String description) {
        return description.length() >= 1 && description.length() <= 256 && !description.isBlank();
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
            }
        }
        else {
            return false;
        }
    }

    public Task addNewTask(String description, String deadline) {
        //Add due date and descriptions to current list
        Task newTask = new Task(description, deadline);
        this.tasks.add(newTask);

        return newTask;
    }

    public void deleteTask(Task selectedTask) {
        //Delete selected task from table
        this.tasks.remove(selectedTask);
    }

    public void clearAllTasks() {
        this.tasks.clear();
    }

    public void editDeadline(String newDeadline, Task selectedTask) {
        selectedTask.setDeadline(newDeadline);
    }

    public void editDescription(String newDescription, Task selectedTask) {
        selectedTask.setDescription(newDescription);
    }

    public ObservableList<Task> findCompletedTasks() {
        ObservableList<Task> completedTasks = FXCollections.observableArrayList();

        for(Task task: this.tasks) {
            //Show only tasks that are checked off
            if(task.getCompletionStatus())
                completedTasks.add(task);
        }
        return completedTasks;
    }

    public ObservableList<Task> findIncompleteTasks() {
        ObservableList<Task> incompleteTasks = FXCollections.observableArrayList();

        for(Task task: this.tasks) {
            //Show only tasks that are checked off
            if(!task.getCompletionStatus())
                incompleteTasks.add(task);
        }

        return incompleteTasks;
    }

    public String createTextFile() {
        StringBuilder formattedList = new StringBuilder();
        for(Task task: this.tasks) {
            String temp = task.getDeadline().isEmpty() ? "n/a" : task.getDeadline();
            formattedList.append(temp).append("\n").append(task.getDescription()).append("\n");

            String checkBoxTemp = (task.getCompletionStatus() ? "complete" : "incomplete");
            formattedList.append(checkBoxTemp).append("\n");
        }

        return formattedList.toString();
    }

    public boolean openList(File toDoListFile) {
        try(Scanner inputFile = new Scanner(toDoListFile)) {
            while(inputFile.hasNextLine()) {
                String deadline;

                //Read in file task
                String deadlineTemp = inputFile.nextLine();
                String description = inputFile.nextLine();
                String completionStatus = inputFile.nextLine();

                if(!deadlineTemp.equals("n/a")) {
                    deadline = deadlineTemp;
                } else {
                    deadline = "";
                }

                Task newTask = deadline.isEmpty() ? new Task(description) : new Task(description, deadline);

                //Sets checkbox to checked if complete
                if(completionStatus.equals("complete")) {
                    //Call checkbox constructor
                    newTask.initCompletion();
                    newTask.getCompletion().fire();
                }

                this.tasks.add(newTask);
            }
            //Loaded list successfully
            return true;
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}
