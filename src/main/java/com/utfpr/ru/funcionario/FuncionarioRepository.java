package com.utfpr.ru.funcionario;

import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository <Funcionario, Integer>{
    public Long countById(Integer id);
}
