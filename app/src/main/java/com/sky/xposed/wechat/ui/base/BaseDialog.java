/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.wechat.ui.base;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky.xposed.wechat.Constant;
import com.sky.xposed.wechat.ui.interfaces.TrackViewStatus;

/**
 * Created by sky on 18-3-11.
 */

public abstract class BaseDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createView(inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 初始化View
        initView(view, getArguments());
    }

    /**
     * 针对New Dialog处理的
     * @return
     */
    protected View createDialogView() {

        // 创建View
        View view = createView(getSkyLayoutInflater(), null);

        // 初始化
        initView(view, getArguments());

        return view;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView(View view, Bundle args);

    public Context getContext() {
        return getActivity();
    }

    public LayoutInflater getSkyLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    public SharedPreferences getSharedPreferences(String name) {
        return getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public SharedPreferences getDefaultSharedPreferences() {
        return getSharedPreferences(Constant.Name.WE_CAT);
    }

    public <T> void trackBind(TrackViewStatus<T> track, String key, T defValue, TrackViewStatus.StatusChangeListener<T> listener) {
        track.bind(getDefaultSharedPreferences(), key, defValue, listener);
    }
}
