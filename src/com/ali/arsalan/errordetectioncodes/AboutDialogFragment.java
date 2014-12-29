package com.ali.arsalan.errordetectioncodes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class AboutDialogFragment extends DialogFragment{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_fragment_about, null);
		
		return new AlertDialog.Builder(getActivity())
		.setIcon(R.drawable.ic_launcher)
		.setView(v)
		.setTitle(R.string.about)
		.setPositiveButton("Good!", null)
		.create();
	}

}
