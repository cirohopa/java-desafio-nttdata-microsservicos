package br.com.nttdata.ms_catalogo_produtos.domain.repository;

import br.com.nttdata.ms_catalogo_produtos.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}