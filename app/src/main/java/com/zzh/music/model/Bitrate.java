package com.zzh.music.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━永无BUG━━━━━
 * Created by Administrator on 2017/3/20.
 *
 * @Date: 2017/3/20
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: Music
 */

public class Bitrate implements Serializable {
    @SerializedName(value = "show_link")
    private String showLink;
    private int free;
    @SerializedName(value = "song_file_id")
    private long songFileId;
    @SerializedName(value = "file_size")
    private long fileSize;
    @SerializedName(value = "file_extension")
    private String fileExtension;
    @SerializedName(value = "file_link")
    private String fileLink;
    @SerializedName(value = "hash")
    private String hash;
    @SerializedName(value = "file_duration")
    private int fileDuration;
    @SerializedName(value = "file_bitrate")
    private int fileBitrate;

    public String getShowLink() {
        return showLink;
    }

    public void setShowLink(String showLink) {
        this.showLink = showLink;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public long getSongFileId() {
        return songFileId;
    }

    public void setSongFileId(long songFileId) {
        this.songFileId = songFileId;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getFileDuration() {
        return fileDuration;
    }

    public void setFileDuration(int fileDuration) {
        this.fileDuration = fileDuration;
    }

    public int getFileBitrate() {
        return fileBitrate;
    }

    public void setFileBitrate(int fileBitrate) {
        this.fileBitrate = fileBitrate;
    }

    @Override
    public String toString() {
        return "Bitrate{" +
                "showLink='" + showLink + '\'' +
                ", free=" + free +
                ", songFileId=" + songFileId +
                ", fileSize=" + fileSize +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileLink='" + fileLink + '\'' +
                ", hash='" + hash + '\'' +
                ", fileDuration=" + fileDuration +
                ", fileBitrate=" + fileBitrate +
                '}';
    }
}
