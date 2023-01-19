package com.progmatic.jdbc;

import com.progmatic.jdbc.model.Client;
import com.progmatic.jdbc.model.Courier;
import com.progmatic.jdbc.model.Order;
import com.progmatic.jdbc.model.Pizza;
import com.progmatic.jdbc.model.Controller;

import java.util.List;
import java.util.Scanner;

public class Main {

    private void printMenu() {
        System.out.println("(U)j rendeles");
        System.out.println("Kere(s)es");
        System.out.println("Ve(v)ok listaja");
        System.out.println("Fu(t)arok listaja");
        System.out.println("Piz(z)ak listaja");
        System.out.println("Ren(d)elesek listaja");
        System.out.println("Új (p)izza hozzáadaása-bemelegítő feladat");
        System.out.println("(K)ilepes");
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
                    case "u" ->
                        System.out.println("uj rendeles");
                    case "s" ->
                        System.out.println("kereses");
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
                        case "p"-> {
                            System.out.println("Add meg az id-t");
                        long pizzaid=Long.parseLong(sc.nextLine());
                            System.out.println("Add meg a pizza nevét");
                        String pizzanev= sc.nextLine();
                            System.out.println("Add meg az árát");
                        int pizzar=Integer.parseInt(sc.nextLine());
                    Pizza pizza=new Pizza(pizzaid,pizzanev,pizzar);
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
}
