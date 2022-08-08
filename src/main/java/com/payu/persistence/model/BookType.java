package com.payu.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Anele Chila
 */
@AllArgsConstructor
@Getter
public enum BookType {

    EBook("eBook"),
    SoftCover("Soft Cover"),
    HardCover("Hard Cover");

    private String type;
}
