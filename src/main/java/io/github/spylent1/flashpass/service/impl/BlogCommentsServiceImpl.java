package io.github.spylent1.flashpass.service.impl;

import io.github.spylent1.flashpass.entity.BlogComments;
import io.github.spylent1.flashpass.mapper.BlogCommentsMapper;
import io.github.spylent1.flashpass.service.IBlogCommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
