package com.utfpr.ru;

import com.utfpr.ru.pedido.Pedido;
import com.utfpr.ru.pedido.PedidoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PedidoRepositoryTests {
    @Autowired
    private PedidoRepository repo;

    @Test
    public void testAddPedidoExterno() throws ParseException {
        Pedido pedido = new Pedido();
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        pedido.setDataPedido(date1);
        pedido.setClienteId(2);
        pedido.setNomeCliente("Guilherme Tarumoto");
        pedido.setExternoCpf(213123);
        pedido.setExternoRg(2312034);

        Pedido savedPedido = repo.save(pedido);
        Assertions.assertThat(savedPedido).isNotNull();
    }

    @Test
    public void testListAll(){
        Iterable<Pedido> pedidos = repo.findAll();
        Assertions.assertThat(pedidos).hasSizeGreaterThan(0);

        for(Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    @Test
    public void testUpdate(){
        Integer pedidoId = 2;
        Optional<Pedido> optionalPedido = repo.findById(pedidoId);
        Pedido pedido = optionalPedido.get();
        pedido.setClienteId(2);
        repo.save(pedido);

        Pedido updatedPedido = repo.findById(pedidoId).get();
        Assertions.assertThat(updatedPedido.getClienteId()).isEqualTo(2);
    }

    @Test
    public void testGet(){
        Integer pedidoId = 2;
        Optional<Pedido> optionalPedido = repo.findById(pedidoId);

        Assertions.assertThat(optionalPedido).isPresent();
        System.out.println(optionalPedido.get());
    }

    @Test void testDelete(){
        Integer pedidoId = 2;
        repo.deleteById(pedidoId);
        Optional<Pedido> optionalPedido = repo.findById(pedidoId);

        Assertions.assertThat(optionalPedido).isNotPresent();
    }
}
