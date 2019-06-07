package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;

    //create a mock for TrackRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackServiceImpl;
    List<Track> list=null;

    Optional optional;

    @Before
    public void setUp(){
        //Initialising mock object
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.setId(11);
        track.setTrackName("Spring day");
        track.setComments("Wings tour");
        list=new ArrayList<>();
        list.add(track);
        optional=Optional.of(track);
    }
    @Test
    public void testSaveTrackSuccess() throws TrackAlreadyExistsException{
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack=trackServiceImpl.createTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that trackRepository save method only called once
        verify(trackRepository,times(1)).save(track);
    }
    @Test(expected = TrackAlreadyExistsException.class)
    public void testSaveTrackFailure() throws TrackAlreadyExistsException{
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack=trackServiceImpl.createTrack(track);
        System.out.println("SavedTrack "+savedTrack);
        Assert.assertEquals(track,savedTrack);

    /* doThrow(new TrackAlreadyExistsException()).when(trackRepository).findById(eq(11));
    * trackService.saveTrack(track);*/

    }

    @Test
    public void deleteTrackSuccess()throws TrackNotFoundException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        List<Track> status=trackServiceImpl.deleteTrack(track.getId());
        Assert.assertEquals(null,status);
    }

    @Test()
    public void deleteTrackTestFailure() throws TrackNotFoundException {
        when(trackRepository.findById(11)).thenReturn(optional);
        trackServiceImpl.deleteTrack(track.getId());
        List<Track> trackList = trackServiceImpl.getAllTracks();
        Assert.assertNotSame(true,trackList.contains(track));
    }
    @Test
    public void testFindByIdTrack() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(11)).thenReturn(true);
        Track savedTrack=trackServiceImpl.findTrack(track.getId());
        Assert.assertEquals(11,savedTrack.getId());

    }
    @Test(expected = TrackNotFoundException.class)
    public void testFindTrackFailure() throws TrackNotFoundException{
        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack=trackServiceImpl.findTrack(track.getId());
        System.out.println("SavedTrack "+savedTrack);
        Assert.assertEquals(track,savedTrack);

    }/*
    @Test
    public void testFindTrackByName() throws TrackNotFoundException{
        trackRepository.trackByName(track.getTrackName());
        when(trackRepository.trackByName(track.getTrackName())).thenReturn(track);
        Track savedTrack=trackServiceImpl.searchTrack(track.getTrackName());
         Assert.assertEquals(track,savedTrack);
       }
    @Test
    public void testFindTrackByNameFailure() throws TrackNotFoundException{
        trackRepository.trackByName(track.getTrackName());
        when(trackRepository.trackByName(track.getTrackName())).thenReturn(null);
        Track savedTrack=trackServiceImpl.searchTrack(track.getTrackName());
        Assert.assertNotEquals(track,savedTrack);
    }*/
    @Test
    public void testUpdateTrack() throws TrackNotFoundException{
       when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.save((Track)any())).thenReturn(track);
        track.setComments("jazz...");
        Track updatedTrack=trackServiceImpl.updateTrack(track);
        Assert.assertEquals("jazz...",updatedTrack.getComments());

    }
    @Test
    public void testGetAllTracks(){
        trackRepository.save(track);
        // stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList=trackServiceImpl.getAllTracks();
        Assert.assertEquals(list,trackList);
    }

    @After
    public  void tearDown(){
        trackRepository.deleteAll();
    }
}
