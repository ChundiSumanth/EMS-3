package com.anarghya.FoodDeliveryProduct.Controller;

//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.halim.web.dto.ProductRepositry;
//import com.halim.web.model.Product;
//import com.halim.web.service.ProductService;
//
//@RestController
//@CrossOrigin(origins = "*")
//public class ProductController {
//	
//	@Autowired
//	private ProductService productService;
//	
//	private final ProductRepositry productRepo;
//
//	@Autowired
//	public ProductController(ProductRepositry productRepo) {
//	    this.productRepo = productRepo;
//	}
//
//	
//	
//	@GetMapping("/showProducts")
//    public String showProducts(Model model) {
//        List<Product> products = productService.getAllProduct();
//        model.addAttribute("products", products);
//        return "/listProducts.html"; 
//    }
//    
//
//@PostMapping("/addP")
//public String saveProduct(@RequestParam("file") MultipartFile file,
//      @RequestParam("pname") String name,
//      @RequestParam("price") int price,
//      @RequestParam("desc") String desc) {
//  productService.saveProductToDB(file, name, desc, price);
//  return "/listProducts.html";
//}
//    
//    @GetMapping("/deleteProd/{id}")
//    public String deleteProduct(@PathVariable("id") Long id)
//    {
//    	
//    	productService.deleteProductById(id);
//    	return "/listProducts.html";
//    }
//    
//    @PostMapping("/changeName")
//    public String changePname(@RequestParam("id") Long id,
//    		@RequestParam("newPname") String name)
//    {
//    	productService.chageProductName(id, name);
//    	return "/listProducts.html";
//    }
//    @PostMapping("/changeDescription")
//    public String changeDescription(@RequestParam("id") Long id ,
//    		@RequestParam("newDescription") String description)
//    {
//    	productService.changeProductDescription(id, description);
//    	return "/listProducts.html";
//    }
//    
//    @PostMapping("/changePrice")
//    public String changePrice(@RequestParam("id") Long id ,
//    		@RequestParam("newPrice") int price)
//    {
//    	productService.changeProductPrice(id, price);
//    	return "/listProducts.html";
//    }
//}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.halim.web.dto.ProductRepositry;
//import com.halim.web.model.Product;
//import com.halim.web.service.ProductService;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "*")
//public class ProductController {
//
//    @Autowired
//    private ProductRepositry productRepositry;
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/listProduct")
//    public List<Product> listProduct() {
//        return productRepositry.findAll();
//    }
//
//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts();
//    }
//
//    @GetMapping("/products/{category}")
//    public List<Product> getProductsByCategory(@PathVariable String category) {
//        return productService.getProductsByCategory(category);
//    }
//
//    
//    
//    @PostMapping(path = "/addP", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> saveProduct(@RequestParam("file") MultipartFile file,
//            @RequestParam("name") String name,
//            @RequestParam("description") String description,
//            @RequestParam("price") int price,
//            @RequestParam("category") String category) {
//        try {
//            productService.saveProductToDB(file, name, description, price, category);
//            return ResponseEntity.ok().body("Product saved successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving product: " + e.getMessage());
//        }
//    }
//
//}






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.anarghya.FoodDeliveryProduct.Entity.Product;
import com.anarghya.FoodDeliveryProduct.Repo.ProductRepository;
import com.anarghya.FoodDeliveryProduct.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepositry;

    @Autowired
    private ProductService productService;

    @GetMapping("/listProduct")
    public List<Product> listProduct() {
        return productRepositry.findAll();
    }
    
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

   
    @PostMapping(path = "/addP", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveProduct(
    		@RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            
            @RequestParam("price") int price,
            @RequestParam("category") String category){
        try {
            productService.saveProductToDB(file, name, price, category);
            return ResponseEntity.ok().body("Item saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving product: " + e.getMessage());
        }
    }
    
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") Long id,
            @RequestParam("file") MultipartFile file,            
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("category") String category) {
            
        try {
            productService.updateProduct(id, file, name, price, category);
            Product updatedProduct = productService.getProductById(id); 
            return ResponseEntity.ok().body(updatedProduct); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product");
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting product");
        }
    }

}
