package com.zzh.music.task;

import android.os.AsyncTask;

/**
 * Created by ZZH on 16/7/13.
 *
 * @Date: 16/7/13
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: String 异步任务执行需要的参数; Integer 进度; Object 返回结果
 */
public class AsyncServiceTask extends AsyncTask<String, Integer, Object>{

    public AsyncServiceTask() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    ///后台运行的方法返回的结构
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values[0]);
    }

    /////后台运行的方法
    @Override
    protected Object doInBackground(String... params) {
        publishProgress();
        return null;
    }


}
