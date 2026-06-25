package net.bifewagyu.gerenciamentoEstoque.status;

import lombok.Getter;

@Getter
public enum GerenciamentoStatus {


    INDISPONIVEL("Livro Indisponivel"),
    DISPONIVEL("Livro Disponivel"),
    EXCLUIDO("REMOVIDO DO SISTEMA")

    ;
    private String descricao;

    GerenciamentoStatus(String s) {

        this.descricao = s;

    }
}
