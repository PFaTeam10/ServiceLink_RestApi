package org.group.smart_city.Service.Interfaces;

import org.group.smart_city.Dto.CitizenDto;
import org.group.smart_city.Dto.GroupDto;
import org.group.smart_city.Dto.MessageDto;
import org.group.smart_city.Entities.Citizen;
import org.group.smart_city.Entities.Group;
import org.group.smart_city.Entities.Message;

import java.util.List;

public interface GroupService {
    public Group GetById(String id);
    public List<Group> GetAll();
    public Group Create(GroupDto groupDto);
    public Group Update(String id, GroupDto groupDto);
    public void Delete(String id);
}
