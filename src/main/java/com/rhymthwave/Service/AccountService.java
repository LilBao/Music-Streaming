package com.rhymthwave.Service;

import java.util.List;
import com.rhymthwave.entity.Account;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {

	Account findAdminByEmail(HttpServletRequest request);

	List<Object> search(String keyword);
}
