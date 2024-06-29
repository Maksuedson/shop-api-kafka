package br.com.shop.model;

import br.com.shop.dto.ShopItemDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShopItem {

	private Long id;
	private String productIdentifier;
	private Integer amount;
	private Float price;
	
	@ManyToOne
	@JoinColumn(name = "shop_id")
	private Shop shop;
	
	public static ShopItem convert(ShopItemDTO shopItemDTO) {
		ShopItem shopItem = new ShopItem();
		shopItem.setProductIdentifier(shopItemDTO.getProductIdentifier());
		shopItem.setAmount(shopItemDTO.getAmount());
		shopItem.setPrice(shopItemDTO.getPrice());
		
		return shopItem;
	}
	
	
}
