package br.com.nttdata.ms_simulador_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // Habilita o uso do OpenFeign para comunicação
public class MsSimuladorPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSimuladorPedidosApplication.class, args);
	}

}