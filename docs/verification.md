# 验证记录

验证日期：2026-10-15。环境：Windows、Eclipse Temurin 17.0.20.1、Maven 3.9.14。

## 已执行

- `mvn -B verify`：构建和可执行 JAR 打包通过。
- 测试：5 个，失败 0，错误 0，跳过 0。
- Mapper XML namespace、实体类型与 Java 包迁移绑定通过。
- Spring Boot 应用上下文启动，Controller 与 Mapper Bean 可被扫描。
- Redis Token 登录拦截与缓存 JSON 序列化测试通过；应用启动测试使用模拟 Redis / Redisson 客户端。

## 待执行

真实 MySQL / Redis 数据读写、完整客户端联调与并发压测未执行。本次应用上下文检查不等于真实中间件联调。GitHub Actions 结果以对应提交的实际运行记录为准。
