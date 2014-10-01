package ar.com.adiego73.game.utils;

import java.util.List;
import java.util.Random;

public class NumbersUtils {

	private static Integer MIN_NUM = 0;
	private static Integer MAX_NUM = 9;

	public static void setRandomNumber(List<Integer> numeroAdivinar,
			Integer amountNumbers) {

		numeroAdivinar.clear();
		for (int i = 0; i < amountNumbers; i++) {
			Integer numero;
			if (i == 0) {
				numero = getRandomWithMinMax(MIN_NUM + 1, MAX_NUM);
			} else {
				do {
					numero = getRandomWithMinMax(MIN_NUM, MAX_NUM);
					// si el numero no puede estar, no esta en ningun lado..
				} while (numeroAdivinar.contains(numero));
			}
			numeroAdivinar.add(i, numero);
		}
	}

	private static Integer getRandomWithMinMax(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
