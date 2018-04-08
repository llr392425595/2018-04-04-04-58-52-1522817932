package practice3;

import java.math.BigDecimal;

public class PriceCalculator {

  private Order order;

  PriceCalculator(Order order) {
    this.order = order;
  }

  public BigDecimal calculate() {
    BigDecimal subTotal = new BigDecimal(0);

    // Total up line items
    for (OrderLineItem lineItem : order.orderLineItemList) {
      subTotal = subTotal.add(lineItem.getPrice());
    }

    // Subtract discounts
    for (BigDecimal discount : order.discounts) {
      subTotal = subTotal.subtract(discount);
    }

    // calculate tax
    BigDecimal tax = subTotal.multiply(this.order.tax);

    // calculate GrandTotal

    return subTotal.add(tax);
  }

}
