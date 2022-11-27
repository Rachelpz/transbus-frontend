package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.ContractDto;

import java.util.List;

public interface ContractService {
    List<ContractDto> getContracts();
    ContractDto getContractById(Integer contractId);
    void createContract(ContractDto contract);
    void updateContract(ContractDto contract);
    void deleteContract(Integer id);
}
