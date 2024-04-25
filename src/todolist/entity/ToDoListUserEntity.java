package todolist.entity;

public class ToDoListUserEntity {

	private String id;
	private String pw;
	private String namekanji;
	private String namekatakana;
	private String nameenglish;
	private String phone;
	private String address;
	private String gender;
	private String email;
	private String birth;
	private String employment;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNamekanji() {
		return namekanji;
	}
	public void setNamekanji(String namekanji) {
		this.namekanji = namekanji;
	}
	public String getNamekatakana() {
		return namekatakana;
	}
	public void setNamekatakana(String namekatakana) {
		this.namekatakana = namekatakana;
	}
	public String getNameenglish() {
		return nameenglish;
	}
	public void setNameenglish(String nameenglish) {
		this.nameenglish = nameenglish;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	@Override
	public String toString() {
		return "ToDoListUserEntity [id=" + id + ", pw=" + pw + ", namekanji=" + namekanji + ", namekatakana=" + namekatakana + ", nameenglish=" + nameenglish + "phone=" + phone + "address=" + address + "gender=" + gender + "email=" + email + "birth=" + birth + "employment" + employment + "]";

	}
}
