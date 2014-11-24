
package grafico;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import atividades.AtividadePrincipal;
import banco_de_dados.DAODespesas;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

import funcoes.FDespesa;

public class Despesa_GraficoDespesa extends DemoBase {

    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Comparativo de Despesas");
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
        double total = FDespesa.valorTotal(this);
        
        int id = 0;
        
        String  tipoDesp[] = {"Estacionamento", "Financiamento",
				"Impostos (IPVA/DPVAT)", "Lava-rápido", "Licenciamento",
				"Multa", "Pedágio", "Reembolso", "Seguro", "Outros"};
        double 	desp[]     = new double[tipoDesp.length];
        
        for(int x = 0; x < DAODespesas.getInstancia(this).getTamanho(); x++){
        	for(int i = 0; i < tipoDesp.length; i++){
        		if(DAODespesas.getInstancia(this).buscarTodos().get(x).getTipo().equals(tipoDesp[i])){
        			desp[i] += DAODespesas.getInstancia(this).buscarTodos().get(x).getValorTotal();
        			break;
        		}
        	}
        }
        
        for(int x = 0; x < desp.length; x++){
        	if(desp[x] > 0){
        		yVals1.add(new Entry((float) (100 / total * desp[x]), id));
        		verif.add(tipoDesp[x] + " - " + String.valueOf("R$ ") + desp[x]);
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
