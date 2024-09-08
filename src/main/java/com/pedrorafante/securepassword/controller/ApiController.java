package com.pedrorafante.securepassword.controller;

import com.pedrorafante.securepassword.controller.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private PasswordService passwordService;

    @PostMapping(value = "/validate-password")
    public ResponseEntity<FailureResponse> validatePassword(@RequestBody BodyRequest request) {
        var failures = passwordService.validatePass(request.password());

        if (failures.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().body(new FailureResponse(failures));
    }
}
