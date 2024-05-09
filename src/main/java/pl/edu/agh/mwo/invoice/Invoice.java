package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }
    public BigDecimal getInvoiceNumber() {
        BigDecimal totalInvoice = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalInvoice = totalInvoice.add(product.getPrice().multiply(quantity));
        }
        return totalInvoice;

    }

    public String getProductList() {
        StringBuilder productList = new StringBuilder();
        productList.append("Invoice Number: ").append(getInvoiceNumber()).append("\n");
        int positionCount = 1;

        Map<Product, Integer> groupedProducts = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            groupedProducts.put(product, groupedProducts.getOrDefault(product, 0) + quantity);
        }
        for (Map.Entry<Product, Integer> entry : groupedProducts.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            BigDecimal totalPrice = product.getPriceWithTax().multiply(new BigDecimal(quantity));

            productList.append("Position ").append(positionCount).append(":\n");
            productList.append("  Name: ").append(product.getName()).append("\n");
            productList.append("  Quantity: ").append(quantity).append("\n");
            productList.append("  Price: ").append(product.getPriceWithTax()).append("\n");
            productList.append("  Total Price: ").append(totalPrice).append("\n");

            positionCount++;
        }
        productList.append("Number of positions: ").append(groupedProducts.size()).append("\n");
        return productList.toString();
    }

    public void printInvoice() {
        String productList = getProductList();
        System.out.println("=== Invoice ===");
        System.out.println(productList);
        System.out.println("===============");
    }

    }


