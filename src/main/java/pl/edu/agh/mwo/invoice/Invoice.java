package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Invoice {
    private Collection<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        // TODO: implement
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity");
        }

        for (int i = 0; i < quantity; i++) {
            addProduct(product);

            // TODO: implement
        }
    }

    public BigDecimal getSubtotal() {
            BigDecimal subtotal = BigDecimal.ZERO;
            for (Product product : products) {
                subtotal = subtotal.add(product.getPrice());
            }
            return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal tax = BigDecimal.ZERO;
        for (Product product : products) {
            tax = tax.add(product.getPriceWithTax().subtract(product.getPrice()));
        }
        return tax;
    }

    public BigDecimal getTotal() {
        return getSubtotal().add(getTax());
    }
}
