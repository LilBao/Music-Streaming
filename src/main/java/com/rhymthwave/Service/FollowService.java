package com.rhymthwave.Service;

import com.rhymthwave.entity.Author;
import com.rhymthwave.entity.Follow;

public interface FollowService {
	Follow snapFollow(Follow follow,Author accountA, Author accountB);
	
	Follow findFollowByAccount(Author accountA, Author accountB);
}
