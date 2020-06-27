package br.com.autorizador.repository;

import br.com.autorizador.constantes.AutorizaEnum;
import br.com.autorizador.constantes.SexoEnum;
import br.com.autorizador.model.ProcedimentoExecucao;
import br.com.autorizador.utils.H2Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2Config.class)
public class ProcedimentoExecucaoRepositoryTest {

    @Autowired
    private ProcedimentoExecucaoRepository repository;

    @Test
    public void semDadosCadastradosNoBdOuProcedimentoNaoEncontrado() {
        Optional<ProcedimentoExecucao> procedimento = repository.findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_Sexo(
                1111, 99, SexoEnum.M);
        assertThat(procedimento.isPresent()).isEqualTo(false);
    }

    @Test
    public void insereEVerificaSeExisteOProcedimento() {
        ProcedimentoExecucao p = new ProcedimentoExecucao();
        p.getProcedimentoExecucao().setProcedimentoId(1234);
        p.getProcedimentoExecucao().setIdade(10);
        p.getProcedimentoExecucao().setSexo(SexoEnum.F);
        p.setAutoriza(AutorizaEnum.SIM);
        repository.save(p);

        Optional<ProcedimentoExecucao> procedimento = repository.findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_Sexo(
                1234, 10, SexoEnum.F);

        assertThat(procedimento.isPresent()).isEqualTo(true);
        assertThat(procedimento.get().getProcedimentoExecucao().getProcedimentoId()).isEqualTo(1234);
    }

    @Test
    public void consultandoUmProcedimento() {
        Optional<ProcedimentoExecucao> procedimento = repository.findByProcedimentoExecucao_ProcedimentoIdAndProcedimentoExecucao_IdadeAndProcedimentoExecucao_SexoAndAutoriza(
                1234, 10, SexoEnum.F, AutorizaEnum.SIM);

        assertThat(procedimento.isPresent()).isEqualTo(true);
        assertThat(procedimento.get().getProcedimentoExecucao().getProcedimentoId()).isEqualTo(1234);
    }

}
