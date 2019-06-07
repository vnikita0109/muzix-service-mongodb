package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "track")
@Service
@Primary
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;


    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }
    @Autowired
    private Environment environment;

    //delay for implementation cacheable

    public void simulateDelay(){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Cacheable
    @Override
    public List<Track> getAllTracks() {

        simulateDelay();
        List<Track> trackList=(List<Track>) trackRepository.findAll();
        return trackList;
    }

    //Creates new track
    @CacheEvict(allEntries = true)
    @Override
    public Track createTrack(Track track) throws TrackAlreadyExistsException {

        if (trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException(environment.getProperty("String.exception2"));
        }
            Track savedTrack= trackRepository.save(track);

return savedTrack;
    }

    //Finds track by using id
    @Override
    public Track findTrack(int id) throws TrackNotFoundException {
        Track foundTrack=null;

        if (trackRepository.existsById(id)){
            foundTrack=trackRepository.findById(11).get();
        }
        else {
            throw new TrackNotFoundException(environment.getProperty("String.exception1"));
        }
        return foundTrack;
    }

    @CacheEvict(allEntries = true)
    @Override
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if (optional.isPresent()){
            trackRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException(environment.getProperty("String.exception1"));
        }
        return trackRepository.findAll();
    }

    //Updates the comments part of track
    @CacheEvict(allEntries = true)
    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        Track existingTrack;
        if (trackRepository.existsById(track.getId())){
            track.setComments(track.getComments());
            existingTrack=trackRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException(environment.getProperty("String.exception1"));
        }

        return existingTrack;
    }

   /* //Finds the track based on track name
    @Override
    public Track searchTrack(String name) throws TrackNotFoundException{
        Track foundTrack=null;

            foundTrack=trackRepository.trackByName(name);

        return foundTrack;
    }*/

}
