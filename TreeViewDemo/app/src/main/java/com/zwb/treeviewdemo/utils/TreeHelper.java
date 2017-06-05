package com.zwb.treeviewdemo.utils;

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
        return result;
    }
}
