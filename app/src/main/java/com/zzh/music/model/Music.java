package com.zzh.music.model;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

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
    private String musicName; //名称
    private String musicUrl; //
    private String musicPath;//文件路径
    private String musicDesc; //描述
    private String musicTag; //标签
    private String musicArtist;
    private int musicDuration;
    private String musicAlbum;//专辑
    private long musicAlbumId;//专辑
    private String musicTitle;//专辑
    private Bitmap mBitmapAlbum;

    private int width;
    private int height;

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

        if (TextUtils.isEmpty(songId)) {
            return musicPath.equals(music.getMusicPath());
        }

        if (!musicPath.equals(music.musicPath)) return false;

        return songId.equals(music.songId);

    }

    @Override
    public int hashCode() {
        if (TextUtils.isEmpty(musicPath)) {
            return songId.hashCode();
        }
        if (TextUtils.isEmpty(songId)) {
            return musicPath.hashCode();
        }
        int result = musicPath.hashCode();
        result = 31 * result + songId.hashCode();
        return result;
    }

    @SerializedName(value = "artist_id")
    private String artistId;
    private String language;
    @SerializedName(value = "pic_big")
    private String picBig;
    @SerializedName(value = "pic_small")
    private String picSmall;
    private String country;
    private String area;
    private String publishtime;
    @SerializedName(value = "album_no")
    private String albumNo;
    private String lrclink;
    @SerializedName(value = "copy_type")
    private String copyType;
    private String hot;
    @SerializedName(value = "all_artist_ting_uid")
    private String allArtistTingUid;
    @SerializedName(value = "resource_type")
    private String resourceType;
    @SerializedName(value = "is_new")
    private String isNew;
    @SerializedName(value = "rank_change")
    private String rankChange;
    private String rank;
    @SerializedName(value = "all_artist_id")
    private String allArtistId;
    private String style;
    @SerializedName(value = "del_status")
    private String delStatus;
    @SerializedName(value = "relate_status")
    private String relateStatus;
    private String toneid;
    @SerializedName(value = "all_rate")
    private String allRate;
    @SerializedName(value = "file_duration")
    private String fileDuration;
    @SerializedName(value = "has_mv_mobile")
    private String hasMvMobile;
    private String versions;
    @SerializedName(value = "bitrate_fee")
    private String bitrateFee;
    @SerializedName(value = "song_id")
    private String songId;
    private String title;
    @SerializedName(value = "ting_uid")
    private String tingUid;

    private String author;
    @SerializedName(value = "album_id")
    private String albumId;
    @SerializedName(value = "album_title")
    private String albumTitle;
    @SerializedName(value = "is_first_publish")
    private String isFirstPublish;
    private String havehigh;
    private String charge;
    @SerializedName(value = "has_mv")
    private String hasMv;
    private String learn;
    @SerializedName(value = "song_source")
    private String songSource;
    @SerializedName(value = "piao_id")
    private String piaoId;
    @SerializedName(value = "korean_bb_song")
    private String koreanBbSong;
    @SerializedName(value = "resource_type_ext")
    private String resourceTypExt;
    @SerializedName(value = "mv_provider")
    private String mvProvider;
    @SerializedName(value = "artist_name")
    private String artistName;

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getAlbumNo() {
        return albumNo;
    }

    public void setAlbumNo(String albumNo) {
        this.albumNo = albumNo;
    }

    public String getLrclink() {
        return lrclink;
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getAllArtistTingUid() {
        return allArtistTingUid;
    }

    public void setAllArtistTingUid(String allArtistTingUid) {
        this.allArtistTingUid = allArtistTingUid;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getRankChange() {
        return rankChange;
    }

    public void setRankChange(String rankChange) {
        this.rankChange = rankChange;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAllArtistId() {
        return allArtistId;
    }

    public void setAllArtistId(String allArtistId) {
        this.allArtistId = allArtistId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public String getRelateStatus() {
        return relateStatus;
    }

    public void setRelateStatus(String relateStatus) {
        this.relateStatus = relateStatus;
    }

    public String getToneid() {
        return toneid;
    }

    public void setToneid(String toneid) {
        this.toneid = toneid;
    }

    public String getAllRate() {
        return allRate;
    }

    public void setAllRate(String allRate) {
        this.allRate = allRate;
    }

    public String getFileDuration() {
        return fileDuration;
    }

    public void setFileDuration(String fileDuration) {
        this.fileDuration = fileDuration;
    }

    public String getHasMvMobile() {
        return hasMvMobile;
    }

    public void setHasMvMobile(String hasMvMobile) {
        this.hasMvMobile = hasMvMobile;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public String getBitrateFee() {
        return bitrateFee;
    }

    public void setBitrateFee(String bitrateFee) {
        this.bitrateFee = bitrateFee;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTingUid() {
        return tingUid;
    }

    public void setTingUid(String tingUid) {
        this.tingUid = tingUid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getIsFirstPublish() {
        return isFirstPublish;
    }

    public void setIsFirstPublish(String isFirstPublish) {
        this.isFirstPublish = isFirstPublish;
    }

    public String getHavehigh() {
        return havehigh;
    }

    public void setHavehigh(String havehigh) {
        this.havehigh = havehigh;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getHasMv() {
        return hasMv;
    }

    public void setHasMv(String hasMv) {
        this.hasMv = hasMv;
    }

    public String getLearn() {
        return learn;
    }

    public void setLearn(String learn) {
        this.learn = learn;
    }

    public String getSongSource() {
        return songSource;
    }

    public void setSongSource(String songSource) {
        this.songSource = songSource;
    }

    public String getPiaoId() {
        return piaoId;
    }

    public void setPiaoId(String piaoId) {
        this.piaoId = piaoId;
    }

    public String getKoreanBbSong() {
        return koreanBbSong;
    }

    public void setKoreanBbSong(String koreanBbSong) {
        this.koreanBbSong = koreanBbSong;
    }

    public String getResourceTypExt() {
        return resourceTypExt;
    }

    public void setResourceTypExt(String resourceTypExt) {
        this.resourceTypExt = resourceTypExt;
    }

    public String getMvProvider() {
        return mvProvider;
    }

    public void setMvProvider(String mvProvider) {
        this.mvProvider = mvProvider;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
                ", mBitmapAlbum=" + mBitmapAlbum +
                ", width=" + width +
                ", height=" + height +
                ", artistId='" + artistId + '\'' +
                ", language='" + language + '\'' +
                ", picBig='" + picBig + '\'' +
                ", picSmall='" + picSmall + '\'' +
                ", country='" + country + '\'' +
                ", area='" + area + '\'' +
                ", publishtime='" + publishtime + '\'' +
                ", albumNo='" + albumNo + '\'' +
                ", lrclink='" + lrclink + '\'' +
                ", copyType='" + copyType + '\'' +
                ", hot='" + hot + '\'' +
                ", allArtistTingUid='" + allArtistTingUid + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", isNew='" + isNew + '\'' +
                ", rankChange='" + rankChange + '\'' +
                ", rank='" + rank + '\'' +
                ", allArtistId='" + allArtistId + '\'' +
                ", style='" + style + '\'' +
                ", delStatus='" + delStatus + '\'' +
                ", relateStatus='" + relateStatus + '\'' +
                ", toneid='" + toneid + '\'' +
                ", allRate='" + allRate + '\'' +
                ", fileDuration='" + fileDuration + '\'' +
                ", hasMvMobile='" + hasMvMobile + '\'' +
                ", versions='" + versions + '\'' +
                ", bitrateFee='" + bitrateFee + '\'' +
                ", songId='" + songId + '\'' +
                ", title='" + title + '\'' +
                ", tingUid='" + tingUid + '\'' +
                ", author='" + author + '\'' +
                ", albumId='" + albumId + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", isFirstPublish='" + isFirstPublish + '\'' +
                ", havehigh='" + havehigh + '\'' +
                ", charge='" + charge + '\'' +
                ", hasMv='" + hasMv + '\'' +
                ", learn='" + learn + '\'' +
                ", songSource='" + songSource + '\'' +
                ", piaoId='" + piaoId + '\'' +
                ", koreanBbSong='" + koreanBbSong + '\'' +
                ", resourceTypExt='" + resourceTypExt + '\'' +
                ", mvProvider='" + mvProvider + '\'' +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
