package ar.com.adiego73.game.utils;

import java.util.Random;

public class NumbersUtils {

	private static Integer MIN_NUM = 0;
	private static Integer MAX_NUM = 9;

	public static void setRandomNumber(Integer[] numeroAdivinar) {

		for (int i = 0; i < numeroAdivinar.length; i++) {
			Integer numero;
			if (i == 0) {
				numero = getRandomWithMinMax(MIN_NUM + 1, MAX_NUM);
				numeroAdivinar[0] = numero;
			} else {
				do {
					numero = getRandomWithMinMax(MIN_NUM, MAX_NUM);
				} while (numero == numeroAdivinar[i - 1]);
				numeroAdivinar[i] = numero;
			}
		}

	}

	private static Integer getRandomWithMinMax(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

}
