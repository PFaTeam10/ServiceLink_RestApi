package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Interfaces.CitizenService;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizen")
public class CitizenWs {
    @Autowired
    private CitizenService citizenService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Citizen>> SignUp(@RequestBody CitizenDto citizenDto) {
        Citizen save = citizenService.Create(citizenDto);
        ApiResponse<Citizen> response = new ApiResponse<>(200, "Citizen Created successfully", save);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<String>> Login(@RequestBody CitizenDto citizenDto) {
        Citizen citizen = citizenService.Authenticate(citizenDto);
        System.out.println("citizen2 : " + citizen);
        String token = jwtUtil.generateToken(citizen.getId());
        System.out.println("generated : " + token);
        ApiResponse<String> response = new ApiResponse<>(200, "Citizen Logged in successfully", token);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Citizen>> Update(@PathVariable String id, @RequestBody CitizenDto citizenDto,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Citizen> notFoundResponse = new ApiResponse<>(401, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        Citizen citizen = citizenService.Update(citizenDto);
        ApiResponse<Citizen> response = new ApiResponse<>(200, "Citizen updated successfully", citizen);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> Delete(@PathVariable String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        citizenService.Delete(id);
        ApiResponse<String> apiResponse = new ApiResponse<>(200, "Citizen deleted successfully", id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Citizen>> GetCitizenById(@PathVariable String id, @RequestHeader("Authorization") String token) {

        if(!jwtUtil.validateToken(token)){
            ApiResponse<Citizen> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        Citizen citizen = citizenService.GetById(id);
        ApiResponse<Citizen> apiResponse = new ApiResponse<>(200, "Citizen found", citizen);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse<Citizen>> updateCitizen(@RequestBody CitizenDto citizenDto,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Citizen> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        Citizen citizen = citizenService.Update(citizenDto);
        ApiResponse<Citizen> response = new ApiResponse<>(200, "Project updated successfully", citizen);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/get")
    public ResponseEntity<ApiResponse<Citizen>> GetCitizenById(@RequestHeader("Authorization") String token) {

        if(!jwtUtil.validateToken(token)){
            ApiResponse<Citizen> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        String idCitizen = jwtUtil.getIdFromToken(token);
       Citizen citizen = citizenService.GetById(idCitizen);
        ApiResponse<Citizen> apiResponse = new ApiResponse<>(200, "Citizen found", citizen);
        return ResponseEntity.ok(apiResponse);
    }

 }
