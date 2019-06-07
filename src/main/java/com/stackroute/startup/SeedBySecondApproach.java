package com.stackroute.startup;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class SeedBySecondApproach implements CommandLineRunner {
    //Track track=null;
    Track track1=new Track();
    @Autowired
    TrackRepository trackRepository;


    @Value("${track1.id}")
    private int id;

    @Value("${track1.trackName}")
    private String trackName;

    @Value("${track1.comments}")
    private String comments;

    @Override
    public void run(String... args) throws Exception {
      track1.setId(id);
      track1.setTrackName(trackName);
      track1.setComments(comments);
      trackRepository.save(track1);
    }
}
