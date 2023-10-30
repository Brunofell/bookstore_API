package com.bruno.bookstore.services;

import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.domain.Livro;
import com.bruno.bookstore.repository.CategoriaRepository;
import com.bruno.bookstore.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados(){
        Categoria cat1 = new Categoria(null, "informática", "Livros de TI");
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Ficção Científica");
        Categoria cat3 = new Categoria(null, "Biografia", "Biografia");

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Loren ipsum", cat1);
        Livro l2 = new Livro(null, "Java", "Robert Martin", "Loren ipsum", cat1);
        Livro l3 = new Livro(null, "Alan Turing", "Robert Martin", "Loren ipsum", cat3);
        Livro l4 = new Livro(null, "I, Robot", "Robert Martin", "Loren ipsum", cat2);

        cat1.getLivros().addAll(Arrays.asList(l1));

        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        this.livroRepository.saveAll(Arrays.asList(l1,l2, l3, l4));
    }
}
