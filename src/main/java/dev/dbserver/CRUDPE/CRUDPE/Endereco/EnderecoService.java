package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import dev.dbserver.CRUDPE.CRUDPE.Exceptions.ResourceNotFoundException;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaModel;
import dev.dbserver.CRUDPE.CRUDPE.Pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public List<EnderecoDTO> listarEnderecos() {
        List<EnderecoModel> enderecos = enderecoRepository.findAll();
        return enderecos.stream()
                .map(enderecoMapper::map)
                .collect(Collectors.toList());
    }

    public EnderecoDTO buscarEnderecoPorId(Long id) {
        EnderecoModel endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com o id " + id + " não encontrado."));
        return enderecoMapper.map(endereco);
    }

    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        PessoaModel pessoa = pessoaRepository.findById(enderecoDTO.getPessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Não é possível criar o endereço. Pessoa com o id " + enderecoDTO.getPessoaId() + " não encontrada."));

        EnderecoModel endereco = enderecoMapper.map(enderecoDTO);
        endereco.setPessoa(pessoa);

        EnderecoModel enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.map(enderecoSalvo);
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        EnderecoModel enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço com o id " + id + " não encontrado para atualização."));

        enderecoExistente.setRua(enderecoDTO.getRua());
        enderecoExistente.setNumero(enderecoDTO.getNumero());
        enderecoExistente.setBairro(enderecoDTO.getBairro());
        enderecoExistente.setCidade(enderecoDTO.getCidade());
        enderecoExistente.setEstado(enderecoDTO.getEstado());
        enderecoExistente.setCep(enderecoDTO.getCep());

        EnderecoModel enderecoSalvo = enderecoRepository.save(enderecoExistente);
        return enderecoMapper.map(enderecoSalvo);
    }

    public void deletarEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço com o id " + id + " não encontrado para deleção.");
        }
        enderecoRepository.deleteById(id);
    }
}
