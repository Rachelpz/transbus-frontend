package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.RequestDto;

import java.util.List;

public interface RequestService {
    List<RequestDto> getRequests();
    RequestDto getRequestById(Integer requestId);
    void createRequest(RequestDto request);
    void updateRequest(RequestDto request);
    boolean deleteRequest(Integer id);
}
