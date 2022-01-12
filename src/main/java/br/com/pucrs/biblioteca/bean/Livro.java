package br.com.pucrs.biblioteca.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class Livro {

    private String id;

    private Integer numero;

    private String titulo;

    private String autor;

    private Integer ano;

    private String status;

    private String usuario;

}
