package utcn.ti.proiect_licenta.dto;


public class LoginInfoDTO {
	private UserDTO userDTO;
	private AngajatInfoDTO angajatInfoDTO;

	public LoginInfoDTO(UserDTO userDTO, AngajatInfoDTO angajatInfoDTO) {
		this.userDTO = userDTO;
		this.angajatInfoDTO = angajatInfoDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public AngajatInfoDTO getAngajatInfoDTO() {
		return angajatInfoDTO;
	}

	public void setAngajatInfoDTO(AngajatInfoDTO angajatInfoDTO) {
		this.angajatInfoDTO = angajatInfoDTO;
	}

}
