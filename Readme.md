
# Spring Cloud Netflix 分布式微服务实践

基于美国Netflix公司开源的分布式应用开发框架，
使用Config配置中心，Eureka服务注册发现，Feign服务调用，Zuul微服务网关，Ribbon负载均衡，
Hystrix服务容错保护/断路器，Spring Boot Admin服务管控

### eureka-server 注册中心

启动应用后，打开 http://localhost:8761

可以看到启动的服务

![服务注册中心](image/1.jpg)

### admin-server 服务管控

打开 http://localhost:5033

![admin](image/2.jpg)

![admin](image/3.jpg)

### config-server 配置中心

该服务将git仓库中的配置文件以接口形式提供出去，客户端可以获取并加载配置。

例如打开 http://localhost:3600/eureka/default ，获取eureka配置信息：

```json
{
    "name": "eureka",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": "a716ea609caaa0811387bcc616227b42e6094b26",
    "state": null,
    "propertySources": [
        {
            "name": "https://github.com/yunTerry/spring-cloud-netflix/config-repo/eureka.yml",
            "source": {
                "spring.application.name": "eureka-server",
                "server.port": 8761,
                "eureka.client.register-with-eureka": false,
                "eureka.client.fetch-registry": false,
                "management.security.enabled": false
            }
        }
    ]
}
```

### token-service 服务提供者

token认证服务，提供生成token和验证token的接口

```java
@GetMapping("/token/generate/{uid}")
String getToken(@PathVariable String uid);

@GetMapping("/token/verify")
boolean verify(@RequestParam String token);

```

### user-service 服务消费者

调用用户相关服务的时候，会消费token认证服务，使用Feign调用服务，通过Hystrix提供容错保护

向 http://localhost:4000/user-service/login 发POST请求

登录或注册成功返回token

```json
{
    "code": 0,
    "msg": "login success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3NjVhNWEyYy1hYzhhLTQxNDctYjI2Yy0zNTFlYTA0NjI4OGEiLCJleHAiOjE1MjA1OTMzODF9.NMUjrRk7MsAROuq1OLOUM9WOefgkZmg5_M_mjiSVa0Cz-SunY2ucgxEvOGdKW7U1AVteNVVtD1v_cQlwA_wREQ"
}
```

把token放在请求头，获取用户信息 http://localhost:4000/user-service/user/info

```json
{
    "code": 0,
    "msg": "success",
    "data": {
        "id": "765a5a2c-ac8a-4147-b26c-351ea046288a",
        "name": "aa",
        "sex": "boy",
        "img": "http://img.com/aa32.jpg",
        "age": 20
    }
}
```

### zuul 微服务网关

用serviceId代替URL，实现动态路由

通过Zuul过滤器进行请求鉴权认证，拦截不合法请求，同时也可以实现API限流操作





