package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;


import java.util.List;

public interface TrackService {
    public Track createTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks();
    public Track findTrack(int id) throws TrackNotFoundException;
    public List<Track> deleteTrack(int id) throws TrackNotFoundException;
    public Track updateTrack(Track track) throws TrackNotFoundException;
    //public Track searchTrack(String name) throws TrackNotFoundException;
}
