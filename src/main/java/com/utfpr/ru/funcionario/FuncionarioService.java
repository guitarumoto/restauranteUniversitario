package com.utfpr.ru.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired private FuncionarioRepository repo;

    public List<Funcionario> listAll(){
        return (List<Funcionario>) repo.findAll();
    }

    public void save(Funcionario funcionario) {
        repo.save(funcionario);
    }

    public boolean loginVerification(Funcionario funcionario){
        List<Funcionario> allFuncionarios = listAll();
        for (Funcionario func : allFuncionarios){
            if(func.getLogin().equals(funcionario.getLogin()) && func.getSenha().equals(funcionario.getSenha())){
                return true;
            }
        }
        return false;
    }

    public Funcionario get(Integer id) throws FuncionarioNotFoundException {
        Optional<Funcionario> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new FuncionarioNotFoundException("Não foi possível encontrar nenhum usuário com ID " + id);
    }

    public void delete(Integer id) throws FuncionarioNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new FuncionarioNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);
    }
}
