package br.com.shop.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.shop.dto.ShopDTO;
import br.com.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

	private final ShopRepository shopRepository;

	@GetMapping
	public List<ShopDTO> getShop(){
		return shopRepository
				.findAll()
				.stream()
				.map(shop -> ShopDTO.convert(shop))
				.collect(Collectors.toList());
	}
	
}
