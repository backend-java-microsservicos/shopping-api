package mz.co.muianga.shoppingapi.service;

import lombok.AllArgsConstructor;
import mz.co.muianga.shoppingapi.converter.DTOConverter;
import mz.co.muianga.shoppingapi.model.Shop;
import mz.co.muianga.shoppingapi.repository.ShopRepository;
import mz.co.muianga.shoppingclient.dto.ItemDTO;
import mz.co.muianga.shoppingclient.dto.ProductDTO;
import mz.co.muianga.shoppingclient.dto.ShopDTO;
import mz.co.muianga.shoppingclient.dto.ShopReportDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .map(DTOConverter::convert)
                .toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository
                .findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(DTOConverter::convert)
                .toList();
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository
                .findAllByDateGreaterThan(shopDTO.getDate())
                .stream()
                .map(DTOConverter::convert)
                .toList();
    }

    public ShopDTO findById(long id) {
        Optional<Shop> shop = shopRepository.findById(id);
        return shop.map(DTOConverter::convert).orElse(null);

    }

    public ShopDTO save(ShopDTO shopDTO) {
        if (userService.getUserByCpf(shopDTO.getUserIdentifier()) == null) {
            return null;
        }

        if (!validateProducts(shopDTO.getItems())) {
            return null;
        }
        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce((float) 0, Float::sum));
        Shop shop = DTOConverter.convert(shopDTO);
        shop.setDate(new Date());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    public List<ShopDTO> getShopsByFilter(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo) {
        List<Shop> shops =
                shopRepository
                        .getShopByFilters(dataInicio, dataFim, valorMinimo);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .toList();
    }

    public ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim) {
        return shopRepository
                .getReportByDate(dataInicio, dataFim);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }
}
