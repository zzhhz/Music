package com.zzh.music.utils.params;

import java.util.concurrent.ConcurrentHashMap;

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
 * Created by ZZH on 17/3/9.
 *
 * @Date: 17/3/9
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 一些必须的参数
 */
public class ZCurHashMap extends ConcurrentHashMap<String,String> {
    public static final String FORMAT = "format";
    public static final String CALLBACK = "callback";
    public static final String FROM = "from";
    public static final String METHOD = "method";
    public static final String TYPE = "type";
    public static final String SIZE = "size";
    public static final String OFF_SET = "offset";

    public ZCurHashMap() {
        put(FORMAT, "json");
        put(CALLBACK,"");
        put(FROM,"webapp_music");
        put(SIZE,"10");
        put(OFF_SET,"0");
    }
}
