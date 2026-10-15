package io.github.spylent1.flashpass.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.ShopType;
import io.github.spylent1.flashpass.mapper.ShopTypeMapper;
import io.github.spylent1.flashpass.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    private final StringRedisTemplate stringRedisTemplate;

    public ShopTypeServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Result queryTypeList() {
        String shoptypeJson = stringRedisTemplate.opsForValue().get("cache:shoptype");

        if (StrUtil.isNotBlank(shoptypeJson)) {
            List<ShopType> shopTypes = JSONUtil.toList(shoptypeJson, ShopType.class);
            return Result.ok(shopTypes);
        }

        List<ShopType> shopTypes = list();
        stringRedisTemplate.opsForValue().set("cache:shoptype", JSONUtil.toJsonStr(shopTypes));
        return Result.ok(shopTypes);
    }
}
