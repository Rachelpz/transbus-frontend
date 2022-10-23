package cu.edu.cujae.pweb.service;




import cu.edu.cujae.pweb.dto.GroupDto;

import java.util.List;

public interface GroupService {
    List<GroupDto> getGroups();
    GroupDto getGroupById(String GroupId);
    void createGroup(GroupDto group);
    void updateGroup(GroupDto group);
    void deleteGroup(String id);
}
