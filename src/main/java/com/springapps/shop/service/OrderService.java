package com.springapps.shop.service;

import com.springapps.shop.entities.CartItem;
import com.springapps.shop.entities.Order;
import com.springapps.shop.entities.OrderItem;
import com.springapps.shop.entities.User;
import com.springapps.shop.exceptions.ResourceNotFoundException;
import com.springapps.shop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private CartItemRepository cartItemRepository;

    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
    }
    //Plasam o comanda pentru un utilizator (cu produsele pe care le are in cosul de cumparaturi)
    //
    //Endpoint: /orders/add/{userId}
    @Transactional
    public Order addOrderToUser(Long userId) {
        //gasesc userul dupa id
        //creez o noua comanda
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Order order = new Order();

        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
        List<OrderItem> orderItems = cartItems.stream()
                //creez un orderitem apeland metoda
                //atasez orderitem la cheia straina order din tabelul orderitem
                .map(cartItem -> mapFromCartItemToOrderItem(cartItem,order))
                .collect(Collectors.toList());
        if (cartItems.isEmpty()){
            throw new ResourceNotFoundException("order cannot be place. cart is empty");
        }
        //pe comanda setez pretul total al comenzi rezultat din apelul unei metode care calculeaza pretul total  orederitems:
        order.setTotalPrice(computeTotalPrice(orderItems));
        order.setOrderItems(orderItems);//atasez lista de orderitems la un order
        // user.getOrders().add(order);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        //sterg lista de cartitem-uri dupa id utilizator
        cartItemRepository.deleteAllByUser_Id(userId);
        return orderRepository.save(order);//salvez order-ul

    }

    private Double computeTotalPrice(List<OrderItem> orderItems) {
        Optional<Double> totalPrice = orderItems.stream()
                .map(orderitem -> orderitem.getPrice())
                .reduce((sum, number) -> sum + number);

        return totalPrice.orElseThrow(() -> new ResourceNotFoundException("total price could not be computed"));
    }

    public OrderItem mapFromCartItemToOrderItem(CartItem cartItem,Order order) {
        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        orderItem.setOrder(order);

        return orderItem;
    }

    @Transactional
    public List<Order> viewOrders(Long userId) {
        List<Order> allOrders = orderRepository.findAllByUser_IdOrderByCreatedAt(userId);
        return allOrders;
    }
}
