package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public List<EnderecoDTO> listarEnderecos(){
        List<EnderecoModel> enderecos = enderecoRepository.findAll();
        return enderecos.stream()
                .map(enderecoMapper::map)
                .collect(Collectors.toList());
    }

    public EnderecoDTO buscarEnderecoPorId(Long id){
        Optional<EnderecoModel> enderecoModel = enderecoRepository.findById(id);
        return enderecoModel.map(enderecoMapper::map).orElse(null);
    }

    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO){
        EnderecoModel endereco = enderecoMapper.map(enderecoDTO);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.map(endereco);
    }

}
