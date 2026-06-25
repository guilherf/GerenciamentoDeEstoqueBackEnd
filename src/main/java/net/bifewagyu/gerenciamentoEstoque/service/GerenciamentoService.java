package net.bifewagyu.gerenciamentoEstoque.service;


import jakarta.transaction.Transactional;
import net.bifewagyu.gerenciamentoEstoque.apiconfig.GerenciamentoConfig;
import net.bifewagyu.gerenciamentoEstoque.dtos.GerenciamentoRequest;
import net.bifewagyu.gerenciamentoEstoque.dtos.GerencimentoResponse;
import net.bifewagyu.gerenciamentoEstoque.entity.GerenciamentoEntity;
import net.bifewagyu.gerenciamentoEstoque.repository.GerenciamentoRepository;
import net.bifewagyu.gerenciamentoEstoque.status.GerenciamentoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class GerenciamentoService {

    @Autowired
    private final GerenciamentoRepository gerenciamentoRepository;

    private final GerenciamentoConfig config;


    public GerenciamentoService(GerenciamentoRepository gerenciamentoRepository, GerenciamentoConfig config) {
        this.gerenciamentoRepository = gerenciamentoRepository;
        this.config = config;
    }



    public List<GerencimentoResponse> getGerenciamento(){

        List<GerenciamentoEntity> getAll = gerenciamentoRepository.findAll();

        return getAll.stream().map(gerenciamento -> new GerencimentoResponse(
                gerenciamento.getId(),
                gerenciamento.getNomeLivro(),
                gerenciamento.getAutor(),
                gerenciamento.getEditora(),
                gerenciamento.getCartegoria(),
                gerenciamento.getFornecedor(),
                gerenciamento.getPrecoCusto(),
                gerenciamento.getPrecoVenda(),
                gerenciamento.getMargemLucro(),
                gerenciamento.getEstoque(),
                gerenciamento.getEstoqueMinimo(),
                gerenciamento.getDataEntrada(),
                gerenciamento.getStatus()
        )).toList();

    }

    public GerencimentoResponse salvarestoque(GerenciamentoRequest request){


        GerenciamentoEntity entity = new GerenciamentoEntity();


        entity.setAutor(request.autor());
        entity.setNomeLivro(request.nomeLivro());
        entity.setEditora(request.editora());
        entity.setCartegoria(request.cartegoria());
        entity.setFornecedor(request.fornecedor());
        entity.setPrecoCusto(request.precoCusto());
        entity.setPrecoVenda(request.precoVenda());
        entity.setMargemLucro(request.margemLucro());
        entity.setEstoque(request.estoque());
        entity.setEstoqueMinimo(request.estoqueMinimo());
        entity.setDataEntrada(LocalDateTime.now().format(config.formatoDataEntrada()));
        entity.setStatus(GerenciamentoStatus.DISPONIVEL.getDescricao());

        GerenciamentoEntity salvar = gerenciamentoRepository.save(entity);




        return new GerencimentoResponse(salvar.getId(),
                salvar.getNomeLivro(),
                salvar.getAutor(),
                salvar.getEditora(),
                salvar.getCartegoria(),
                salvar.getFornecedor(),
                salvar.getPrecoCusto(),
                salvar.getPrecoVenda(),
                salvar.getMargemLucro(),
                salvar.getEstoque(),
                salvar.getEstoqueMinimo(),
                salvar.getDataEntrada(),
                salvar.getStatus());

    }



    public GerencimentoResponse consultarEstoque(Long id){


        GerenciamentoEntity entity = gerenciamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado " + id));


        return new GerencimentoResponse(entity.getId(),
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
                entity.getStatus());


    }


    public void deletarEstoquePorid(Long id){
        gerenciamentoRepository.deleteById(id);
    }



    public List<GerenciamentoEntity> buscarLivroNome(String nomeLivro){


        if (nomeLivro == null || nomeLivro.trim().isEmpty()){

            return List.of();

        }

        return gerenciamentoRepository.findBynomeLivro(nomeLivro);


    }
    @Transactional
    public List<GerenciamentoEntity> adicionarId(Long id, int quantidade) {

        GerenciamentoEntity entity = gerenciamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto Não encontrado " + id));

        entity.adicionarEstoque(quantidade);

        return Collections.singletonList(gerenciamentoRepository.save(entity));

    }


    @Transactional
    public List<GerenciamentoEntity> removerEstoque(Long id, int quantidade) {

        GerenciamentoEntity entity = gerenciamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));


        entity.removerEstoque(quantidade);

        return Collections.singletonList(gerenciamentoRepository.save(entity));

    }







}
