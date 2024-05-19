package org.group.smart_city.Service.Implementations;
import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Respository.CitizenRepository;

import org.group.smart_city.Service.Interfaces.CitizenService;
import org.modelmapper.ModelMapper;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitizenServiceImp implements CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();
    public Citizen Create(CitizenDto citezenDto) {
        System.out.println(citezenDto);
        Citizen citizen = modelMapper.map(citezenDto, Citizen.class);
        String hashedPassword = BCrypt.hashpw(citizen.getPassword(),BCrypt.gensalt());
        citizen.setPassword(hashedPassword);
        return citizenRepository.save(citizen);
    }

    public Citizen Authenticate(CitizenDto citizenDto) {
        Citizen citizen = citizenRepository.findCitizenByEmail(citizenDto.getEmail());
        System.out.println("citizen2 : " + citizen);
        if (citizen == null) {
            throw new AppException("No User found with this email");
        }

        if (BCrypt.checkpw(citizenDto.getPassword(), citizen.getPassword())) {
             return citizen;
        }
        throw new AppException("Password Incorrect");
    }

    @Override
    public Citizen Update(String id, CitizenDto citizenDto) {
        Citizen citizen = citizenRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        modelMapper.map(citizenDto,citizen);
        citizenRepository.save(citizen);
        return citizen;
    }
    @Override
    public void Delete(String id)
    {

        Citizen citizen = citizenRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        citizenRepository.deleteById(citizen.getId());
    }

    @Override
    public Citizen GetById(String _id) {
        return citizenRepository.findById(_id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

}

