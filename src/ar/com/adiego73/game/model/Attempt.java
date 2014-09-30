package ar.com.adiego73.game.model;

public class Attempt {

	private Integer id;
	private String number;
	private String help;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	@Override
	public String toString() {
		return id + "\t\t" + help + "\t\t" + number;
	}
}
