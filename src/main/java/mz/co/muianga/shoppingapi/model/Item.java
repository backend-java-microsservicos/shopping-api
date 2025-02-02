package mz.co.muianga.shoppingapi.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import mz.co.muianga.shoppingapi.dto.ItemDTO;

@Embeddable
@Getter
@Setter
public class Item {

    private String productIdentifier;
    private Float price;
    // gets and sets
    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(
                itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}
