package com.zzh.music.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ZZH on 17/3/9.
 *
 * @Date: 17/3/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class Billboard implements Serializable{
    @SerializedName(value = "billboard_type")
    private String billboardType;
    @SerializedName(value = "billboard_no")
    private String billboardNo;
    @SerializedName(value = "update_date")
    private String updateDate;
    @SerializedName(value = "billboard_songnum")
    private String billboardSongNum;
    @SerializedName(value = "havemore")
    private int haveMore;
    private String name;
    private String comment;
    private String pic_s640;
    private String pic_s444;
    private String pic_s260;
    private String pic_s210;
    @SerializedName(value = "web_url")
    private String webUrl;

    public String getBillboardType() {
        return billboardType;
    }

    public void setBillboardType(String billboardType) {
        this.billboardType = billboardType;
    }

    public String getBillboardNo() {
        return billboardNo;
    }

    public void setBillboardNo(String billboardNo) {
        this.billboardNo = billboardNo;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getBillboardSongNum() {
        return billboardSongNum;
    }

    public void setBillboardSongNum(String billboardSongNum) {
        this.billboardSongNum = billboardSongNum;
    }

    public int getHaveMore() {
        return haveMore;
    }

    public void setHaveMore(int haveMore) {
        this.haveMore = haveMore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPic_s640() {
        return pic_s640;
    }

    public void setPic_s640(String pic_s640) {
        this.pic_s640 = pic_s640;
    }

    public String getPic_s444() {
        return pic_s444;
    }

    public void setPic_s444(String pic_s444) {
        this.pic_s444 = pic_s444;
    }

    public String getPic_s260() {
        return pic_s260;
    }

    public void setPic_s260(String pic_s260) {
        this.pic_s260 = pic_s260;
    }

    public String getPic_s210() {
        return pic_s210;
    }

    public void setPic_s210(String pic_s210) {
        this.pic_s210 = pic_s210;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
