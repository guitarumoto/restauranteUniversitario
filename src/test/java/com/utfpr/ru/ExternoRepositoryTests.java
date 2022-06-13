package com.utfpr.ru;


import com.utfpr.ru.cliente.externo.Externo;
import com.utfpr.ru.cliente.externo.ExternoRepository;
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
public class ExternoRepositoryTests {

    @Autowired
    private ExternoRepository repo;
    @Test
    public void testAddNewExterno(){
        Externo externo = new Externo();
        externo.setNome("Guilherme Externo");
        externo.setEmail("guilhermeExterno@aaa");
        externo.setCpf(123456);
        externo.setRg(123321);

        Externo savedExterno = repo.save(externo);
        Assertions.assertThat(savedExterno).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Externo> externos = repo.findAll();
        Assertions.assertThat(externos).hasSizeGreaterThan(0);

        for(Externo externo : externos) {
            System.out.println(externo);
        }
    }

    @Test
    public void testUpdate(){
        Integer externoId = 3;
        Optional<Externo> optionalExterno = repo.findById(externoId);
        Externo externo = optionalExterno.get();
        externo.setRg(123123123);
        repo.save(externo);

        Externo updatedExterno = repo.findById(externoId).get();
        Assertions.assertThat(updatedExterno.getRg()).isEqualTo(123123123);
    }

    @Test
    public void testGet(){
        Integer externoId = 3;
        Optional<Externo> optionalExterno = repo.findById(externoId);

        Assertions.assertThat(optionalExterno).isPresent();
        System.out.println(optionalExterno.get());
    }

    @Test void testDelete(){
        Integer externoId = 3;
        repo.deleteById(externoId);
        Optional<Externo> optionalExterno = repo.findById(externoId);

        Assertions.assertThat(optionalExterno).isNotPresent();
    }
}
