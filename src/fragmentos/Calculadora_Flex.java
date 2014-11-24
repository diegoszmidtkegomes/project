
package fragmentos;

import grafico.Cores;
import grafico.DemoBase;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import atividades.AtividadePrincipal;
import banco_de_dados.DAOVeiculo;

import com.example.fullservicecar.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendPosition;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.YLabels.YLabelPosition;

public class Calculadora_Flex extends DemoBase {

    private BarChart mChart;
    private static float pGas, pEt;
    private static int auto = 70, cor = Color.rgb(255, 255, 255);
    private static boolean vis = false;
    private static String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.calculadora_flex_fragment);
        
        getActionBar().setTitle("Calculadora Flex");
        getActionBar().setSubtitle(DAOVeiculo.getInstancia(this).buscarTodos().get(0).getNome());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mChart = (BarChart) findViewById(R.id.chart1);

        // enable the drawing of values
        mChart.setDrawYValues(true);

        mChart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // disable 3D
        mChart.set3DEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        mChart.setUnit(" %");

        // mChart.setDrawXLabels(false);

        mChart.setDrawGridBackground(false);
        mChart.setDrawHorizontalGrid(true);
        mChart.setDrawVerticalGrid(false);
        // mChart.setDrawYLabels(false);

        // sets the text size of the values inside the chart
        mChart.setValueTextSize(10f);

        mChart.setDrawBorder(false);
        // mChart.setBorderPositions(new BorderPosition[] {BorderPosition.LEFT,
        // BorderPosition.RIGHT});

        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        XLabels xl = mChart.getXLabels();
        xl.setPosition(XLabelPosition.BOTTOM);
        xl.setCenterXLabelText(true);
        xl.setTypeface(tf);

        YLabels yl = mChart.getYLabels();
        yl.setTypeface(tf);
        yl.setLabelCount(8);
        yl.setPosition(YLabelPosition.BOTH_SIDED);

        mChart.setValueTypeface(tf);

        final SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);
        seek.setProgress(auto);
        final EditText porc = (EditText) findViewById(R.id.porc);
        porc.setText(String.valueOf(auto));
        final EditText gas = (EditText) findViewById(R.id.precGas);
        gas.setText(String.valueOf(pGas));
        final EditText et = (EditText) findViewById(R.id.precEta);
        et.setText(String.valueOf(pEt));
        Button calc = (Button) findViewById(R.id.calc);
        
        final RelativeLayout info_bg = (RelativeLayout) findViewById(R.id.info_bg);
        info_bg.setVisibility(View.VISIBLE);
        if(!vis){
        	info_bg.setVisibility(View.GONE);
        }
        info_bg.setBackgroundColor(cor);
        final TextView info = (TextView) findViewById(R.id.info);
        info.setText(texto);
        seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(progress <= 99){
					porc.setText(String.valueOf(progress));
				}else{
					porc.setText("99");
				}
				
			}
		});
        
        porc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(porc.getText().toString()) <= 99){
					seek.setProgress(Integer.valueOf(porc.getText().toString()));
				}else{
					seek.setProgress(99);
				}
			}
		});
        porc.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(porc.getText().toString()) <= 99){
					seek.setProgress(Integer.valueOf(porc.getText().toString()));
				}else{
					seek.setProgress(99);
				}
			}
		});
        

        ArrayList<String> xVals = new ArrayList<String>();
        
        xVals.add("Gasolina");
        xVals.add("Etanol");
        
        
        

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        if(vis){
        	yVals1.add(new BarEntry(100, 0));
        }else{
        	yVals1.add(new BarEntry(0, 0));
        }
        yVals1.add(new BarEntry((Float.parseFloat(et.getText().toString()) / Float.parseFloat(gas.getText().toString())) * 100, 1));
        BarDataSet set1 = new BarDataSet(yVals1, "Combustíveis");
        set1.setBarSpacePercent(35f);
        set1.setColors(Cores.getCoresCalFlex());
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        
        mChart.setData(data);

        calc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(porc.getText().toString()) <= 99){
					seek.setProgress(Integer.valueOf(porc.getText().toString()));
				}else{
					seek.setProgress(99);
					porc.setText("99");
				}
				auto = Integer.valueOf(porc.getText().toString());
				pGas = Float.parseFloat(gas.getText().toString());
				pEt = Float.parseFloat(et.getText().toString());
				if(pEt / pGas * 100 >= auto){
					cor = Color.rgb(153, 204, 0 );
					texto = ("Recomendamos o uso da Gasolina");
				}else{
					cor = Color.rgb(255, 187, 51);
					texto = ("Recomendamos o uso do Etanol");
				}
				vis = true;
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
        
        // setting data

        Legend l = mChart.getLegend();
        l.setPosition(LegendPosition.BELOW_CHART_LEFT);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);

         mChart.setDrawLegend(true);
        
         
         
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
