package practice2;

import java.math.BigDecimal;
import java.util.List;
import practice3.Order;

public class Receipt {

  public Receipt() {
    tax = new BigDecimal(0.1);
    tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  private BigDecimal tax;

  public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
    BigDecimal subTotal = calculateSubtotal(products, items);
    subTotal = getSubtractDiscounts(products, items, subTotal);

    BigDecimal grandTotal = getGrandTotal(subTotal,tax);

    return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  private BigDecimal getGrandTotal(BigDecimal subTotal, BigDecimal tax) {
    return subTotal.add(subTotal.multiply(tax));
  }

  private BigDecimal getReducedPrice(Product product, OrderItem item) {
    return product.getPrice().multiply(product.getDiscountRate())
        .multiply(new BigDecimal(item.getCount()));
  }

  private BigDecimal getSubtractDiscounts(List<Product> products, List<OrderItem> items, BigDecimal subTotal) {
    for (Product product : products) {
      OrderItem curItem = findOrderItemByProduct(items, product);
      BigDecimal reducedPrice = getReducedPrice(product, curItem);
      subTotal = subTotal.subtract(reducedPrice);
    }
    return subTotal;
  }

  private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
    OrderItem curItem = null;
    for (OrderItem item : items) {
      if (item.getCode() == product.getCode()) {
        curItem = item;
        break;
      }
    }
    return curItem;
  }

  private BigDecimal getItemTotal(Product product,OrderItem item) {
    return product.getPrice().multiply(new BigDecimal(item.getCount()));
  }

  private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
    BigDecimal subTotal = new BigDecimal(0);
    for (Product product : products) {
      OrderItem item = findOrderItemByProduct(items, product);
      BigDecimal itemTotal = getItemTotal(product, item);
      subTotal = subTotal.add(itemTotal);
    }
    return subTotal;
  }
}
