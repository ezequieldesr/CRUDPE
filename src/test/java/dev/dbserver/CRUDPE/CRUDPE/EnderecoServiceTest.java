package dev.dbserver.CRUDPE.CRUDPE;

import dev.dbserver.CRUDPE.CRUDPE.Endereco.*;
import dev.dbserver.CRUDPE.CRUDPE.Endereco.Domain.Endereco;
import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.Domain.Pessoa;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaRepository;
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
public class EnderecoServiceTest {
    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private EnderecoService enderecoService;

    private EnderecoDTO enderecoDTO;
    private Endereco endereco;
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa(1L, "João da Silva", LocalDate.of(1990, 1, 1), "12345678901", null);

        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(101L);
        enderecoDTO.setRua("Avenida Paulista");
        enderecoDTO.setPessoaId(1L);

        endereco = new Endereco();
        endereco.setId(101L);
        endereco.setRua("Avenida Paulista");
        endereco.setPessoa(pessoa);
    }

    @Test
    @DisplayName("Deve criar um endereço com sucesso quando a pessoa existe")
    void deveCriarEnderecoComSucesso() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(enderecoMapper.map(any(EnderecoDTO.class))).thenReturn(endereco);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(enderecoMapper.map(any(Endereco.class))).thenReturn(enderecoDTO);

        EnderecoDTO enderecoSalvo = enderecoService.criarEndereco(enderecoDTO);

        assertNotNull(enderecoSalvo);
        assertEquals(101L, enderecoSalvo.getId());
        verify(pessoaRepository, times(1)).findById(1L);
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar endereço para uma pessoa que não existe")
    void deveLancarExcecaoAoCriarEnderecoParaPessoaInexistente() {
        long pessoaIdInexistente = 99L;
        enderecoDTO.setPessoaId(pessoaIdInexistente);
        when(pessoaRepository.findById(pessoaIdInexistente)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            enderecoService.criarEndereco(enderecoDTO);
        });

        assertEquals("Não é possível criar o endereço. Pessoa com o id " + pessoaIdInexistente + " não encontrada.", exception.getMessage());

        verify(enderecoRepository, never()).save(any());
    }
}
