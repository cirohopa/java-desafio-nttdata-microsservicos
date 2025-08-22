package br.com.nttdata.ms_simulador_pedidos.client;

import br.com.nttdata.ms_simulador_pedidos.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "MS-CATALOGO-PRODUTOS")
public interface CatalogoProdutosClient {

    /**
     * Declaração do método para buscar um produto por ID no microsserviço de catálogo.
     * O OpenFeign implementará este método para chamar o endpoint GET /produtos/{id}.
     */
    @GetMapping("/produtos/{id}")
    ResponseEntity<ProductDTO> buscarProdutoPorId(@PathVariable("id") Long id);

    /**
     * Declaração do método para listar todos os produtos no microsserviço de catálogo.
     * O OpenFeign implementará este método para chamar o endpoint GET /produtos.
     */
    @GetMapping("/produtos")
    ResponseEntity<List<ProductDTO>> listarProdutos();
}