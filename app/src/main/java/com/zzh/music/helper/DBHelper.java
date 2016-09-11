package com.zzh.music.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZZH on 16/7/1.
 *
 * @Date: 16/7/1
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 数据库辅助类
 */
public class DBHelper extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "zzh_music.db";
    private static DBHelper mInstance;
    private static String CREATE_TABLE="CREATE TABLE IF NOT EXISTS ";

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 数据库操作
     * @param ctx
     * @return
     */
    public static DBHelper getInstance(Context ctx){
        if (mInstance == null) {
            synchronized (DBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DBHelper(ctx);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.beginTransaction();
            //




            //
            db.setTransactionSuccessful();
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String createTableMusic(){
        StringBuilder builder = new StringBuilder(CREATE_TABLE);
        builder.append(" (id TEXT, ")//主键
                .append("musicName TEXT,")//音乐名称
                .append("musicAlbum TEXT,")//专辑名称
                .append("musicUrl TEXT,") //封面
                .append("musicTag TEXT,")//标签
                .append("musicDesc TEXT,") //简介
                .append("musicArtist TEXT,")//
                .append("musicDuration TEXT,")//
                .append("type TEXT") //类型
        .append(")");
        return builder.toString();
    }


}
