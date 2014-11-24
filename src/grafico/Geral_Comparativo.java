
package grafico;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

import funcoes.FAbastecimento;
import funcoes.FDespesa;
import funcoes.FRelatorios;
import funcoes.FServico;

public class Geral_Comparativo extends DemoBase {

    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_piechart);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Comparativo de Gastos");
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
        double total = FRelatorios.getTotal(this);
        
        int id = 0;
        
        
        if(FAbastecimento.valorTotal(this) > 0){
        	yVals1.add(new Entry((float) (100 / total * FAbastecimento.valorTotal(this)), id));
        	
        	verif.add("Abastecimentos");
        	id++;
        }        
        
        if(FDespesa.valorTotal(this) > 0){
        	yVals1.add(new Entry((float)(100 / total * FDespesa.valorTotal(this)), id));
        	
        	verif.add("Despesas");
        	id++;
        }
        
        
        if(FServico.valorTotal(this) > 0){
        	yVals1.add(new Entry((float)(100 / total * FServico.valorTotal(this)), id));
        	verif.add("Serviços");
        	id++;
        }
        
       //String[] mParties = {"Abastecimentos", "Despesas", "Serviços"};

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < yVals1.size(); i++){
        	//mParties[i] = verif.get(i);
           // xVals.add(mParties[i % mParties.length]);
            xVals.add(verif.get(i));
        }

        PieDataSet set1 = new PieDataSet(yVals1, "Comparativo de Gastos");
        set1.setSliceSpace(3f);
        //set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
		/*set1.setColors(new int[] { Color.rgb(255, 174, 24), Color.rgb(131, 182, 0),
				 Color.rgb(22, 165, 215)});*/
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
