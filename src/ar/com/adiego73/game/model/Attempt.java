package ar.com.adiego73.game.model;

public class Attempt {

	private Integer id;
	private Integer number;
	private String help;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
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
