package com.zwb.treeviewdemo.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.zwb.treeviewdemo.utils.Node;
import com.zwb.treeviewdemo.utils.TreeHelper;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/6/5.
 */

public abstract class TreeListViewAdapter<T> extends BaseAdapter {
    protected List<T> datas;
    protected Context context;
    protected int defaultExpandLevel;
    private List<Node> totalNodes;
    private List<Node> visibleNodes;
    protected LayoutInflater mInflater;
    private ListView mTree;

    /**
     * 设置Node的点击回调
     *
     * @author zhy
     */
    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    private OnTreeNodeClickListener mListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
        this.mListener = mListener;
    }


    public TreeListViewAdapter(ListView tree, List<T> datas, Context context, int defaultExpandLevel) throws IllegalAccessException {
        this.datas = datas;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.defaultExpandLevel = defaultExpandLevel;
        totalNodes = TreeHelper.sortNodes(datas, defaultExpandLevel);
        visibleNodes = TreeHelper.filterVisibleNodes(totalNodes);
        mTree = tree;
        mTree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                expandOrCollapse(position);

                if (mListener != null) {
                    mListener.onClick(visibleNodes.get(position), position);
                }

            }

        });
    }

    /**
     * 点击搜索或者展开
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node node = visibleNodes.get(position);
        if (node != null) {
            if (node.isLeaf()) {
                return;
            }
            node.setExpand(!node.isExpand());
            visibleNodes = TreeHelper.filterVisibleNodes(totalNodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return visibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return visibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = visibleNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        // 设置内边距
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
