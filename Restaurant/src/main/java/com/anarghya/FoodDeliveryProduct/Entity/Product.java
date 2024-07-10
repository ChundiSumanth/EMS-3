package com.anarghya.FoodDeliveryProduct.Entity;


import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "offer-page")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String category; 
    


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

  
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getFile() {
        // TODO Auto-generated method stub
        return null;
    }

   

    public Product(Long id, String name, int price,
            String image, String category) {
        super();
        this.id = id;
        this.name = name;
       
        this.price = price;
                       this.image = image;
        this.category = category;
           }

    public Product() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name  +  ", price=" + price
                 +  ", image=" + image
                + ", category=" + category +  "]";
    }
    
   
}
