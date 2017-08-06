package com.mgr2.dto.convertion;

import org.springframework.stereotype.Service;

import com.mgr2.dto.RateDTO;
import com.mgr2.model.Rating;

@Service("ratingDTOConverter")
public class RatingDTOConverter {
	
	public Rating convertEmailDTOtoModel(RateDTO rDTO){
		Rating r = new Rating();
		r.setRate(rDTO.getRate());
		r.setTrainingid(rDTO.getTrainingid());
		return r;
	}
	
	public Rating convertDTOtoModel(RateDTO rDTO){
		Rating r = new Rating();
		r.setRate(rDTO.getRate());
		r.setId(rDTO.getId());
		r.setTrainingid(rDTO.getTrainingid());
		r.setUserid(rDTO.getUserid());
		return r;
	}

}
