package com.zwb.treeviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.zwb.treeviewdemo.bean.TreeBean;
import com.zwb.treeviewdemo.utils.Node;
import com.zwb.treeviewdemo.utils.TreeHelper;
import com.zwb.treeviewdemo.utils.adapter.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<TreeBean> treeBeen;
    private ListView listView;
    private TreeAdapter<TreeBean> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        try {
            init();
            adapter = new TreeAdapter(listView, treeBeen, this, 1);
            listView.setAdapter(adapter);
            adapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
                @Override
                public void onClick(Node node, int position) {
                    Toast.makeText(MainActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws IllegalAccessException {
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

        List<Node> nodes = TreeHelper.convertBeanToNode(treeBeen);
        Log.e("TAG", "===" + nodes);
    }
}
