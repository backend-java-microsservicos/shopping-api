package mz.co.muianga.shoppingapi.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Item {

    private String productIdentifier;
    private Float price;
}
