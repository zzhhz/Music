package com.zzh.music.loader;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzh.music.R;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zzh on 2016/3/7.
 * 加载图片工具类
 */
public class ImageLoader {
    public static final int IMAGE_LOADER_FROM_URI = 1000;
    private static ImageLoader mInstance;
    /**
     * 图片缓存的核心对象
     **/
    private LruCache<String, Bitmap> mLruCache;

    private Semaphore mSemaphorePoolThreadHandler = new Semaphore(0);

    private Semaphore mSemaphoreThreadPool;
    private static Context mContext;


    public static ImageLoader getInstance(Context ctx) {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader(DEFAULT_THREAD_COUNT, Type.LIFO);
                    mCachedBit = getDefaultArtwork(ctx);
                    mContext = ctx;
                }
            }
        }
        return mInstance;
    }

    private ImageLoader(int threadCount, Type type) {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type) {
        /**后台轮训线程**/
        mPoolThread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                mPoolThreadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //线程池取出任务去执行
                        mThreadPool.execute(getTask());

                        try {
                            mSemaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //释放一个信号量
                mSemaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };

        mPoolThread.start();

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheMemory = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getRowBytes() * value.getHeight();
            }
        };

        /**线程池***/
        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mTaskQueue = new LinkedList<>();
        mType = Type.LIFO;

        mSemaphoreThreadPool = new Semaphore(threadCount);
    }

    /**
     * 从任务队列取出方法
     */
    private Runnable getTask() {
        if (mType == Type.FIFO) {
            return mTaskQueue.removeFirst();
        } else {
            return mTaskQueue.removeLast();
        }
    }

    /**
     * 声明线程
     ***/
    private ExecutorService mThreadPool;
    /**
     * 线程池的数量
     **/
    private static int DEFAULT_THREAD_COUNT = 3;

    private Type mType = Type.LIFO;

    private LinkedList<Runnable> mTaskQueue;

    enum Type {
        FIFO, LIFO
    }


    /**
     * 后台轮训线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHandler;
    /**
     * UI线程
     **/
    private Handler mUIHandler;

    /**
     * 设置图片
     *
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView) {
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 获取得到的图片
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    String path = holder.path;
                    ImageView imageView = holder.imageView;
                    if (imageView.getTag().toString().equals(path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            sendImageViewToUI(path, imageView, bm);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {
                    //加载图片
                    //图片压缩
                    //1.获取图片的宽高
                    ImageSize imageSize = getImageViewSize(imageView);
                    //2.压缩图片
                    Bitmap bitmap = decodeSampledBitmapFromPath(path, imageSize.width, imageSize.height);
                    //3.加入到缓存
                    addBitmapToLruCache(path, bitmap);
                    sendImageViewToUI(path, imageView, bitmap);
                    mSemaphoreThreadPool.release();

                }
            });
        }
    }
    public void loadImageUri(final long songId, final long albumId, final ImageView imageView) {
        final String path = String.valueOf(songId + albumId);
        imageView.setTag(path);
        if (mUIHandler == null) {
            mUIHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 获取得到的图片
                    ImageBeanHolder holder = (ImageBeanHolder) msg.obj;
                    Bitmap bm = holder.bitmap;
                    String path = holder.path;
                    ImageView imageView = holder.imageView;
                    if (imageView.getTag().toString().equals(path)) {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null) {
            sendImageViewToUI(path, imageView, bm);
        } else {
            addTasks(new Runnable() {
                @Override
                public void run() {
                    //加载图片
                    //图片压缩
                    //1.获取图片的宽高
                    ImageSize imageSize = getImageViewSize(imageView);
                    //2.压缩图片
                    Bitmap bitmap = getMusicArt(mContext, songId,albumId,true);

                    //3.加入到缓存
                    addBitmapToLruCache(path, bitmap);
                    sendImageViewToUI(path, imageView, bitmap);
                    mSemaphoreThreadPool.release();

                }
            });
        }
    }

    /**
     * 发送到UI线程，更新图片
     *
     * @param path      图片路径
     * @param imageView 显示控件
     * @param bm        bitmap
     */
    private void sendImageViewToUI(String path, ImageView imageView, Bitmap bm) {
        Message message = Message.obtain();
        ImageBeanHolder holder = new ImageBeanHolder();
        holder.bitmap = bm;
        holder.path = path;
        holder.imageView = imageView;
        message.obj = holder;
        mUIHandler.sendMessage(message);
    }

    private void addBitmapToLruCache(String path, Bitmap bitmap) {
        if (getBitmapFromLruCache(path) != null) {
            if (bitmap != null) {
                mLruCache.put(path, bitmap);
            }
        }
    }

    /**
     * 压缩图片
     *
     * @param path   图片路径
     * @param width  图片宽
     * @param height 图片高
     * @return 返回Bitmap对象
     */
    private Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //不加载到内存
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateImSampleSize(options, width, height);
        //加载到内存
        options.inJustDecodeBounds = false;
        //在这里判断,图片是本地路径,还是网络路径,还是URI
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);

        return bitmap;
    }

    /***
     * @param options   采样
     * @param reqWidth  宽
     * @param reqHeight 高
     * @return
     */
    private int calculateImSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        if (width > reqWidth || height > reqHeight) {
            int widthRadio = Math.round(width * 1f / reqWidth);
            int heightRadio = Math.round(height * 1f / reqHeight);

            inSampleSize = Math.max(widthRadio, heightRadio);

        }
        return inSampleSize;
    }

    /**
     * 根据ImageVew获得适当的宽高
     *
     * @param imageView 采样对象
     * @return 返回宽高对象
     */
    @SuppressLint("NewApi")
    private ImageSize getImageViewSize(ImageView imageView) {
        ImageSize imageSize = new ImageSize();
        DisplayMetrics displayMetrics = imageView.getContext().getResources().getDisplayMetrics();
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        //int width = (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT? 0: imageView.getWidth());
        int width = imageView.getWidth();
        if (width <= 0) {
            width = lp.width;
        }

        if (width <= 0) {
            width = imageView.getMaxWidth();
        }
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }


        int height = imageView.getHeight();

        if (height <= 0) {
            height = lp.height;
        }

        if (height <= 0) {
            height = imageView.getMaxHeight();
        }
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        imageSize.height = height;
        imageSize.width = width;
        return imageSize;
    }

    private synchronized void addTasks(Runnable runnable) {
        mTaskQueue.add(runnable);
        try {
            if (mPoolThreadHandler == null)
                mSemaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mPoolThreadHandler.sendEmptyMessage(0x110);
    }

    private Bitmap getBitmapFromLruCache(String path) {
        return mLruCache.get(path);
    }

    private class ImageBeanHolder {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

    private class ImageSize {
        int width;
        int height;
    }

    /**
     * 根据专辑ID和歌曲ID, 来获取歌曲封面,或者是专辑封面
     * @param context 上下文环境
     * @param song_id 歌曲ID
     * @param album_id 专辑ID
     * @param allowdefault 是否允许默认封面
     * @return
     */
    public Bitmap getMusicArt(Context context, long song_id, long album_id, boolean allowdefault){
        if (album_id < 0) {
            // This is something that is not in the database, so get the album art directly
            // from the file.
            if (song_id >= 0) {
                Bitmap bm = getArtworkFromFile(context, song_id, -1);
                if (bm != null) {
                    return bm;
                }
            }
            if (allowdefault) {
                return mCachedBit;
            }
            return null;
        }
        ContentResolver res = context.getContentResolver();
        Uri uri = ContentUris.withAppendedId(sArtworkUri, album_id);
        if (uri != null) {
            InputStream in = null;
            try {
                in = res.openInputStream(uri);
                return BitmapFactory.decodeStream(in, null, sBitmapOptions);
            } catch (FileNotFoundException ex) {
                // The album art thumbnail does not actually exist. Maybe the user deleted it, or
                // maybe it never existed to begin with.
                Bitmap bm = getArtworkFromFile(context, song_id, album_id);
                if (bm != null) {
                    if (bm.getConfig() == null) {
                        bm = bm.copy(Bitmap.Config.RGB_565, false);
                        if (bm == null && allowdefault) {
                            return mCachedBit;
                        }
                    }
                } else if (allowdefault) {
                    bm = mCachedBit;
                }
                return bm;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                }
            }
        }

        return null;
    }

    private static Bitmap getArtworkFromFile(Context context, long songid, long albumid) {
        Bitmap bm = null;
        byte [] art = null;
        String path = null;
        if (albumid < 0 && songid < 0) {
            throw new IllegalArgumentException("Must specify an album or a song id");
        }
        try {
            if (albumid < 0) {
                Uri uri = Uri.parse("content://media/external/audio/media/" + songid + "/albumart");
                ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r");
                if (pfd != null) {
                    FileDescriptor fd = pfd.getFileDescriptor();
                    bm = BitmapFactory.decodeFileDescriptor(fd);
                }
            } else {
                Uri uri = ContentUris.withAppendedId(sArtworkUri, albumid);
                ParcelFileDescriptor pfd = context.getContentResolver().openFileDescriptor(uri, "r");
                if (pfd != null) {
                    FileDescriptor fd = pfd.getFileDescriptor();
                    bm = BitmapFactory.decodeFileDescriptor(fd);
                }
            }
        } catch (FileNotFoundException ex) {

        }
        if (bm != null) {
            mCachedBit = bm;
        }
        return bm;
    }
    //裁剪图片
    private static Bitmap getDefaultArtwork(Context context) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        // BitmapFactory.decodeStream(context.getResources().getDrawable(R.mipmap.ic_launcher), null, opts);
        return BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher, opts);
    }

    private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
    private static Bitmap mCachedBit = null;
    private static final BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();

}
