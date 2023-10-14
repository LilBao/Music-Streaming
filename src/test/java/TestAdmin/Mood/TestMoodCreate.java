package TestAdmin.Mood;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.rhymthwave.DAO.MoodDAO;
import com.rhymthwave.ServiceAdmin.Implement.MoodServiceImp;

public class TestMoodCreate {

	@InjectMocks
	private MoodServiceImp moodServiceImp;

	@Mock
	private MoodDAO moodDAO;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testMoodCreateSuccess() {
		
	}
}
