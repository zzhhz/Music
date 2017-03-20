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
 * @Description: Music 歌曲详情
 */

public class Song implements Serializable {

    @SerializedName(value = "special_type")
    private int specialType;
    @SerializedName(value = "pic_huge")
    private String picHuge;
    @SerializedName(value = "resource_type")
    private String resourceType;
    @SerializedName(value = "pic_premium")
    private String picPremium;
    private int havehigh;
    private String author;
    private String toneid;
    @SerializedName(value = "has_mv")
    private int hasMv;
    private int learn;
    @SerializedName(value = "song_id")
    private String songId;
    @SerializedName(value = "piao_id")
    private String piaoId;
    @SerializedName(value = "artist_id")
    private String artist_id;

    private String lrclink;
    @SerializedName(value = "relate_status")
    private String relateStatus;
    @SerializedName(value = "pic_big")
    private String picBig;
    @SerializedName(value = "play_type")
    private String playType;
    @SerializedName(value = "album_id")
    private String albumId;
    @SerializedName(value = "album_title")
    private String albumTitle;
    @SerializedName(value = "bitrate_fee")
    private String bitrateFee;
    @SerializedName(value = "song_source")
    private String songSource;
    @SerializedName(value = "all_artist_id")
    private String allArtistId;
    @SerializedName(value = "all_artist_ting_uid")
    private String allArtistTingUid;
    @SerializedName(value = "all_rate")
    private String allRate;
    private int charge;
    @SerializedName(value = "is_first_publish")
    private int isFirstPublish;
    @SerializedName(value = "copy_type")
    private String copyType;
    @SerializedName(value = "korean_bb_song")
    private String koreanBbSong;
    @SerializedName(value = "pic_radio")
    private String picRadio;
    private String title;
    @SerializedName(value = "pic_small")
    private String picSmall;
    @SerializedName(value = "album_no")
    private String albumNo;
    @SerializedName(value = "resource_type_ext")
    private String resourceTypeExt;
    @SerializedName(value = "ting_uid")
    private String tingUid;
    @SerializedName(value = "has_mv_mobile")
    private int hasMvMobile;//0 meiyou

    public int getSpecialType() {
        return specialType;
    }

    public void setSpecialType(int specialType) {
        this.specialType = specialType;
    }

    public String getPicHuge() {
        return picHuge;
    }

    public void setPicHuge(String picHuge) {
        this.picHuge = picHuge;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getPicPremium() {
        return picPremium;
    }

    public void setPicPremium(String picPremium) {
        this.picPremium = picPremium;
    }

    public int getHavehigh() {
        return havehigh;
    }

    public void setHavehigh(int havehigh) {
        this.havehigh = havehigh;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getToneid() {
        return toneid;
    }

    public void setToneid(String toneid) {
        this.toneid = toneid;
    }

    public int getHasMv() {
        return hasMv;
    }

    public void setHasMv(int hasMv) {
        this.hasMv = hasMv;
    }

    public int getLearn() {
        return learn;
    }

    public void setLearn(int learn) {
        this.learn = learn;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getPiaoId() {
        return piaoId;
    }

    public void setPiaoId(String piaoId) {
        this.piaoId = piaoId;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }

    public String getLrclink() {
        return lrclink;
    }

    public void setLrclink(String lrclink) {
        this.lrclink = lrclink;
    }

    public String getRelateStatus() {
        return relateStatus;
    }

    public void setRelateStatus(String relateStatus) {
        this.relateStatus = relateStatus;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
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

    public String getBitrateFee() {
        return bitrateFee;
    }

    public void setBitrateFee(String bitrateFee) {
        this.bitrateFee = bitrateFee;
    }

    public String getSongSource() {
        return songSource;
    }

    public void setSongSource(String songSource) {
        this.songSource = songSource;
    }

    public String getAllArtistId() {
        return allArtistId;
    }

    public void setAllArtistId(String allArtistId) {
        this.allArtistId = allArtistId;
    }

    public String getAllArtistTingUid() {
        return allArtistTingUid;
    }

    public void setAllArtistTingUid(String allArtistTingUid) {
        this.allArtistTingUid = allArtistTingUid;
    }

    public String getAllRate() {
        return allRate;
    }

    public void setAllRate(String allRate) {
        this.allRate = allRate;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getIsFirstPublish() {
        return isFirstPublish;
    }

    public void setIsFirstPublish(int isFirstPublish) {
        this.isFirstPublish = isFirstPublish;
    }

    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    public String getKoreanBbSong() {
        return koreanBbSong;
    }

    public void setKoreanBbSong(String koreanBbSong) {
        this.koreanBbSong = koreanBbSong;
    }

    public String getPicRadio() {
        return picRadio;
    }

    public void setPicRadio(String picRadio) {
        this.picRadio = picRadio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getAlbumNo() {
        return albumNo;
    }

    public void setAlbumNo(String albumNo) {
        this.albumNo = albumNo;
    }

    public String getResourceTypeExt() {
        return resourceTypeExt;
    }

    public void setResourceTypeExt(String resourceTypeExt) {
        this.resourceTypeExt = resourceTypeExt;
    }

    public String getTingUid() {
        return tingUid;
    }

    public void setTingUid(String tingUid) {
        this.tingUid = tingUid;
    }

    public int getHasMvMobile() {
        return hasMvMobile;
    }

    public void setHasMvMobile(int hasMvMobile) {
        this.hasMvMobile = hasMvMobile;
    }
}
