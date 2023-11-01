package com.bruno.bookstore.services;

import java.util.Optional;

import java.util.List;

import com.bruno.bookstore.DTOs.CategoriaDTO;
import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.exceptions.DataIntegrityViolationException;
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
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Categoria create(Categoria obj){
        obj.setId(null);
        return repository.save(obj);
    }


    public Categoria update(Integer id, CategoriaDTO objDTO) {
        Categoria obj = findById(id);
        obj.setNome(objDTO.getNome());
        obj.setDescricao(objDTO.getDescricao());
        return repository.save(obj);
    }


    public void delete(Integer id) {
        findById(id);
        try{
            repository.deleteById(id);
        }catch (org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(
                    "Categoria não pode ser deletado! Possui livros associados.");
        }

    }
}
