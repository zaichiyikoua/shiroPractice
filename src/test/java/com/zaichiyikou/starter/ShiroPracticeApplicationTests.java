package com.zaichiyikou.starter;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroPracticeApplicationTests {

    /**
     * 加密测试
     */
    @Test
    void contextLoads() {
        // 要加密的字符串
        String source = "12312";
        // 盐 可以通过java.util自带的UUID拿到一个
        String salt = UUID.randomUUID().toString();
        // 迭代次数 就是进行多重加盐迭代的迭代次数 次数越多，加密效果越好
        Integer hashIterations = 100;

        Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
        System.out.println(md5Hash);
        // f7c21c8319929840c18bc20e07d6556a
        // 这就是md5加密得到的密文
        Sha1Hash sha1Hash = new Sha1Hash(source, salt, hashIterations);
        System.out.println(sha1Hash);
        // b537e853da065855730a014dd82c9580a6cca70b
        // 这就是sha1加密得到的密文
    }

}
