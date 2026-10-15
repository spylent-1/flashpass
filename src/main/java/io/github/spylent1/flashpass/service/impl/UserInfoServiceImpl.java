package io.github.spylent1.flashpass.service.impl;

import io.github.spylent1.flashpass.entity.UserInfo;
import io.github.spylent1.flashpass.mapper.UserInfoMapper;
import io.github.spylent1.flashpass.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
