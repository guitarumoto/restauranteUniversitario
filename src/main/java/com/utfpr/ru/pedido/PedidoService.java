package com.utfpr.ru.pedido;

import com.utfpr.ru.cliente.externo.Externo;
import com.utfpr.ru.cliente.externo.ExternoRepository;
import com.utfpr.ru.funcionario.Funcionario;
import com.utfpr.ru.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private ExternoRepository extRepo;

    public void save(Pedido pedido) {
        repo.save(pedido);
    }


    public List<Externo> listAll(){
        return (List<Externo>) extRepo.findAll();
    }

    public Pedido cpfVerification(Pedido pedido){
        Pedido pedidoExterno = new Pedido();
        List<Externo> allExternos = listAll();
        for (Externo ext : allExternos){
            if(ext.getCpf().equals(pedido.getExternoCpf())){
                pedidoExterno.setClienteId(ext.getId());
            }
        }
        return pedidoExterno;
    }
}
