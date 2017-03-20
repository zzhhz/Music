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

public class Album implements Serializable{
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
}
