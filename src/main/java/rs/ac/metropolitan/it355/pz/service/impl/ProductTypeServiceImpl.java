package rs.ac.metropolitan.it355.pz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.metropolitan.it355.pz.model.Order;
import rs.ac.metropolitan.it355.pz.model.ProductType;
import rs.ac.metropolitan.it355.pz.repository.ProductTypeRepository;
import rs.ac.metropolitan.it355.pz.service.ProductTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType getProductTypeById(int productTypeID) {
        Optional<ProductType> productTypeResult = productTypeRepository.findById(productTypeID);

        ProductType productType = null;
        if (productTypeResult.isPresent()) {
            productType = productTypeResult.get();
        } else {
            throw new RuntimeException("There is no product type with id: " + productTypeID);
        }
        return productType;
    }
}

