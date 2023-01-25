package com.modsen.route.service.services;

import java.util.List;

public interface RouteExecutingService {

    List<String> getRoute(String algorithm, String from, String to);
}
