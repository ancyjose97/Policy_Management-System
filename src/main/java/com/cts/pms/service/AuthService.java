package com.cts.pms.service;

import com.cts.pms.dto.LoginDto;
import com.cts.pms.dto.RegisterDto;


public interface AuthService {

	String registerCustomer(RegisterDto registerDto);

	String login(LoginDto loginDto);

}
