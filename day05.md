### Day05 核心需求：店铺营业状态设置
苍穹外卖的营业状态（1为营业，0为打烊）因为只有这一个状态值，且会被高频访问（每个用户打开小程序都要看），所以非常适合存储在 Redis 中，而不是 MySQL 数据库里。

### 你的开发闯关路线图（共 4 步） 第一步：准备 Redis 环境
1. 确保你本地已经启动了 Redis 服务（默认端口通常是 6379）。
2. 你可以使用图形化界面工具（如 Another Redis Desktop Manager）连接上你的 Redis，方便一会儿查看数据是否写进去了。 第二步：检查并确认 Spring Boot 集成 Redis
注：刚才排查问题时我顺手帮你把这部分的依赖和配置加上了，你可以去确认一下，就当复习：

1. 去 sky-server 的 pom.xml 里看看是不是有了 spring-boot-starter-data-redis 依赖。
2. 去 application-dev.yml 和 application.yml 里看看是不是有了 spring.redis 的相关配置（host, port 等）。 第三步：开发【管理端】接口
你需要去 com.sky.controller.admin 包下新建一个 ShopController 类。 需要实现的接口有两个：

1. 设置营业状态
   - 请求路径: PUT /admin/shop/{status}
   - 参数: 路径参数 status (Integer 类型，1 或 0)
   - 逻辑: 使用 Spring 提供的 RedisTemplate ，往 Redis 里存入一个固定的 key（比如叫 "SHOP_STATUS" ），值为传进来的 status 。
2. 查询营业状态
   - 请求路径: GET /admin/shop/status
   - 逻辑: 使用 RedisTemplate 去 Redis 里读取 "SHOP_STATUS" 的值并返回。 第四步：开发【用户端】接口
用户端也需要知道店铺有没有营业，所以你要去 com.sky.controller.user 包下也新建一个 ShopController 类。 需要实现的接口有一个：

1. 查询营业状态
   - 请求路径: GET /user/shop/status
   - 逻辑: 和管理端查询状态的代码几乎一模一样，去 Redis 里面把 "SHOP_STATUS" 的值拿出来返回给前端。
(注：因为在 admin 和 user 两个包下都有 ShopController 类，Spring 启动时可能会报 Bean 名称冲突的错误，记得在 @RestController 注解里给它们起个别名，比如 @RestController("adminShopController") )