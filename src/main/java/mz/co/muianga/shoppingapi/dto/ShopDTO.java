package mz.co.muianga.shoppingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    private Float total;
    private Date date;
    @NotNull
    private List<ItemDTO> items;

}
