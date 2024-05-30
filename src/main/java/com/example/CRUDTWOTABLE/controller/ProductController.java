/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.CRUDTWOTABLE.controller;

import com.example.CRUDTWOTABLE.model.Category;
import com.example.CRUDTWOTABLE.model.Product;
import com.example.CRUDTWOTABLE.repository.CategoryRepository;
import com.example.CRUDTWOTABLE.repository.ProductRepository;
import com.example.CRUDTWOTABLE.repository.ProductViewRepository;
import com.example.CRUDTWOTABLE.viewmodel.ProductView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    CategoryRepository categoryRepository;
    private List<ProductView> products = new ArrayList<>();
    private List<Category> categorys = new ArrayList<>();
    @ModelAttribute("products")
    private List<ProductView> fillProducts(){
        return products = productViewRepository.fillAllProduct();
    }
    @ModelAttribute("categorys")
    private List<Category> fillCategorys(){
        return categorys = categoryRepository.findAll();
    }
    @GetMapping("/list")
    public String list(){
        return "/Product/list.html";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "/Product/edit.html";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,@ModelAttribute Product productedit){
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productedit.getName());
        product.setPrice(productedit.getPrice());
        product.setId_category(productedit.getId_category());
        productRepository.save(product);
        return "redirect:/product/list";
    }
    @GetMapping("/add")
    public String add(){
        return "/Product/add.html";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/product/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,Model model){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/product/list";
    }
}
