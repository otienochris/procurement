package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.ApplicationObjectDto;
import com.otienochris.procurement_management_system.mappers.ApplicationObjectMapper;
import com.otienochris.procurement_management_system.models.ApplicationObject;
import com.otienochris.procurement_management_system.models.Document;
import com.otienochris.procurement_management_system.models.enums.POStatusEnum;
import com.otienochris.procurement_management_system.repositories.ApplicationObjectRepository;
import com.otienochris.procurement_management_system.repositories.DocumentRepository;
import com.otienochris.procurement_management_system.responses.ApplicationObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ApplicationObjectService {
    private final ApplicationObjectRepository applicationObjectRepository;
    private final ApplicationObjectMapper applicationObjectMapper;
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    public List<ApplicationObjectResponse> allFRQs() {
        List<ApplicationObjectResponse> responses = new ArrayList<>();
        applicationObjectRepository.findAll().forEach(requestForQuotation -> {
            responses.add(createResponse(requestForQuotation));
        });
        return responses;
    }

    public ApplicationObjectResponse getRFQById(Integer id) {
        ApplicationObject applicationObject = applicationObjectRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("The RFQ with id: " + id + " is not available!");
        });
        return createResponse(applicationObject);
    }

    public ApplicationObjectResponse saveRFQ(ApplicationObjectDto requestForQuotationDto) {
        ApplicationObject applicationObject =
                applicationObjectMapper.applicationDtoToApplication(requestForQuotationDto);
        applicationObject.getQuotationDocument().setType("Quotation");
        applicationObject.getInformationDocument().setType("Information");
        applicationObject.setStatus(POStatusEnum.PENDING);
        return createResponse(applicationObjectRepository.save(applicationObject));
    }

    public void updateRFQ(Integer id, ApplicationObjectDto requestForQuotationDto) {

        ApplicationObject newApplicationObject =
                applicationObjectMapper.applicationDtoToApplication(requestForQuotationDto);

        applicationObjectRepository.findById(id).ifPresentOrElse(applicationObject -> {

                    Document oldInformationDocument = applicationObject.getInformationDocument();
                    Document oldQuotation = applicationObject.getQuotationDocument();

                    Document newInformationDocument = newApplicationObject.getInformationDocument();
                    Document newQuotation = newApplicationObject.getQuotationDocument();

                    // if the provided terms and condition exists, just update the content, else overwrite
                    if (newInformationDocument != null)
                        if (oldInformationDocument.getFileName().equals(newInformationDocument.getFileName())) {
                            documentRepository.findByFileName(newInformationDocument.getFileName()).ifPresent(document -> {
                                document.setContent(newInformationDocument.getContent());
                                documentRepository.save(document);
                            });
                        } else {
                            newInformationDocument.setType("Information");
                            applicationObject.setInformationDocument(newInformationDocument);
                            documentService.deleteFile(oldInformationDocument.getFileName());
                        }

                    // if the provided quotation exist, change the content, else overwrite
                    if (newQuotation != null)
                        if (oldQuotation.getFileName().equals(newQuotation.getFileName())) {
                            documentRepository.findByFileName(newQuotation.getFileName()).ifPresent(document -> {
                                document.setContent(newQuotation.getContent());
                                documentRepository.save(document);
                            });
                        } else {
                            newQuotation.setType("Quotation");
                            applicationObject.setQuotationDocument(newQuotation);
                            documentService.deleteFile(oldQuotation.getFileName());
                        }
                    // update the message
                    applicationObject.setMessage(newApplicationObject.getMessage());
                    applicationObject.setPurchaseOrderId(newApplicationObject.getPurchaseOrderId());
                    applicationObject.setSupplierId(newApplicationObject.getSupplierId());
                    applicationObjectRepository.save(applicationObject);
                },
                () -> {
                    throw new NoSuchElementException(" The Application with id: " + id + " is not found!");
                });
    }

    public void deleteById(Integer id) {
        applicationObjectRepository.findById(id).ifPresentOrElse(applicationObjectRepository::delete, () -> {
            throw new NoSuchElementException("Application with id: " + id + " not found!");
        });
    }

    public void handleApprovals(Integer id, POStatusEnum status) {
        if (status == POStatusEnum.COMPLETED)
            applicationObjectRepository.findById(id).ifPresent(object -> {
                Stream<ApplicationObject> stream = applicationObjectRepository.findAll().stream();
                stream.filter(applicationObject ->
                        applicationObject.getPurchaseOrderId().equals(object.getPurchaseOrderId()))
                        .forEach(applicationObject -> {
                            applicationObject.setStatus(POStatusEnum.CANCELLED);
                            applicationObjectRepository.save(applicationObject);
                        });
                object.setStatus(status);
                applicationObjectRepository.save(object);
            });
        else
            applicationObjectRepository.findById(id).ifPresent(applicationObject -> {
                applicationObject.setStatus(status);
                applicationObjectRepository.save(applicationObject);
            });

    }

    private ApplicationObjectResponse createResponse(ApplicationObject applicationObject) {
        Document quotationDoc = applicationObject.getQuotationDocument();
        Document informationDocument = applicationObject.getInformationDocument();

        String quotationDocName = StringUtils.cleanPath(Objects.requireNonNull(quotationDoc.getFileName()));
        String termsAndConditionDocName = StringUtils.cleanPath(Objects.requireNonNull(informationDocument.getFileName()));

        String quotationUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(quotationDocName)
                .toUriString();
        String informationDocumentUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/documents/download/")
                .path(termsAndConditionDocName)
                .toUriString();
        return ApplicationObjectResponse.builder()
                .id(applicationObject.getId())
                .dateCreated(applicationObject.getDateCreated())
                .message(applicationObject.getMessage())
                .purchaseOrderId(applicationObject.getPurchaseOrderId())
                .quotationDownloadUrl(quotationUrl)
                .informationDownloadUrl(informationDocumentUrl)
                .supplierId(applicationObject.getSupplierId())
                .status(applicationObject.getStatus())
                .build();
    }
}
