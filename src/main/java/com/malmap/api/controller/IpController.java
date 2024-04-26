package com.malmap.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malmap.api.model.Ip;
import com.malmap.api.model.IpLocation;
import com.malmap.api.repository.IpRepository;

@RestController
@RequestMapping("/api/ips")
@CrossOrigin(origins = "http://localhost:5173")
public class IpController {
    private final IpRepository ipRepository;

    public IpController(IpRepository ipRepository) {
        this.ipRepository = ipRepository;
    }

    @GetMapping
    public List<Ip> getAllIps() {
        return ipRepository.findAll();
    }

    @GetMapping("/limit")
    public List<Ip> getIpsWithLimit() {
        return ipRepository.findIpsWithLimit();
    }

    @GetMapping("/location")
    public List<IpLocation> getIpsLocation() {
        Pageable pageable = PageRequest.of(0, 1000); // 0페이지에서 1000개의 결과 조회
        return ipRepository.findIpsLocation(pageable);
    }

    @GetMapping("/country-count")
    public List<Map<String, Object>> getCountryCount() {
        List<Object[]> results = ipRepository.countByCountryCode();
        
        List<Map<String, Object>> countryCountList = new ArrayList<>();
        
        for (Object[] result : results) {
            String countryCode = (String) result[0];
            Long count = (Long) result[1];
            
            Map<String, Object> countryCount = new HashMap<>();
            countryCount.put("country_code", countryCode);
            countryCount.put("count", count);
            
            countryCountList.add(countryCount);
        }
        
        return countryCountList;
    }

    @GetMapping("/country-count-gps")
    public List<Map<String, Object>> getCountryCountWithGps() {
        List<Object[]> results = ipRepository.countByCountryCodeWithGps();
        
        List<Map<String, Object>> countryCountList = new ArrayList<>();
        
        for (Object[] result : results) {
            String countryCode = (String) result[0];
            Long count = (Long) result[1];
            Double longitude = (Double) result[2];
            Double latitude = (Double) result[3];
            
            Map<String, Object> countryCount = new HashMap<>();
            countryCount.put("country_code", countryCode);
            countryCount.put("count", count);
            countryCount.put("longitude", longitude != null ? longitude : 0.0);
            countryCount.put("latitude", latitude != null ? latitude : 0.0);
            
            countryCountList.add(countryCount);
        }
        
        return countryCountList;
    }
}