package com.example.springmvc.controller;

import com.example.springmvc.model.Employee;
import com.example.springmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/employees/add", method = RequestMethod.GET)
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "edit";
    }

    @RequestMapping(path = "/employees", method = RequestMethod.POST)
    public String saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    }

    @RequestMapping(path = "/employees/edit/{id}", method = RequestMethod.GET)
    public String editEmployee(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("employee", employeeRepository.findById(id));
        return "edit";
    }

    @RequestMapping(path = "/employees/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }
}
