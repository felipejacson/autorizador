package br.com.autorizador.repository;

import br.com.autorizador.constantes.AutorizaEnum;
import br.com.autorizador.constantes.SexoEnum;
import br.com.autorizador.model.ProcedimentoExecucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcedimentoExecucaoRepository extends JpaRepository<ProcedimentoExecucao, Long> {

	Optional<ProcedimentoExecucao> findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_Sexo(long procedimentoId, int idade, SexoEnum sexo);

	Optional<ProcedimentoExecucao> findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_SexoAndAutoriza(long procedimentoId, int idade, SexoEnum sexo, AutorizaEnum autoriza);

}