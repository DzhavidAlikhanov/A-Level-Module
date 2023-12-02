package Test2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("src\\Test2\\input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Product> products = lines.stream()
                .skip(1)
                .map(line -> {
                    String[] parts = line.split("\\|");
                    String name = parts[0].trim();  // Change index to 0 for the name
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim().replace(',', '.'));
                    return new Product(name, quantity, price);
                })
                .collect(Collectors.toList());

        List<Product> sortedByQuantity = products.stream()
                .sorted(Comparator.comparingInt(Product::getQuantity).reversed())
                .collect(Collectors.toList());
        printToFile("1. Сортировка по количеству (по убыванию):", sortedByQuantity);

        long totalQuantity = products.stream()
                .mapToLong(Product::getQuantity)
                .sum();
        printToFile("2. Общее количество продуктов в холодильнике: " + totalQuantity);

        double averagePrice = products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
        printToFile("3. Средняя цена продуктов: " + averagePrice);

        List<Product> sortedByPrice = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
        printToFile("4. Сортировка по цене (по убыванию):", sortedByPrice);

        double totalCost = products.stream()
                .mapToDouble(product -> product.getQuantity() * product.getPrice())
                .sum();
        printToFile("5. Общая стоимость продуктов: " + totalCost);
    }

    private static void printToFile(String header, List<Product> products) {
        System.out.println(header);
        products.forEach(System.out::println);
        System.out.println();

        try {
            Files.write(Paths.get("src\\Test2\\output.txt"), (header + "\n" + products.stream()
                    .map(Product::toString)
                    .collect(Collectors.joining("\n")) + "\n\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printToFile(String message) {
        System.out.println(message);

        try {
            Files.write(Paths.get("src\\Test2\\output.txt"), (message + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Product {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s | %d | %.2f", name, quantity, price);
    }
}
