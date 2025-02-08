package mz.co.muianga.shoppingapi.controller;

import jakarta.validation.Valid;
import mz.co.muianga.shoppingapi.service.ShopService;
import mz.co.muianga.shoppingclient.dto.ShopDTO;
import mz.co.muianga.shoppingclient.dto.ShopReportDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    private final ShopService shopService;

    public ShoppingController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<ShopDTO> getShops() {
        List<ShopDTO> produtos = shopService.getAll();
        return produtos;
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(
            @PathVariable String userIdentifier) {
        List<ShopDTO> produtos =
                shopService.getByUser(userIdentifier);
        return produtos;
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> produtos = shopService.getByDate(shopDTO);
        return produtos;
    }
    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping
    public ShopDTO newShop(@RequestHeader(name = "key") String key,
            @Valid @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam(name = "dataInicio")
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                          @RequestParam(name = "dataFim", required = false)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
                                          @RequestParam(name = "valorMinimo", required = false)
                                          Float valorMinimo) {

        return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "dataInicio")
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim")
            @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {

        return shopService.getReportByDate(dataInicio, dataFim);
    }
}
