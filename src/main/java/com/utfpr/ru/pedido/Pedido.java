package com.utfpr.ru.pedido;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date dataPedido;

    @Column(nullable = false)
    private Integer clienteId;

    @Column(length = 45, nullable = false)
    private String nomeCliente;

    @Column(nullable = true, unique = true, length = 15)
    private Integer alunoRa;
}
