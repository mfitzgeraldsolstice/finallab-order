package com.finallab.order;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.finallab.order.model.Orders;
import com.finallab.order.repository.OrdersRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrdersRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void testFindById(){
        Orders anOrder = new Orders();

        entityManager.persistAndFlush(anOrder);

        Orders foundOrder = ordersRepository.findById(1L);

        assertThat(foundOrder.getId(), Matchers.is(Matchers.equalTo(1L)));
    }

}
