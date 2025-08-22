package br.com.nttdata.ms_simulador_pedidos.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoRequestDTO {
    private List<Long> idsProdutos;
}