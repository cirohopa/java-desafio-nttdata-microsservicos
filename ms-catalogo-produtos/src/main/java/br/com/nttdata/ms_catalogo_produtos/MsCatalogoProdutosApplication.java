package br.com.nttdata.ms_catalogo_produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCatalogoProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCatalogoProdutosApplication.class, args);
	}

}
