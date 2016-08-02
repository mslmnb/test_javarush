package kz.gala.hibernate.controller;

import kz.gala.hibernate.model.Test;
import kz.gala.hibernate.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Gala on 17.07.2016.
 */
@Controller
public class TestController {
    private TestService testService;

    @Autowired(required = true)
    @Qualifier(value = "testService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "tests", method = RequestMethod.GET)
    public String listTests(@ModelAttribute("filter") String filter, Model model){

        model.addAttribute("test", new Test());
        model.addAttribute("listTests", this.testService.listTests(filter));
        model.addAttribute("filter", filter);
        if (filter=="ready")
            model.addAttribute("filterMsg", "    Применен фильтр: дела выполнены");
        else
            if (filter=="unready")
                model.addAttribute("filterMsg", "    Применен фильтр: дела не выполнены");
            else
                model.addAttribute("filterMsg", "");
        return "tests";
    }

    @RequestMapping(value = "/tests/add", method = RequestMethod.POST)
    public String addTest(@ModelAttribute("test") Test test){
        System.out.println("addtest: "+ test.getCreateddate());
        System.out.println("addtest: "+ test.getReadydate());
        System.out.println("addtest: "+ test.getIsready());
        if(test.getId() == 0){
            this.testService.addTest(test);
        }else {
            this.testService.updateTest(test);
        }
        return "redirect:/tests";
    }

    @RequestMapping("/remove/{id}")
    public String removeTest(@PathVariable("id") int id){
        this.testService.removeTest(id);

        return "redirect:/tests";
    }

    @RequestMapping("edit/{id}")
    public String editTest(@PathVariable("id") int id, Model model){
        Test test = this.testService.getTestById(id);

        model.addAttribute("listTests", this.testService.listTests("all"));
        model.addAttribute("test", test);
        System.out.println("editTest: "+ test.getId());
        System.out.println("editTest: "+ test.getCreateddate());
        System.out.println("editTest: "+ test.getIsready());

        return "tests";

    }

    @RequestMapping(value = "/tests/filter")
     public String filterTest(@ModelAttribute("filter") String filter, Model model){

        return "redirect:/tests";
    }

    // для определения шаблона ввода даты
    /*@InitBinder
    public void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }*/
}
