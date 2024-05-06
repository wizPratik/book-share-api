package com.book_share.api.location;

import com.book_share.api.base.api.BaseController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/locations")
public final class LocationController implements BaseController {

    private final LocationService locationService;

    public LocationController(final LocationService locationService1) {
        this.locationService = locationService1;
    }

    @GetMapping
    public ResponseEntity<List<String>> getCities() {
        return ResponseEntity.ok(locationService.getCities());
    }
}
