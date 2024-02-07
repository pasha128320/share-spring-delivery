package com.pizza.delivery.controller;


import com.pizza.delivery.dto.PositionsDto;
import com.pizza.delivery.model.Positions;
import com.pizza.delivery.security.SecurityUtil;
import com.pizza.delivery.service.impl.PositionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import static com.pizza.delivery.mappers.PositionsMappers.mapToPositions;
import java.util.List;

@Controller
public class PositionsController {

    PositionsServiceImpl positionsService;

    @Autowired
    public PositionsController(PositionsServiceImpl positionsService) {
        this.positionsService = positionsService;
    }

    @GetMapping
    public String getHomePage(Model model){
        List<PositionsDto> positions = positionsService.findAllPositions();
        model.addAttribute("positions", positions);

        model.addAttribute("title","Главная страница");
        return "home";
    }

    @GetMapping("/add")
    public String addPosition(Model model){
        Positions positions = new Positions();
        model.addAttribute("position",positions);
        return "add-position";
    }

    @PostMapping("/add")
    public String postAddPosition(@ModelAttribute("position") PositionsDto dto, Model model){
        Positions positions = mapToPositions(dto);
        positionsService.savePositions(positions);

        return"redirect:/";
    }




}
