package com.djcps.wms.stocktaking.model;

import java.util.List;

/**
 * 新增全盘任务仓库相关参数接收
 * @author:wzy
 * @company:djwms
 * @create:2018/1/23
 **/
public class ListAddTaskByAllPO {
    List<AddTaskByAllPO> addTaskByAllPOList;

    public List<AddTaskByAllPO> getAddTaskByAllPOList() {
        return addTaskByAllPOList;
    }

    public void setAddTaskByAllPOList(List<AddTaskByAllPO> addTaskByAllPOList) {
        this.addTaskByAllPOList = addTaskByAllPOList;
    }
}
