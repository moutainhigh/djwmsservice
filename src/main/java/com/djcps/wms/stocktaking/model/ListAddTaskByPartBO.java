package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * 新增部分盘点列表参数类
 * @author:wzy
 * @company:djwms
 * @create:2018/1/26
 **/
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
