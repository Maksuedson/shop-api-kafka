package br.com.shop.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.shop.model.Shop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {

	private String identifier;
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
				.map(ShopItemDTO::convert)
				.toList());
		
		return shopDTO;
	}
	
}
