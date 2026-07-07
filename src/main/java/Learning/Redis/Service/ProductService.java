package Learning.Redis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Learning.Redis.Entity.Product;
import Learning.Redis.Repository.ProductRepository;
@Service
public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }
   public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }
}
