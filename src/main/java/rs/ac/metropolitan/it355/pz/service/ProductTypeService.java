package rs.ac.metropolitan.it355.pz.service;

import rs.ac.metropolitan.it355.pz.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> findAll();

    ProductType getProductTypeById(int productTypeID);
}
