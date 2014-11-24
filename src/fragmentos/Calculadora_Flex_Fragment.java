package fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fullservicecar.R;


public class Calculadora_Flex_Fragment extends Fragment{
	
	
	public Calculadora_Flex_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.calculadora_flex_fragment, container,false);

		

       /*mChart = (BarChart) view.findViewById(R.id.chart1);
        
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
        
        SeekBar seek = (SeekBar) view.findViewById(R.id.seekBar1);
        EditText porc = (EditText) view.findViewById(R.id.porc);
        EditText gas = (EditText) view.findViewById(R.id.precGas);
        EditText et = (EditText) view.findViewById(R.id.precEta);
        porc.setText("200", TextView.BufferType.EDITABLE);
        gas.setText("200");
        et.setText("200");
        
        mChart.invalidate();
        mChart.setData(data);*/
        return view;
	}
	

}
