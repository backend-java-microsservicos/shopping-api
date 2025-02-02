package mz.co.muianga.shoppingapi.service;

import mz.co.muianga.shoppingapi.converter.DTOConverter;
import mz.co.muianga.shoppingapi.dto.ItemDTO;
import mz.co.muianga.shoppingapi.dto.ShopDTO;
import mz.co.muianga.shoppingapi.dto.ShopReportDTO;
import mz.co.muianga.shoppingapi.model.Shop;
import mz.co.muianga.shoppingapi.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

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
}
