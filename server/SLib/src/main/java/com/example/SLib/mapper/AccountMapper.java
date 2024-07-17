package com.example.SLib.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.SLib.dto.AccountDTO;
import com.example.SLib.entity.Account;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {
    public Account toAccount(AccountDTO accountDTO);
    public AccountDTO toAccountDTO(Account account);
}
