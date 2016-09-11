package com.zzh.music.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

import com.zzh.music.model.Music;

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
    };
    private String where =  "mime_type in ('audio/mpeg','audio/x-ms-wma') and _display_name <> 'audio' and is_music > 0 " ;
    private String sortOrder = Media.DATA;

    public static MusicLoader getInstance(Context ctx){
        synchronized (MusicLoader.class) {
            if (musicLoader == null) {
                contentResolver = ctx.getContentResolver();
                musicLoader = new MusicLoader();
            }
        }
        return musicLoader;
    }

    private MusicLoader(){                                                                                                             //利用ContentResolver的query函数来查询数据，然后将得到的结果放到MusicInfo对象中，最后放到数组中
    }

    public List<Music> getMusicList(){
        Cursor cursor = contentResolver.query(contentUri, projection, where, null, sortOrder);
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
            Log.d(TAG, "MusicLoader: ");
            while (cursor.moveToNext()){
                String title = cursor.getString(displayNameCol);
                String album = cursor.getString(albumCol);
                long id = cursor.getLong(idCol);
                int duration = cursor.getInt(durationCol);
                long size = cursor.getLong(sizeCol);
                String artist = cursor.getString(artistCol);
                String url = cursor.getString(urlCol);
                Log.d(TAG, "MusicLoader: "+"title: "+title+", album: "+album+", id: "+id+", duration: "+duration+", size: "+size+", artist: "+artist+", url: "+url);
                Music music = new Music();
                music.setId(id);
                music.setMusicAlbum(album);
                music.setMusicDuration(duration);
                music.setMusicSize(size);
                music.setMusicArtist(artist);
                music.setMusicUrl(url);
                music.setMusicName(title);
                musicList.add(music);
            }
        }
        return musicList;
    }

    public Uri getMusicUriById(long id){
        Uri uri = ContentUris.withAppendedId(contentUri, id);
        return uri;
    }
}
