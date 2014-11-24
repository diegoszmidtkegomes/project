
package grafico;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOServicos;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

import funcoes.FServico;

public class Servico_GraficoServico extends DemoBase {

    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Comparativo de Serviços");
        getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
        mChart = (PieChart) findViewById(R.id.chart1);

        // change the color of the center-hole
        mChart.setHoleColor(Color.rgb(235, 235, 235));

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        mChart.setValueTypeface(tf);
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

        mChart.setHoleRadius(60f);

        mChart.setDescription("Full Service Car");

        mChart.setDrawYValues(true);
        mChart.setDrawCenterText(true);

        mChart.setDrawHoleEnabled(true);

        mChart.setRotationAngle(0);

        // draws the corresponding description value into the slice
        mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);

        // display percentage values
        mChart.setUsePercentValues(true);
        // mChart.setUnit(" â‚¬");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        // mChart.setTouchEnabled(false);
        
        mChart.setCenterText("Comparativo de gastos");
        //mChart.set
        
        setData(3, 100);

        mChart.animateXY(1500, 1500);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);

    }

     

    private ArrayList<String> verif = new ArrayList<String>();

    private void setData(int count, float range) {
    	
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        double total = FServico.valorTotal(this);
        
        int id = 0;
        
        String  tipoServ[] = { "Ar Condicionado",
			    "Bateria", "Buzinas",
			    "Carroceria/Chassis", "Cinto",
			    "Diretação Hidráulica",
			    "Filtro de Ar", "Filtro de Ar da Cabine", "Filtro de Óleo", 
			    "Fluído da Embreagem", "Fluído da Transmissão", 
			    "Fluído de Freio",
			    "Inspeção Técnica", 
			    "Limpadores de Para-brisa", "Luzes",
			    "Mão de Obra",
			    "Pastilha de Freio", "Pneus", "Pneus - Alinhamento/Balanceamento",
			    "Pneus - Calibragem", "Pneus - Rodízio",
			    "Radiador", "Reparo no Motor", "Revisão",
			    "Sistema de Aquecimento", "Sistema de Embreagem", "Sistema de Exaustão",
			    "Sistema de Refrigeramento", "Suspensão/Amortecedores",
			    "Troca de Freio", "Troca de Óleo",
			    "Velas de Ignição", "Vidros/Espelhos",
			    "Outros"};
        double 	serv[] = new double[tipoServ.length];
        
        for(int x = 0; x < DAOServicos.getInstancia(this).getTamanho(); x++){
        	for(int i = 0; i < tipoServ.length; i++){
        		if(DAOServicos.getInstancia(this).buscarTodos().get(x).getTipo().equals(tipoServ[i])){
        			serv[i] += DAOServicos.getInstancia(this).buscarTodos().get(x).getValorTotal();
        			break;
        		}
        	}
        }
        
        for(int x = 0; x < serv.length; x++){
        	if(serv[x] > 0){
        		yVals1.add(new Entry((float) (100 / total * serv[x]), id));
        		verif.add(tipoServ[x] + " - " + String.valueOf("R$ ") + serv[x]);
            	id++;
        	}
        }
        

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < yVals1.size(); i++){
            xVals.add(verif.get(i));
        }

        PieDataSet set1 = new PieDataSet(yVals1, "Comparativo de Gastos");
        set1.setSliceSpace(3f);
        //set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
		set1.setColors(Cores.getCoresG());
        PieData data = new PieData(xVals, set1);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);
        mChart.invalidate();
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case android.R.id.home:

			startActivity(new Intent(this, AtividadePrincipal.class));

			return true;

		default:

			return super.onOptionsItemSelected(item);
		}
	}
    
}
