package net.bifewagyu.gerenciamentoEstoque.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "livro_estoques")
@Data
public class GerenciamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String nomeLivro;

    @Column
    private String autor;

    @Column
    private String editora;

    @Column
    private String cartegoria;

    @Column
    private String fornecedor;

    @Column
    private Double precoCusto;

    @Column
    private Double precoVenda;

    @Column
    private Double margemLucro;

    @Column
    private int estoque;

    @Column
    private String estoqueMinimo;

    @Column
    private String dataEntrada;

    @Column
    private String status;



    public void adicionarEstoque(int quantidade) {
        this.estoque += quantidade;
    }


    public void removerEstoque(int quantidade) {
        if (this.estoque - quantidade < 0) {
            throw new IllegalArgumentException("Estoque insuficiente para a operação");
        }

        this.estoque -= quantidade;
    }









}
