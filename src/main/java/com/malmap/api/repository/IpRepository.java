package com.malmap.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.malmap.api.model.Ip;
import com.malmap.api.model.IpLocation;

public interface IpRepository extends JpaRepository<Ip, Long> {
    // Custom query methods if needed
    @Query(value = "SELECT * FROM ips LIMIT 1000", nativeQuery = true)
    List<Ip> findIpsWithLimit();

    @Query("SELECT new com.malmap.api.model.IpLocation(i.countryCode, i.latitude, i.longitude) FROM Ip i")
    List<IpLocation> findIpsLocation(Pageable pageable);

    @Query("SELECT i.countryCode, COUNT(i) FROM Ip i GROUP BY i.countryCode")
    List<Object[]> countByCountryCode();

    @Query("SELECT i.countryCode, COUNT(i), g.longitude, g.latitude " +
       "FROM Ip i " +
       "LEFT JOIN Gps g ON i.countryCode = g.countryCode " +
       "GROUP BY i.countryCode")
    List<Object[]> countByCountryCodeWithGps();
}
