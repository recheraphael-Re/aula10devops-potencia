package com.CalcPotencia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PotenciaApiTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void deveCalcularViaGet() throws Exception {
        mvc.perform(get("/api/potencia").param("tensao", "220").param("corrente", "5"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.potencia").value(1100.0))
                .andExpect(jsonPath("$.unidade").value("W"));
    }

    @Test
    void deveCalcularViaPost() throws Exception {
        mvc.perform(post("/api/potencia").contentType(MediaType.APPLICATION_JSON)
                .content("{\"tensao\":121,\"corrente\":2.3}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.potencia").value(org.hamcrest.Matchers.closeTo(278.3, 0.000001)));
    }

    @Test
    void deveRejeitarCampoAusente() throws Exception {
        mvc.perform(post("/api/potencia").contentType(MediaType.APPLICATION_JSON).content("{\"tensao\":220}"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/potencia").param("tensao", "220"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarValoresInvalidos() throws Exception {
        for (String tensao : new String[]{"abc", "NaN", "Infinity", "1e309"}) {
            mvc.perform(get("/api/potencia").param("tensao", tensao).param("corrente", "5"))
                    .andExpect(status().isBadRequest());
        }
        mvc.perform(get("/api/potencia").param("tensao", "1e308").param("corrente", "5"))
                .andExpect(status().isBadRequest());
    }
}
