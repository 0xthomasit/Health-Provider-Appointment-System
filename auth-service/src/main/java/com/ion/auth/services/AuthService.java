package com.ion.auth.services;

import com.ion.auth.dtos.JwtResponseDTO;
import com.ion.auth.models.User;

/**
 * Author: Thomas Ng
 * User:0xthomasit
 * Date:06-04-2026
 */

public interface AuthService {

    String createUser(User user);

    JwtResponseDTO generateToken(String username);

    void validateToken(String jwtToken);

}