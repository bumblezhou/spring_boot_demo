package com.example.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcurementController {
  @GetMapping("/procurements")
  public String procurements(Model model){
      return "procurements";
  }
}
