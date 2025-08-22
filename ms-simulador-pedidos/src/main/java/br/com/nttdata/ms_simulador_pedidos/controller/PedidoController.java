package br.com.nttdata.ms_simulador_pedidos.controller;

import br.com.nttdata.ms_simulador_pedidos.client.CatalogoProdutosClient;
import br.com.nttdata.ms_simulador_pedidos.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final CatalogoProdutosClient catalogoProdutosClient;

    // Injetando Feign Client via construtor
    public PedidoController(CatalogoProdutosClient catalogoProdutosClient) {
        this.catalogoProdutosClient = catalogoProdutosClient;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> simularPedido(@RequestBody PedidoRequestDTO request) {

        List<ProductDTO> produtosDoPedido = new ArrayList<>();
        double valorTotal = 0.0;

        // Itera sobre cada ID de produto recebido na requisição
        for (Long produtoId : request.getIdsProdutos()) {
            try {
                // USA O FEIGN CLIENT para chamar o microsserviço de catálogo
                ResponseEntity<ProductDTO> responseProduto = catalogoProdutosClient.buscarProdutoPorId(produtoId);

                if (responseProduto.getStatusCode().is2xxSuccessful() && responseProduto.getBody() != null) {
                    ProductDTO produto = responseProduto.getBody();
                    produtosDoPedido.add(produto);
                    valorTotal += produto.getPreco();
                }
            } catch (Exception e) {
                // Loga um aviso se um produto não for encontrado, mas continua o processo
                System.out.println("Aviso: Produto com ID " + produtoId + " não encontrado ou serviço indisponível.");
            }
        }

        // Monta o objeto de resposta
        PedidoResponseDTO responseDTO = new PedidoResponseDTO();
        responseDTO.setProdutos(produtosDoPedido);
        responseDTO.setValorTotal(valorTotal);

        return ResponseEntity.ok(responseDTO);
    }
}