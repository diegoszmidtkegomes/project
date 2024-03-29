package grafico;

import android.support.v4.app.FragmentActivity;

import com.example.fullservicecar.R;

public abstract class DemoBase extends FragmentActivity {
    
    protected String[] mMonths = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec" };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
