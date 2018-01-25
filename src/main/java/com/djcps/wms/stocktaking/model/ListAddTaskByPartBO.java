package com.djcps.wms.stocktaking.model;

import java.util.List;

public class ListAddTaskByPartBO {
    private List<AddTaskByPartBO> taskList;

    public List<AddTaskByPartBO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<AddTaskByPartBO> taskList) {
        this.taskList = taskList;
    }
    @Override
    public String toString() {
        return "ListAddTaskByPartBO{" +
                "taskList=" + taskList +
                '}';
    }
}
