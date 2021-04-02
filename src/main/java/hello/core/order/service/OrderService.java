package hello.core.order.service;

import hello.core.order.domain.Order;

public interface OrderService {

    /**
     *
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return Order 객체
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
