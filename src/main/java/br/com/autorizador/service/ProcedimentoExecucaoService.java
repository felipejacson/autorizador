package br.com.autorizador.service;

import br.com.autorizador.model.ProcedimentoExecucao;
import br.com.autorizador.repository.ProcedimentoExecucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class ProcedimentoExecucaoService {

	@Autowired
	private ProcedimentoExecucaoRepository rep;

	public Optional<ProcedimentoExecucao> findByAllAttributes(@NotNull ProcedimentoExecucao procedimento) {
		return rep.findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_SexoAndAutoriza(
				procedimento.getProcedimentoExecucao().getProcedimentoId(),
				procedimento.getProcedimentoExecucao().getIdade(),
				procedimento.getProcedimentoExecucao().getSexo(),
				procedimento.getAutoriza());
	}

	public Optional<ProcedimentoExecucao> findByProcedimentoIdadeSexo(@NotNull ProcedimentoExecucao procedimento) {
		return rep.findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_Sexo(
				procedimento.getProcedimentoExecucao().getProcedimentoId(),
				procedimento.getProcedimentoExecucao().getIdade(),
				procedimento.getProcedimentoExecucao().getSexo());
	}

	public Optional<ProcedimentoExecucao> save(@NotNull ProcedimentoExecucao procedimento) {
		Optional<ProcedimentoExecucao> u = findByAllAttributes(procedimento);
		if (!u.isPresent()) {
			return Optional.of(rep.save(procedimento));
		}
		return Optional.empty();
	}

}
