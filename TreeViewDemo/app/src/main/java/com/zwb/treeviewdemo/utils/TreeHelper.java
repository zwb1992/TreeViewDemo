package com.zwb.treeviewdemo.utils;

import android.util.Log;

import com.zwb.treeviewdemo.R;
import com.zwb.treeviewdemo.utils.annotation.TreeNodeId;
import com.zwb.treeviewdemo.utils.annotation.TreeNodeLable;
import com.zwb.treeviewdemo.utils.annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 2017/6/5.
 */

public class TreeHelper {

    /**
     * 将用户数据转换成树形结构
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> convertBeanToNode(List<T> datas) throws IllegalAccessException {
        List<Node> result = new ArrayList<>();
        Node node;
        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String label = null;
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodePid.class) != null) {
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeLable.class) != null) {
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }
            node = new Node(id, pid, label);
            result.add(node);
        }

        /**
         * 设置Node间的节点关系
         */
        for (int i = 0; i < result.size(); i++) {
            Node nodeOne = result.get(i);
            for (int j = i + 1; j < result.size(); j++) {
                Node nodeTwo = result.get(j);
                //如果 nodeOne 的父亲是 nodeTwo
                if (nodeOne.getpId() == nodeTwo.getId()) {
                    nodeOne.setParent(nodeTwo);
                    nodeOne.getChildren().add(nodeOne);
                    //如果 nodeTwo 的父亲是 nodeOne
                } else if (nodeOne.getId() == nodeTwo.getpId()) {
                    nodeOne.getChildren().add(nodeTwo);
                    nodeTwo.setParent(nodeOne);
                }
            }
        }

        for (Node n : result) {
            setNodeIcon(n);
        }

        return result;
    }

    /**
     * 给树形结构排序
     *
     * @param datas
     * @return
     */
    public static <T> List<Node> sortNodes(List<T> datas, int defaultExpandLevel)
            throws IllegalArgumentException, IllegalAccessException {
        List<Node> result = new ArrayList<>();
        List<Node> convertNodes = convertBeanToNode(datas);
        List<Node> rootNodes = getRootNodes(convertNodes);
        for (Node rootNode : rootNodes) {
            addNode(result, rootNode, defaultExpandLevel, 1);
        }

        return result;
    }

    /**
     * 过滤出可见的节点
     *
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 从所有节点中获取根节点
     *
     * @param nodes
     * @return
     */
    public static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot()) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 把一个节点的所有孩子节点都放入result
     *
     * @param result
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {
        result.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf())
            return;

        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result, node.getChildren().get(i), defaultExpandLevel,
                    currentLevel + 1);
        }
    }

    /**
     * 为Node设置图标
     *
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if (n.getChildren().size() > 0 && n.isExpand()) {
            n.setIcon(R.mipmap.tree_ex);
        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
            n.setIcon(R.mipmap.tree_ec);
        } else {
            n.setIcon(-1);
        }
    }
}
