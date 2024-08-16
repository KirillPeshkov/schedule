package com.example.schedule.api;

import com.example.schedule.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")

@Component
public class MainController {

    private final MainService mainService;

    @EventListener(ApplicationReadyEvent.class)
    public void main() {
        System.out.println("/createData");
        mainService.createData();
    }

}
