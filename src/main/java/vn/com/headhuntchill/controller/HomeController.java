package vn.com.headhuntchill.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.com.headhuntchill.dto.JobDto;
import vn.com.headhuntchill.dto.ResponseData;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/")
public class HomeController {

    private final WebClient webClient;

    public HomeController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/")
    public String homePage(Model model) {
        Mono<ResponseData<List<JobDto>>> responseDataMono = this.webClient.get().uri("http://localhost:9000/job/v1/popular-job")
                .retrieve().bodyToMono(new ParameterizedTypeReference<ResponseData<List<JobDto>>>() {}).doOnError(throwable -> {
                    System.out.println("Errr");
                });
        Flux<JobDto> jobDtoFlux = responseDataMono.flatMapMany(responseData -> Flux.fromIterable(responseData.getData()));
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(jobDtoFlux);
        model.addAttribute("jobList", reactiveDataDrivenMode);
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
