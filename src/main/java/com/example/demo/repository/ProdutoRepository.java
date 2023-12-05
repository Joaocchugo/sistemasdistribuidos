package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
        default boolean findAndDelete (long id){
            return findById(id)
                    .map(prod -> delete(prod.getId()))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

    default boolean delete (long id){
        deleteById(id);
        return true;
    }
}
