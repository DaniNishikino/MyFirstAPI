package com.example.demo.controller;

import com.example.demo.entities.Categoria;
import com.example.demo.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Listagem de Categorias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar a API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public ResponseEntity<List<Categoria>> getAll() {
        List<Categoria> categorias = categoriaService.getAll();
        if (!categorias.isEmpty()) {
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Coletar um elemento da API por um ID", description = "Listagem de Categorias por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar a API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public ResponseEntity<Categoria> getById(@PathVariable Integer id){
        Categoria categoria = categoriaService.getById(id);
        if (categoria != null){
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @Operation(summary = "Salvar uma nova Categoria", description = "Salvar Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar a API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma Categoria existente", description = "Atualizar Categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar a API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria){
        Categoria categoriaAtualizada = categoriaService.updateCategoria(id, categoria);
        if (categoriaAtualizada != null){
            return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma categoria por um ID fornecido", description = "Deletar categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A requisição foi executada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Requisição Inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar a API"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.")})
    public ResponseEntity<Boolean> deleteCategoria(@PathVariable Integer id){
        if (categoriaService.deleteCategoria(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
    }
}
