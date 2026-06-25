package net.bifewagyu.gerenciamentoEstoque.repository;


import net.bifewagyu.gerenciamentoEstoque.entity.GerenciamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GerenciamentoRepository extends JpaRepository<GerenciamentoEntity, Long> {


            List<GerenciamentoEntity> findBynomeLivro(String nomeLivro);



}
