package cu.edu.cujae.pweb.service;


import cu.edu.cujae.pweb.dto.ContractDto;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService {

    @Override
    public List<ContractDto> getContracts() {

        List<ContractDto> contracts = new ArrayList<>();
        contracts.add(new ContractDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 5.5F, 4.5F, "Solicitud 1", false ));
        contracts.add(new ContractDto(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-|[a-zA-Z]", "").substring(0, 6)), Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 2.8F, 6.8F, "Solicitud 2", false ));

        return contracts;
    }

    @Override
    public ContractDto getContractById(Integer contractId) {
        return getContracts().stream().filter(r -> r.getContract_id().equals(contractId)).findFirst().get();
    }

    @Override
    public void createContract(ContractDto contract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateContract(ContractDto contract) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteContract(Integer id) {
        // TODO Auto-generated method stub

    }

}
