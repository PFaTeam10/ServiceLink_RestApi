package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Dto.ServiceTypeDto;
import org.group.smart_city.Entities.ServiceType;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Implementations.ServiceTypeImp;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceType")
public class ServiceTypeWs {
    @Autowired
    private ServiceTypeImp serviceTypeImp;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ServiceType>> create(@RequestBody EmployeeDto employeeDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceType> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        ApiResponse<ServiceType> response = new ApiResponse<>(200, "serviceType Created successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceType>> getServiceTypeById(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceType> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to retrieve an employee by ID
        ServiceType serviceType = serviceTypeImp.getById(id);
        if (serviceType == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<ServiceType> response = new ApiResponse<>(200, "ServiceType found successfully", serviceType);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceType>> update(@PathVariable("id") String id, @RequestBody ServiceTypeDto serviceTypeDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceType> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to update an employee
        ServiceType serviceType = serviceTypeImp.Update(id, serviceTypeDto);
        if (serviceType == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<ServiceType> response = new ApiResponse<>(200, "serviceType updated successfully", serviceType);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to delete an employee
        serviceTypeImp.Delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "serviceType deleted successfully", id));
    }
}
