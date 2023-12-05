package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    /** Deleta produto por id específico */
    public void delete(@PathVariable long id){
       /**Programação funcional*/
        repository.findAndDelete(id);
    }

    @PutMapping("{id}")
    /** Alterar produto por id específico */
    public Produto update(@PathVariable long id, @RequestBody Produto produto){
        final var msg = "O ID informado nao coincide com o ID do objeto passado";
        if (id!= produto.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "msg");
        }
        return repository.save(produto);
    }

    @PostMapping
    public Produto insert(@RequestBody Produto produto){

        return repository.save(produto);
    }

    /**
     * Conjunto de documentações
     * https://www.openapis.org/
     * https://swagger.io/
     * http://localhost:8080/swagger-ui/index.html
     * */
}
