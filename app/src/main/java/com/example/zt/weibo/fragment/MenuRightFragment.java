package com.example.zt.weibo.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.zt.weibo.model.BlogUser;
import com.example.zt.weibo.MyApp;
import com.example.zt.weibo.R;
import com.example.zt.weibo.tools.URL;

import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuRightFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    private MyBlogFragment myBlogFragment;

    private BlogFragment blogFragment;
    private MyDataFragment myDataFragment;

    private CategorizationFragment categorizationFragment;

    private MyApp myApp;
    private ImageView circleImageView;
    private BlogUser blogUser;
    private TextView textView;


    public MenuRightFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_right, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myApp = (MyApp) getActivity().getApplication();
        radioGroup = (RadioGroup) view.findViewById(R.id.menu_radioGroup);
        textView = (TextView) view.findViewById(R.id.menu_blognick);

        blogUser = myApp.getBlogUser();
        radioGroup.setOnCheckedChangeListener(this);

        if (TextUtils.isEmpty(blogUser.getBloguser_nickname())) {
            textView.setText("您当前还没有昵称");
        } else {
            textView.setText(blogUser.getBloguser_nickname());
        }
        circleImageView = (ImageView) view.findViewById(R.id.menu_headImage);
        String url1 = URL.URL_HEADIMAGE + String.valueOf(blogUser.getBloguser_id()) + ".png";
        x.image().bind(circleImageView, url1);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (group.getCheckedRadioButtonId()) {
            case R.id.menu_toutiao:
                if (blogFragment == null) {
                    blogFragment = new BlogFragment();
                }

                transaction.replace(R.id.main_frame, blogFragment);

                break;

            case R.id.menu_cate:
                if (categorizationFragment == null) {
                    categorizationFragment = new CategorizationFragment();
                }

                transaction.replace(R.id.main_frame, categorizationFragment);

                break;
            case R.id.menu_mydata:

                if (myDataFragment == null) {
                    myDataFragment = new MyDataFragment();
                }
                transaction.replace(R.id.main_frame, myDataFragment);
                break;
        }
        transaction.commit();

    }
}
