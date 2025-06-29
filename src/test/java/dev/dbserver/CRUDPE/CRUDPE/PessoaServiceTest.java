package dev.dbserver.CRUDPE.CRUDPE;

import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.*;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.Domain.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaMapper pessoaMapper;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João da Silva");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("João da Silva");
        pessoaDTO.setCpf("12345678901");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
    }

    @Test
    @DisplayName("Deve criar uma pessoa com sucesso")
    void deveCriarPessoaComSucesso() {
        when(pessoaMapper.map(any(PessoaDTO.class))).thenReturn(pessoa);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
        when(pessoaMapper.map(any(Pessoa.class))).thenReturn(pessoaDTO);

        PessoaDTO pessoaSalva = pessoaService.criarPessoa(pessoaDTO);

        assertNotNull(pessoaSalva);
        assertEquals("João da Silva", pessoaSalva.getNome());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar uma pessoa que não existe")
    void deveLancarExcecaoAoBuscarPessoaInexistente() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            pessoaService.buscarPessoaPorId(99L);
        });

        verify(pessoaRepository, times(1)).findById(99L);
    }

}
