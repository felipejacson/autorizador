package br.com.autorizador.model;

import br.com.autorizador.constantes.SexoEnum;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProcedimentoExecucaoId implements Serializable {

	private long procedimentoId;
	private int idade;
	private SexoEnum sexo;

	public ProcedimentoExecucaoId() {
	}

	public long getProcedimentoId() {
		return procedimentoId;
	}

	public void setProcedimentoId(long procedimentoId) {
		this.procedimentoId = procedimentoId;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProcedimentoExecucaoId that = (ProcedimentoExecucaoId) o;
		return procedimentoId == that.procedimentoId &&
				idade == that.idade &&
				sexo == that.sexo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(procedimentoId, idade, sexo);
	}
}
