package com.stackroute.startup;

import com.stackroute.domain.Track;

import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class SeedByFirstApproach implements ApplicationListener<ContextRefreshedEvent> {
    Track track=new Track();

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    private Environment environment;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track= Track.builder()
               .id(Integer.parseInt(environment.getProperty("track.id")))
               .trackName(environment.getProperty("track.trackName"))
               .comments(environment.getProperty("track.comments"))
               .build();
        trackRepository.save(track);

    }
}
