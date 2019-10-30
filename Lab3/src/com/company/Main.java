package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void menu() {
        System.out.println("Меню программы:");
        System.out.println("1.  Регистрация участников");
        System.out.println("2.  Сменить пользователя");
        System.out.println("3.  Добавить заявку");
        System.out.println("4.  Вывести список пользователей");
        System.out.println("5.  Вывести список заявок на продажу");
        System.out.println("6.  Вывести список заявок на покупку");
        System.out.println("7.  Вывести список исполненых заявок");
        System.out.println("8.  Провести торги");
        System.out.println("9.  Ввести тестовые данные");
        System.out.println("111. Повторный вывод меню");
        System.out.println("0. Завершение программы");
    }

    public static void main(String[] args) {
        int command;
        ArrayList<Request> done = new ArrayList<Request>();
        Admin admin = new Admin();
        TradeSystem ts = new TradeSystem();
        ts.addUser("admin","admin","admin");
        ts.setCurrentUser(new Client("admin","admin","admin"));
        menu();
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("\nВведите команду ");
            command = in.nextInt();
            ;
            switch (command) {
                case 0: {
                    System.out.print("program ended");
                    break;
                }
                case 1: {
                    in.nextLine();
                    System.out.println("Введите новое имя");
                    String n = in.nextLine();
                    System.out.println("Введите новый логин");
                    String l = in.nextLine();
                    System.out.println("Введите новый пароль");
                    String p = in.nextLine();
                    ts.addUser(n, l, p);
                    break;
                }
                case 2: {
                    in.nextLine();
                    System.out.println("Введите логин");
                    String l = in.nextLine();
                    System.out.println("Введите пароль");
                    String p = in.nextLine();
                    if (ts.findUser(p, l)) System.out.println("Вы успешно вошли под именем " + ts.getCurrentUser().getName());
                    else System.out.println("Неверный логин иили пароль");
                    break;
                }
                case 3: {
                    if (ts.getCurrentUser().getName() == "admin") System.out.println("Администратор не может участвовать в торгах");
                    else{
                        in.nextLine();
                        System.out.println("Введите название товара");
                        String pt = in.nextLine();
                        System.out.println("Введите цену");
                        int pe = in.nextInt();
                        System.out.println("Введите количество");
                        int c = in.nextInt();
                        System.out.println("Введите тип предложения (0 - покупка; 1 - продажа)");
                        int t = in.nextInt();
                        if(t==0){
                            ts.buyerRequests.add(new Request(ts.getCurrentUser(), pt, pe, c ,t));
                        }
                        else{
                            ts.sellerRequests.add(new Request(ts.getCurrentUser(), pt, pe, c ,t));
                        }
                    }
                    break;
                }
                case 4: {
                    for (Client cl : ts.users) {
                        System.out.println(cl.getName());
                    }
                    break;
                }
                case 5: {
                    for (Request rq : ts.sellerRequests) {
                        System.out.println(" товар: " + rq.getProduct() + " цена: " + rq.getPrice() + " кол-во: " + rq.getCount() + " продавец: " + rq.getRequester().getName());
                    }
                    break;
                }
                case 6: {
                    for (Request rq : ts.buyerRequests) {
                        System.out.println(" товар: " + rq.getProduct() + " цена: " + rq.getPrice() + " кол-во: " + rq.getCount() + " покупатель: " + rq.getRequester().getName());
                    }
                    break;
                }
                case 7: {
                    for (Request rq : done) {
                        if(rq.getType()==0) System.out.print("Покупка ");
                        else System.out.print("Продажа ");
                        System.out.println(" товар: " + rq.getProduct() + " цена: " + rq.getPrice() + " исполнитель: " + rq.getRequester().getName());
                    }
                    break;
                }
                case 8: {
                    done.addAll(ts.processRequests());
                    break;
                }
                case 9: {
                    ts.addUser("1", "1","1");
                    ts.findUser("1","1");
                    ts.sellerRequests.add(new Request(ts.getCurrentUser(), "2", 10, 1,1));
                    ts.sellerRequests.add(new Request(ts.getCurrentUser(), "3", 10, 1,1));
                    ts.sellerRequests.add(new Request(ts.getCurrentUser(), "4", 10, 1,1));
                    ts.sellerRequests.add(new Request(ts.getCurrentUser(), "5", 10, 1,1));
                    ts.sellerRequests.add(new Request(ts.getCurrentUser(), "6", 10, 1,1));
                    ts.addUser("2", "2","2");
                    ts.findUser("2","2");
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "2", 8, 1,0));
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "3", 11, 1,0));
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "4", 10, 1,0));
                    ts.addUser("3", "3","3");
                    ts.findUser("3","3");
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "2", 9, 1,0));
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "3", 13, 1,0));
                    ts.buyerRequests.add(new Request(ts.getCurrentUser(), "4", 10, 1,0));
                    break;
                }
                case 111: {
                    menu();
                    break;
                }
                default:
                    System.out.print("wrong command number");
            }
        } while (command != 0);
    }
}
