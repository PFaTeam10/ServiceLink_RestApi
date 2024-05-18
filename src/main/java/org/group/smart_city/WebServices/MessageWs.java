package org.group.smart_city.WebServices;


import org.group.smart_city.Dto.MessageDto;

import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.group.smart_city.Entities.ServiceProvider;
import org.group.smart_city.Response.ApiResponse;
import org.group.smart_city.Service.Interfaces.CitizenService;
import org.group.smart_city.Service.Interfaces.GroupService;
import org.group.smart_city.Service.Interfaces.MessageService;
import org.group.smart_city.Service.Interfaces.ServiceProviderService;
import org.group.smart_city.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageWs {
    @Autowired
    private MessageService messageService;

    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private CitizenService citizenService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Message>> Create(@RequestBody MessageDto messageDto) {
//        if(!jwtUtil.validateToken(token)){
//            ApiResponse<Message> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
//        }
        System.out.print("messageDto"+messageDto.toString());
        Message save = messageService.Create(messageDto);
        ApiResponse<Message> response = new ApiResponse<>(200, "MessageDto Created successfully", save);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Message>>> GetMessagesByIdGroup(@RequestHeader("Authorization") String token) {

        if(!jwtUtil.validateToken(token)){
            ApiResponse<List<Message>> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        String idCitizen = jwtUtil.getIdFromToken(token);
        Citizen citizen = citizenService.GetById(idCitizen);
        List<Message> messageList = messageService.GetAllByCitizen(citizen);
        ApiResponse<List<Message> > apiResponse = new ApiResponse<>(200, "Messages found", messageList);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/serviceprovider/{id}")
    public ResponseEntity<ApiResponse<List<Message>>> GetMessagesByIdGroup(@PathVariable String id, @RequestHeader("Authorization") String token) {


        if(!jwtUtil.validateToken(token)){
            ApiResponse<List<Message>> notFoundResponse = new ApiResponse<>(404, "UnAuthorized", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundResponse);
        }
        System.out.print("id"+id);
        ServiceProvider serviceProvider = serviceProviderService.getById(id);

        System.out.print("serviceProvider : "+serviceProvider.getName());

        List<Message> messageList = messageService.GetAllByServiceProvider(serviceProvider);
        ApiResponse<List<Message> > apiResponse = new ApiResponse<>(200, "Messages found", messageList);
        return ResponseEntity.ok(apiResponse);
    }

}
