package br.com.shop.model;

import java.time.LocalDate;
import java.util.List;

import br.com.shop.dto.ShopDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String identifier;
	private String status;
	
	@Column(name = "date_shop")
	private LocalDate dateShop;
	
	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy = "shop")
	private List<ShopItem> items;
	
	public static Shop convert(ShopDTO shopDTO) {
		Shop shop = new Shop();
		shop.setIdentifier(shopDTO.getIdentifier());
		shop.setStatus(shopDTO.getStatus());
		shop.setDateShop(shopDTO.getDateShop());
		shop.setItems(shopDTO
				.getItems()
				.stream()
				.map(ShopItem::convert)
				.toList()
				);
		
		return shop;
	}
	
}
