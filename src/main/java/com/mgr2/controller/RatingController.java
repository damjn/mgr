package com.mgr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgr2.dto.RateDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.repository.RatingRepository;
import com.mgr2.service.RatingService;

@Controller
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	RatingRepository ratingRepository;
	
	@RequestMapping(value = "/getRate",method = RequestMethod.POST)
	public @ResponseBody RateDTO getUserRate(@RequestBody RateDTO rDTO){
		return ratingService.getUserRate(rDTO);
	}
	
	@RequestMapping(value = "/rate",method = RequestMethod.POST)
    public @ResponseBody String rateTraining (@RequestBody RateDTO rDTO){
		return ratingService.rateTraining(rDTO);
    }
	
	@RequestMapping(value = "/rate",method = RequestMethod.PUT)
    public @ResponseBody String updateRate (@RequestBody RateDTO rDTO){
		return ratingService.updateRate(rDTO);
    }
	
	

}
