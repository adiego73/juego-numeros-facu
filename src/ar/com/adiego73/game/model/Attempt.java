package ar.com.adiego73.game.model;

public class Attempt {

	private Integer intento;
	private String number;
	private String help;

	public Integer getIntento() {
		return intento;
	}

	public void setIntento(Integer id) {
		this.intento = id;
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
		return intento + "\t\t" + help + "\t\t" + number;
	}
}
