package io.github.spylent1.flashpass;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.spylent1.flashpass.dto.Result;
import org.junit.jupiter.api.Test;

import java.util.List;

class JsonCacheParseTest {

    @Test
    void parseShopTypeCacheAndSerializeResult() throws Exception {
        String json = "[{\"icon\":\"/types/ms.png\",\"updateTime\":1640229871000,\"sort\":1,\"createTime\":1640175467000,\"name\":\"美食\",\"id\":1}]";
        List<?> shopTypes = JSONUtil.toList(json, io.github.spylent1.flashpass.entity.ShopType.class);
        new ObjectMapper().writeValueAsString(Result.ok(shopTypes));
    }
}
