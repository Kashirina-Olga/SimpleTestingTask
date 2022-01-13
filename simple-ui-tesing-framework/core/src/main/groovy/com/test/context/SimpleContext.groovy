package com.test.context

import org.springframework.context.annotation.Bean


class SimpleContext extends AbstractContext {

    SimpleContext() {
        super(["simple-test"])
    }

    @Bean
    def init() {}

}
