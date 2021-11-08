/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Task {
    private SimpleStringProperty description;
    private SimpleStringProperty deadline;
    private CheckBox completion;

    public Task(String description, String deadline) {
        //Assign description to parameter (call SimpleStringProperty constructor)
        this.description = new SimpleStringProperty(description);

        //Assign dueDate to parameter
        this.deadline = new SimpleStringProperty(deadline);
    }

    public Task(String description) {
        //Assign description to parameter (call SimpleStringProperty constructor)
        this.description = new SimpleStringProperty(description);

        //Assign dueDate to parameter
        this.deadline = new SimpleStringProperty("");
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getDeadline() {
        return deadline.get();
    }

    public void setDeadline(String deadline) {
        this.deadline.set(deadline);
    }

    public void initCompletion() {
        this.completion = new CheckBox();
    }

    public CheckBox getCompletion() {
        return this.completion;
    }

    public boolean getCompletionStatus() {
        return this.completion.isSelected();
    }
}
