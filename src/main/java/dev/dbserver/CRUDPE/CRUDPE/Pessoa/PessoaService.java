package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.Domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaMapper pessoaMapper;

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream()
                .map(pessoaMapper::map)
                .collect(Collectors.toList());
    }

    public PessoaDTO buscarPessoaPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com o id " + id + " não encontrada."));
        return pessoaMapper.map(pessoa);
    }

    public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.map(pessoaDTO);

        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(endereco -> endereco.setPessoa(pessoa));
        }

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return pessoaMapper.map(pessoaSalva);
    }

    public PessoaDTO atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com o id " + id + " não encontrada para atualização."));

        if (pessoaDTO.getNome() != null && !pessoaDTO.getNome().isEmpty()) {
            pessoaExistente.setNome(pessoaDTO.getNome());
        }
        if (pessoaDTO.getDataNascimento() != null) {
            pessoaExistente.setDataNascimento(pessoaDTO.getDataNascimento());
        }
        if (pessoaDTO.getCpf() != null && !pessoaDTO.getCpf().isEmpty()) {
            pessoaExistente.setCpf(pessoaDTO.getCpf());
        }

        Pessoa pessoaSalva = pessoaRepository.save(pessoaExistente);
        return pessoaMapper.map(pessoaSalva);
    }

    public void deletarPessoaPorId(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pessoa com o id " + id + " não encontrada para deleção.");
        }
        pessoaRepository.deleteById(id);
    }

    public int mostrarIdade(Long id) {
        PessoaDTO pessoaDTO = buscarPessoaPorId(id);
        return Period.between(pessoaDTO.getDataNascimento(), LocalDate.now()).getYears();
    }
}
