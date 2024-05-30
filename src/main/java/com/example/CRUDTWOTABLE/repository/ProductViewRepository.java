    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.CRUDTWOTABLE.repository;

import com.example.CRUDTWOTABLE.viewmodel.ProductView;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HieuCute
 */
@Repository
public interface ProductViewRepository extends JpaRepository<ProductView, Long> {

    @Query("Select new ProductView (p.id,p.name,p.price,p.id_category,c.name) from Product p\n"
            + "join Category c\n"
            + "on p.id_category = c.id")
    List<ProductView> fillAllProduct();

}
