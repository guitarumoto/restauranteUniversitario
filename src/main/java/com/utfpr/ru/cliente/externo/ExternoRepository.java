package com.utfpr.ru.cliente.externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExternoRepository extends CrudRepository<Externo, Integer> {

}
