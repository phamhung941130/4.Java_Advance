package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.form.AccountCreateForm;
import com.vti.form.AccountFilterForm;
import com.vti.form.AccountUpdateForm;
import com.vti.service.IAccountService;
import com.vti.validation.AccountIDExists;
import com.vti.validation.AccountIDExists;
import com.vti.validation.DepartmentIDExists;
import net.bytebuddy.implementation.bytecode.Throw;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@Validated
public class AccountController {
    @Autowired
    private IAccountService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Page<AccountDTO> findAll(@PageableDefault(sort = "id") Pageable pageable, AccountFilterForm filterForm) {
        Page<Account> accounts = service.findAll(pageable, filterForm);
        List<AccountDTO> accountDTOS = mapper.map(
                accounts.getContent(), new TypeToken<List<AccountDTO>>() {
                }.getType()
        );
        return new PageImpl<>(accountDTOS, pageable, accounts.getTotalElements());
    }


    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable("id") @AccountIDExists int id) {
        Account account = service.findById(id);
        return mapper.map(account, AccountDTO.class).add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                                .methodOn(AccountController.class)
                                .getAccountById(id)
                ).withSelfRel()
        );


    }

    @PostMapping
    public void createAccount(@RequestBody @Valid AccountCreateForm form) {
        service.create(form);
    }

    @PutMapping("")
    public void updateAccount(@RequestBody AccountUpdateForm form) {

        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable("id") int id) {
        service.deleteById(id);
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


