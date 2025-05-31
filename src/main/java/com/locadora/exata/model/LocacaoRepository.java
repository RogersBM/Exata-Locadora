package com.locadora.exata.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Integer> {

    List<Locacao> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim);

    List<Locacao> findByDataFimIsNull();
}
