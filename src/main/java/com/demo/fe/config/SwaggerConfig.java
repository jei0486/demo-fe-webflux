//package com.demo.fe.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.reactivestreams.Publisher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.config.EnableWebFlux;
//import org.springframework.web.reactive.config.ResourceHandlerRegistry;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@EnableWebFlux
//@Configuration
//public class SwaggerConfig implements WebFluxConfigurer {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .genericModelSubstitutes( Mono.class, Flux.class, Publisher.class)
//                .select()
//                .paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .build();
//    }
//
//    /* swagger-ui 페이지 연결 핸들러 설정 */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry
//                .addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry
//                .addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        WebFluxConfigurer.super.addResourceHandlers(registry);
//    }
//
//}
