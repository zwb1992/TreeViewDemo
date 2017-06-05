package com.zwb.treeviewdemo;

import android.util.Log;

import com.zwb.treeviewdemo.bean.TreeBean;
import com.zwb.treeviewdemo.utils.Node;
import com.zwb.treeviewdemo.utils.TreeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/6/5.
 */

public class Test {
    private static List<TreeBean> treeBeen;
    public static void main(String[] args){
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() throws IllegalAccessException {
        treeBeen = new ArrayList<>();
        TreeBean treeBean = new TreeBean(0, 0, "根节点0-0");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(1, 0, "根节点1-0");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(2, 0, "根节点2-0");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(3, 1, "根节点1-1");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(4, 1, "根节点1-2");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(5, 2, "根节点2-1");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(6, 2, "根节点2-2");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(7, 0, "根节点7-0");
        treeBeen.add(treeBean);
        treeBean = new TreeBean(8, 3, "根节点1-1-0");
        treeBeen.add(treeBean);

        List<Node> allNodes = TreeHelper.sortNodes(treeBeen,1);
//        Log.e("TAG", "===" + nodes.size());
        System.out.println("===" + allNodes.toString());

        List<Node> visibleNode = TreeHelper.filterVisibleNodes(allNodes);
        System.out.println("===" + visibleNode.toString());

    }

}
