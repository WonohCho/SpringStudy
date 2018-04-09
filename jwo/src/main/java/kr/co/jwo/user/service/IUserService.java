package kr.co.jwo.user.service;

import kr.co.jwo.user.dto.UserDTO;

public interface IUserService {
	
	public void write(UserDTO userDTO);
	public void edit(UserDTO userDTO);
	public UserDTO view(int userId);
	public int viewCountByLoginId(String loginId);
	public void remove(int userId);
}
