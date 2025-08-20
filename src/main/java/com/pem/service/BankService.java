package com.pem.service;

import java.util.List;

import com.pem.dto.bank.BankDto;
import com.pem.dto.bank.BankRequestDto;

public interface BankService {

	boolean saveBank(BankRequestDto dto);

	List<BankDto> getAllBanks(String user);

}
