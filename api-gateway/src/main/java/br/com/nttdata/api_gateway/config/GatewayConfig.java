package br.com.nttdata.api_gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    //Logger para ajudar a depurar
    private static final Logger log = LoggerFactory.getLogger(GatewayConfig.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ms-catalogo-produtos", r -> r.path("/produtos/**")
                        .uri("lb://MS-CATALOGO-PRODUTOS"))
                .route("ms-simulador-pedidos", r -> r.path("/pedidos/**")
                        .uri("lb://MS-SIMULADOR-PEDIDOS"))
                .build();
    }

    /**
     * Este Bean cria um filtro global que será executado para TODA requisição
     * que passar pelo Gateway. Ele irá imprimir o caminho da requisição no console.
     */
    @Bean
    public GlobalFilter logFilter() {
        return (exchange, chain) -> {
            log.info(">>> REQUISIÇÃO RECEBIDA: {}", exchange.getRequest().getPath());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("<<< RESPOSTA ENVIADA: {}", exchange.getResponse().getStatusCode());
            }));
        };
    }
}