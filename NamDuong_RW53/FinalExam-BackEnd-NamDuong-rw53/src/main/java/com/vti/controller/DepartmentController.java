package com.vti.controller;

import com.vti.dto.DepartmentDTO;

import com.vti.entity.Department;
import com.vti.form.DepartmentCreateForm;
import com.vti.form.DepartmentFilterForm;
import com.vti.form.DepartmentUpdateForm;
import com.vti.service.IDepartmentService;
import com.vti.validation.DepartmentIDExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/departments")
@Validated
//@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class DepartmentController {
    @Autowired
    private IDepartmentService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageSource messageSource;

    //    @GetMapping
//    public Page<DepartmentDTO> findAll(Pageable pageable, DepartmentFilterForm filterForm) {
//        Page<Department> departments = service.findAll(pageable, filterForm);
//        List<DepartmentDTO> departmentDTOS = mapper.map(
//                departments.getContent(), new TypeToken<List<DepartmentDTO>>() {
//                }.getType()
//        );
//        for (DepartmentDTO departmentDTO : departmentDTOS) {
//            // add hateoas account
//            List<DepartmentDTO.AccountDTO> accountDTOS = departmentDTO.getAccounts();
//            for (DepartmentDTO.AccountDTO accountDTO : accountDTOS) {
//                accountDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountController.class)
//                                .getAccountById(accountDTO.getId()))
//                        .withSelfRel());
//            }
//            // add hateoas department
//            departmentDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DepartmentController.class)
//                            .getDepartmentById(departmentDTO.getId()))
//                    .withSelfRel());
//        }
//        return new PageImpl<>(departmentDTOS, pageable, departments.getTotalElements());
//    }
    @GetMapping
    public Page<DepartmentDTO> findAll(@PageableDefault(sort = "totalMembers",direction = Sort.Direction.DESC)
            Pageable pageable,
            DepartmentFilterForm form
    ) {
        Page<Department> page = service.findAll(pageable, form);
        List<Department> departments = page.getContent();
        List<DepartmentDTO> dtos = mapper.map(departments, new TypeToken<List<DepartmentDTO>>() {
        }.getType());
        for (DepartmentDTO dto : dtos) {
            dto.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(dto.getId())).withSelfRel());
            for (DepartmentDTO.AccountDTO account : dto.getAccounts()) {
                account.add(linkTo(methodOn(AccountController.class).getAccountById(account.getId())).withSelfRel());
            }
        }
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable("id") @DepartmentIDExists int id) {
        Department entity = service.findById(id);
        return mapper.map(entity, DepartmentDTO.class).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(DepartmentController.class)
                                .getDepartmentById(id)
                ).withSelfRel()
        );
    }

    @PostMapping
    public void createDepartment(@RequestBody @Valid DepartmentCreateForm form) {
        service.create(form);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable("id") @DepartmentIDExists int id, @RequestBody DepartmentUpdateForm form) {
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentById(@PathVariable("id") int id) {
        service.deleteById(id);
    }

//    @DeleteMapping("/name/{name}")
////    public void deleteDepartmentByName(@PathVariable("name") String name) {
////        service.de(name);
////    }

    @GetMapping("/messages")
    public String testMessages(@RequestParam(value = "key") String key) {
        return messageSource.getMessage(
                key,
                null,
                "Default message",
                LocaleContextHolder.getLocale());
    }

    @GetMapping("/messages/vi")
    public String testMessagesVi(@RequestParam(value = "key") String key) {
        return messageSource.getMessage(
                key,
                null,
                "Default message",
                new Locale("vi", "VN"));
    }

    @GetMapping("/messages/en")
    public String testMessagesEn(@RequestParam(value = "key") String key) {
        return messageSource.getMessage(
                key,
                null,
                "Default message",
                Locale.US);
    }

    @GetMapping("/exception")
    public void testException() {
        throw new EntityNotFoundException("... Exception information");
    }

}
