package org.group.smart_city.WebServices;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Interfaces.GroupService;
import org.group.smart_city.Service.Interfaces.GroupService;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/group")
public class GroupWs {
    @Autowired
    private GroupService groupService;
    @Autowired
    JwtUtil jwtUtil;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Group>> SignUp(@RequestBody GroupDto group, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Group> notFoundResponse = new ApiResponse<>(401, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        Group save = groupService.Create(group);
        ApiResponse<Group> response = new ApiResponse<>(200, "Citizen Created successfully", save);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Group>> Update(@PathVariable String id, @RequestBody GroupDto groupDto, @RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Group> notFoundResponse = new ApiResponse<>(401, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        Group group = groupService.Update(id,groupDto);
        ApiResponse<Group> response = new ApiResponse<>(200, "Citizen updated successfully", group);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> Delete(@PathVariable String id,@RequestHeader("Authorization") String token) {
        if(!jwtUtil.validateToken(token)){
            ApiResponse<String> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        groupService.Delete(id);
        ApiResponse<String> apiResponse = new ApiResponse<>(200, "Citizen deleted successfully", id);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Group>> GetCitizenById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        Group group = groupService.GetById(id);
        if(!jwtUtil.validateToken(token)){
            ApiResponse<Group> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        ApiResponse<Group> apiResponse = new ApiResponse<>(200, "Group found", group);
        return ResponseEntity.ok(apiResponse);
    }

}
