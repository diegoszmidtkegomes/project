package fragmentos;

import funcoes.Mensagem;
import gps.GPS_Opc1;
import gps.GPS_Opc3;
import gps.GPS_Opc4;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.fullservicecar.R;

public class GPS_Fragment extends Fragment {

	ImageButton btendatualoutroendereco;
	ImageButton bteenderecoendereco;
	ImageButton btcoordenadas;
	ImageButton btenderecocoordenadas;
	boolean conectadointernet;
	boolean conectadogps;

	public GPS_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.gps_menu, container, false);

		btendatualoutroendereco = (ImageButton) view
				.findViewById(R.id.gps_bt_menu_opc1);
		bteenderecoendereco = (ImageButton) view
				.findViewById(R.id.gps_bt_menu_opc2);
		btcoordenadas = (ImageButton) view.findViewById(R.id.gps_bt_menu_opc3);
		btenderecocoordenadas = (ImageButton) view
				.findViewById(R.id.gps_bt_menu_opc4);
		btendatualoutroendereco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				conectadogps = validagps();
				if (conectadogps) {
					conectadointernet = validainternet();
					if (conectadointernet) {
						Intent intent = new Intent(getActivity(),
								GPS_Opc1.class);
						startActivity(intent);

					} else {
						mostraAlerta("Internet");
					}

				} else {
					mostraAlerta("GPS");
				}
			}
		});

		bteenderecoendereco.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Mensagem("Aviso!", "Função temporariamente indisponível devido atualizações!", "OK", R.drawable.icone_informativo, getActivity());
				/*conectadogps = validagps();
				if (conectadogps) {
					conectadointernet = validainternet();
					if (conectadointernet) {
						Intent intent = new Intent(getActivity(),
								GPS_Opc2.class);
						startActivity(intent);

					} else {
						mostraAlerta("Internet");
					}

				} else {
					mostraAlerta("GPS");
				}*/
			}
		});
		btcoordenadas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				conectadogps = validagps();
				if (conectadogps) {
					conectadointernet = validainternet();
					if (conectadointernet) {
						Intent intent = new Intent(getActivity(),
								GPS_Opc3.class);
						startActivity(intent);

					} else {
						mostraAlerta("Internet");
					}

				} else {
					mostraAlerta("GPS");
				}
			}
		});
		btenderecocoordenadas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				conectadogps = validagps();
				if (conectadogps) {
					conectadointernet = validainternet();
					if (conectadointernet) {
						Intent intent = new Intent(getActivity(),
								GPS_Opc4.class);
						startActivity(intent);

					} else {
						mostraAlerta("Internet");
					}

				} else {
					mostraAlerta("GPS");
				}
			}
		});

		return view;

	}

	public boolean validagps() {
		LocationManager service = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = service
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validainternet() {
		ConnectivityManager gerenciador = (ConnectivityManager) getActivity()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo informacao = gerenciador.getActiveNetworkInfo();
		if ((informacao != null) && (informacao.isConnectedOrConnecting())
				&& (informacao.isAvailable())) {
			return true;
		} else {
			return false;
		}
	}

	private void mostraAlerta(String tipo) {
		if (tipo == "GPS") {
			Toast.makeText(getActivity(), "GPS DESABILITADO",
					Toast.LENGTH_SHORT).show();
			// Intent intent = new
			// Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			// startActivity(intent);
		} else {
			if (tipo == "Internet") {
				Toast.makeText(getActivity(), "Sem conexão com a internet",
						Toast.LENGTH_SHORT).show();
			}

		}

	}

}
