package com.zzh.music;

/**
 * Created by ZZH on 16/5/18.
 *
 * @Date: 16/5/18
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 保存常量
 */
public class MusicConstants {
    public static final int PAGE_SIZE = 8;//分页查询，每页默认是8条数据
    public static final int DEFAULT_MUSIC_IMAGE = R.mipmap.ic_launcher;//音乐默认图片
    public static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=%s&type=%d&size=%d&offset=%d";
    public static final String EVENT_MUSIC_PLAY_CHANGE_STATUS = "com.zzh.music.music_play_change_status";//音乐状态发生改变，播放暂停
    public static String URL_NETWORK_MUSIC_PLAY = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=%s&songid=%s";
    public static final String URL_SEARCH = "";
    /**
     * 参数：	type = 1-新歌榜,2-热歌榜,11-摇滚榜,12-爵士,16-流行,21-欧美金曲榜,22-经典老歌榜,23-情歌对唱榜,24-影视金曲榜,25-网络歌曲榜
     */
    public static final String URL_HOT_MUSIC_LIST = String.format(BASE_URL, "baidu.ting.billboard.billList", 2, 10, 0);
    public static final String URL_NEW_MUSIC_LIST = String.format(BASE_URL, "baidu.ting.billboard.billList", 1, 10, 0);
    public static final String URL_FASHION_MUSIC_LIST = String.format(BASE_URL, "baidu.ting.billboard.billList", 16, 10, 0);
    public static final String URL_NETWORK_MUSIC_LIST = String.format(BASE_URL, "baidu.ting.billboard.billList", 1, 10, 0);
}
