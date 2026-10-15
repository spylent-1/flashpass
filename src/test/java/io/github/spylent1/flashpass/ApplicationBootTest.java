package io.github.spylent1.flashpass;

import io.github.spylent1.flashpass.controller.UserController;
import io.github.spylent1.flashpass.mapper.VoucherMapper;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "DB_PASSWORD=test-only-no-live-database")
class ApplicationBootTest {
    @MockBean private RedissonClient redissonClient;
    @MockBean private StringRedisTemplate redisTemplate;
    @Autowired private ApplicationContext context;

    @Test
    void migratedApplicationDiscoversControllersAndMappers() {
        assertNotNull(context.getBean(UserController.class));
        assertNotNull(context.getBean(VoucherMapper.class));
    }
}
