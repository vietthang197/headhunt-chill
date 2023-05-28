package vn.com.headhuntchill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/job-detail")
    public String jobDetail() {
        return "job-detail";
    }

    @GetMapping("/search-job")
    public String searchJob() {
        return "search-job";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
