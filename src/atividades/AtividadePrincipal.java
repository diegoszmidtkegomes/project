package atividades;

import java.util.ArrayList;
import java.util.List;

import menu_lateral.CustomDrawerAdapter;
import menu_lateral.DrawerItem;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import banco_de_dados.Configuracoes;
import banco_de_dados.DAOConfiguracoes;
import banco_de_dados.DAOLembretes;
import banco_de_dados.DAOMotorista;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;

import fragmentos.Abastecimentos_Fragment;
import fragmentos.Calculadora_Flex;
import fragmentos.Configuracoes_Fragment;
import fragmentos.Contato_Fragment;
import fragmentos.CustoVeicular_Fragment;
import fragmentos.Despesas_Fragment;
import fragmentos.GPS_Fragment;
import fragmentos.Home_Fragment;
import fragmentos.Lembretes_Fragment;
import fragmentos.Relatorios_Fragment;
import fragmentos.Servicos_Fragment;
import fragmentos.Sobre_Nos_Fragment;
import fragmentos.Veiculos_Fragment;
import funcoes.FLembrete;
import funcoes.Mensagem;

public class AtividadePrincipal extends Activity {

	// Variáveis
	
	private static DrawerLayout drawerLayout;
	
	private static ListView menuLateral;
	
	private ActionBarDrawerToggle iconeDrawer;

	private CharSequence tituloApp;
	
	private CharSequence tituloFragment;

	public List<DrawerItem> listaDeDados;
	
	public static Context contexto;
	
	public static int posicao = 2;
	
	public static boolean notificacao = true;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.atividade_principal);
		
		contexto = this;
		
		initConfig();
		
		if(notificacao){
			new FLembrete().acionarLembrete(contexto);
			notificacao = false;
		}
		
		
		instanciarVariaveis();

		adicionarItensMenuLateral();

		habilitarMenuLateral();
		
		habilitarBotaoMenuLateral();

		habilitarAcaoBotaoMenuLateral();
		
		selecionarItem(posicao);
		

	}
	
	
	
	private Menu adicionarActionBar(Menu menu, int id){
		
		menu.findItem(id).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		menu.findItem(id).setVisible(true);
		return menu;
		
	}
	
	// Método para instanciar as variáveis
	
	private void initConfig(){
		
		if(DAOConfiguracoes.getInstancia(this).getTamanho() == 0){
			DAOConfiguracoes.getInstancia(this).inserir(new Configuracoes(1));
		}
		
		if(DAOConfiguracoes.getInstancia(this).buscarTodos().get(0).getLembreteAtivo() == 0){
			notificacao = false;
		}
		
	}
	
	private void instanciarVariaveis(){
		
		listaDeDados = new ArrayList<DrawerItem>();
		tituloFragment = tituloApp = getTitle();
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		menuLateral = (ListView) findViewById(R.id.menu_lateral);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
	}
	
	// Método para adicionar as funcionalidades no menu lateral
	
	private void adicionarItensMenuLateral(){
		
		// 0
		listaDeDados.add(new DrawerItem(true));
		// 1
		listaDeDados.add(new DrawerItem("Informações do usuário"));
		listaDeDados.add(new DrawerItem("Home", R.drawable.icone_home));
		listaDeDados.add(new DrawerItem("Veículos", R.drawable.icone_veiculos));
		
		// 4
		listaDeDados.add(new DrawerItem("Sessões"));
		listaDeDados.add(new DrawerItem("Abastecimentos",R.drawable.icone_abastecimento));
		listaDeDados.add(new DrawerItem("Despesas", R.drawable.icone_despesas));
		listaDeDados.add(new DrawerItem("Serviços", R.drawable.icone_servicos));
		listaDeDados.add(new DrawerItem("Relatórios", R.drawable.icone_relatorios));

		// 10
		listaDeDados.add(new DrawerItem("Ferramentas"));
		listaDeDados.add(new DrawerItem("GPS", R.drawable.icone_gps));
		listaDeDados.add(new DrawerItem("Custo Veícular", R.drawable.icone_calculadora));
		listaDeDados.add(new DrawerItem("Calculadora Flex", R.drawable.icone_calculadora));
		listaDeDados.add(new DrawerItem("Lembretes", R.drawable.icone_lembretes, true, "+ " + String.valueOf(DAOLembretes.getInstancia(this).getTamanho())));

		// 15
		listaDeDados.add(new DrawerItem("Outros"));
		//listaDeDados.add(new DrawerItem("Ajuda", R.drawable.icone_ajuda));
		listaDeDados.add(new DrawerItem("Configurações", R.drawable.icone_configuracoes));
		listaDeDados.add(new DrawerItem("Contato", R.drawable.icone_contato));
		listaDeDados.add(new DrawerItem("Sobre nós", R.drawable.icone_sobre_nos));
		
		
		
	}
	
	// Método para habilitar o menu lateral
	
	private void habilitarMenuLateral(){
		
		menuLateral.setAdapter(new CustomDrawerAdapter(this, R.layout.menu_lateral_corpo, listaDeDados));
		//menuLateral.setOnItemClickListener(new SlideMenuClickListener());
		menuLateral.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selecionarItem(position);
			}
		});
		
	}
	// Método para habilitar o botão do menu lateral
	
	private void habilitarBotaoMenuLateral(){
		
		// Habilita a visualização do Toggle
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Habilita a ação do Toggle
		
		getActionBar().setHomeButtonEnabled(true);
		
	}
	
	// Método para habilitar os cliques de ação do botão do menu lateral
	
	private void habilitarAcaoBotaoMenuLateral(){
		
		iconeDrawer = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.icone_menu, R.string.abrir_menu, R.string.fechar_menu) {

			public void onDrawerClosed(View view) {
				
				listaDeDados.set(13, new DrawerItem("Lembretes", R.drawable.icone_lembretes, true, "+ " + String.valueOf(DAOLembretes.getInstancia(contexto).getTamanho())));
				getActionBar().setTitle(tituloFragment);
				invalidateOptionsMenu();
				
			}

			public void onDrawerOpened(View drawerView) {
				
				listaDeDados.set(13, new DrawerItem("Lembretes", R.drawable.icone_lembretes, true, "+ " + String.valueOf(DAOLembretes.getInstancia(contexto).getTamanho())));
				getActionBar().setTitle(tituloApp);
				getActionBar().setSubtitle(DAOMotorista.getInstancia(getApplicationContext()).buscarTodos().get(0).getNome());
				invalidateOptionsMenu();
				
			}

		};
		
		drawerLayout.setDrawerListener(iconeDrawer);
		
	}
	

	// Método para abrir as funções selecionadas no menu
	
	public void selecionarItem(int position) {

		if(position != 12){
			posicao = position;
		}
		
		Fragment fragment = null;
		
		switch (position) {

		case 2:
			
			fragment = new Home_Fragment();
			
			break;
			
			
		case 3:
			
			fragment = new Veiculos_Fragment();
			
			break;
			
		case 5:
			
			fragment = new Abastecimentos_Fragment();
			
			break;
			
		case 6:
			
			
			fragment = new Despesas_Fragment();
			
			break;
			
		case 7:
			
			
			fragment = new Servicos_Fragment();
			
			break;
			
		case 8:
			
			fragment = new Relatorios_Fragment();
			
			break;
			
		case 10:
			
			//startActivity(new Intent(this, ActMenu.class));
			fragment = new GPS_Fragment();
			
			break;
			
		case 11:
			
			
			fragment = new CustoVeicular_Fragment();
			
			break;
			
		case 12:
			
			startActivity(new Intent(this, Calculadora_Flex.class));
			//fragment = new Calculadora_Flex_Fragment();
			
			break;
			
		case 13:
			
			fragment = new Lembretes_Fragment();
			break;
			
		/*case 15:
			
			fragment = new Ajuda_Fragment();
			
			break;
		*/
		case 15:
			
			fragment = new Configuracoes_Fragment();
			
			break;
			
		case 16:
			
			fragment = new Contato_Fragment();
			
			break;
			
		case 17:
			
			fragment = new Sobre_Nos_Fragment();
			
			break;
			
		default:
			
			break;
			
		}

		if(fragment != null){
			
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.corpo, fragment).commit();

			menuLateral.setItemChecked(position, true);
			menuLateral.setSelection(position);
			setTitle(listaDeDados.get(position).getTituloFuncao());
			drawerLayout.closeDrawer(menuLateral);

		}else{
			Log.e("MainActivity", "Erro ao criar o fragment");
		}
	}
	
	public static void abrirMenuLateral(){
		drawerLayout.openDrawer(menuLateral);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
		
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		if(posicao == 3 || posicao == 5 || posicao == 6 || posicao == 7|| posicao == 13){
			adicionarActionBar(menu, R.id.action_adicionar);
		}else{
			if(posicao == 2){
				adicionarActionBar(menu, R.id.action_editar);
			}else{
				if(posicao == 11){
					adicionarActionBar(menu, R.id.action_info);
				}
			}
		}
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// Quando clicar no icone, deve abrir ou fechar o menu lateral

if (iconeDrawer.onOptionsItemSelected(item)) {
			
			return true;
		}
		
		switch(item.getItemId()){
		
		case R.id.action_editar:
			
			switch(posicao){
			
				case 2:
				
				startActivity(new Intent(AtividadePrincipal.this, Perfil_Activity.class));
		    
				break;
				
				default:
					
					break;
					
			}
		
		case R.id.action_adicionar:
			
			switch(posicao){
			
				case 3:
					
					new Mensagem("Aviso!", "Disponível somente na versão PRO!", "OK", R.drawable.icone_aviso, contexto);
					break;
					
				case 5:
					
					startActivity(new Intent(AtividadePrincipal.this, Abastecimento_Activity.class));
			        break;
			
				case 6:
					
			        startActivity(new Intent(AtividadePrincipal.this, Despesas_Activity.class));
			        break;
			        
				case 7:
					
					startActivity(new Intent(AtividadePrincipal.this, Servicos_Activity.class));
			        break;
			        
				case 13:
					startActivity(new Intent(AtividadePrincipal.this, Lembretes_Activity.class));
			        break;
			
			}
			
			return true;
			
		case R.id.action_info:
			
			new Mensagem("Informação", 
					"* Custo de oportunidade: \n" +
					"   Valor investido no carro que você acumularia em uma aplicação financeira.\n\n" +
					"* Depreciação anual: \n" +
					"   O quanto seu carro desvaloriza ano a ano." /*+
					"* Distância dia (Km): \n" +
					"     Estimativa de quantos quilômetros você percorre diariamente com o carro."*/,
					"Ok, entendido!", R.drawable.icone_informativo, this);
			
			return true;
			
		default:
			
			return super.onOptionsItemSelected(item);
	
	}

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		
		super.onPostCreate(savedInstanceState);
		
		// Sincroniza o estado do toggle depois que o bundle é restaurado
		
		iconeDrawer.syncState();
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
		
		// Qualquer alteração como sair de tela ou sla, reseta o toggle ou altera, deixando ele mais para esquerda/direita
		
		iconeDrawer.onConfigurationChanged(newConfig);
		
	}

	@Override
	public void setTitle(CharSequence title) {
		
		tituloFragment = title;
		getActionBar().setTitle(tituloFragment);
		if(posicao == 2){
			getActionBar().setSubtitle(DAOMotorista.getInstancia(getApplicationContext()).buscarTodos().get(0).getNome());
		}else{
			getActionBar().setSubtitle(DAOVeiculo.getInstancia(getApplicationContext()).buscarTodos().get(0).getNome());
		}
		
	}
	

}