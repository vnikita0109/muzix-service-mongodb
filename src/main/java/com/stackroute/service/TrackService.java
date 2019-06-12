package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;


import java.util.List;

public interface TrackService {

    //method declarations that are going to be used in business logic

    public Track createTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks() throws TrackNotFoundException;
    public Track findTrackById(int id) throws TrackNotFoundException;
    public List<Track> deleteTrack(int id) throws TrackNotFoundException;
    public Track updateTrack(Track track) throws TrackNotFoundException;

}
