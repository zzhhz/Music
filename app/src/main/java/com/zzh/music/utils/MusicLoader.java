package com.zzh.music.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import com.zzh.music.MusicConstants;
import com.zzh.music.R;
import com.zzh.music.model.Music;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZH on 16/7/1.
 *
 * @Date: 16/7/1
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 加载本地的音乐文件
 */
public class MusicLoader {
    private static final String TAG = "MusicLoader";

    private static List<Music> musicList = new ArrayList<>();

    private static MusicLoader musicLoader;
    private static int imageWidth;

    private static Context mContext;

    private static ContentResolver contentResolver;
    //Uri，指向external的database
    private Uri contentUri = Media.EXTERNAL_CONTENT_URI;
    //projection：选择的列; where：过滤条件; sortOrder：排序。
    private String[] projection = {
            Media._ID,
            Media.DISPLAY_NAME,
            Media.DATA,
            Media.ALBUM,
            Media.ARTIST,
            Media.DURATION,
            Media.SIZE,
            Media.TITLE,
            Media.ALBUM_ID,
    };
    private String where =  "mime_type in ('audio/mpeg','audio/x-ms-wma') and _display_name <> 'audio' and is_music > 0" ;
    private String sortOrder = Media.DATA;

    public static MusicLoader getInstance(Context ctx){
        synchronized (MusicLoader.class) {
            if (musicLoader == null) {
                contentResolver = ctx.getContentResolver();
                musicLoader = new MusicLoader();
                mCachedBit = getDefaultArtwork(ctx);
                mContext = ctx;
                imageWidth = (DensityUtils.getDisplayWidth(ctx) - 20) / 2;
            }
        }
        return musicLoader;
    }

    private MusicLoader(){                                                                                                             //利用ContentResolver的query函数来查询数据，然后将得到的结果放到MusicInfo对象中，最后放到数组中
    }

    public List<Music> getMusicList(int pageNum){
        return getMusicList(pageNum, 8);
    }

    /**
     *
     * @param pageNum 页数，默认0
     * @param pageSize 行数,默认10行
     * @return
     */
    public List<Music> getMusicList(int pageNum, int pageSize){

        if (pageSize < 0)
        {
            pageSize = MusicConstants.PAGE_SIZE;
        }
        int count = 0;
        if (pageNum < 1){
            count = 0;
        } else {
            count = pageNum * pageSize - 1;
        }

        Cursor cursor = contentResolver.query(contentUri, projection, where, null, sortOrder+" ASC LIMIT "+count+" , "+pageSize);
        //清空集合
        musicList.clear();
        if(cursor == null){
            Log.d(TAG,"cursor null--------------");
        }/*else if(!cursor.moveToFirst()){
            Log.d(TAG,"Line(39) Music Loader cursor.moveToFirst() returns false.");
        }*/else{
            int displayNameCol = cursor.getColumnIndex(Media.DISPLAY_NAME);
            int albumCol = cursor.getColumnIndex(Media.ALBUM);
            int idCol = cursor.getColumnIndex(Media._ID);
            int durationCol = cursor.getColumnIndex(Media.DURATION);
            int sizeCol = cursor.getColumnIndex(Media.SIZE);
            int artistCol = cursor.getColumnIndex(Media.ARTIST);
            int urlCol = cursor.getColumnIndex(Media.DATA);
            int auId = cursor.getColumnIndex(Media.ALBUM_ID);
            int auTitle = cursor.getColumnIndex(Media.TITLE);
            while (cursor.moveToNext()){
                String title = cursor.getString(displayNameCol);
                String album = cursor.getString(albumCol);
                long id = cursor.getLong(idCol);
                int duration = cursor.getInt(durationCol);
                long size = cursor.getLong(sizeCol);
                String artist = cursor.getString(artistCol);
                String url = cursor.getString(urlCol);
                long albumId = cursor.getLong(auId);
                String albumTitle = cursor.getString(auTitle);

                Log.d(TAG, "MusicLoader: "+"title: "+title+", album: "+album+", id: "+id+", duration: "+duration+", size: "+size+", artist: "+artist+", url: "+url+", albumId: "+ albumId+", albumTitle: "+ albumTitle+"----");
                Music music = new Music();
                music.setId(id);
                music.setMusicAlbum(album);
                music.setMusicDuration(duration);
                music.setMusicSize(size);
                music.setMusicArtist(artist);
                music.setMusicUrl(url);
                music.setMusicPath(url);
                music.setMusicName(title);
                music.setMusicAlbumId(albumId);
                music.setMusicTitle(albumTitle);
                musicList.add(music);
            }
        }
        return musicList;
    }

    public Uri getMusicUriById(long id){
        Uri uri = ContentUris.withAppendedId(contentUri, id);
        return uri;
    }

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
