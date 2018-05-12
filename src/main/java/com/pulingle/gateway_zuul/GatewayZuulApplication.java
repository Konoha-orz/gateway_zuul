package com.pulingle.gateway_zuul;

import com.pulingle.gateway_zuul.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class GatewayZuulApplication {

	@Bean
	public TokenFilter preRequestFilter(){
		return new TokenFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
}
