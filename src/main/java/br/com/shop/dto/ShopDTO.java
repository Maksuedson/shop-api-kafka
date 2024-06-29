package br.com.shop.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.shop.model.Shop;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShopDTO {

	private String Identifier;
	private String status;
	private LocalDate dateShop;
	private List<ShopItemDTO> items = new ArrayList<>();;
	
	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDTO = new ShopDTO();
		shopDTO.setIdentifier(shop.getIdentifier());
		shopDTO.setDateShop(shop.getDateShop());
		shopDTO.setStatus(shop.getStatus());
		shopDTO.setItems(shop.getItems()
				.stream()
				.map(i -> ShopItemDTO.convert(i))
				.collect(Collectors.toList()));
		
		return shopDTO;
	}
	
}
