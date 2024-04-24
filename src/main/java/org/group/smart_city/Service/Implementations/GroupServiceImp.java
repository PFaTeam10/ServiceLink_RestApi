package org.group.smart_city.Service.Implementations;
import org.apache.catalina.Store;
import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;
import org.group.smart_city.Respository.CitizenRepository;
import org.group.smart_city.Respository.GroupRepository;
import org.group.smart_city.Service.Interfaces.CitizenService;
import org.group.smart_city.Service.Interfaces.GroupService;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.group.smart_city.Exceptions.AppException;
import org.group.smart_city.Response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImp implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    ModelMapper modelMapper = new ModelMapper();



    @Override
    public Group Update(String id, GroupDto groupDto) {
        Group group = groupRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        modelMapper.map(groupDto,group);
        groupRepository.save(group);
        return group;
    }
    @Override
    public void Delete(String id)
    {

        Group group = groupRepository.findById(id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        groupRepository.deleteById(group.getId());
    }

    @Override
    public Group GetById(String _id) {
        return groupRepository.findById(_id).orElseThrow(() ->
                new AppException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
    }

    @Override
    public List<Group> GetAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group Create(GroupDto groupDto) {
        if (groupDto == null) {
            throw new IllegalArgumentException("EducationDto must not be null");
        }
        Group group = modelMapper.map(groupDto, Group.class);
        if (group == null) {
            throw new AppException("Mapping from EducationDto to Education failed");
        }
        System.out.print(group);
        return groupRepository.save(group);
    }



}

