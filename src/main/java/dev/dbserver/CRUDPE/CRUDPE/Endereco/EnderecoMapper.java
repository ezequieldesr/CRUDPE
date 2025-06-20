package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {
    public EnderecoModel map(EnderecoDTO enderecoDTO){
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(enderecoDTO.getId());
        enderecoModel.setRua(enderecoDTO.getRua());
        enderecoModel.setNumero(enderecoDTO.getNumero());
        enderecoModel.setBairro(enderecoDTO.getBairro());
        enderecoModel.setCidade(enderecoDTO.getCidade());
        enderecoModel.setEstado(enderecoDTO.getEstado());
        enderecoModel.setCep(enderecoDTO.getCep());

        return enderecoModel;
    }

    public EnderecoDTO map(EnderecoModel enderecoModel){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(enderecoModel.getId());
        enderecoDTO.setRua(enderecoModel.getRua());
        enderecoDTO.setNumero(enderecoModel.getNumero());
        enderecoDTO.setBairro(enderecoModel.getBairro());
        enderecoDTO.setCidade(enderecoModel.getCidade());
        enderecoDTO.setEstado(enderecoModel.getEstado());
        enderecoDTO.setCep(enderecoModel.getCep());

        return enderecoDTO;
    }

}
