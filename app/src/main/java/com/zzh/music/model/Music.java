package com.zzh.music.model;

import java.io.Serializable;

/**
 * Created by ZZH on 16/6/15.
 *
 * @Date: 16/6/15
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 专辑, 音乐实体类
 */
public class Music implements Serializable {
    private int type; //类型,1.音乐; 2.专辑; 3...
    private long id;
    private long musicSize;//音乐大小
    private String musicName; //音乐名称  *
    private String musicUrl; //音乐封面
    private String musicLrcUrl;//歌词
    private String musicPath;//文件路径  *
    private String musicDesc; //描述
    private String musicTag; //标签
    private String musicArtist;//演唱者
    private int musicDuration;
    private String musicAlbum;//专辑
    private String musicAlbumAvatar;//专辑封面
    private long musicAlbumId;//专辑
    private String musicTitle;//专辑名称

    private int width;
    private int height;

    public String getMusicLrcUrl() {
        return musicLrcUrl;
    }

    public void setMusicLrcUrl(String musicLrcUrl) {
        this.musicLrcUrl = musicLrcUrl;
    }

    public String getMusicAlbumAvatar() {
        return musicAlbumAvatar;
    }

    public void setMusicAlbumAvatar(String musicAlbumAvatar) {
        this.musicAlbumAvatar = musicAlbumAvatar;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public long getMusicAlbumId() {
        return musicAlbumId;
    }

    public void setMusicAlbumId(long musicAlbumId) {
        this.musicAlbumId = musicAlbumId;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public Music() {
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Music music = (Music) o;

        return musicPath != null ? musicPath.equals(music.musicPath) : music.musicPath == null;

    }

    @Override
    public int hashCode() {
        return musicPath != null ? musicPath.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Music{" +
                "type=" + type +
                ", id=" + id +
                ", musicSize=" + musicSize +
                ", musicName='" + musicName + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", musicPath='" + musicPath + '\'' +
                ", musicDesc='" + musicDesc + '\'' +
                ", musicTag='" + musicTag + '\'' +
                ", musicArtist='" + musicArtist + '\'' +
                ", musicDuration=" + musicDuration +
                ", musicAlbum='" + musicAlbum + '\'' +
                ", musicAlbumId=" + musicAlbumId +
                ", musicTitle='" + musicTitle + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
