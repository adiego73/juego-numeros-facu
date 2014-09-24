package ar.com.adiego73.game.model;

import java.util.List;

import ar.com.adiego73.game.utils.NumbersUtils;

public class Game {

	private Integer numeroAdivinar[] = new Integer[4];
	private Integer intentos;
	private Boolean gano;

	public Game() {
		this.build();
	}

	public void build() {
		intentos = 0;
		gano = Boolean.FALSE;
		NumbersUtils.setRandomNumber(numeroAdivinar);
	}

	public String probarNumeros(List<Integer> numeros) {
		Integer regular = 0;
		Integer bien = 0;
		String result = "";
		for (int i = 0; i < numeroAdivinar.length; i++) {
			if (numeroAdivinar[i].equals(numeros.get(i))) {
				bien++;
			} else if (numeros.contains(numeroAdivinar[i])) {
				regular++;
			}
		}
		intentos++;
		result += (bien > 0) ? bien + "B " : "";
		result += (regular > 0) ? regular + "R" : "";

		if (bien == 4)
			gano = Boolean.TRUE;

		return result;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public Boolean getGano() {
		return gano;
	}
	
	public Integer getNumeroAdivinar(){
		String numero = "";
		for(int i = 0; i < numeroAdivinar.length; i++){
			numero += numeroAdivinar[i];
		}
		return Integer.valueOf(numero);
	}
}
