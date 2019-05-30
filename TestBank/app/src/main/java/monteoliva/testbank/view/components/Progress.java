package monteoliva.testbank.view.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import monteoliva.testbank.R;

public class Progress extends LinearLayout {
	/**
	 * Constructor
	 *
	 * @param context
	 */
	public Progress(@NonNull Context context) { super(context); init(context); }
	public Progress(@NonNull Context context, @NonNull AttributeSet attrs) { super(context, attrs); init(context);}

	/**
	 * Metodo de inicializacao
	 *
	 * @param context
	 */
	private void init(Context context) {
		setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		inflater.inflate(R.layout.progress, this);
	}

	public void hide() { setVisibility(View.GONE);    }
	public void show() { setVisibility(View.VISIBLE); }
}