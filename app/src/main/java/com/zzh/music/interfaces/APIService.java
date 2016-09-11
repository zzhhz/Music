package com.zzh.music.interfaces;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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

    @POST("MusicService/")
    Observable<ResponseBody> testApi(@Body String id);

    @FormUrlEncoded
    @POST("MusicService/user/login.zzh")
    Observable<ResponseBody> login(@Field("userName") String userName, @Field("passWord") String passWord);



















































}
