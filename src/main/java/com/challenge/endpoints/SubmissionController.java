package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @GetMapping
    ResponseEntity<List<SubmissionDTO>> getFindByChallengeIdAndAccelerationId(@PathParam("challengeId") Long challengeId, @PathParam("accelerationId") Long accelerationId) {
        List<Submission> submissions = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
        List<SubmissionDTO> response = new ArrayList<>();

        if (submissions.size() == 0) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        for (Submission s : submissions) {
            SubmissionDTO submissionDTO = submissionMapper.map(s);
            response.add(submissionDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}