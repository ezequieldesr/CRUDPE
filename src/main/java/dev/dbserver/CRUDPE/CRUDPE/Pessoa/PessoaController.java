package dev.dbserver.CRUDPE.CRUDPE.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/listar")
    public List<PessoaModel> listarPessoas(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public PessoaModel buscarPorId(@PathVariable Long id){ return pessoaService.buscarPessoaPorId(id);}

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

    @GetMapping("/{id}/idade")
    public String mostrarIdade(){return "Voce tem ... de idade";}
}
