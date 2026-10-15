package io.github.spylent1.flashpass.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.github.spylent1.flashpass.dto.UserDTO;
import io.github.spylent1.flashpass.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        if (StrUtil.isNotBlank(token)) {
            String tokenKey = RedisConstants.LOGIN_USER_KEY + token;
            Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(tokenKey);
            if (!userMap.isEmpty()) {
                UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDTO(), false);
                UserHolder.saveUser(userDTO);
                stringRedisTemplate.expire(tokenKey, RedisConstants.LOGIN_USER_TTL, TimeUnit.MINUTES);
                return true;
            }
        }

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user == null) {
            response.setStatus(401);
            return false;
        }
        if (user instanceof UserDTO) {
            UserHolder.saveUser((UserDTO) user);
        } else if (user instanceof User) {
            UserHolder.saveUser(BeanUtil.copyProperties(user, UserDTO.class));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
