package ar.com.adiego73.game.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

	private Integer id;
	private Date date;
	private Integer score;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date) + "\t\t" + score;
	}
}
