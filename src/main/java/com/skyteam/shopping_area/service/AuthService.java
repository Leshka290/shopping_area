package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto register);
}
