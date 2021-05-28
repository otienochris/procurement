package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.ContractDto;
import com.otienochris.procurement_management_system.mappers.ContractMapper;
import com.otienochris.procurement_management_system.models.Contract;
import com.otienochris.procurement_management_system.repositories.ContractRepo;
import com.otienochris.procurement_management_system.responses.ContractResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepo contractRepo;
    private final ContractMapper contractMapper;
//    all
    public List<ContractResponse> getAllContracts(){
        List<ContractResponse> responses = new ArrayList<>();
        contractRepo.findAll().forEach(contract -> responses.add(createResponse(contract)));
        return responses;
    }
//    one
    public ContractResponse getById(Integer id){
        return createResponse(contractRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

//    delete
    public void deleteById(Integer id){
        contractRepo.findById(id).ifPresentOrElse(contractRepo::delete,NoSuchElementException::new);
    }

//    save
    public ContractResponse save(ContractDto contractDto){
        Contract newContract = contractMapper.contractDtoToContract(contractDto);
        return createResponse(contractRepo.save(newContract));
    }
//    update

    private ContractResponse createResponse(Contract contract){
        String contractDocFilename = StringUtils.cleanPath(contract.getContractDocument().getFileName());
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(contractDocFilename)
                .toUriString();
        return ContractResponse.builder()
                .expiryDate(contract.getExpiryDate())
                .status(contract.getStatus())
                .supplierId(contract.getSupplierId())
                .contractDocumentUrl(url)
                .build();
    }
}
