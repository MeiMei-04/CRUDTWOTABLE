/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.CRUDTWOTABLE.controller;

import com.example.CRUDTWOTABLE.model.Category;
import com.example.CRUDTWOTABLE.repository.CategoryRepository;
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
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
    private List<Category> categorys = new ArrayList<>();
    @GetMapping("/list")
    public String fillAll(Model model){
        categorys = categoryRepository.findAll();
        model.addAttribute("categorys", categorys);
        return "/Category/list.html";
    }
    @GetMapping("/add")
    public String add(){
        return "";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/category/list";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model){
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        return "";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,@ModelAttribute Category categoryEdit){
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(categoryEdit.getName());
        categoryRepository.save(category);
        return "redirect:/category/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
        return "redirect:/category/list";
    }
}
