/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.StoreSua.controller;

import com.example.StoreSua.model.ChucVu;
import com.example.StoreSua.model.NguoiDung;
import com.example.StoreSua.repository.ChucVuRepository;
import com.example.StoreSua.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HieuCute
 */
@Controller
public class RegisterController {

    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    ChucVuRepository chucVuRepository;

    @PostMapping("/sign_up")
    public String register(@RequestParam("taikhoan") String taikhoan, @RequestParam("matkhau") String matkhau, @RequestParam("matkhauxacnhan") String matkhauxacnhan, Model model) {
        System.out.println(taikhoan);
        System.out.println(matkhau);
        System.out.println(matkhauxacnhan);
        NguoiDung nguoiDung = new NguoiDung();
        Long y = 1L;
        System.out.println(y);
        ChucVu chucVu = chucVuRepository.findById(y).orElseThrow();
        System.out.println(chucVu.getChucvu_id()+chucVu.getTenchucvu());
        String textFail = null;
        if (matkhau.equals(matkhauxacnhan)) {
            nguoiDung.setTaikhoan(taikhoan);
            nguoiDung.setMatkhau(matkhau);
            nguoiDung.setChucVu(chucVu);
            nguoiDungRepository.save(nguoiDung);
            return "redirect:/login";
        }
        textFail = "Register false";
        model.addAttribute("textFail", textFail);
        return "/login/login.html";
    }
}
