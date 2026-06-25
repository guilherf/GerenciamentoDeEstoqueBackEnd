package net.bifewagyu.gerenciamentoEstoque.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

//Envia
public record GerenciamentoRequest(@NotBlank(message = "Necessito do nome do livro") String nomeLivro,
                                    String autor,
                                     String editora,
                                   String cartegoria,
                                    String fornecedor,
                                    Double precoCusto,
                                     Double precoVenda,
                                    Double margemLucro,
                                     int estoque,
                                    String estoqueMinimo, String status ) {
}
