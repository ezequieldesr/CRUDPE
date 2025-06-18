package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @GetMapping("/listar")
    public String listarPessoas(){
        return "Listar pessoas com sucesso";
    }

    @PostMapping("/criar")
    public String criarPessoa(){
        return "Pessoa criada com sucesso";
    }

    @PutMapping("/atualizar")
    public String atualizarPessoa(){
        return "Atualizar pessoa com sucesso";
    }

    @DeleteMapping("/deletar")
    public String deletarPessoa(){
        return "Deletar pessoa com sucesso";
    }
}
