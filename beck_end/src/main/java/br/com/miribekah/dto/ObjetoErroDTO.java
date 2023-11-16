package br.com.miribekah.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjetoErroDTO implements Serializable {
    private String error;
    private String cod;
}
