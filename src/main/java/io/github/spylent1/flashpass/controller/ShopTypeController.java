package io.github.spylent1.flashpass.controller;


import io.github.spylent1.flashpass.dto.Result;
import io.github.spylent1.flashpass.entity.ShopType;
import io.github.spylent1.flashpass.service.IShopTypeService;
import io.github.spylent1.flashpass.service.impl.ShopTypeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Resource
    private IShopTypeService typeService;

    @GetMapping("list")
    public Result queryTypeList() {

        return typeService.queryTypeList();
    }
}
