package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
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
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    ResponseEntity<Optional<Company>> getFindById(@PathVariable Long id) {
        Optional<Company> company = companyService.findById(id);

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<Company>> getByParam(@PathParam("accelerationId") Long accelerationId, @PathParam("userId") Long userId) {
        List<Company> companies = new ArrayList<>();

        if (accelerationId != null) {
            companies = companyService.findByAccelerationId(accelerationId);
        }

        if (userId != null) {
            companies = companyService.findByUserId(userId);
        }

        if (companies.size() == 0) {
            return new ResponseEntity<>(companies, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}
