package com.saurabh.demo.controller;
import com.saurabh.demo.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathService mathService;

    // 1. Multiplication
    @GetMapping("/multiply")
    public int multiply(@RequestParam int a, @RequestParam int b) {
        return mathService.multiply(a, b);
    }

    // 2. Division
    @GetMapping("/divide")
    public double divide(@RequestParam int a, @RequestParam int b) {
        return mathService.divide(a, b);
    }

    // 3. Table
    @GetMapping("/table")
    public String table(@RequestParam int number, @RequestParam(defaultValue = "10") int upTo) {
        return mathService.generateTable(number, upTo);
    }

    // 4. Counting
    @GetMapping("/count")
    public String count(@RequestParam int n) {
        return mathService.countUpTo(n);
    }

}

