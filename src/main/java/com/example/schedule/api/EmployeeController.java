package com.example.schedule.api;

import com.example.schedule.model.api.EmployeeResponse;
import com.example.schedule.model.api.MainResponse;
import com.example.schedule.model.entity.EmployeeEntity;
import com.example.schedule.model.enums.ResponseEnum;
import com.example.schedule.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")

@Component
public class EmployeeController {

    private final EmployeeService employeeService;
    private EmployeeEntity employeeEntity;
    private EmployeeResponse employeeResponse;

    @GetMapping("/getEmployee")
    public ResponseEntity<EmployeeEntity> getEmployee (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getEmployee");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if (queryParam.get("id") != null) {
            return ResponseEntity.ok(employeeService.getEmployee(queryParam.get("id").toString()));
        } else {
            return null;
        }
    }

    @GetMapping("/getEmployeeAll")
    public ResponseEntity<List<EmployeeEntity>> getEmployeeAll (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/getEmployeeAll");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        return ResponseEntity.ok(employeeService.getEmployeeAll());
    }

    @PostMapping("/postEmployee")
    public EmployeeResponse postEmployee (
            @RequestParam Map<String, Object> queryParam,
            @RequestHeader Map<String, Object> queryHeader,
            @RequestBody(required = false) Map<String, Object> queryBody
    ) {
        System.out.println("/postEmployee");
        System.out.println(queryParam);
        System.out.println(queryHeader);
        System.out.println(queryBody);

        if ((queryBody.get("name") != null) && (queryBody.get("status") != null) && (queryBody.get("position") != null)) {
            employeeEntity = employeeService.postEmployee(
                    queryBody.get("name").toString(),
                    queryBody.get("status").toString(),
                    queryBody.get("position").toString()
            );
            employeeResponse = new EmployeeResponse(
                    new MainResponse(
                            ResponseEnum.SUCCESS,
                            null
                    ),
                    employeeEntity
            );
        } else {
            employeeResponse = new EmployeeResponse(
                    new MainResponse(
                            ResponseEnum.ERROR,
                            "Переданы не все параметры"
                    ),
                    null
            );
        }
        return employeeResponse;
    }

}
