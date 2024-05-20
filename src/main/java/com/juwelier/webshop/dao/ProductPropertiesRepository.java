package com.juwelier.webshop.dao;

import com.juwelier.webshop.models.ProductProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertiesRepository extends JpaRepository<ProductProperties, Long> {
}
