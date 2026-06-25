package net.bifewagyu.gerenciamentoEstoque.dtos;


//Raesposta do site
public record GerencimentoResponse(long id,
                                   String nomeLivro,
                                  String autor,
                                  String editora,
                                  String cartegoria,
                                  String fornecedor,
                                  Double precoCusto,
                                  Double precoVenda,
                                  Double margemLucro,
                                  int estoque,
                                  String estoqueMinimo,
                                  String dataEntrada,
                                  String status) {
}
