package com.challenge.endpoints;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationService accelerationService;

    @GetMapping("/{id}")
    ResponseEntity<Optional<Acceleration>> getFindById(@PathVariable Long id) {
        Optional<Acceleration> accelerations = accelerationService.findById(id);

        if (accelerations == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accelerations, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Acceleration>> getFindByCompanyId(@PathParam("companyId") Long companyId) {
        List<Acceleration> accelerations = accelerationService.findByCompanyId(companyId);

        if (accelerations == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accelerations, HttpStatus.OK);
    }
}
