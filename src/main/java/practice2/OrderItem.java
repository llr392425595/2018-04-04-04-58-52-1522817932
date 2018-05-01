package practice2;

import java.math.BigDecimal;

public class OrderItem {

  private Integer count;
  private long code;
  private Product product;

  OrderItem(long code, int count) {
    this.code = code;
    this.count = count;
    this.product = null;
  }

  public OrderItem(Integer count, long code, Product product) {
    this.count = count;
    this.code = code;
    this.product = product;
  }

  public Integer getCount() {
    return count;
  }

  public long getCode() {
    return code;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  private BigDecimal getItemTotal() {
    return this.product == null ? BigDecimal.valueOf(0)
        : BigDecimal.valueOf(this.count).multiply(this.product.getPrice());
  }

  private BigDecimal getReducedPrice() {
    return getItemTotal().multiply(this.product.getDiscountRate());
  }

  public BigDecimal getDiscountedItemTotal() {
    return getItemTotal().subtract(getReducedPrice());
  }
}
