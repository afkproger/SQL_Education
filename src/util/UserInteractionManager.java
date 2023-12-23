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

    public  static Choice makeUserChoice(){
        System.out.println("___________________________________________________");
        System.out.println("1 - Получить информацию о ребёнке");
        System.out.println("2 - Получить информацию о всех детях");

        int choice = isInteger();

        while (true){
            switch (choice){
                case 1:
                    return Choice.GETBYID;

                case 2:
                    return Choice.GETALL;

                default:
                    System.out.println("Нет такого пункта, повторите ввод");
            }
        }
    }


    public  static Choice makeAdminChoice(){
        System.out.println("1 - Добавить детский сад в базу");
        System.out.println("2 - Получить информацию о всех детях из определённого детского сада");
        System.out.println("3 - Удалить детский сад из базы");

        int choice = isInteger();

        while (true){
            switch (choice){
                case 1:
                    return Choice.ADD;

                case 2:
                    return Choice.GETALL;

                case 3:
                    return Choice.DELET;

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
