package com.mgr2.service;

import com.mgr2.dto.RateDTO;

public interface RatingService {

	String rateTraining(RateDTO rDTO);

	String updateRate(RateDTO rDTO);

	RateDTO getUserRate(RateDTO rDTO);

}
