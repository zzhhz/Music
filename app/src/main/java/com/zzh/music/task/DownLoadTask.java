package com.zzh.music.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Administrator on 2017/3/22.
 *
 * @Date: 2017/3/22
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music 单例模式 下载文件
 */

public class DownLoadTask {
    private static final String TAG = "--download task--";
    private static DownLoadTask mInstance;
    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);
    private Semaphore mSemaphoreThreadPool;
    private Thread mPoolThread;
    private ExecutorService mThreadPool;
    private static Context mContext;
    private int threadCount = 3;
    private Type mType = Type.FIFO;
    private LinkedList<Runnable> mTaskQueue;//存储所有的下载任务

    public DownLoadTask() {
        init();
    }

    private Handler mPoolThreadHandler;

    private void init() {
        //后台轮训线程
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        mThreadPool.execute(getTask());
                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

        /**线程池***/
        mThreadPool = Executors.newFixedThreadPool(threadCount);

        mTaskQueue = new LinkedList<>();
        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else {
            return mTaskQueue.removeLast();
        }
    }

    public DownLoadTask(int threadCount, Type mType) {
        this();
        this.threadCount = threadCount;
        this.mType = mType;
    }

    private enum Type {
        LIFO, FIFO
    }

    public static DownLoadTask getInstance() {

        if (mInstance == null) {
            synchronized (DownLoadTask.class) {
                if (mInstance == null) {
                    mInstance = new DownLoadTask();
                }
            }
        }
        return mInstance;
    }

    /**
     * 将下载线程添加到下载序列中
     *
     * @param fileUrl   网络文件的路径
     * @param save2Path 保存本地的路径
     * @param listener  文件的监听
     */
    public void start(final String fileUrl, final String save2Path, final OnDownLoadListener listener) {
        addTasks(new Runnable() {
            @Override
            public void run() {
                getFiles(save2Path, fileUrl, listener);
                mSemaphoreThreadPool.release();//释放一个信号量
            }
        });
    }

    //开启下载，不设置监听
    private void getFiles(String save2Path, String fileUrl) {
        getFiles(save2Path, fileUrl, null);
    }

    /**
     * 开启下载，
     *
     * @param save2Path 文件保存的下载路径
     * @param fileUrl   文件的网络地址
     * @param listener  下载过程的监听
     */
    private void getFiles(String save2Path, String fileUrl, OnDownLoadListener listener) {
        File localFile = new File(save2Path);
        FileOutputStream fos = null; //写文件
        InputStream in = null;//获取输入流
        URL url = null;
        HttpURLConnection http = null;
        try {
            url = new URL(fileUrl);
            http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(60 * 1000);
            http.setConnectTimeout(5 * 1000);
            http.setReadTimeout(5 * 1000);
            http.setRequestMethod("GET");
            http.setRequestProperty(
                    "Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap,application/x-ms-application, application/vnd.ms-excel,application/vnd.ms-powerpoint,application/msword, */*");
            http.setRequestProperty("Accept-Language", "zh-CN");
            http.setRequestProperty("Referer", url.toString());
            http.setRequestProperty("Charset", "UTF-8");
            http.setRequestProperty("Connection", "Keep-Alive");
            http.connect();
            int status = http.getResponseCode();
            if (status == 200)//连接成功
            {
                int fileSize = http.getContentLength();//文件大小不一致
                if (fileSize != localFile.length()) {
                    in = http.getInputStream();
                    if (localFile.exists()) {
                        localFile.delete();
                    } else {
                        if (!localFile.getParentFile().exists()) {//父路径不存在则创建文件夹
                            localFile.getParentFile().mkdirs();
                        }
                        localFile.createNewFile();
                    }
                    fos = new FileOutputStream(localFile);
                    byte buf[] = new byte[1024 * 4];
                    int len = -1;
                    int length = 0;
                    while ((len = in.read(buf)) != -1) {
                        length = len + length;
                        if (listener != null) {
                            listener.onLength(length * 100 / fileSize);
                        }
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    if (listener != null) {
                        listener.onSuccess(save2Path);
                    }
                }
            }
        } catch (MalformedURLException e) {
            Log.d(TAG, "run: URL failed ");
            if (listener != null) {
                listener.onFailed("URL failed ");
            }
            e.printStackTrace();
        } catch (IOException e) {
            Log.d(TAG, "run: HttpURLConnection failed ");
            if (listener != null) {
                listener.onFailed("HttpURLConnection failed ");
            }
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    in.close();
                    if (http != null) {
                        http.disconnect();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addTasks(Runnable runnable) {
        mTaskQueue.add(runnable);
        try {
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        mPoolThreadHandler.sendEmptyMessage(0x110);

    }

    /**
     * 下载进度的监听方法
     */
    interface OnDownLoadListener {
        void onSuccess(String path);//下载成功

        void onFailed(String error);//现在失败

        void onLength(int len);//进度百分比
    }
}
