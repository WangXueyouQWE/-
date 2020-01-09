package com.example.servicebestpractice;

/**
 * Created by Administrator on 2020/1/7.
 * 定义五个回调方法，用于对下载过程中的各种状态进行监听和回调。
 */

public interface DownLoadListener {
    void onPregress(int progress);   //用于通知当前的下载进度
    void onSuccess();                  //是否下载成功
    void onFailed();
    void onPaused();      //通知下载暂停事件
    void onCanceled();      //用于通知下载取消事件
}
