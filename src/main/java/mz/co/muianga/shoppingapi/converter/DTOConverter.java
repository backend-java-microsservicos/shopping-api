package mz.co.muianga.shoppingapi.converter;

import mz.co.muianga.shoppingapi.model.Item;
import mz.co.muianga.shoppingapi.model.Shop;
import mz.co.muianga.shoppingclient.dto.ItemDTO;
import mz.co.muianga.shoppingclient.dto.ShopDTO;

public class DTOConverter {

    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(
                item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop.getItems().stream()
                .map(DTOConverter::convert)
                .toList());
        return shopDTO;
    }

    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(
                itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    public static Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setTotal(shopDTO.getTotal());
        shop.setDate(shopDTO.getDate());
        shop.setItems(shopDTO
                .getItems()
                .stream()
                .map(DTOConverter::convert)
                .toList());
        return shop;
    }
}
