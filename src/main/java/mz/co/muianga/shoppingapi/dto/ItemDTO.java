package mz.co.muianga.shoppingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;

}
