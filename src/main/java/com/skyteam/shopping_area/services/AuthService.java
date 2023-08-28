package com.skyteam.shopping_area.services;

import com.skyteam.shopping_area.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
