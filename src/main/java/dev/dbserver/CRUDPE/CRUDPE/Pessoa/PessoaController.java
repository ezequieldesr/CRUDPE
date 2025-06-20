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
    public PessoaModel criarPessoa(@RequestBody PessoaModel pessoaModel){
        return pessoaService.criarPessoa(pessoaModel);
    }

    @PutMapping("/atualizar")
    public String atualizarPessoa(){
        return "Atualizar pessoa com sucesso";
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPessoa(@PathVariable Long id){
         pessoaService.deletarPessoaPorId(id);
    }

    @GetMapping("/idade/{id}")
    public int mostrarIdade(@PathVariable Long id){return pessoaService.mostrarIdade(id);}
}
