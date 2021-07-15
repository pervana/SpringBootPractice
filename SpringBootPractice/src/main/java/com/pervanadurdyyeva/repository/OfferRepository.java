package com.pervanadurdyyeva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pervanadurdyyeva.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
