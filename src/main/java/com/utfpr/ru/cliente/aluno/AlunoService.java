package com.utfpr.ru.cliente.aluno;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repo;


    public List<Aluno> listAll(){
        return (List<Aluno>) repo.findAll();
    }

    public Aluno verificaRa (Integer ra){
        List<Aluno> allAlunos = listAll();
        for(Aluno aluno : allAlunos){
            if(Objects.equals(aluno.getRa(), ra)){
                return aluno;
            }
        }
        return null;
    }
}
