import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};


        //int[] basket = new int[products.length];
        Basket basket = new Basket(products, prices);
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + "." + products[i] + " " + prices[i] + "руб./шт.");
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введи номер товара и количество через пробел или end: ");
            String line = scanner.nextLine();  // Ввод данных
            if (line.equals("end")) {         // Выход из программы
                break;
            }
            String[] parts = line.split(" "); // "3 15" -> ["3", "15"] разделитель строки по пробелу
            int productNum = Integer.parseInt(parts[0]) - 1; // "3" -> 2 приведение ввода пользователя к значению массива
            int count = Integer.parseInt(parts[1]); // "15" -> 15 ввод кол-ва товара пользователем


            //basket[productNum] = basket[productNum] + productCount;
            basket.addToCart(productNum, count);
            basket.saveTxt(new File("basket.txt"));
        }

        Basket basket2 = Basket.loadFromTxtFile(new File("basket.txt"));
        basket.printCart();
    }

                }





