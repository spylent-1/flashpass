package io.github.spylent1.flashpass.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.Shop;
import io.github.spylent1.flashpass.mapper.ShopMapper;
import io.github.spylent1.flashpass.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryById(Long id) {
       //从redis查询缓存

        String shopJson = stringRedisTemplate.opsForValue().get("cache:shop:"+id);
        //判断是存在
        if(StrUtil.isNotBlank(shopJson)){
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return Result.ok(shop);
        }
        if(shopJson !=null){
            return Result.fail("店铺不存在");
        }

        Shop shop = getById(id);

        if(shop == null){
            stringRedisTemplate.opsForValue().set( "cache:shop:"+id, "",
                    2, java.util.concurrent.TimeUnit.MINUTES);
            return Result.fail("店铺不存在");
        }
        stringRedisTemplate.opsForValue().set("cache:shop:"+id, JSONUtil.toJsonStr(shop),
                30, java.util.concurrent.TimeUnit.MINUTES);

        return Result.ok(shop);
    }

    private boolean tryLock(String key){
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, java.util.concurrent.TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }


    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }



    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if(id == null){
            return Result.fail("店铺id不能为空");
        }
        updateById(shop);
        stringRedisTemplate.delete("cache:shop:"+shop.getId());
        return Result.ok();
    }
}
