package com.yi.payroll;

import com.yi.payroll.exceptions.EmployeeNotFoundException;
import com.yi.payroll.model.Employee;
import com.yi.payroll.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> all(){
        return repository.getAll();
    }

//    @PostMapping("/employees")
//    Employee newEmployee(@RequestBody Employee newEmployee){
//        return repository.save(newEmployee);
//    }

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id){
        return repository.get(id);
    }

}
