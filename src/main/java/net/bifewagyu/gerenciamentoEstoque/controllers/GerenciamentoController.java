package net.bifewagyu.gerenciamentoEstoque.controllers;


import net.bifewagyu.gerenciamentoEstoque.dtos.GerenciamentoRequest;
import net.bifewagyu.gerenciamentoEstoque.dtos.GerencimentoResponse;
import net.bifewagyu.gerenciamentoEstoque.entity.GerenciamentoEntity;
import net.bifewagyu.gerenciamentoEstoque.service.GerenciamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class GerenciamentoController {



    private final GerenciamentoService gerenciamentoService;


    public GerenciamentoController(GerenciamentoService gerenciamentoService) {
        this.gerenciamentoService = gerenciamentoService;
    }



    @PostMapping("/SalvarLivro")
    public ResponseEntity<GerencimentoResponse> salvarEstoque(@RequestBody @Validated GerenciamentoRequest request) {

        GerencimentoResponse response = gerenciamentoService.salvarestoque(request);


        return  new ResponseEntity<>(response, HttpStatus.CREATED);



    }


    @GetMapping("/Consultar ")
    public ResponseEntity<GerencimentoResponse> consultarEstoque(Long id){


        GerencimentoResponse response = gerenciamentoService.consultarEstoque(id);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }



    @GetMapping("/alllivros")
    public ResponseEntity<List<GerencimentoResponse>> todosEstoque(){




        List<GerencimentoResponse> response = gerenciamentoService.getGerenciamento();



        return ResponseEntity.ok(response);

    }




    @GetMapping("/buscarlivro/{id}")
    public ResponseEntity<GerencimentoResponse> buscarEstoque(@PathVariable Long id){

        GerencimentoResponse response = gerenciamentoService.consultarEstoque(id);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

    }


    @GetMapping("/buscarLivroNome/por-nome")
    public ResponseEntity<List<GerencimentoResponse>> buscarLivroPorNome(@RequestParam String nomeLivro){

        List<GerenciamentoEntity> listaBancoDeDados = gerenciamentoService.buscarLivroNome(nomeLivro);


        if (listaBancoDeDados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return getListResponseEntity(listaBancoDeDados);


    }


    @DeleteMapping("/deletarLivro/{id}")
    public void deletarEstoque(@PathVariable Long id){

        gerenciamentoService.deletarEstoquePorid(id);

    }


   @PatchMapping("/{id}/adicionar")
    public ResponseEntity<List<GerencimentoResponse>> adicionarEstoque(@PathVariable Long id, @RequestParam int quantidade){

        List<GerenciamentoEntity> listabanco = gerenciamentoService.adicionarId(id, quantidade);

       return getListResponseEntity(listabanco);

   }



   @PatchMapping("/{id}/remover")
    public ResponseEntity<List<GerencimentoResponse>> removerEstoque(@PathVariable Long id, @RequestParam int quantidade){
        List<GerenciamentoEntity> listaparada = gerenciamentoService.removerEstoque(id, quantidade);

       return getListResponseEntity(listaparada);

   }

    private ResponseEntity<List<GerencimentoResponse>> getListResponseEntity(List<GerenciamentoEntity> listaparada) {
        List<GerencimentoResponse> listapronta = listaparada.stream().map(entity -> new GerencimentoResponse(


                entity.getId(),
                entity.getNomeLivro(),
                entity.getAutor(),
                entity.getEditora(),
                entity.getCartegoria(),
                entity.getFornecedor(),
                entity.getPrecoCusto(),
                entity.getPrecoVenda(),
                entity.getMargemLucro(),
                entity.getEstoque(),
                entity.getEstoqueMinimo(),
                entity.getDataEntrada(),
                entity.getStatus()

        )).toList();


        return  ResponseEntity.ok(listapronta);
    }


}
