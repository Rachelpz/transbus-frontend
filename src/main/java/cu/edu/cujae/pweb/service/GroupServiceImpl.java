package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.GroupDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private RestService restService;

    @Override
    public List<GroupDto> getGroups() {

        List<GroupDto> groups = new ArrayList<GroupDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<GroupDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/groups/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            groups = apiRestMapper.mapList(response, GroupDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return groups;
    }

    @Override
    public GroupDto getGroupById(Integer groupId) {
        GroupDto group = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<GroupDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/groups/{groupId}");
            String uri = template.expand(groupId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            group = apiRestMapper.mapOne(response, GroupDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return group;
    }

    @Override
    public void createGroup(GroupDto group) {
        restService.POST("/api/v1/groups/", group, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateGroup(GroupDto group) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/groups/", params, group, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteGroup(Integer groupId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/groups/{groupId}");
        String uri = template.expand(groupId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }
}
