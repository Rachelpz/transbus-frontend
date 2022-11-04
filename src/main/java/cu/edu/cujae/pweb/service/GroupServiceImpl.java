package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.GroupDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Override
    public List<GroupDto> getGroups() {

        List<GroupDto> groups = new ArrayList<>();
        groups.add(new GroupDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Grupo1", 50, "Canada", false));
        groups.add(new GroupDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), "Grupo2", 30, "Mexico", false));

        return groups;
    }

    @Override
    public GroupDto getGroupById(Integer groupId) {
        return getGroups().stream().filter(r -> r.getGroup_id().equals(groupId)).findFirst().get();
    }

    @Override
    public void createGroup(GroupDto group) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateGroup(GroupDto group) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteGroup(Integer id) {
        // TODO Auto-generated method stub

    }


}
