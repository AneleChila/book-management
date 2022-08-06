package com.payu.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Anele Chila
 */
@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date", nullable = false)
    private Date createDate;

    @Column(name = "last_modified_date", nullable = false)
    private Date lastModifiedDate;
}
