package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp(){                                    //to create new object before every method call
        track=new Track();
        track.setId(11);
        track.setTrackName("Spring day");
        track.setComments("Wings tour");
    }

    //to check if saving track attempt succeed
    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertEquals(11,fetchTrack.getId());
    }

    //to check if saving track attempt fails
    @Test
    public void testSaveTrackFailure(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    //to check track finding method passess
    @Test
    public void testFindTrackById(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertEquals(testTrack,fetchTrack);
    }

    //to check track finding method fails
    @Test
    public void testFindTrackByIdFailure(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,fetchTrack);
    }

    //to check track gets deleted successfully
    @Test
    public void testDeleteTrack(){
        Track track=new Track(12,"Make it right","Persona");
        Track track1=new Track(13,"Mikrosomos","Persona");
        trackRepository.save(track);
        trackRepository.save(track1);
        trackRepository.deleteById(12);
       List<Track> list= trackRepository.findAll();
        Assert.assertEquals(13,list.get(0).getId());
    }


    //to check if all tracks are displayed from database
    @Test
    public void testGetAllTracks(){
        Track track = new Track(11,"Spring day","Wings tour");
        Track track1 = new Track(12,"Just one day","Red bullet");
        trackRepository.save(track);
        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Spring day",list.get(0).getTrackName());
    }
    @Test
    public void testGetAllTracksFailure() {
        Track track1= new Track(11,"Spring day","Wings tour");
        Track track2 = new Track(12,"Just one day","Red bullet");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list = trackRepository.findAll();
        Assert.assertNotEquals("Just one day",list.get(0).getTrackName());
    }

    //to delete all objects after every method completed its task
    @After
    public  void tearDown(){
        trackRepository.deleteAll();
    }
}
