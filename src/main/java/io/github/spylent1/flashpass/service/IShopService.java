package io.github.spylent1.flashpass.service;

import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    Result queryById(Long id);

    Result update(Shop shop);
}
