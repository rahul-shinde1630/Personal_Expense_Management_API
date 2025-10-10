package com.pem.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pem.dto.lentmoney.LentMoneyRequestDto;
import com.pem.dto.lentmoney.LentMoneyResponseDto;
import com.pem.entity.LentMoney;
import com.pem.mapper.LentMoneyMapper;
import com.pem.repository.LentMoneyRepository;
import com.pem.repository.UserRepository;
import com.pem.service.LentMoneyService;

@Service
public class LentMoneyServiceImpl implements LentMoneyService {

	@Autowired
	private LentMoneyRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean save(LentMoneyRequestDto dto) {
		LentMoney entity = LentMoneyMapper.toEntity(dto);
		entity.setUser(userRepository.findByEmail(dto.getUser()));
		repository.save(entity);
		return true;
	}

	@Override
	public List<LentMoneyResponseDto> getByEmail(String email) {
		List<LentMoney> list = repository.findByUserEmailAndIsDeletedFalse(email);
		List<LentMoneyResponseDto> dtoList = new ArrayList<>();

		for (LentMoney entity : list) {
			dtoList.add(LentMoneyMapper.toDto(entity));
		}

		return dtoList;
	}

	@Override
	public boolean delete(Long lentId) {
		Optional<LentMoney> optional = repository.findByLentId(lentId);

		if (optional.isPresent()) {
			LentMoney entity = optional.get();
			entity.setDeleted(true);

			repository.save(entity);
			return true;
		} else {
			System.out.println("LentMoney not found with id: " + lentId);
			return false;
		}
	}

	@Override
	public LentMoneyRequestDto getById(Long lentId) {
		Optional<LentMoney> optional = repository.findByLentId(lentId);
		if (optional.isPresent()) {
			return LentMoneyMapper.toRequestDto(optional.get());
		} else {
			throw new RuntimeException("LentMoney not found with id: " + lentId);
		}
	}

	@Override
	public boolean update(LentMoneyRequestDto dto) {
		Optional<LentMoney> optional = repository.findByLentId(dto.getLentId());
		if (optional.isPresent()) {
			LentMoney existing = optional.get();
			existing.setLentId(dto.getLentId());
			existing.setPersonName(dto.getPersonName());
			existing.setAmount(dto.getAmount());
			existing.setLentDate(dto.getLentDate());
			existing.setDueDate(dto.getDueDate());
			existing.setReason(dto.getReason());
			existing.setRemainingAmount(dto.getRemainingAmount());
			existing.setStatus(LentMoney.Status.valueOf(dto.getStatus().toUpperCase()));

			existing.setUser(userRepository.findByEmail(dto.getUser()));

			repository.save(existing);
			return true;
		} else {
			System.out.println("LentMoney not found with id: " + dto.getLentId());
			return false;
		}
	}

	@Override
	public List<LentMoneyResponseDto> getUnpaidByEmail(String email) {
		if (email == null || email.isEmpty()) {
			return Collections.emptyList();
		}

		LentMoney.Status paidStatus = LentMoney.Status.PAID;

		List<LentMoney> lentList = repository.findUnpaidByEmail(email, paidStatus);

		// Convert to DTOs using lambda
		List<LentMoneyResponseDto> dtoList = new ArrayList<>();
		for (LentMoney entity : lentList) {
			dtoList.add(LentMoneyMapper.toDto(entity));
		}
		return dtoList;
	}

}
