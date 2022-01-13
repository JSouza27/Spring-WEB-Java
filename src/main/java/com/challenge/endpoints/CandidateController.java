package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Autowired
    CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    ResponseEntity<CandidateDTO> getFindById(
        @PathVariable("userId") Long userId,
        @PathVariable("accelerationId") Long accelerationId,
        @PathVariable("companyId") Long companyId
    ) {
        Optional<Candidate> candidate = candidateService.findById(userId, companyId, accelerationId);

        if (candidate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CandidateDTO response = candidateMapper.map(candidate.get());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<CandidateDTO>> getByParam(@PathParam("companyId") Long companyId) {
        List<Candidate> candidates = candidateService.findByCompanyId(companyId);

        if (candidates.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CandidateDTO> response = new ArrayList<>();
        for (Candidate c : candidates) {
            CandidateDTO candidateDTO = candidateMapper.map(c);
            response.add(candidateDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
