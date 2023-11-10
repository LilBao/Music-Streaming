package com.rhymthwave.Service;

import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Monitor;
import com.rhymthwave.entity.Recording;

public interface MonitorService {
	Monitor checkExist(Recording recording, Account account);
}
