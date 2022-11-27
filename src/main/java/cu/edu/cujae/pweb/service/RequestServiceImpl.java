package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.RequestDto;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService{

    @Override
    public List<RequestDto> getRequests() {

        List<RequestDto> requests = new ArrayList<>();
        requests.add(new RequestDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), Date.valueOf(LocalDate.now()), "Petitioner 1", "Group 1", "Service 1", "Vehicle 1", "sdsa", false ));
        requests.add(new RequestDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), Date.valueOf(LocalDate.now()), "Petitioner 2", "Group 2", "Service 2", "Vehicle 2", "sdsa", false ));

        return requests;
    }

    @Override
    public RequestDto getRequestById(Integer requestId) {
        return getRequests().stream().filter(r -> r.getRequest_id().equals(requestId)).findFirst().get();
    }

    @Override
    public void createRequest(RequestDto request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateRequest(RequestDto request) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteRequest(Integer id) {
        // TODO Auto-generated method stub

    }

}
