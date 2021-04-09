package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.Dtos.SupplierDto;
import com.otienochris.procurement_management_system.models.Role;
import com.otienochris.procurement_management_system.models.RoleEnum;
import com.otienochris.procurement_management_system.models.Supplier;
import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.repositories.RoleRepository;
import com.otienochris.procurement_management_system.repositories.SupplierRepo;
import com.otienochris.procurement_management_system.responses.SupplierResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepo supplierRepo;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;


    //create an supplier
//    todo ensure no duplicate roles are stored
    public SupplierResponse createSupplier(SupplierDto supplierDto, HttpServletRequest request) {

        if (supplierRepo.existsById(supplierDto.getKRA()))
            throw new DuplicateKeyException("A supplier with kra: " + supplierDto.getKRA() + " already exists");

        String encodePassword = encoder.encode(supplierDto.getPassword());

        User user = User.builder()
                .isActive(false)
                .username(supplierDto.getKRA())
                .password(encodePassword)
                .roles(
                        roleRepository.findByRole(RoleEnum.ROLE_SUPPLIER).orElse(Role.builder()
                                .role(RoleEnum.ROLE_SUPPLIER)
                                .build())
                )
                .build();
        Supplier supplier = Supplier.builder()
                .description(supplierDto.getDescription())
                .kRA(supplierDto.getKRA())
                .name(supplierDto.getName())
                .user(user)
                .build();

        Supplier savedSupplier = supplierRepo.save(supplier);
        userService.sendEmailVerificationToken(savedSupplier.getKRA(),savedSupplier.getEmail());

        return createResponse(savedSupplier);
    }


    //get an supplier by id
    public SupplierResponse getSupplier(String kra) {
        return createResponse(
                supplierRepo.findById(kra).orElseThrow(() -> {
                    throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
                }));
    }

    public List<SupplierResponse> getAllSuppliers() {
        List<SupplierResponse> responses = new ArrayList<>();
        supplierRepo.findAll().forEach(supplier -> {
            responses.add(createResponse(supplier));
        });
        return responses;
    }

    //update a supplier
    public void updateSupplier(SupplierDto supplierDto, String kra) {
        supplierRepo.findById(kra).ifPresentOrElse(supplier -> {
            supplier.setDescription(supplierDto.getDescription());
            supplier.setName(supplierDto.getName());
            supplier.getUser().setPassword(supplierDto.getPassword());
        }, () -> {
            throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
        });
    }

    //delete a supplier
    public void deleteSupplier(String kra) {
        supplierRepo.findById(kra).orElseThrow(() -> {
            throw new NoSuchElementException("Supplier with kra: " + kra + " does not exist!");
        });
    }

    // helper methods
    private SupplierResponse createResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .isAccountActive(supplier.getUser().getIsActive())
                .description(supplier.getDescription())
                .kRA(supplier.getKRA())
                .name(supplier.getName())
                .build();
    }

//    todo https://api.appruve.co/v1/verifications/ke/kra?pin=string

}
