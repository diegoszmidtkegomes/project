package menu_lateral;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import banco_de_dados.DAOMotorista;

import com.example.fullservicecar.R;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

	private Context context;
	private List<DrawerItem> drawerItemList;
	private int layoutResID;
	private int usuarioAtual = 0;
	
	// Classe para o suporte das funções do menu lateral
	
	private static class DrawerItemHolder {
		
		TextView tituloFuncao, nomeSessao, contador;
		ImageView iconeFuncao;
		LinearLayout cabecalhoSessao, corpoFuncao, usuarioSpinnerLayout;
		Spinner usuarioSpinner;
		
	}
	
	// Construtor para o menu lateral personalizado
	
	public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
		
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	// Insere as telas no menu lateral
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			
			// O contexto é herdado por uma view em xml, por isso chamamos o inflater
			
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			
			// Referencio o drawer aos itens lá do xml
			
			drawerHolder.usuarioSpinnerLayout = (LinearLayout) view.findViewById(R.id.usuarioSpinnerLayout);
			drawerHolder.usuarioSpinner = (Spinner) view.findViewById(R.id.usuarioSpinner);
			
			drawerHolder.cabecalhoSessao = (LinearLayout) view.findViewById(R.id.cabecalhoSessao);
			drawerHolder.nomeSessao = (TextView) view.findViewById(R.id.nomeSessao);
			
			drawerHolder.corpoFuncao = (LinearLayout) view.findViewById(R.id.corpoFuncao);
			drawerHolder.iconeFuncao = (ImageView) view.findViewById(R.id.iconeFuncao);
			drawerHolder.tituloFuncao = (TextView) view.findViewById(R.id.tituloFuncao);
			
			drawerHolder.contador = (TextView) view.findViewById(R.id.contador);
			
			// Esse set tag é só pra n precisar criar varios .setOnClickListener, dai crio só um e pego pela posição
			
			view.setTag(drawerHolder);

		} else {
			
			// Se já foi criado a view, só pegamos ela pela tag q é herda o onClickListener
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		
		// Aqui é os itens do drawer
		
		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
		
		if (dItem.isSpinner()) {
			
			// Se o item for um spinner, só deixo visivel o spinner do usuario
			
			drawerHolder.cabecalhoSessao.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.corpoFuncao.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.usuarioSpinnerLayout.setVisibility(LinearLayout.VISIBLE);

			final List<SpinnerItem> listaUsuario = new ArrayList<SpinnerItem>();

			//listaUsuario.add(new SpinnerItem(R.drawable.icone_usuario_leonardo, "Leonardo Carmona","leocarmona@hotmail.com.br"));
			listaUsuario.add(new SpinnerItem(R.drawable.user, DAOMotorista.getInstancia(getContext()).buscarTodos().get(0).getNome(), DAOMotorista.getInstancia(getContext()).buscarTodos().get(0).getEmail()));

			//listaUsuario.add(new SpinnerItem(R.drawable.icone_usuario_diego, "Diego Szmidtke", "diego.szmidtke@outlook.com"));
			
			CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context, R.layout.menu_lateral_usuario, listaUsuario);
			
			drawerHolder.usuarioSpinner.setAdapter(adapter);
			
			drawerHolder.usuarioSpinner.setSelection(usuarioAtual);
			
			drawerHolder.usuarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			        
			    	if(usuarioAtual != pos){
			    		
			    		Toast.makeText(context, "Usuário atual: " + listaUsuario.get(pos).nomeUsuario, Toast.LENGTH_SHORT).show();
			    		usuarioAtual = pos;
			    		
			    	}
			    	
			    }
			    public void onNothingSelected(AdapterView<?> parent) {
			    }
			});
			
		} else if (dItem.getNomeSessao() != null) {
			
			// Como nas outras partes eu n uso titulo e sim as variavel do xml, verifico se o titulo n é nulo, dai significa q é o nome da sessão
			drawerHolder.cabecalhoSessao.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.corpoFuncao.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.usuarioSpinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			
			drawerHolder.nomeSessao.setText(dItem.getNomeSessao());

		} else {

			// Se n for spinner e o titulo for nulo, significa q é parte do corpo das funcc
			
			drawerHolder.cabecalhoSessao.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.usuarioSpinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.corpoFuncao.setVisibility(LinearLayout.VISIBLE);

			drawerHolder.iconeFuncao.setImageDrawable(view.getResources().getDrawable(dItem.getIconeFuncao()));
			drawerHolder.tituloFuncao.setText(dItem.getTituloFuncao());

		}
		
		if(dItem.isContadorVisivel()){
			
			drawerHolder.contador.setText(dItem.getContador());
			drawerHolder.contador.setVisibility(LinearLayout.VISIBLE);
			
		}else{
			
			drawerHolder.contador.setVisibility(LinearLayout.INVISIBLE);
			
		}
		return view;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		
		return drawerItemList.size();
		
	}

	@Override
	public DrawerItem getItem(int position) {
		// TODO Auto-generated method stub
		
		return drawerItemList.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		
		return position;
				
	}
	
}