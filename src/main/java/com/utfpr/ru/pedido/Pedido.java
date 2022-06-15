package com.utfpr.ru.pedido;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime dataPedido;

    @Column(nullable = false)
    private Integer clienteId;

    @Column(length = 45, nullable = false)
    private String nomeCliente;

    @Column(nullable = true, unique = true, length = 15)
    private Integer alunoRa;

    @Column(nullable = false, unique = true, length = 15)
    private Integer externoCpf;

    @Column(nullable = false, unique = true, length = 15)
    private Integer externoRg;

    @Column(nullable = false, length = 45)
    private String emailCliente;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(Integer alunoRa) {
        this.alunoRa = alunoRa;
    }

    public Integer getExternoCpf() {
        return externoCpf;
    }

    public void setExternoCpf(Integer externoCpf) {
        this.externoCpf = externoCpf;
    }

    public Integer getExternoRg() {
        return externoRg;
    }

    public void setExternoRg(Integer externoRg) {
        this.externoRg = externoRg;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
}
