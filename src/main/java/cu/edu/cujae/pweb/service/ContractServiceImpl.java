package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.ContractDto;
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
public class ContractServiceImpl implements ContractService {

    @Autowired
    private RestService restService;

    @Override
    public List<ContractDto> getContracts() {

        List<ContractDto> contracts = new ArrayList<ContractDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.GET("/api/v1/contracts/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            contracts = apiRestMapper.mapList(response, ContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    @Override
    public ContractDto getContractById(Integer contractId) {
        ContractDto contract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/contracts/{contractId}");
            String uri = template.expand(contractId).toString();
            String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            contract = apiRestMapper.mapOne(response, ContractDto.class);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return contract;
    }

    @Override
    public void createContract(ContractDto contract) {
        restService.POST("/api/v1/contracts/", contract, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void updateContract(ContractDto contract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/contracts/", params, contract, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteContract(Integer contractId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/contracts/{contractId}");
        String uri = template.expand(contractId).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
