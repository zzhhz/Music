package com.zzh.music.utils.web;

import android.util.Log;

import com.zzh.music.interfaces.APIService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZZH on 16/9/6.
 *
 * @Date: 16/9/6
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 用于封装OkHttp3, retrofit2, RxJava
 */
public class RetrofitUtils {

    public static final String BASE_URL = "http://tingapi.ting.baidu.com/";
    public static Retrofit sRetrofit;

    public static APIService Api() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)//+IP
                    .addConverterFactory(GsonConverterFactory.create())//+解析
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//
                    .client(sHttpClient)//
                    .build();
        }

        APIService service = sRetrofit.create(APIService.class);
        return service;
    }

    static OkHttpClient sHttpClient = new OkHttpClient.Builder()//
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Log.e("---url--", "----request url: " +request.toString());
                    Response response = chain.proceed(chain.request());
                    okhttp3.MediaType mediaType = response.body().contentType();
                    String content = response.body().string();
                    Log.e("---url--", "----request body: " +content);
                    return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
                }
            }).build();

    public static String nextPage(int page){
        int offset = 0;
        if (page == 0){
            offset = 0;
        } else {
           offset =  page * 10 - 1;
        }

        return String.valueOf(offset);
    }
}
