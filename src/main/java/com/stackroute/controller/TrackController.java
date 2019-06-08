package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "MuzixControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)

public class TrackController {

    TrackService trackService;
    ResponseEntity responseEntity;

    @Autowired
    public TrackController(TrackService trackService){
        this.trackService=trackService;
    }

    //to display all tracks available in database
    @GetMapping("allTracks")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?>getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    //to create new track
    @PostMapping("track")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?>saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
            trackService.createTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully created track !", HttpStatus.CREATED);

        return responseEntity;
    }

    // to find track using name
    @GetMapping("track/{trackName}")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?>searchTrack(@PathVariable String trackName) throws TrackNotFoundException {

        trackService.findTrackByName(trackName);
        responseEntity=new ResponseEntity<String>("Track found !",HttpStatus.FOUND);

        return responseEntity;
    }

    //to find track using id
    @GetMapping("track/{id}")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?>findTrack(@PathVariable int id) throws TrackNotFoundException {

            trackService.findTrackById(id);
            responseEntity=new ResponseEntity<String>("Track found !",HttpStatus.FOUND);

        return responseEntity;
    }
    //to delete track using id
    @DeleteMapping("track/{id}")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?> deleteexistingTrack(@PathVariable("id") int id) throws TrackNotFoundException {
            trackService.deleteTrack(id);
            responseEntity=new ResponseEntity<String>("Track deleted !",HttpStatus.OK);

        return responseEntity;
    }
    //to update track details
    @PutMapping("track/{id}")
    @ApiOperation("Get all the tracks here")
    @ApiResponses(value = {@ApiResponse(code=200,message ="OK",response = Track.class)})
    public ResponseEntity<?>updateexistingTrack(@RequestBody Track track) throws TrackNotFoundException {
        trackService.updateTrack(track);
        responseEntity=new ResponseEntity<String>("Track updated",HttpStatus.OK);

        return responseEntity;
    }
}
