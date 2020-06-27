package br.com.autorizador.model;

import br.com.autorizador.constantes.AutorizaEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProcedimentoExecucao implements Serializable {

    @EmbeddedId
    private ProcedimentoExecucaoId procedimentoExecucao;

    private AutorizaEnum autoriza;

    public ProcedimentoExecucao() {
        setProcedimentoExecucao(new ProcedimentoExecucaoId());
    }

    public ProcedimentoExecucaoId getProcedimentoExecucao() {
        return procedimentoExecucao;
    }

    public void setProcedimentoExecucao(ProcedimentoExecucaoId procedimentoExecucao) {
        this.procedimentoExecucao = procedimentoExecucao;
    }

    public AutorizaEnum getAutoriza() {
        return autoriza;
    }

    public void setAutoriza(AutorizaEnum autoriza) {
        this.autoriza = autoriza;
    }
}
