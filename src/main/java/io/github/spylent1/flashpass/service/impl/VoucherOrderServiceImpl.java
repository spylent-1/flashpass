package io.github.spylent1.flashpass.service.impl;

import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.SeckillVoucher;
import io.github.spylent1.flashpass.entity.Voucher;
import io.github.spylent1.flashpass.entity.VoucherOrder;
import io.github.spylent1.flashpass.mapper.VoucherOrderMapper;
import io.github.spylent1.flashpass.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.spylent1.flashpass.utils.RedisIdWorker;
import io.github.spylent1.flashpass.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private IVoucherOrderService voucherOrderService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Autowired
    private SeckillVoucherServiceImpl seckillVoucherServiceImpl;

    @Override
    @Transactional
    public Result seckillVoucher(Long voucherId) {
        //1查询优惠卷
        SeckillVoucher voucher = seckillVoucherServiceImpl.getById(voucherId);
        //判断时间
        if (voucher.getBeginTime().isAfter(LocalDateTime.now())) {
            return Result.fail("秒杀尚未开始！");
        }
        if (voucher.getEndTime().isBefore(LocalDateTime.now())) {
            return Result.fail("秒杀已经结束！");
        }
        if(voucher.getStock() <= 0){
            return Result.fail("库存不足！");
        }
        //扣除库存
        boolean success = seckillVoucherServiceImpl.update().setSql("stock = stock - 1")
                .eq("voucher_id", voucherId).gt("stock" , 0)
                .update();
        if(!success){
            return Result.fail("库存不足！");
        }
        //判断一人一单
      Long userid = UserHolder.getUser().getId();
       int count = query().eq("user_id", userid).eq("voucher_id", voucherId).count();
        if(count > 0){
            return Result.fail("用户已经购买过该优惠券！");
        }

        //创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);
        Long userId = UserHolder.getUser().getId();
        voucherOrder.setUserId(userId);
        voucherOrder.setVoucherId(voucherId);
        save(voucherOrder);

        return Result.ok(orderId);
    }
}
