package br.com.pucrs.biblioteca.service;

import br.com.pucrs.biblioteca.bean.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BibliotecaService {

    //Inicia variavel listaDeLivros que é uma lista da entidade LIVRO
    List<Livro> listaDeLivros;

    public BibliotecaService(List<Livro> listaDeLivros) {
        // instancializa a variavel listaDeLivros e define um novo array pra ela (se torna um array vazio)
        this.listaDeLivros = new ArrayList<>();

        // Popula o array da listaDeLivros
        listaDeLivros.add(Livro
                .builder()
                .numero(00001)
                .ano(2017)
                .autor("Alexandro Aolchique")
                .status("Disponivel")
                .titulo("Como fazer sentido e bater o martelo")
                .build());

        listaDeLivros.add(Livro
                .builder()
                .numero(00002)
                .ano(2015)
                .autor("Chimamanda Ngozi Adichie")
                .status("Disponivel")
                .titulo("Sejamos todos feministas")
                .build());


        listaDeLivros.add(Livro
                .builder()
                .numero(00003)
                .ano(2010)
                .autor("Hortência Marcari")
                .status("Disponivel")
                .titulo("Basquete 101")
                .build());



    }

    // Método que filtra o livro pelo titulo
    public Optional<Livro> buscarLivros(String titulo) {
        return listaDeLivros.stream()
                .filter(livro -> Objects.equals(livro.getTitulo(), titulo))
                .findFirst();
    }

    // Metodo que filtra o livro, checa se ele está disponivel, muda seu status e adiciona o usuario que retirou o livro;
    // Retorna um booleano se a função funciona (true) ou se não funciona (erro)
    public AtomicBoolean retirarLivro(String usuario, String nomeDolivro) {
       AtomicBoolean sucesso = new AtomicBoolean(true);
        buscarLivros(nomeDolivro).ifPresentOrElse(livro -> {
            if (livro.getStatus() == "Disponivel") {
                livro.setUsuario(usuario);
                livro.setStatus("Indisponivel");
                sucesso.set(true);
            } else {
                System.out.println("não foi possivel alugar este livro!");
                sucesso.set(false);
            }
        },  () -> {
            System.out.println("não foi possivel alugar este livro!");
            sucesso.set(false);
        });

        return sucesso;
    }

    // Metodo que filtra o livro, muda seu status e remove o usuario que retirou o livro;
    // Retorna um booleano se a função funciona (true) ou se não funciona (erro)
    public AtomicBoolean devolverLivro(String usuario, String nomeDolivro) {
        // um boooleano que pode ser setado, é dinamico = atomicBoolean
        AtomicBoolean sucesso = new AtomicBoolean(true);

        //acessa a função buscarLivros que retorna um opcional.
        // se esse opcional estiver presente (ifpresent) ele remove o nome do usuario e bota o status do livro pra disponivel
        buscarLivros(nomeDolivro).ifPresentOrElse(livro -> {
                livro.setUsuario(null);
                livro.setStatus("Disponivel");
        },  () -> {
            //or else, se não estiver prsente, ele printa que não foi possivel devolver e faz o sucesso retornar um false
            System.out.println("não foi possivel devolver este livro!");
            sucesso.set(false);
        });

        return sucesso;

    }

    // Metodo que adiciona um novo livro a variavel ListadeLivros;
    // Retorna um booleano se a função funciona (true) ou se não funciona (erro)
    public boolean doarNovoLivro(Livro novoLivro) {
        return listaDeLivros.add(Livro.builder()
                .titulo(novoLivro.getTitulo())
                .autor(novoLivro.getAutor())
                .ano(novoLivro.getAno())
                .status("Disponivel")
                .build());

    }
}
