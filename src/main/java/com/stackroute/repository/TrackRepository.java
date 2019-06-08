package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,Integer> {

    //declaring new method to find track using trackName
    public Track findByName(String name);
}
