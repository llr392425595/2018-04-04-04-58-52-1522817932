package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

  Receipt() {
    tax = new BigDecimal(0.1);
    tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  private BigDecimal tax;

  public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
    BigDecimal subTotal = getDiscountedSubTotal(products, items);
    BigDecimal grandTotal = subTotal.add(subTotal.multiply(tax));
    return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  private BigDecimal getDiscountedSubTotal(List<Product> products, List<OrderItem> items) {
    List<OrderItem> orderItemList = createOrderList(products, items);
    return orderItemList.stream().map(OrderItem::getDiscountedItemTotal).reduce(BigDecimal::add).get();
  }

  private List<OrderItem> createOrderList(List<Product> products,List<OrderItem> items) {
    items.forEach(item -> products.forEach(product -> {
      if (product.getCode() == item.getCode()) {
        item.setProduct(product);
      }
    }));
    return items;
  }
}
