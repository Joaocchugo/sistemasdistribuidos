package com.example.demo.controller;

import org.springframework.http.HttpStatusCode;

/** Os getters criados não tem o prefixo get */
public record ErrorMessage (String message, HttpStatusCode status) {

}
