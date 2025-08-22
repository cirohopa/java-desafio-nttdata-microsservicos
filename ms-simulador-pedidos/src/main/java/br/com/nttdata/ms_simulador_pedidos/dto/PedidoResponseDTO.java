package br.com.nttdata.ms_simulador_pedidos.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private List<ProductDTO> produtos;
    private Double valorTotal;
}