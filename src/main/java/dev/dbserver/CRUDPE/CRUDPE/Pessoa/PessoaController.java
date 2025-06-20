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
    public List<PessoaDTO> listarPessoas(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public PessoaDTO buscarPorId(@PathVariable Long id){ return pessoaService.buscarPessoaPorId(id);}

    @PostMapping("/criar")
    public PessoaDTO criarPessoa(@RequestBody PessoaDTO pessoaDTO){
        return pessoaService.criarPessoa(pessoaDTO);
    }

    @PutMapping("/atualizar/{id}")
    public PessoaDTO atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada){
        return pessoaService.atualizarPessoa(id,pessoaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPessoa(@PathVariable Long id){
         pessoaService.deletarPessoaPorId(id);
    }

    @GetMapping("/idade/{id}")
    public int mostrarIdade(@PathVariable Long id){return pessoaService.mostrarIdade(id);}
}
