package br.com.nttdata.ms_catalogo_produtos.controller;

import br.com.nttdata.ms_catalogo_produtos.domain.entity.Product;
import br.com.nttdata.ms_catalogo_produtos.domain.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductRepository productRepository;

    // Usando injeção de dependência via construtor (boa prática)
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Endpoint para criar um novo produto.
     * Mapeado para: POST /produtos
     */
    @PostMapping
    public ResponseEntity<Product> criarProduto(@RequestBody Product produto) {
        Product produtoSalvo = productRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    /**
     * Endpoint para listar todos os produtos.
     * Mapeado para: GET /produtos
     */
    @GetMapping
    public ResponseEntity<List<Product>> listarProdutos() {
        List<Product> produtos = productRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Endpoint para buscar um produto específico pelo seu ID.
     * Mapeado para: GET /produtos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarProdutoPorId(@PathVariable Long id) {
        Optional<Product> optionalProduto = productRepository.findById(id);

        if (optionalProduto.isPresent()) {
            return ResponseEntity.ok(optionalProduto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}