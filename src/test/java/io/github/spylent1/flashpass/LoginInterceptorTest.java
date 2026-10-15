package io.github.spylent1.flashpass;

import io.github.spylent1.flashpass.dto.UserDTO;
import io.github.spylent1.flashpass.utils.LoginInterceptor;
import io.github.spylent1.flashpass.utils.RedisConstants;
import io.github.spylent1.flashpass.utils.UserHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoginInterceptorTest {

    @AfterEach
    void tearDown() {
        UserHolder.removeUser();
    }

    @Test
    void preHandleLoadsUserFromAuthorizationToken() throws Exception {
        StringRedisTemplate stringRedisTemplate = mock(StringRedisTemplate.class);
        HashOperations<String, Object, Object> hashOperations = mock(HashOperations.class);
        String token = "login-token";
        String key = RedisConstants.LOGIN_USER_KEY + token;
        Map<Object, Object> userMap = new HashMap<>();
        userMap.put("id", "7");
        userMap.put("nickName", "user_7");
        userMap.put("icon", "");

        when(stringRedisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.entries(key)).thenReturn(userMap);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("authorization", token);
        MockHttpServletResponse response = new MockHttpServletResponse();
        LoginInterceptor interceptor = new LoginInterceptor(stringRedisTemplate);

        assertTrue(interceptor.preHandle(request, response, new Object()));
        UserDTO user = UserHolder.getUser();
        assertEquals(7L, user.getId());
        assertEquals("user_7", user.getNickName());
        verify(stringRedisTemplate).expire(key, RedisConstants.LOGIN_USER_TTL, java.util.concurrent.TimeUnit.MINUTES);
    }
}
