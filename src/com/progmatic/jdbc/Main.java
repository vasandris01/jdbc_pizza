package com.progmatic.jdbc;

import com.progmatic.jdbc.model.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private void printMenu() {
        System.out.println("(U)j dolog felvétele");
        System.out.println("Kere(s)es");
        System.out.println("Ve(v)ok listaja");
        System.out.println("Fu(t)arok listaja");
        System.out.println("Piz(z)ak listaja");
        System.out.println("Ren(d)elesek listaja");
        System.out.println("Új (p)izza hozzáadaása-bemelegítő feladat");
        System.out.println("(K)ilepes");
    }

    private void printSaveMenu() {
        System.out.println("Új pizza felvétele(1)");
        System.out.println("Új futár felvétele(2)");
        System.out.println("Új vásárló felvétele(3)");
        System.out.println("Új rendelés felvétele(4)");
        System.out.println("Vissza a főmenübe(bármi más)");
    }

    public void save_anything(Controller controller) {
        printSaveMenu();
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        switch (s) {
            case "1" -> {
                System.out.println("add meg az id t");
                long id = Long.parseLong(sc.nextLine());
                System.out.println("add meg a nevet");
                String name = sc.nextLine();
                System.out.println("add meg az árát");
                int ar = Integer.parseInt(sc.nextLine());
                controller.addPizza(new Pizza(id, name, ar));
            }
            case "2" -> {
            }
            case "3" -> {
            }
            case "4" -> {
                rendeles(controller, sc);
            }
        }
    }

    public void start() {
        try (
                Scanner sc = new Scanner(System.in);
                Controller controll = new Controller()
        ) {
            System.out.println("*".repeat(30));
            System.out.println("*" + "Pizza Prog" + "*");
            System.out.println("*".repeat(30) + "\n");

            String s;
            this.printMenu();
            while (!(s = sc.nextLine()).equalsIgnoreCase("k")) {
                switch (s.toLowerCase()) {
                    case "u" -> {
                        save_anything(controll);
                    }

                    case "s" -> System.out.println("kereses");
//                        this.startSearch(engine);
                    case "v" -> {
                        List<Client> allC = controll.getAllClient();
                        for (Client c : allC) {
                            System.out.println(c);
                        }
                        System.out.println("\n");
                    }
                    case "d" -> {
                        List<Order> allO = controll.getAllOrder();
                        for (Order o : allO) {
                            System.out.println(o);
                        }
                        System.out.println("\n");
                    }
                    case "t" -> {
                        List<Courier> allC = controll.getAllCourier();
                        for (Courier c : allC) {
                            System.out.println(c);
                        }
                        System.out.println("\n");
                    }
                    case "z" -> {
                        List<Pizza> allP = controll.getAllPizza();
                        for (Pizza p : allP) {
                            System.out.println(p);
                        }
                        System.out.println("\n");
                    }
                    case "p" -> {
                        System.out.println("Add meg az id-t");
                        long pizzaid = Long.parseLong(sc.nextLine());
                        System.out.println("Add meg a pizza nevét");
                        String pizzanev = sc.nextLine();
                        System.out.println("Add meg az árát");
                        int pizzar = Integer.parseInt(sc.nextLine());
                        Pizza pizza = new Pizza(pizzaid, pizzanev, pizzar);
                    }

                    default -> System.out.println("Ilyen menuelem nincs, kerem valasszon ujra.\n");
                }
                this.printMenu();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.start();
    }

    public void rendeles(Controller controll, Scanner sc) {
        System.out.println("Ki vagy koma? (id)");
        long vazon = Long.parseLong(sc.nextLine());
        List<Client> allC = controll.getAllClient();
        Client client = null;
        for (Client c : allC) {
            if (c.cid() == vazon) {
                client = c;
                break;
            }
        }
        if (client == null) {
            //új vásárló
        }

        List<Order> orders = controll.getAllOrder();
        long razon = orders.get(0).oid();
        for (int i = 1; i < orders.size(); i++) {
            if (razon < orders.get(i).oid()) {
                razon = orders.get(i).oid();
            }
        }
        razon++;

        List<Pizza> allP = controll.getAllPizza();
        List<OrderItem> orderItems = new LinkedList<>();
        for (Pizza p : allP) {
            System.out.println("Hány db " + p.name() + " kérsz?");
            short db = Short.parseShort(sc.nextLine());
            if (db > 0) {
                orderItems.add(new OrderItem(razon, p, db));
            }
        }
        List<Courier> couriers = controll.getAllCourier();
        Courier courier = couriers.get((int) (Math.random() * couriers.size()));


        Order order = new Order(razon, client, courier, orderItems, LocalDateTime.now());

        controll.addOrdder(order);
        for (OrderItem o : orderItems) {
            controll.addOrderItem(o);
        }
    }
}
