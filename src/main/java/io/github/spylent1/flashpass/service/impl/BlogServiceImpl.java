package io.github.spylent1.flashpass.service.impl;

import io.github.spylent1.flashpass.entity.Blog;
import io.github.spylent1.flashpass.mapper.BlogMapper;
import io.github.spylent1.flashpass.service.IBlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
