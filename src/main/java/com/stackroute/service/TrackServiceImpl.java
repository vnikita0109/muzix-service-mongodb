package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Implementation of Service class methods
@CacheConfig(cacheNames = "track")
@Service
@Primary
public class TrackServiceImpl implements TrackService {

   private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }
    @Autowired
    private Environment environment;

    @Value("${String.exception1}")
    String trackDoesNotExist;
    @Value("${String.exception2}")
    String trackAlreadyExist;

    //creates intentional delay for implementation cacheable

    public void simulateDelay(){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //display all tracks available in database
    @Cacheable
    @Override
    public List<Track> getAllTracks() throws TrackNotFoundException{
        simulateDelay();
        List<Track> trackList=(List<Track>) trackRepository.findAll();
        if(trackList.isEmpty()){
            throw new TrackNotFoundException(trackDoesNotExist);
        }
        return trackList;
    }

    //Creates new track
    @CacheEvict(allEntries = true)
    @Override
    public Track createTrack(Track track) throws TrackAlreadyExistsException {

        if (trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException(trackAlreadyExist);
        }
        Track savedTrack= trackRepository.save(track);

        if(savedTrack==null){
            throw new TrackAlreadyExistsException(trackAlreadyExist);
        }
        return savedTrack;
    }

    //Finds track by using provided id
    @Override
    public Track findTrackById(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        Track track=null;
        if (optional.isPresent()){
            track=(Track) optional.get();
        }
        else {
            throw new TrackNotFoundException(trackDoesNotExist);
        }
        return track;
    }

    //Deletes track by using provided id
    @CacheEvict(allEntries = true)
    @Override
    public List<Track> deleteTrack(int id) throws TrackNotFoundException {
        Optional optional=trackRepository.findById(id);
        if (optional.isPresent()){
            trackRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException(trackDoesNotExist);
        }
        return trackRepository.findAll();
    }

    //Updates the comments of track
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
            throw new TrackNotFoundException(trackDoesNotExist);
        }

        return existingTrack;
    }


}
