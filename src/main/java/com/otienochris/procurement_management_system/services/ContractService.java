package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.ContractDto;
import com.otienochris.procurement_management_system.mappers.ContractMapper;
import com.otienochris.procurement_management_system.models.Contract;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.enums.ContractStatusEnum;
import com.otienochris.procurement_management_system.repositories.ContractRepo;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
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
    private final DocumentRepository documentRepository;

    //    all
    public List<ContractResponse> getAllContracts() {
        List<ContractResponse> responses = new ArrayList<>();
        contractRepo.findAll().forEach(contract -> responses.add(createResponse(contract)));
        return responses;
    }

    //    one
    public ContractResponse getById(Integer id) {
        return createResponse(contractRepo.findById(id).orElseThrow(NoSuchElementException::new));
    }

    //    delete
    public void deleteById(Integer id) {
        contractRepo.findById(id).ifPresentOrElse(contractRepo::delete, NoSuchElementException::new);
    }

    //    save
    public ContractResponse save(ContractDto contractDto) {
        Contract newContract = contractMapper.contractDtoToContract(contractDto);
        newContract.setStatus(ContractStatusEnum.IN_PROGRESS);
        newContract.getContractDocument().setType("Contract");
        return createResponse(contractRepo.save(newContract));
    }
//    update

    public void updateContract(Integer id, ContractDto contractDto) {
        Contract newContract = contractMapper.contractDtoToContract(contractDto);
        Document newContractDocument = newContract.getContractDocument();

        contractRepo.findById(id).ifPresentOrElse(contract -> {
            String oldContract = contract.getContractDocument().getFileName();

            contract.setExpiryDate(newContract.getExpiryDate());
            contract.setPurchaseOrderId(newContract.getPurchaseOrderId());
            contract.setSupplierId(newContract.getSupplierId());
            if (newContractDocument != null)
                if (contract.getContractDocument().getFileName().equals(newContractDocument.getFileName())) {
                    documentRepository.findByFileName(newContractDocument.getFileName()).ifPresent(document -> {
                        document.setContent(newContractDocument.getContent());
                        documentRepository.save(document);
                    });
                } else {
                    newContractDocument.setType("Contract Doc");
                    contract.setContractDocument(newContractDocument);
                    documentRepository.deleteById(oldContract);
                }

            contractRepo.save(contract);
        }, () -> {
            throw new NoSuchElementException("Item with id: " + id + " not found");
        });
    }

    private ContractResponse createResponse(Contract contract) {
        String contractDocFilename = StringUtils.cleanPath(contract.getContractDocument().getFileName());
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(contractDocFilename)
                .toUriString();
        return ContractResponse.builder()
                .id(contract.getId())
                .expiryDate(contract.getExpiryDate())
                .dateAwarded(contract.getDateAwarded())
                .status(contract.getStatus())
                .supplierId(contract.getSupplierId())
                .purchaseOrderId(contract.getPurchaseOrderId())
                .contractDocumentUrl(url)
                .build();
    }
}
