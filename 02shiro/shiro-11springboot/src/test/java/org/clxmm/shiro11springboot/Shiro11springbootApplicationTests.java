package org.clxmm.shiro11springboot;

import org.junit.jupiter.api.Test;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Shiro11springbootApplicationTests {


    @Resource(name = "redissonClientForShiro")
    RedissonClient redissonClient;


    @Test
    void contextLoads() {


        RAtomicLong clxmm = redissonClient.getAtomicLong("clxmm11");

        System.out.println(clxmm.get());
//        clxmm.incrementAndGet();

    }

}
