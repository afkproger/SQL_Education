package util;

import java.util.Scanner;

public class UserInteractionManager {
    public static final Scanner consoleInput = new Scanner(System.in);
    public static int isInteger() {
        while (true) {
            try {
                String inputData = consoleInput.nextLine();
                int age = Integer.parseInt(inputData);
                if (age > 0) {
                    return age;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода , повторите");
            }
        }
    }

    public  static UserChoice makeUserChoice(){
        System.out.println("___________________________________________________");
        System.out.println("1 - Получить информацию о ребёнке");
        System.out.println("2 - Получить информацию о всех детях");
        System.out.println("3 - Выход");

        int choice = isInteger();

        while (true){
            switch (choice){
                case 1:
                    return UserChoice.GETBYID;

                case 2:
                    return UserChoice.GETALL;
                case 3:
                    return UserChoice.EXIT;
                default:
                    System.out.println("Нет такого пункта, повторите ввод");
            }
        }
    }


    public  static AdminChoice makeAdminChoice(){
        System.out.println("1 - Добавить детский сад в базу");
        System.out.println("2 - Добавить ребёнка в базу");
        System.out.println("3 - Удалить ребёнка из базы");
        System.out.println("4 - Получить всю информацию из базы данных");
        System.out.println("5 - Обновить информацию о ребёнке");
        System.out.println("6 - Выход из программы");

        int choice = isInteger();

        while (true){
            switch (choice){
                case 1:
                    return AdminChoice.ADDKINDERGARTEN;
                case 2:
                    return AdminChoice.ADDCHILD;
                case 3:
                    return AdminChoice.DELET;
                case 4:
                    return AdminChoice.GETALL;

                case 5:
                    return AdminChoice.UPDATE;

                case 6:
                    return AdminChoice.EXIT;

                default:
                    System.out.println("Нет такого пункта, повторите ввод");
            }
        }
    }


    public static HowToLoginChoice makeLoginChoice(){
        System.out.println("Как вы хотите войти");
        System.out.println("1 - Как администратор");
        System.out.println("2 - Как пользователь");
        int choice = UserInteractionManager.isInteger();
        while (true){
            switch (choice){
                case 1:
                    return HowToLoginChoice.ADMIN;
                case 2:
                    return HowToLoginChoice.USER;

                default:
                    System.out.println("Ошибка ввода, повторите ");

            }
        }
    }


}
