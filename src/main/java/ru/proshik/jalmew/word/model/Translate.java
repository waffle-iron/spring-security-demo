package ru.proshik.jalmew.word.model;

public class Translate {

	private String address;

	private int age;

	private String educations;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEducations() {
		return educations;
	}

	public void setEducations(String educations) {
		this.educations = educations;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Translate translate = (Translate) o;

		if (age != translate.age) return false;
		if (address != null ? !address.equals(translate.address) : translate.address != null) return false;
		return !(educations != null ? !educations.equals(translate.educations) : translate.educations != null);

	}

	@Override
	public int hashCode() {
		int result = address != null ? address.hashCode() : 0;
		result = 31 * result + age;
		result = 31 * result + (educations != null ? educations.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Translate{" +
				"address='" + address + '\'' +
				", age=" + age +
				", educations='" + educations + '\'' +
				'}';
	}
}
