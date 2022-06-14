package com.utfpr.ru;

import com.utfpr.ru.funcionario.Funcionario;
import com.utfpr.ru.funcionario.FuncionarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class FuncionarioRepositoryTests {
    @Autowired
    private FuncionarioRepository repo;

    @Test
    public void testAddNewEmployee(){
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("guitarumoto@gmail.coma");
        funcionario.setNome("Guilherme Tarumoto");
        funcionario.setLogin("tarumoto");
        funcionario.setSenha("12345");

        Funcionario savedEmployee = repo.save(funcionario);
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Funcionario> funcionarios = repo.findAll();
        Assertions.assertThat(funcionarios).hasSizeGreaterThan(0);

        for(Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    @Test
    public void testUpdate(){
        Integer funcionarioId = 1;
        Optional<Funcionario> optionalFuncionario = repo.findById(funcionarioId);
        Funcionario funcionario = optionalFuncionario.get();
        funcionario.setSenha("aaaaa");
        repo.save(funcionario);

        Funcionario updatedFuncionario = repo.findById(funcionarioId).get();
        Assertions.assertThat(updatedFuncionario.getSenha()).isEqualTo("aaaaa");
    }

    @Test
    public void testGet(){
        Integer funcionarioId = 1;
        Optional<Funcionario> optionalFuncionario = repo.findById(funcionarioId);

        Assertions.assertThat(optionalFuncionario).isPresent();
        System.out.println(optionalFuncionario.get());
    }

    @Test void testDelete(){
        Integer funcionarioId = 4;
        repo.deleteById(funcionarioId);
        Optional<Funcionario> optionalFuncionario = repo.findById(funcionarioId);

        Assertions.assertThat(optionalFuncionario).isNotPresent();
    }

}
