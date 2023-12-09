package br.com.miribekah.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class BillingDTO implements Serializable {

    private Boolean free;
    private Boolean database;
}
