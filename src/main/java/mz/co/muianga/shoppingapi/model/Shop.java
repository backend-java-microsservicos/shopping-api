package mz.co.muianga.shoppingapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mz.co.muianga.shoppingapi.dto.ShopDTO;

import java.util.Date;
import java.util.List;

@Entity(name = "shop")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userIdentifier;
    private float total;
    private Date date;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item",
            joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public static Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setTotal(shopDTO.getTotal());
        shop.setDate(shopDTO.getDate());
        shop.setItems(shopDTO
                .getItems()
                .stream()
                .map(Item::convert)
                .toList());
        return shop;
    }
}
