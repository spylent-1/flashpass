package io.github.spylent1.flashpass.service.impl;

import io.github.spylent1.flashpass.entity.SeckillVoucher;
import io.github.spylent1.flashpass.mapper.SeckillVoucherMapper;
import io.github.spylent1.flashpass.service.ISeckillVoucherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀优惠券表，与优惠券是一对一关系 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2022-01-04
 */
@Service
public class SeckillVoucherServiceImpl extends ServiceImpl<SeckillVoucherMapper, SeckillVoucher> implements ISeckillVoucherService {

}
