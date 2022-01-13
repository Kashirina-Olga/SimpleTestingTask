package com.test.context

import com.test.client.HttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class SimpleContext extends AbstractContext  {

    SimpleContext(){
        super(["simple-test","mail"])
    }

    @Bean
    @Value('${test.host.url}')
    HttpClient testHttpClient(String url){
        new HttpClient(url)
    }

}
