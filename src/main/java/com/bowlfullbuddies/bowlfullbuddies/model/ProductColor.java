package com.bowlfullbuddies.bowlfullbuddies.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ProductColor {

    private String name; // e.g. "Red"
    private String code; // e.g. "#FF0000"
}

