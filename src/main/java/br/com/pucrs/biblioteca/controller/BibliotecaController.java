package br.com.pucrs.biblioteca.controller;

import br.com.pucrs.biblioteca.bean.Livro;
import br.com.pucrs.biblioteca.service.BibliotecaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BibliotecaController {

    private BibliotecaService service;

    public BibliotecaController(BibliotecaService service) {
        this.service = service;
    }

    // rota que faz um patch(httpMethod). Deve ser inserido como parametro o usuario que está retirando
    // e o nome do livro que deseja retirar
    // retorna se foi possivel ou não fazer a chamada do metodo do serviço
    @PatchMapping(value = "/livro")
    public ResponseEntity<Object> retirarLivro(
            @RequestParam() String usuario,
            @RequestParam() String nomeDolivro
            ) {

        return ResponseEntity.ok(service.retirarLivro(usuario, nomeDolivro));
    }

    // rota que faz um put(httpMethod). Deve ser inserido como parametro o usuario que está retirando
    // e o nome do livro que deseja devolver
    // retorna se foi possivel ou não fazer a chamada do metodo do serviço
    @PutMapping(value = "/livro")
    public ResponseEntity<Object> devolverLivro(
            @RequestParam() String usuario,
            @RequestParam() String nomeDolivro
    ) {

        return ResponseEntity.ok(service.devolverLivro(usuario, nomeDolivro));
    }

    // rota que faz um post(httpMethod). Deve ser inserido como parametro a entidade LIVRO como um JSON com os atributos necessarios
    // como titulo, ano e autor.
    // retorna se foi possivel ou não fazer a chamada do metodo do serviço
    @PostMapping(value = "/doar/livro")
    public ResponseEntity<Object> devolverLivro(
            @RequestParam() Livro novoLivro
    ) {
        return ResponseEntity.ok(service.doarNovoLivro(novoLivro));
    }
}
