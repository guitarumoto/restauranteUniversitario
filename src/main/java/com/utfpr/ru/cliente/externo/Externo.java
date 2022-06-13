package com.utfpr.ru.cliente.externo;

import com.utfpr.ru.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Externo extends Cliente {
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Autowired private Cliente cliente;

    @Column(nullable = true, unique = true, length = 15)
    private Integer rg;

    @Column(nullable = true, unique = true, length = 15)
    private Integer cpf;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }
}
