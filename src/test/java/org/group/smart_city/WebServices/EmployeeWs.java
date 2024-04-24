package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Entities.Employee;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Implementations.EmployeeImp;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeWs {
    @Autowired
    private EmployeeImp employeeImp;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody EmployeeDto employeeDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Employee> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        ApiResponse<Employee> response = new ApiResponse<>(200, "Employee Created successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Employee> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to retrieve an employee by ID
        Employee employee = employeeImp.getById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<Employee> response = new ApiResponse<>(200, "Employee found successfully", employee);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeDto updatedEducationDto,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Employee> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to update an employee
        Employee employee = employeeImp.Update(id, updatedEducationDto);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<Employee> response = new ApiResponse<>(200, "Employee updated successfully", employee);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteEmployee(@PathVariable("id") String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to delete an employee
        employeeImp.Delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Employee deleted successfully", id));
    }
}
