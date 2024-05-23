package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.EmployeeDto;
import org.group.smart_city.Dto.ReclamationDto;
import org.group.smart_city.Entities.Reclamation;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Interfaces.ReclamationService;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reclamation")
public class ReclamationWs {
    @Autowired
    private ReclamationService reclamationService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Reclamation>> createReclamation(@RequestBody ReclamationDto reclamationDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Reclamation> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        String idCitizen = jwtUtil.getIdFromToken(token);
        Reclamation reclamation = reclamationService.create(reclamationDto,idCitizen);
        ApiResponse<Reclamation> response = new ApiResponse<>(200, "Reclamation Created successfully", reclamation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reclamation>> getReclamationById(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Reclamation> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        Reclamation reclamation = reclamationService.getById(id);
        if (reclamation == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<Reclamation> response = new ApiResponse<>(200, "Reclamation found successfully", reclamation);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Reclamation>> update(@PathVariable("id") String id, @RequestBody ReclamationDto reclamationDto,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Reclamation> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        Reclamation reclamation = reclamationService.Update(id, reclamationDto);
        if (reclamation == null) {
            return ResponseEntity.notFound().build();
        }
        ApiResponse<Reclamation> response = new ApiResponse<>(200, "Reclamation updated successfully", reclamation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        // Add logic to delete an employee
        reclamationService.Delete(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "reclamation deleted successfully", id));
    }
    @GetMapping("/serviceprovider/ignored")
    public ResponseEntity<ApiResponse<List<Reclamation>>> ReportsByServiceRejected(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<List<Reclamation>> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        String idService = jwtUtil.getIdFromToken(token);
        List<Reclamation> reclamations = reclamationService.GetByServiceProviderIgnored(idService);
        return ResponseEntity.ok(new ApiResponse<>(200, "reclamation List", reclamations));
    }
    @GetMapping("/serviceprovider/accepted")
    public ResponseEntity<ApiResponse<List<Reclamation>>> ReportsByServiceAccepted(@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<List<Reclamation>> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
        }
        String idService = jwtUtil.getIdFromToken(token);
        List<Reclamation> reclamations = reclamationService.GetByServiceProviderAccepted(idService);
        return ResponseEntity.ok(new ApiResponse<>(200, "reclamation List", reclamations));
    }
//    @GetMapping("/serviceprovider")
//    public ResponseEntity<ApiResponse<List<Reclamation>>> ReportsByService(@RequestHeader("Authorization") String token) {
//        if(!jwtUtil.validateToken(token)){
//            ApiResponse<List<Reclamation>> unauthorizedResponse = new ApiResponse<>(401, "Unauthorized", null);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedResponse);
//        }
//        String idService = jwtUtil.getIdFromToken(token);
//        System.out.println(idService);
//        List<Reclamation> reclamations = reclamationService.GetByServiceProvider(idService);
//        return ResponseEntity.ok(new ApiResponse<>(200, "reclamation List", reclamations));
//    }
}
