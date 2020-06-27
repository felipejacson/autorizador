package br.com.autorizador.controller;

import br.com.autorizador.constantes.AutorizaEnum;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcedimentoExecucaoRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    void getProcedimentoInexistente() {
        ResponseEntity response = testRestTemplate.getForEntity("/autorizador/procedimento/1111/idade/99/sexo/M", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(AutorizaEnum.NAO.toString());
    }

    @Test
    @Order(2)
    void insereUmProcedimento() {
        HttpEntity request = new HttpEntity<>(String.class);
        ResponseEntity response = testRestTemplate.postForEntity("/autorizador/cadastro/procedimento/1111/idade/99/sexo/M/autoriza/SIM", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(HttpStatus.OK.toString());
    }

    @Test
    @Order(3)
    void getProcedimentoExistente() {
        ResponseEntity response = testRestTemplate.getForEntity("/autorizador/procedimento/1111/idade/99/sexo/M", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(AutorizaEnum.SIM.toString());
    }

    @Test
    @Order(4)
    void insereUmProcedimentoIgualAoAnterior() {
        HttpEntity request = new HttpEntity<>(String.class);
        ResponseEntity response = testRestTemplate.postForEntity("/autorizador/cadastro/procedimento/1111/idade/99/sexo/M/autoriza/SIM", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Dados já existentes");
    }

}
