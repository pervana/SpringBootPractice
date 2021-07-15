package com.pervanadurdyyeva.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pervanadurdyyeva.model.Offer;
import com.pervanadurdyyeva.model.OfferDTO;
import com.pervanadurdyyeva.model.TourAgencyOffer;
import com.pervanadurdyyeva.repository.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	@Value("${urlAddress.travelAgency}")
	private String url;
	
	
	@Autowired
	private OfferRepository offerRepository;

	@Override
	public List<Offer> getAll() {
		// TODO Auto-generated method stub
		return offerRepository.findAll();
	}

	@Override
	public Offer getById(int id) {
		// TODO Auto-generated method stub
		return offerRepository.getById(id);
	}

	@Override
	public void saveOrUpdate(Offer offer) {
		// TODO Auto-generated method stub
		
		if(offer.getId() == 0) {
			offer.setCreationDate(new Date());
			
		}
		
		offerRepository.save(offer);
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		offerRepository.deleteById(id);
	}

	@Override
	public List<OfferDTO> getAllDTO() {
		// TODO Auto-generated method stub
		List<Offer> offers = getAll();
		List<OfferDTO> offerDTO = (List<OfferDTO>) offers.stream().map(o -> new OfferDTO(o.getId(), o.getCode(), o.getName(), o.getDuration(), false, "Local offer"))
				.collect(Collectors.toList());
		return offerDTO;
	}

	@Override
	public List<OfferDTO> getAllFromExternalApplication() {{
		List<OfferDTO> offerDTOs = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		String response = "";
		
		try {
			response = restTemplate.getForObject(url, String.class);
			ObjectMapper mapper = new ObjectMapper();
			List<TourAgencyOffer> touAgencyOffers = mapper.readValue(response, new TypeReference<List<TourAgencyOffer>>() {});
			
			if(!touAgencyOffers.isEmpty()) {
				offerDTOs = touAgencyOffers.stream()
						.map(o -> new OfferDTO(0, o.getCode(), o.getName(), o.getDuration(), true, TourAgencyOffer.SOURCE))
						.collect(Collectors.toList());
			}
		} catch (ResourceAccessException | JsonProcessingException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return offerDTOs;

		
		
	}
	
		
	
	}
	

}
