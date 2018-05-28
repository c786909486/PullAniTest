package com.axun.pullanitest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

/**
 * Created by hz-java on 2018/5/28.
 */

public class QQHeaderView extends FrameLayout implements IHeaderView {

    private QQView qqView;
    private TextView tvGo;

    private int height = 0;


    public QQHeaderView(@NonNull Context context) {
        this(context,null);
    }

    public QQHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View rootView = View.inflate(getContext(), R.layout.view_qqheader, null);
        qqView = (QQView) rootView.findViewById(R.id.qq_view);
        tvGo = rootView.findViewById(R.id.tv_go);

        addView(rootView);
    }


    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {

        qqView.setHeight((int) (fraction*headHeight));
        height = (int) (fraction*headHeight);
        if (fraction>1f){
            tvGo.setVisibility(VISIBLE);
        }else {
            tvGo.setVisibility(GONE);
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        Log.d("Header","maxHeader:"+maxHeadHeight+"\ncurrentHeader:"+headHeight+"\nfraction"+fraction*headHeight);
        qqView.setHeight((int) (fraction*headHeight));
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {

    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        ValueAnimator animator = ValueAnimator.ofInt(height,0);
        int value = (int) animator.getAnimatedValue();
        qqView.setHeight(value);
        animator.setDuration(5000);
        animator.start();
    }

    @Override
    public void reset() {
        qqView.setHeight(0);
    }
}
