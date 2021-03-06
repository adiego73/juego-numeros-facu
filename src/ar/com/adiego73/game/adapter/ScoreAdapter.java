package ar.com.adiego73.game.adapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ar.com.adiego73.game.R;
import ar.com.adiego73.game.model.Score;
import ar.com.adiego73.game.utils.AssetsHelper;

public class ScoreAdapter extends ArrayAdapter<Score> {

	private Context context;
	private List<Score> scores;
	private static LayoutInflater inflater = null;

	public ScoreAdapter(Context cntx, List<Score> scores) {
		super(cntx, R.layout.scores_view_item_layout, scores);

		this.context = cntx;
		this.scores = scores;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder viewHolder;

		if (vi == null) {
			vi = inflater.inflate(R.layout.scores_view_item_layout, parent,
					false);

			viewHolder = new ViewHolder();
			viewHolder.date = (TextView) vi.findViewById(R.id.txtScoreDate);
			viewHolder.date.setTypeface(AssetsHelper.getDolceFontTypeFace());
			viewHolder.totalAttempts = (TextView) vi
					.findViewById(R.id.txtScoreTotalAttempts);
			viewHolder.totalAttempts.setTypeface(AssetsHelper.getDolceFontTypeFace());

			vi.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) vi.getTag();
		}

		if (!scores.isEmpty()) {
			Score score = scores.get(position);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", new Locale("es_AR"));
			viewHolder.date.setText(format.format(score.getDate()));
			viewHolder.totalAttempts.setText(score.getScore().toString());
		}

		return vi;
	}

	private static class ViewHolder {
		public TextView date;
		public TextView totalAttempts;
	}

}
