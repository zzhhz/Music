package com.zzh.music;

import com.zzh.music.model.Contacts;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;

/**
 * Created by ZZH on 16/6/17.
 *
 * @Date: 16/6/17
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: 单元测试
 */
public class JunitTest {
    @Test
    public void chinese2Pinyin() throws BadHanyuPinyinOutputFormatCombination {
        Contacts contacts = new Contacts();

        String string = contacts.getUserNameChar();

        System.out.println(string);

    }
}
