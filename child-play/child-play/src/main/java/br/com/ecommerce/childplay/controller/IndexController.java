package br.com.ecommerce.childplay.controller;

import java.sql.SQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/childplay")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index() throws ClassNotFoundException, SQLException {
        return new ModelAndView("index");
    }
    
}
