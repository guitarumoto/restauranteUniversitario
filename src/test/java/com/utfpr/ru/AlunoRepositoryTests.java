package com.utfpr.ru;

import com.utfpr.ru.cliente.aluno.Aluno;
import com.utfpr.ru.cliente.aluno.AlunoRepository;
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
public class AlunoRepositoryTests {

    @Autowired
    private AlunoRepository repo;

    @Test
    public void testAddNewAluno(){
        Aluno aluno = new Aluno();
        aluno.setRa(2312050);
        aluno.setEmail("guilhermeAluno@aa");
        aluno.setNome("Guilherme Aluno");

        Aluno savedAluno = repo.save(aluno);
        Assertions.assertThat(savedAluno).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Aluno> alunos = repo.findAll();
        Assertions.assertThat(alunos).hasSizeGreaterThan(0);

        for(Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

    @Test
    public void testUpdate(){
        Integer alunoId = 1;
        Optional<Aluno> optionalAluno = repo.findById(alunoId);
        Aluno aluno = optionalAluno.get();
        aluno.setRa(123321);
        repo.save(aluno);

        Aluno updatedAluno = repo.findById(alunoId).get();
        Assertions.assertThat(updatedAluno.getRa()).isEqualTo(123321);
    }

    @Test
    public void testGet(){
        Integer alunoId = 1;
        Optional<Aluno> optionalAluno = repo.findById(alunoId);

        Assertions.assertThat(optionalAluno).isPresent();
        System.out.println(optionalAluno.get());
    }

    @Test void testDelete(){
        Integer alunoId = 1;
        repo.deleteById(alunoId);
        Optional<Aluno> optionalAluno = repo.findById(alunoId);

        Assertions.assertThat(optionalAluno).isNotPresent();
    }
}
