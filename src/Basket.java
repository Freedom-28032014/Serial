import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] counts;

    private Basket() {

    }

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.counts = new int[products.length];
    }

    public void addToCart(int productNum, int amount) { //метод добавления amount штук продукта номер productNum в корзину;
        counts[productNum] += amount;

    }

    public void printCart() { // метод вывода на экран покупательской корзины.
        int sum = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(products[i] + " " + prices[i] + " руб./шт " +
                        (prices[i]) * counts[i] + "руб.");
                sum += prices[i] * counts[i];
            }
        }
        System.out.println("Итого:" + sum + "руб.");

    }


    public void saveTxt(File textFile) throws IOException { //метод сохранения корзины в текстовый файл;

        try (PrintWriter writer = new PrintWriter(textFile)) {
            writer.println(products.length);

            String productsline = Arrays.stream(products)
                    .collect(Collectors.joining(""));
            writer.println(productsline);

            for (int price : prices) {
                writer.print(price + " ");

            }
            writer.println();

            for (int count : counts) {
                writer.print(count + " ");

            }
            writer.println();

        }
    }


    public static Basket loadFromTxtFile(File textFile) throws IOException {
        try (Scanner scanner = new Scanner(textFile)) {
            int size = Integer.parseInt(scanner.nextLine());

            String[] products = new String[size];
            String[] productsParts = scanner.nextLine().trim().split(" ");
            for (int i = 0; i < productsParts.length; i++) {
                products[i] = productsParts[i];
            }

            int[] prices = new int[size];
            String[] pricesParts = scanner.nextLine().trim().split("");
            for (int i = 0; i < pricesParts.length; i++) {
                prices[i] = Integer.parseInt(pricesParts[i]);
            }
            int[] counts = new int[size];
            String[] countParts = scanner.nextLine().trim().split("");
            for (int i = 0; i < countParts.length; i++) {
                counts[i] = Integer.parseInt(countParts[i]);

            }


            Basket basket = new Basket();
            basket.products = products;
            basket.prices = prices;
            basket.counts = counts;
            return basket;


        }
    }

    public void saveBin(File binFile) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFile))) {
            out.writeObject(this);
        }
    }

    public static Basket loadFromBinFile(File binFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            return (Basket) in.readObject();
        }
    }
}





