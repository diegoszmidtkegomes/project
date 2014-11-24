package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fullservicecar.R;


public class Sobre_Nos_Fragment extends Fragment{
	
	public Sobre_Nos_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.sobre_nos_fragment, container,false);
		getActivity().getActionBar().setSubtitle("Full Service Car");
		
		return view;
	}

	
}
