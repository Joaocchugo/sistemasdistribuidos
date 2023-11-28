package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto") /** Caminho inicial dos métodos do Controller */
@AllArgsConstructor /** Cria um construtor com todos os atributos da classe */
public class ProdutoController {
    private final ProdutoRepository repository;

    @GetMapping
    /**
     * Indica que o método deve ser chamado enviando requisição HTTP GET
     * Só após isso é que o método fica disponível para acesso via HTTP
     * @return
     */

    /** Lista todos os produtos */
    public List<Produto> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    /** Lista produto por id específico */
    public Produto findById(@PathVariable long id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Produto insert(@RequestBody Produto produto){
        return repository.save(produto);
    }
}
