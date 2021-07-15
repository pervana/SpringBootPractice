package com.pervanadurdyyeva.service;

import java.util.List;

import com.pervanadurdyyeva.model.Offer;
import com.pervanadurdyyeva.model.OfferDTO;

public interface OfferService {
	
	public List<Offer> getAll();
    public Offer getById(int id);
    public void saveOrUpdate(Offer offer);
    public void delete(int id);
    public List<OfferDTO> getAllDTO();
    public List<OfferDTO> getAllFromExternalApplication();
}
