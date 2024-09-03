package br.com.validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.validator.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByIdentifier(String identifier);

}
