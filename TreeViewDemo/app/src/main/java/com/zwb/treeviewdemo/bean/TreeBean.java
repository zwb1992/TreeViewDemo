package com.zwb.treeviewdemo.bean;

import com.zwb.treeviewdemo.utils.annotation.TreeNodeId;
import com.zwb.treeviewdemo.utils.annotation.TreeNodeLable;
import com.zwb.treeviewdemo.utils.annotation.TreeNodePid;

/**
 * Created by zwb
 * Description
 * Date 2017/6/5.
 */

public class TreeBean {

    @TreeNodeId
    private int id;
    @TreeNodePid
    private int pId;
    @TreeNodeLable
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreeBean{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                '}';
    }

    public TreeBean(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public TreeBean() {
    }
}
