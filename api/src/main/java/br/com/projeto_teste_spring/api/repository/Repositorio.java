package br.com.projeto_teste_spring.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto_teste_spring.api.model.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer>{
    
        //Método que retorna todos os dados da tabela
        //equivale ao um 'select * from' no MySQL
        List<Pessoa> findAll();

        //neste caso, como será retornado a pessoa com o respectivo ID
        //não é necessário usar o <List>
        Pessoa findById(int id);

        //retorna os dados cadastrados na tabela, ordenando alfabéticamente
        List<Pessoa> findByOrderByNome();

        //retorna pessoas de mesmo nome, ordenando da maior para menor idade
        List<Pessoa> findByNomeOrderByIdadeDesc(String nome);

        //retorna dados onde haja um termo específico (letra, num, etc.)
        List<Pessoa> findByNomeContaining(String termo);

        //essa anotation permite que seja feito a query a mão no banco
        @Query(value = "SELECT SUM(Idade) FROM PESSOA", nativeQuery = true)
        int somaIdades();

        @Query(value = "SELECT * FROM Pessoa WHERE Idade >= :idade", nativeQuery = true)
        List<Pessoa> idadeMaiorIgual(int idade); 

        int countById(int id);
}
