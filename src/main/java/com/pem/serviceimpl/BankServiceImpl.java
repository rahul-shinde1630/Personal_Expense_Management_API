package com.pem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.bank.BankDto;
import com.pem.dto.bank.BankRequestDto;
import com.pem.entity.Bank;
import com.pem.mapper.BankMapper;
import com.pem.repository.BankRepository;
import com.pem.repository.UserRepository;
import com.pem.service.BankService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean saveBank(BankRequestDto dto) {
		try {
			Bank bank = bankMapper.toEntity(dto);
			bankRepository.save(bank);
			return true;
		} catch (Exception e) {
			System.out.println("Error saving bank: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<BankDto> getAllBanks(String user) {

		List<Bank> banks = bankRepository.findByUser_Email(user);

		List<BankDto> result = new ArrayList<>();
		for (Bank b : banks) {
			result.add(bankMapper.toDto(b));
		}
		return result;
	}

}
