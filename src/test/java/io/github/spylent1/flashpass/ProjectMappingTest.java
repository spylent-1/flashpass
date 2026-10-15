package io.github.spylent1.flashpass;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import io.github.spylent1.flashpass.entity.Voucher;
import io.github.spylent1.flashpass.mapper.VoucherMapper;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMappingTest {
    @Test
    void migratedMapperXmlResolvesInterfaceAndResultType() throws Exception {
        MybatisConfiguration configuration = new MybatisConfiguration();
        try (InputStream xml = getClass().getResourceAsStream("/mapper/VoucherMapper.xml")) {
            assertNotNull(xml);
            new XMLMapperBuilder(xml, configuration, "mapper/VoucherMapper.xml",
                    configuration.getSqlFragments()).parse();
        }
        assertTrue(configuration.hasMapper(VoucherMapper.class));
        assertEquals(Voucher.class, configuration.getMappedStatement(
                VoucherMapper.class.getName() + ".queryVoucherOfShop")
                .getResultMaps().get(0).getType());
    }

    @Test
    void applicationScansTheMigratedMapperPackage() {
        assertArrayEquals(new String[]{VoucherMapper.class.getPackage().getName()},
                FlashPassApplication.class.getAnnotation(MapperScan.class).value());
    }
}
