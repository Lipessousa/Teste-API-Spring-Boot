package br.com.projeto_teste_spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto_teste_spring.api.model.Mensagem;
import br.com.projeto_teste_spring.api.model.Pessoa;
import br.com.projeto_teste_spring.api.repository.Repositorio;

@Service
public class Servico {
    
    //mensagem que será exibida dependendo do status da requisição
    @Autowired
    private Mensagem mensagem;

    //objeto que faz requisição no banco
    @Autowired
    private Repositorio acao;

    //Método para cadastro de clientes, será chamado na classe Controller
    public ResponseEntity<?> cadastrar(Pessoa p){
        if (p.getNome().equals("")) {
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(p.getIdade() <= 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(p), HttpStatus.CREATED);
        }
    }

    //Método para selecionar pessoas, será chamado na classe controller
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    //Método para buscar pessoa pelo Id
    public ResponseEntity<?> buscaPorId(int id){
        if (acao.countById(id) == 0) {
            mensagem.setMensagem("Não foi encontrado nenhum registro");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findById(id), HttpStatus.OK);
        }
    }

    //Método para edição dos dados de uma pessoa
    public ResponseEntity<?> editar(Pessoa p){
        if (acao.countById(p.getId()) == 0) {
            mensagem.setMensagem("Este código não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else if(p.getNome().equals("")){
            mensagem.setMensagem("É necessário informar um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if (p.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST); 
        }else{
            return new ResponseEntity<>(acao.save(p), HttpStatus.OK); 
        }
    }

    //Método para remoção
    public ResponseEntity<?> remove(int id){
        if (acao.countById(id) == 0) {
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            Pessoa p = acao.findById(id);
            acao.delete(p);

            mensagem.setMensagem("Exclusão efetuada com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}
