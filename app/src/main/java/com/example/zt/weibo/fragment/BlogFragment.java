package com.example.zt.weibo.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;

import com.example.zt.weibo.adapter.MyBlogAdapter;
import com.example.zt.weibo.model.BlogUi;
import com.example.zt.weibo.R;
import com.example.zt.weibo.tools.URL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView listView;


    private MyBlogAdapter blogAdapter;


    private View listHeadView;


    private Button footButton;
    private int page = 2;


    public BlogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listHeadView = inflater.inflate(R.layout.listheadview, null);
        footButton = (Button) listHeadView.findViewById(R.id.listFoot);
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.blogfrg_swipRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.my_color);
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE);

        listView = (ListView) view.findViewById(R.id.frg_blog_listView);
        blogAdapter = new MyBlogAdapter(getActivity());

        listView.addFooterView(listHeadView);
        listView.setAdapter(blogAdapter);

        //???ListView????????????

        getBlog("1");


        footButton.setOnClickListener(this);


    }

    public void getBlog(String data) {

        RequestParams requestParams = new RequestParams(URL.URL_BLOG);
        requestParams.addBodyParameter("data", data);
        x.http().post(requestParams, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                List<BlogUi> list = JSON.parseArray(result, BlogUi.class);
                blogAdapter.addAll(list);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });


    }

    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(), "????????????", Toast.LENGTH_LONG).show();
        getBlog("1");
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onClick(View v) {
        getBlog(String.valueOf(page));
        blogAdapter.notifyDataSetChanged();
        listView.setSelection(0);
        page++;

    }
}
