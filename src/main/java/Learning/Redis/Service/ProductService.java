package Learning.Redis.Service;

import org.springframework.stereotype.Service;

import Learning.Redis.Entity.Product;
import Learning.Redis.Repository.ProductRepository;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final RedisService redisService;
    
    ProductService(ProductRepository productRepository, RedisService redisService) {
        this.productRepository = productRepository;
        this.redisService = redisService;
    }
   public Product getProductById(String id) {
      Product cachedProduct = (Product) redisService.get(id);
      if(cachedProduct != null) {
          return cachedProduct;
      }

        Product product =  productRepository.findById(id).orElse(null);
       redisService.set(id, product); // Cache the product for 1 hour
        return product ; 
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
