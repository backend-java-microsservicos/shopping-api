package mz.co.muianga.shoppingapi.repository;

import mz.co.muianga.shoppingapi.dto.ShopReportDTO;
import mz.co.muianga.shoppingapi.model.Shop;

import java.util.Date;
import java.util.List;

public interface ReportRepository {
    List<Shop> getShopByFilters(
            Date dataInicio,
            Date dataFim,
            Float valorMinimo);

    ShopReportDTO getReportByDate(
            Date dataInicio,
            Date dataFim);
}
