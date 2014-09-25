package ar.com.adiego73.game.attempt.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ar.com.adiego73.game.model.Attempt;

public class AttemptAdapter extends ArrayAdapter<Attempt> {

	private Context context;
	private List<Attempt> attempts;
	private static LayoutInflater inflater = null;

	public AttemptAdapter(Context cntx, List<Attempt> attempts) {
		super(cntx, ar.com.adiego73.game.R.layout.attempts_view_item_layout,
				attempts);
		this.context = cntx;
		this.attempts = attempts;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder viewHolder;

		if (vi == null) {
			vi = inflater.inflate(
					ar.com.adiego73.game.R.layout.attempts_view_item_layout,
					parent, false);

			viewHolder = new ViewHolder();
			viewHolder.id = (TextView) vi
					.findViewById(ar.com.adiego73.game.R.id.txtAttemptId);
			viewHolder.help = (TextView) vi
					.findViewById(ar.com.adiego73.game.R.id.txtAttemptHelp);
			viewHolder.number = (TextView) vi
					.findViewById(ar.com.adiego73.game.R.id.txtAttemptNumber);

			vi.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) vi.getTag();
		}

		if (!attempts.isEmpty()) {
			Attempt att = attempts.get(position);
			viewHolder.id.setText(att.getId().toString());
			viewHolder.help.setText(att.getHelp());
			viewHolder.number.setText(att.getNumber().toString());
		}

		return vi;
	}

	private static class ViewHolder {
		public TextView id;
		public TextView help;
		public TextView number;
	}

}
