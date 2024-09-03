package br.com.validator.events;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.validator.dto.ShopDTO;
import br.com.validator.dto.ShopItemDTO;
import br.com.validator.entity.Product;
import br.com.validator.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

	private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
	private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
	private final ProductRepository productRepository;
	private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

	@KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
	public void listenShopTopic(ShopDTO shopDTO) {
		try {
			log.info("Compra recebida no tópico: {}.", shopDTO.getIdentifier());

			boolean sucess = true;
			for (ShopItemDTO item : shopDTO.getItems()) {
				Product product = productRepository.findByIdentifier(item.getProductIdentifier());

				if (!isValidShop(item, product)) {
					shopError(shopDTO);
					sucess = false;
					break;
				}
			}
			if (sucess) {
				shopSucess(shopDTO);
			}
		} catch (Exception e) {
			log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		}
	}

	//valida se a compra possui algum erro
	private boolean isValidShop(ShopItemDTO item, Product product) {
		return product != null && product.getAmount() >= item.getAmount();
	}

	//Envia uma mensagem para o Kafka indicando erro na compra
	private void shopError(ShopDTO shopDTO) {
		log.error("Erro no processamento da compra {}", shopDTO.getIdentifier());
		shopDTO.setStatus("ERROR");
		kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
	}

	//Envia uma mensagem para o Kafka indicando sucesso na compra
	private void shopSucess(ShopDTO shopDTO) {
		log.error("Compra {} efetuada com sucesso. ", shopDTO.getIdentifier());
		shopDTO.setStatus("SUCESS");
		kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
	}

}
