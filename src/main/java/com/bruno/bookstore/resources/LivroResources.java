package com.bruno.bookstore.resources;

import com.bruno.bookstore.DTOs.CategoriaDTO;
import com.bruno.bookstore.DTOs.LivroDTO;
import com.bruno.bookstore.domain.Categoria;
import com.bruno.bookstore.domain.Livro;
import com.bruno.bookstore.services.CategoriaService;
import com.bruno.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros") //value =
public class LivroResources {
    @Autowired
    private LivroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // So vai achar livros pela categoria, omite o autor e texto: localhost:8080/livros?categoria=1
    @GetMapping // PRO PADRAO DTO.
    public ResponseEntity<List<LivroDTO>> findAll(
            @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat){
        List<Livro> list = service.findAll(id_cat);
        List<LivroDTO> listDTO = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Livro> updatePatch(@PathVariable Integer id, @RequestBody Livro obj){
        Livro newObj = service.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @PostMapping
    public ResponseEntity<Livro> create(
            @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat, @RequestBody Livro obj){
        Livro newObj = service.create(id_cat, obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/livros/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
