package practice3;

import java.math.BigDecimal;

public class PriceCalculator {

  private Order order;

  PriceCalculator(Order order) {
    this.order = order;
  }

  public BigDecimal calculate() {
    BigDecimal discountedSubTotal = getDiscountedSubtotal();
    return getGrandTotal(discountedSubTotal, this.order.tax);
  }

  private BigDecimal getSubTotal() {
    return order.orderLineItemList.stream().map(OrderLineItem::getPrice).reduce(BigDecimal::add)
        .get();
  }

  private BigDecimal discount() {
    return order.discounts.stream().reduce(BigDecimal::add).get();
  }

  private BigDecimal getDiscountedSubtotal() {
    return getSubTotal().subtract(discount());
  }

  private BigDecimal getGrandTotal(BigDecimal discountedSubTotal,BigDecimal taxRate) {
    BigDecimal taxMoney = discountedSubTotal.multiply(taxRate);
    return discountedSubTotal.add(taxMoney);
  }

}
