package fr.umlv.m2.jee.persistence.product;

import fr.umlv.m2.jee.persistence.category.Category;

import java.io.Serializable;

public class Product implements Serializable{
  private String id;
  private String name;
  private String price;
  private String description;
  private String imageUrl;
  private Category category;
  // categoryId is only used by ProductDao to fill the category field above
  // initialized to -1 which is an invalid id and will raise an exception in
  // productDao.find if it is not set with a valid id
  private long categoryId = -1;

  public Product(){
  }

  public Product(String id, String name, String price, String description,
      String imageUrl, Category category) {
    super();
    this.id = id;
    this.name = name;
    this.price = price;
    this.description = description;
    this.imageUrl = imageUrl;
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public double getPriceDouble() {
    return Double.parseDouble(price);
  }

  public void setPriceDouble(double price) {
    this.price = String.valueOf(price);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
    this.categoryId = category.getId();
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
    result = prime * result
        + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((price == null) ? 0 : price.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (categoryId != other.categoryId)
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (imageUrl == null) {
      if (other.imageUrl != null)
        return false;
    } else if (!imageUrl.equals(other.imageUrl))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", price=" + price
        + ", description=" + description + ", imageUrl=" + imageUrl
        + ", category=" + category + ", categoryId=" + categoryId + "]";
  }

}
