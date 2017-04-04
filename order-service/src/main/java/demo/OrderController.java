package demo;

import demo.adapter.User;
import demo.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserAdapter userAdapter;

    @Autowired
    public OrderController(OrderRepository orderRepository, UserAdapter userAdapter) {
        this.orderRepository = orderRepository;
        this.userAdapter = userAdapter;
    }

    @GetMapping("/order/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable long userId) {

        User u = this.userAdapter.getUserDetail(userId);

        List<Order> orderList = this.orderRepository.findByUserId(userId);
        for (Order order: orderList) {
            order.setUser(u);
        }

        return  orderList;
    }
}
