package com.zzh.music.interfaces;

import com.zzh.music.model.Album;
import com.zzh.music.model.BaseModel;
import com.zzh.music.model.Song;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ZZH on 16/9/6.
 *
 * @Date: 16/9/6
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 接口数据
 *
 */
public interface APIService {

    @FormUrlEncoded
    @POST("MusicService/user/login.zzh")
    Observable<ResponseBody> login(@Field("userName") String userName, @Field("passWord") String passWord);

    @GET("v1/restserver/ting")
    Observable<ResponseBody> testApi(@QueryMap Map<String, String> params);
    @POST("/v1/restserver/ting")
    Observable<BaseModel<Album>> getRecommendType(@QueryMap Map<String, String> params);
    @POST("/v1/restserver/ting")//音乐详情
    Observable<BaseModel<Song>> getMusicInfo(@QueryMap Map<String, String> params);
    @GET("/v1/restserver/ting?method=baidu.ting.song.play&from=android&format=json")//音乐详情
    Observable<BaseModel<Song>> getSongsInfo(@Query("songid") String songId);




















































}
