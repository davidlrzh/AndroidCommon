/*
 * Copyright (c) 1997-2015 Niuman. All rights reserved.
 */

package cn.niuman.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import cn.niuman.androidextendedviews.R;
import cn.niuman.views.NFlowLinearLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlowLinearLayoutFragment extends Fragment {

    public FlowLinearLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flow_linearlayout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        String[] words = {"一个", "关键词", "充值", "周杰伦", "中国好声音", "京东", "淘宝", "人民币", "男主播", "双头蛇", "5岁女孩开车上路",
                "航班遇晴空颠簸", "中国", "最美水上公路通车", "失联女生"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.item_word, words);

        NFlowLinearLayout fllWords = (NFlowLinearLayout) view.findViewById(R.id.fll_words);
        fllWords.setAdapter(adapter);
    }
}
