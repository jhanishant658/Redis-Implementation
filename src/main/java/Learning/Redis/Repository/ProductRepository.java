package Learning.Redis.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Learning.Redis.Entity.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    
} 