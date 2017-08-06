package com.mgr2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr2.dto.RateDTO;
import com.mgr2.dto.convertion.RatingDTOConverter;
import com.mgr2.model.Rating;
import com.mgr2.model.User;
import com.mgr2.repository.RatingRepository;
import com.mgr2.repository.UserRepository;
import com.mgr2.service.RatingService;

@Service("ratingService")
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RatingDTOConverter ratingDTOConverter;

	@Override
	public String rateTraining(RateDTO rDTO) {
		
		Rating r = ratingDTOConverter.convertDTOtoModel(rDTO);
		ratingRepository.save(r);
		return "RATE SAVED";
	}

	@Override
	public String updateRate(RateDTO rDTO) {
		
		Rating r = ratingRepository.findByTrainingidAndUserid(rDTO.getTrainingid(), rDTO.getUserid());
		if(r == null){
			System.out.println("pierwsza ocena");
			r = ratingDTOConverter.convertDTOtoModel(rDTO);
		}
		r.setRate(rDTO.getRate());
		ratingRepository.save(r);
		return "RATE SAVED";
	}

	@Override
	public RateDTO getUserRate(RateDTO rDTO) {
		User user = userRepository.findByEmail(rDTO.getEmail());
		Integer rate = ratingRepository.findRateByUserIdAndTrainingID(user.getId(), rDTO.getTrainingid());
		if(rate == null){
			rate = 0;
		}
		rDTO.setRate(rate);
		rDTO.setUserid(user.getId());
		return rDTO;
	}

}
