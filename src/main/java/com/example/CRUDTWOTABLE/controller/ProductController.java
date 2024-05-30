/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.CRUDTWOTABLE.controller;

import com.example.CRUDTWOTABLE.repository.ProductRepository;
import com.example.CRUDTWOTABLE.repository.ProductViewRepository;
import com.example.CRUDTWOTABLE.viewmodel.ProductView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HieuCute
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductViewRepository productViewRepository;
    private List<ProductView> products = new ArrayList<>();
    @GetMapping("/list")
    public String list(Model model){
        products = productViewRepository.fillAllProduct();
        model.addAttribute("products", products);
        return "/Product/list.html";
    }
}
