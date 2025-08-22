package br.com.nttdata.api_gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {

    @Value("${desafio.security.token}")
    private String secretToken;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Pega o cabeçalho "Authorization" da requisição
        List<String> authHeader = exchange.getRequest().getHeaders().get("Authorization");

        // Verifica se o cabeçalho não existe ou não começa com "Bearer "
        if (authHeader == null || authHeader.isEmpty() || !authHeader.get(0).startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // Bloqueia a requisição
        }

        // Extrai o token (removendo o "Bearer ")
        String token = authHeader.get(0).substring(7);

        // Valida o token
        if (!secretToken.equals(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // Bloqueia a requisição
        }

        // Se o token for válido, permite que a requisição continue
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Garante que este filtro rode antes dos filtros de roteamento
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange.anyExchange().permitAll());
        return http.build();
    }
}