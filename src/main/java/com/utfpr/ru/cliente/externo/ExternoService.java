package com.utfpr.ru.cliente.externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternoService {

    @Autowired
    private ExternoRepository repo;

    public void save(Externo externo) {
        repo.save(externo);
    }
}
