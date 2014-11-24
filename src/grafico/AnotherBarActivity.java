package grafico;



import java.util.ArrayList;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.fullservicecar.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.XLabels.XLabelPosition;

public class AnotherBarActivity extends DemoBase{

	private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.calculadora_flex_fragment);


        /*mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarX.setOnSeekBarChangeListener(this);

        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
        mSeekBarY.setOnSeekBarChangeListener(this);*/

        mChart = (BarChart) findViewById(R.id.chart1);
        
        mChart.setDrawYValues(false);

        mChart.setUnit(" %");
        mChart.setDescription("");
        
        mChart.setDrawYValues(true);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        mChart.setMaxVisibleValueCount(60);

        // disable 3D
        mChart.set3DEnabled(false);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        
        mChart.setDrawVerticalGrid(false);
        mChart.setDrawHorizontalGrid(false);
        mChart.setDrawGridBackground(false);

        XLabels xLabels = mChart.getXLabels();
        xLabels.setPosition(XLabelPosition.BOTTOM);
        xLabels.setCenterXLabelText(true);
        xLabels.setSpaceBetweenLabels(0);

         mChart.setDrawYLabels(false);
         mChart.setDrawLegend(false);

        
        // add a nice and smooth animation
        mChart.animateY(2500);
        
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        yVals1.add(new BarEntry((int) 100, 0));
        yVals1.add(new BarEntry((int) 250, 1));
        
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Gasolina");
        xVals.add("Etanol");

        BarDataSet set1 = new BarDataSet(yVals1, "");
        //set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setColors(Cores.getCores());
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        
        //int valor = ((SeekBar) findViewById(R.id.seekBar1)).getProgress();
        
       /* SeekBar seek = (SeekBar) findViewById(R.id.seekBar1);
        EditText porc = (EditText) findViewById(R.id.porc);
        EditText gas = (EditText) findViewById(R.id.precGas);
        EditText et = (EditText) findViewById(R.id.precEta);
        porc.setText("200", TextView.BufferType.EDITABLE);
        gas.setText("200");
        et.setText("200");*/
        mChart.setData(data);
        mChart.invalidate();

//        Legend l = mChart.getLegend();
//        l.setPosition(LegendPosition.BELOW_CHART_CENTER);
//        l.setFormSize(8f);
//        l.setFormToTextSpace(4f);
//        l.setXEntrySpace(6f);

        // mChart.setDrawLegend(false);*/
    }
}
