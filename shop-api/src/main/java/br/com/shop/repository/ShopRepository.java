package br.com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.shop.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{

	
}
