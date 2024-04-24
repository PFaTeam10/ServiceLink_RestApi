package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.ServiceProviderDto;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Implementations.ServiceProviderImp;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/serviceprovider")
public class ServiceProviderWs {
    @Autowired
    private ServiceProviderImp serviceProviderImp;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ServiceProvider>> create(@RequestBody ServiceProviderDto serviceProviderDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceProvider> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        ApiResponse<ServiceProvider> response = new ApiResponse<>(200, "ServiceProvider Created successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceProvider>> getById(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceProvider> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to retrieve an employee by ID
        ServiceProvider serviceProvider = serviceProviderImp.getById(id);
        if (serviceProvider == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<ServiceProvider> response = new ApiResponse<>(200, "ServiceProvider found successfully", serviceProvider);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceProvider>> update(@PathVariable("id") String id, @RequestBody ServiceProviderDto serviceProviderDto,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<ServiceProvider> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to update an employee
        ServiceProvider employee = serviceProviderImp.Update(id , serviceProviderDto);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<ServiceProvider> response = new ApiResponse<>(200, "ServiceProvider updated successfully", employee);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to delete an employee
        serviceProviderImp.Delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "ServiceProvider deleted successfully", id));
    }
}
