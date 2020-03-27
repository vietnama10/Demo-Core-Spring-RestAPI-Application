package vn.dhteams.service.onlineshop.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.dhteams.service.onlineshop.controller.UserController;
import vn.dhteams.service.onlineshop.domain.User;
import vn.dhteams.service.onlineshop.service.UserService;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;

	@Test
	public void checkUniTest() {
		final int expected = 3;
		final int actual = 3;
		assertEquals(expected, actual);
	}

	@Test
	public void getAllUser_Success() throws Exception {
		List<User> userList = new ArrayList<User>();
		ObjectMapper oMapper = new ObjectMapper();
		User user1 = new User(); user1.setId(1L); user1.setUserName("user1");
		User user2 = new User(); user2.setId(2L); user2.setUserName("user2");
		userList.add(user1);
		userList.add(user2);
		Map<String, Object> getAllUserResult = new HashMap<>();
		getAllUserResult.put("result", userList);
		getAllUserResult.put("status", true);
		when(userService.getAllUser(null, null, null, null)).thenReturn(getAllUserResult);
		Map<String, Object> result = userController.getAllUser(null, null, null, null);
		assertTrue(Boolean.parseBoolean(result.get("status").toString()));
		assertThat(result.get("result")).isNotEqualTo(null);
		List<User> listUser = oMapper.readValue(oMapper.writeValueAsString(result.get("result"))
						, new TypeReference<List<User>>(){});
		assertThat(listUser.size()).isEqualTo(2);
		assertThat(listUser.get(0).getId()).isEqualTo(user1.getId());
	}
	
	@Test
	public void getAllUser_ThrowException() throws Exception {
		doThrow(RuntimeException.class).when(userService).getAllUser(null, null, null, null);
		Map<String, Object> result = userController.getAllUser(null, null, null, null);
		assertFalse(Boolean.parseBoolean(result.get("status").toString()));
	}
}
