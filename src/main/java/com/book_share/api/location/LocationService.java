package com.book_share.api.location;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public final class LocationService {

    public List<String> getCities() {
        return Arrays.stream(City.values()).map(City::getName).toList();
    }
}
