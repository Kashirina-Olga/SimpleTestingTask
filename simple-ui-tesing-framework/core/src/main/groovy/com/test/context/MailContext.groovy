package com.test.context
import org.springframework.context.annotation.Bean

class MailContext extends AbstractContext {

    MailContext() {
        super(["mail-test"])
    }

//    @Bean
//    @Value('${user.management.gateway.url}')
//    HttpClient userManagementDefaultHttpClient(String url) {
//        new HttpClient(url)
//    }

    @Bean
    def init() {}

}

