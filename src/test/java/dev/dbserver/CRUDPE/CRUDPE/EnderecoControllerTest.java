package dev.dbserver.CRUDPE.CRUDPE;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoController;
import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoDTO;
import dev.dbserver.CRUDPE.CRUDPE.Endereco.EnderecoService;
import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnderecoService enderecoService;

    private EnderecoDTO enderecoDTO;

    @BeforeEach
    void setUp() {
        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(101L);
        enderecoDTO.setRua("Avenida Paulista");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setPessoaId(1L);
    }

    @Test
    @DisplayName("Deve criar um endereço e retornar status 201 Created")
    void deveCriarEnderecoERetornarStatus201() throws Exception {
        when(enderecoService.criarEndereco(any(EnderecoDTO.class))).thenReturn(enderecoDTO);

        mockMvc.perform(post("/api/enderecos/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(101L))
                .andExpect(jsonPath("$.rua").value("Avenida Paulista"));
    }

    @Test
    @DisplayName("Deve retornar status 404 ao tentar criar endereço para pessoa inexistente")
    void deveRetornarStatus404AoCriarEnderecoParaPessoaInexistente() throws Exception {
        when(enderecoService.criarEndereco(any(EnderecoDTO.class)))
                .thenThrow(new ResourceNotFoundException("Pessoa com o id 99 não encontrada."));

        mockMvc.perform(post("/api/enderecos/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isNotFound());
    }
}
