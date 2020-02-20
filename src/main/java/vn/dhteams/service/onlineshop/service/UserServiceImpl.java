package vn.dhteams.service.onlineshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vn.dhteams.service.onlineshop.dao.UserDao;
import vn.dhteams.service.onlineshop.domain.Users;
import vn.dhteams.service.onlineshop.dto.PaginationDto;
import vn.dhteams.service.onlineshop.repository.UserRepository;
import vn.dhteams.service.onlineshop.utils.Messages;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, Object> getAllUser(Integer page, Integer pageSize, String sortData, String searchData) {
		Map<String, Object> result = new HashMap<>();
		if(sortData == null || sortData == "") {
			sortData = "id DESC";
		}
		Pageable pageable = null;
		if(page != null & pageSize != null) {
			pageable = PageRequest.of(page - 1, pageSize);
			result.put("pagination", new PaginationDto(
					page, pageSize, userDao.listUser(null, sortData, searchData).size()));
		}
		result.put("result", userDao.listUser(pageable, sortData, searchData));
		result.put("status", true);
		return result;
	}

	@Override
	public Map<String, Object> getById(Long id) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", userRepository.findById(id));
		result.put("status", true);
		return result;
	}

	@Override
	public Map<String, Object> getByUserName(String userName) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", userRepository.findByUserName(userName));
		result.put("status", true);
		return result;
	}

	@Override
	public Map<String, Object> createUser(Users user) {
		Map<String, Object> result = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user.setNguoiTao(auth.getName());
		result.put("result", userRepository.save(user));
		result.put("msg", Messages.SUCCESS_CREATE);
		result.put("status", true);
		return result;
	}

	@Override
	public Map<String, Object> updateUser(Users user) {
		Map<String, Object> result = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user.setNguoiSua(auth.getName());
		result.put("result", userRepository.save(user));
		result.put("msg", Messages.SUCCESS_UPDATE);
		result.put("status", true);
		return result;
	}

	@Override
	public Map<String, Object> deleteUser(List<Long> userIds) {
		Map<String, Object> result = new HashMap<>();
		List<Users> listUserWillDelete = new ArrayList<>();
		userIds.forEach(id -> {
			Optional<Users> opUser = userRepository.findById(id);
			if(opUser.isPresent()) {
				listUserWillDelete.add(opUser.get());
			}
		});
		userRepository.deleteAll(listUserWillDelete);
		result.put("msg", Messages.SUCCESS_DELETE);
		result.put("status", true);
		return result;
	}

}
