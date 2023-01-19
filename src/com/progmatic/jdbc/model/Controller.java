package com.progmatic.jdbc.model;

import com.progmatic.jdbc.DBEngine;
import com.progmatic.jdbc.model.*;
import dao.*;

import java.util.List;

public class Controller implements AutoCloseable {

    DBEngine engine;
    PizzaDao pizzaDao;
    CourierDao courierDao;
    ClientDao clientDao;
    OrderItemDao orderItemDao;
    OrderDao orderDao;

    public Controller() {
        this.engine = new DBEngine();
        this.pizzaDao = new PizzaDao(this.engine);
        this.courierDao = new CourierDao(this.engine);
        this.clientDao = new ClientDao(this.engine);
        this.orderItemDao = new OrderItemDao(this.engine, this.pizzaDao);
        this.orderDao = new OrderDao(this.engine, courierDao, clientDao, this.orderItemDao);
    }

    public List<Pizza> getAllPizza() {
        return this.pizzaDao.getAll();
    }

    public List<Courier> getAllCourier() {
        return this.courierDao.getAll();
    }

    public List<Client> getAllClient() {
        return this.clientDao.getAll();
    }

    public List<Order> getAllOrder() {
        return this.orderDao.getAll();
    }

    public void addOrdder(Order order){
        this.orderDao.save(order);
    }
    public void addOrderItem(OrderItem orderItem){
        this.orderItemDao.save(orderItem);
    }

    //első feladat pizza felvétlele
    public void addPizza(Pizza pizza){
        this.pizzaDao.save(pizza);
    }

    @Override
    public void close() throws Exception {
        if (engine != null && !engine.isClosed()) {
            engine.close();
        }
    }
}
