package br.com.projeto_teste_spring.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.projeto_teste_spring.api.model.Cliente;
import br.com.projeto_teste_spring.api.model.Pessoa;
import br.com.projeto_teste_spring.api.repository.Repositorio;
import br.com.projeto_teste_spring.api.service.Servico;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class Controller {

    // o AutoWired faz com que não seja necessário instância de objetos
    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;

    //------------ GETS --------------------

    // Teste GET de dados no banco
    @GetMapping("/api")
    public ResponseEntity<?> listaPessoa() {
        return servico.selecionar();
    }

    // Teste GET de dados no banco, passando Id da pesoa como parâmetro de URL
    @GetMapping("/api/{id}")
    public ResponseEntity<?> listaPorId(@PathVariable int id) {
        return servico.buscaPorId(id);
    }

    // Teste GET de dados do banco, retornando dados de maneira ordenada pelo nome
    @GetMapping("/api/ordenaNome")
    public List<Pessoa> odernaNome() {
        return acao.findByOrderByNome();
    }

    // Teste GET, retorna pessoas de mesmo nome, ordenando da maior para menor idade
    @GetMapping("/api/ordenaNome2")
    public List<Pessoa> odernaNome2() {
        return acao.findByNomeOrderByIdadeDesc("Patricia");
    }

    // Teste GET, retorna dados ontem contenha um termo específico(letra, num, etc.)
    @GetMapping("/api/nomeContem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("l");
    }

    // ------------ uso de anotation @Query --------------------

    // Teste GET de anotation @Query do JPA
    @GetMapping("/api/somaIdades")
    public String somaIdades() {
        return "A soma de todas as idades é: " + acao.somaIdades();
    }


    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(40);
    }


    // ------------ POSTS --------------------

    // método POST na API, inserindo dados no banco
    @PostMapping("/api")
    public ResponseEntity<?> cadastro(@RequestBody Pessoa p) {
        return servico.cadastrar(p);
    }

    //------------ PUTS --------------------

    // método PUT na API
    @PutMapping("/api")
    public ResponseEntity<?> edicao(@RequestBody Pessoa p) {
        return servico.editar(p);
    }

    //------------ DELETES --------------------

    // método DELETE na API, exlcusão por Id
    @DeleteMapping("/api/{id}")
    public ResponseEntity<?> remove(@PathVariable int id) {
        return servico.remove(id);
    }

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count();
    }

    //-------------------- TESTES BÁSICOS ---------------------

    // teste GET com string básica
    @GetMapping("")
    public String message() {
        return "Hello World";
    }

    // teste GET com string básica, passando variavel pela rota
    @GetMapping("/boasVindas/{nome}")
    public String boasVindas(@PathVariable String nome) {
        return "Seja bem vindo " + nome;

    }

    // teste POST básico
    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p) {
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente c){

    }

}
