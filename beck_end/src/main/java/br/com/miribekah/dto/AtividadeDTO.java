package br.com.miribekah.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class AtividadeDTO implements Serializable {
    private String text;
    private String code;
}
