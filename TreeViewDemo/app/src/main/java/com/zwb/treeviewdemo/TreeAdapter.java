package com.zwb.treeviewdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zwb.treeviewdemo.bean.TreeBean;
import com.zwb.treeviewdemo.utils.Node;
import com.zwb.treeviewdemo.utils.adapter.TreeListViewAdapter;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/6/5.
 */

public class TreeAdapter<T> extends TreeListViewAdapter<T> {

    public TreeAdapter(ListView tree, List<T> datas, Context context, int defaultExpandLevel) throws IllegalAccessException {
        super(tree, datas, context, defaultExpandLevel);
    }

    @Override
    public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHelper viewHelper = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            viewHelper = new ViewHelper(convertView);
            convertView.setTag(viewHelper);
        } else {
            viewHelper = (ViewHelper) convertView.getTag();
        }
        viewHelper.tvLabel.setText(node.getName());
        if (node.isLeaf()) {
            viewHelper.img.setVisibility(View.INVISIBLE);
        } else {
            viewHelper.img.setVisibility(View.VISIBLE);
            viewHelper.img.setImageResource(node.getIcon());
        }
        return convertView;
    }

    class ViewHelper {
        private ImageView img;
        private TextView tvLabel;

        public ViewHelper(View convertView) {
            img = (ImageView) convertView.findViewById(R.id.img);
            tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
        }
    }
}
