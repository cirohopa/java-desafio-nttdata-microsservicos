package br.com.nttdata.ms_simulador_pedidos.dto;

import lombok.Data;

/**
 * DTO para representar os dados de um produto recebidos do microsserviço de catálogo.
 */
@Data // Anotação do Lombok que cria getters, setters, toString, etc.
public class ProductDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
}