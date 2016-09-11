package com.zzh.music.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZZH on 16/6/15.
 *
 * @Date: 16/6/15
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 专辑,音乐实体类
 */
public class Music implements Parcelable{
    private int type; //类型,1.音乐; 2.专辑; 3...
    private long id;
    private long musicSize;
    private String musicName; //名称
    private String musicUrl; //封面
    private String musicDesc; //描述
    private String musicTag; //标签
    private String musicArtist;
    private int musicDuration;
    private String musicAlbum;//专辑

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(long musicSize) {
        this.musicSize = musicSize;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public int getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(int musicDuration) {
        this.musicDuration = musicDuration;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicDesc() {
        return musicDesc;
    }

    public void setMusicDesc(String musicDesc) {
        this.musicDesc = musicDesc;
    }

    public String getMusicTag() {
        return musicTag;
    }

    public void setMusicTag(String musicTag) {
        this.musicTag = musicTag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
        dest.writeLong(this.id);
        dest.writeLong(this.musicSize);
        dest.writeString(this.musicName);
        dest.writeString(this.musicUrl);
        dest.writeString(this.musicDesc);
        dest.writeString(this.musicTag);
        dest.writeString(this.musicArtist);
        dest.writeInt(this.musicDuration);
        dest.writeString(this.musicAlbum);
    }

    public Music() {
    }

    protected Music(Parcel in) {
        this.type = in.readInt();
        this.id = in.readLong();
        this.musicSize = in.readLong();
        this.musicName = in.readString();
        this.musicUrl = in.readString();
        this.musicDesc = in.readString();
        this.musicTag = in.readString();
        this.musicArtist = in.readString();
        this.musicDuration = in.readInt();
        this.musicAlbum = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel source) {
            return new Music(source);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };
}
