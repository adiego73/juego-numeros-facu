package ar.com.adiego73.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.adiego73.game.utils.NumbersUtils;

public class Game implements Serializable {

	private static final long serialVersionUID = -2125128642666543600L;
	private static final Integer CANT_NUM = 4;

	private List<Integer> numeroAdivinar = new ArrayList<Integer>(CANT_NUM);
	private Integer intentos;
	private Boolean gano;
	private List<Attempt> attempts;

	public Game() {
		this.build();
	}

	public void build() {
		intentos = 0;
		gano = Boolean.FALSE;
		NumbersUtils.setRandomNumber(numeroAdivinar, CANT_NUM);
		attempts = new ArrayList<Attempt>();
	}

	public Integer[] probarNumeros(List<Integer> numeros) {
		Integer regular = 0;
		Integer bien = 0;
		Integer result[] = new Integer[2];
		for (int i = 0; i < numeroAdivinar.size(); i++) {
			if (numeroAdivinar.get(i).equals(numeros.get(i))) {
				bien++;
			} else if (numeros.contains(numeroAdivinar.get(i))) {
				regular++;
			}
		}
		intentos++;
		result[0] = bien;
		result[1] = regular;

		if (bien == CANT_NUM)
			gano = Boolean.TRUE;

		return result;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public Boolean getGano() {
		return gano;
	}

	public Integer getNumeroAdivinar() {
		String numero = "";
		for (Integer i : numeroAdivinar) {
			numero += i.toString();
		}
		return Integer.valueOf(numero);
	}

	public List<Attempt> getAttempts() {
		return attempts;
	}

	public void setAttempts(List<Attempt> attempts) {
		this.attempts = attempts;
	}

	public void addAttempt(Attempt att) {
		this.getAttempts().add(att);
	}

}
