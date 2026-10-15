package io.github.spylent1.flashpass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.spylent1.flashpass.dto.LoginFormDTO;
import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.User;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);
}
