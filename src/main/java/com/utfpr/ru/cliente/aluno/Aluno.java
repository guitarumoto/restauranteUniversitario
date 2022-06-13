package com.utfpr.ru.cliente.aluno;

import com.utfpr.ru.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Aluno extends Cliente {
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @Autowired private Cliente cliente;
    @Column(nullable = true, unique = true, length = 15)
    private Integer ra;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }
}
