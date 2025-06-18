package dev.dbserver.CRUDPE.CRUDPE.Endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/listar")
    public String listarEnderecos(){
        return "Listagem de enderco com sucesso";
    }

    @PostMapping("/criar")
    public String criarEndereco(){
        return "Pessoa criada com sucesso";
    }

    @PutMapping("/atualizar")
    public String atualizarEndereco(){
        return "Atualizar pessoa com sucesso";
    }

    @DeleteMapping("/deletar")
    public String deletarEndereco(){
        return "Deletar pessoa com sucesso";
    }

}
