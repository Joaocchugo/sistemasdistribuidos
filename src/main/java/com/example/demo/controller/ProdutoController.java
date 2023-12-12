package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.net.URI;
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
    public ResponseEntity<List<Produto>> findAll(){

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    /** Lista um produto específico pelo ID */
    public ResponseEntity<Produto> findById(@PathVariable long id) {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)

                /** Forma que não devolve nenhum tipo de mensagem */
                //.orElse(ResponseEntity.notFound().build());

                /** Forma que devolve algum tipo de mensagem */
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }

    @PostMapping
    /** Inserir um novo produto */
    public ResponseEntity<Produto> insert(@Valid @RequestBody Produto produto){
        final var newProduto = repository.save(produto);
        final var location = URI.create("/produto/" + newProduto.getId());
        return ResponseEntity.created(location)
                .body(newProduto);
    }

    @PutMapping("{id}")
    /** Alterar produto por ID específico */
    public ResponseEntity<Produto> update(@PathVariable long id, @RequestBody Produto produto){
        final var msg = "O ID informado nao coincide com o ID do objeto passado";
        if (id!= produto.getId()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "msg");
        }
        return ResponseEntity.ok(repository.save(produto));
    }

    @DeleteMapping("/{id}")
    /** Deleta produto por ID específico */
    public ResponseEntity delete(@PathVariable long id){
        repository.findAndDelete(id);
        return ResponseEntity.noContent().build();
    }

    /** DOCUMENTOS
     * https://www.openapis.org/
     * https://swagger.io/
     * http://localhost:8080/swagger-ui/index.html
     * */
}
