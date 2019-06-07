package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp(){
        track=new Track();
        track.setId(11);
        track.setTrackName("Spring day");
        track.setComments("Wings tour");
    }

    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertEquals(11,fetchTrack.getId());
    }
    @Test
    public void testSaveTrackFailure(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(track);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,track);
    }

    @Test
    public void testFindTrack(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertEquals(testTrack,fetchTrack);
    }
    @Test
    public void testFindTrackFailure(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track fetchTrack=trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack,fetchTrack);
    }
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
    @Test
    public void testDeleteTrackFailure(){
        Track track=new Track(12,"Make it right","Persona");
        Track track1=new Track(13,"Mikrosomos","Persona");
        trackRepository.save(track);
        trackRepository.save(track1);
        trackRepository.deleteById(12);
        List<Track> list= trackRepository.findAll();
        Assert.assertNotSame(12,list.get(0).getId());
    }
  /*  @Test
    public void testTrackByName(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track existingTrack=trackRepository.trackByName(track.getTrackName());
        Assert.assertEquals("Spring day",existingTrack.getTrackName());
    }
    @Test
    public void testTrackByNameFailure(){
        Track testTrack=new Track(11,"Spring day","Wings tour");
        trackRepository.save(testTrack);
        Track track=trackRepository.trackByName("Spring day");
        Assert.assertNotSame(testTrack,track);
    }*/
    @Test
    public void testGetAllTracks(){
        Track track = new Track(11,"Spring day","Wings tour");
        Track track1 = new Track(12,"Just one day","Red bullet");
        trackRepository.save(track);
        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Spring day",list.get(0).getTrackName());

    }

    @After
    public  void tearDown(){
        trackRepository.deleteAll();
    }
}
