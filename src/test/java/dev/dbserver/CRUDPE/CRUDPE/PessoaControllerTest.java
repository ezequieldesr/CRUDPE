package dev.dbserver.CRUDPE.CRUDPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaController;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaDTO;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PessoaService pessoaService;

    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("João da Silva");
        pessoaDTO.setCpf("12345678901");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        pessoaDTO.setEnderecos(null);
    }

    @Test
    @DisplayName("Deve criar uma pessoa e retornar status 201 Created")
    void deveCriarPessoaERetornarStatus201() throws Exception {
        when(pessoaService.criarPessoa(any(PessoaDTO.class))).thenReturn(pessoaDTO);

        mockMvc.perform(post("/api/pessoas/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João da Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678901"));
    }

    @Test
    @DisplayName("Deve retornar status 404 Not Found ao buscar pessoa inexistente")
    void deveRetornarStatus404AoBuscarPessoaInexistente() throws Exception {
        long pessoaIdInexistente = 99L;
        when(pessoaService.buscarPessoaPorId(pessoaIdInexistente))
                .thenThrow(new ResourceNotFoundException("Pessoa com o id " + pessoaIdInexistente + " não encontrada."));

        mockMvc.perform(get("/api/pessoas/{id}", pessoaIdInexistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
