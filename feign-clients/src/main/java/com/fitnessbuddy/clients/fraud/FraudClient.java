package com.fitnessbuddy.clients.fraud;

import com.fitnessbuddy.clients.config.MyFeignClientConfiguration;
import com.fitnessbuddy.clients.fraud.requests.AddFraudsterRequest;
import com.fitnessbuddy.clients.fraud.response.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "fraud", configuration = MyFeignClientConfiguration.class
)
public interface FraudClient {
    @RequestMapping(method=RequestMethod.GET, path = "api/fraud-check")
    FraudCheckResponse isFraud(@RequestParam(value="emailAddress") String emailAddress,
                               @RequestParam(value="ipAddress") String ipAddress);

    @RequestMapping(method = RequestMethod.POST, path = "api/fraud/newFraudster", consumes = "application/json")
    ResponseEntity<String> addNewFraudster(AddFraudsterRequest fraudsterRequest, @RequestParam(value="addedBy") String addedBy);

}
