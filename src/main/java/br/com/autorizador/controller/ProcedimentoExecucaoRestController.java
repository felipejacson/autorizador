package br.com.autorizador.controller;

import br.com.autorizador.constantes.AutorizaEnum;
import br.com.autorizador.constantes.SexoEnum;
import br.com.autorizador.model.ProcedimentoExecucao;
import br.com.autorizador.service.ProcedimentoExecucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/autorizador")
public class ProcedimentoExecucaoRestController {

    @Autowired
    private ProcedimentoExecucaoService service;

    @GetMapping(path = "/procedimento/{procedimentoId}/idade/{idade}/sexo/{sexo}")
    public ResponseEntity getAutorizacao(@PathVariable long procedimentoId, @PathVariable int idade, @PathVariable SexoEnum sexo) {
        ProcedimentoExecucao p = new ProcedimentoExecucao();
        p.getProcedimentoExecucao().setProcedimentoId(procedimentoId);
        p.getProcedimentoExecucao().setIdade(idade);
        p.getProcedimentoExecucao().setSexo(sexo);
        Optional<ProcedimentoExecucao> opt = service.findByProcedimentoIdadeSexo(p);
        if (opt.isPresent()) {
            if(opt.get().getAutoriza() == AutorizaEnum.SIM) {
                return ResponseEntity.ok(AutorizaEnum.SIM.toString());
            } else {
                return ResponseEntity.ok(AutorizaEnum.NAO.toString());
            }
        } else {
            return ResponseEntity.ok(AutorizaEnum.NAO.toString());
        }
    }

    @PostMapping(path = "/cadastro/procedimento/{procedimentoId}/idade/{idade}/sexo/{sexo}/autoriza/{autoriza}")
    public ResponseEntity findByAttribute(@PathVariable long procedimentoId, @PathVariable int idade, @PathVariable SexoEnum sexo, @PathVariable AutorizaEnum autoriza) {
        ProcedimentoExecucao p = new ProcedimentoExecucao();
        p.getProcedimentoExecucao().setProcedimentoId(procedimentoId);
        p.getProcedimentoExecucao().setIdade(idade);
        p.getProcedimentoExecucao().setSexo(sexo);
        p.setAutoriza(autoriza);

        Optional<ProcedimentoExecucao> opt = service.findByProcedimentoIdadeSexo(p);
        if (!opt.isPresent()) {
            opt = service.save(p);
            if (opt.isPresent()) {
                return ResponseEntity.ok(HttpStatus.OK.toString());
            } else {
                return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.toString());
            }
        } else {
            return ResponseEntity.badRequest().body("Dados já existentes");
        }
    }

}
