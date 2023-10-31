package com.bruno.bookstore.services;

import java.util.Optional;

import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.exceptions.ObjectNotFoundException;
import com.bruno.bookstore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }
}
