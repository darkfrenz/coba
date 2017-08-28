package org.i_smartway.myvg;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.i_smartway.myvg.login_helper.DatabaseHandler;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;




public class VacationCounterPesanan extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    //button pembelian tiket
    private Button btnBuyTicket;
    final Context context = this;

    //tambah kurang
    private int pesanan=0;
    Button tambah,kurang;
    TextView jumlah;

    //data database
//    private String kode_transaksi="Vac-Buy";
//    private String id_user="778839";
//    private String status_transaksi="Aktif";
//    private String telp_user="081267889988";
//    private String jumlah_pesanan="3";
//    private String harga_item="50000";

//    private static final String REGISTER_URL = "http://xviarch.hol.es/myvg1/transaksi.php";
    private static final String TRANSAKSI_URL = "http://demovg.hol.es/transaksi.php";
    private DatabaseHandler db;
    private HashMap<String,String> user = new HashMap<>();

    private static String KEY_UID = "uid";
    private static String KEY_SALDO = "saldo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vacation_aceh_detail_wisata);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        //akses SQLite
        db = new DatabaseHandler(getApplicationContext());
        user = db.getUserDetails();

        //button beli tiket
        btnBuyTicket = (Button) findViewById(R.id.btnBuyTicket);
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.vacation_aceh_dialog_counter);
                dialog.setTitle("Jumlah Pesanan :");

                jumlah = (TextView) dialog.findViewById(R.id.jumlahPesanan);
                tambah = (Button) dialog.findViewById(R.id.tambah);
                kurang = (Button) dialog.findViewById(R.id.kurang);
                jumlah.setText(Integer.toString(pesanan));

                tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pesanan ++;
                        jumlah.setText(Integer.toString(pesanan));
                    }
                });

                kurang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pesanan --;
                        jumlah.setText(Integer.toString(pesanan));
                    }
                });

//                TextView text1 = (TextView) findViewById(R.id.text2);
//                text1.setText("Android Custom dialog Sample");
//                ImageView image = (ImageView) findViewById(R.id.image);
//                image.setImageResource(R.drawable.lembang);


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        transaksiUser();
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });


        //simpan data




        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

    }

    //SIMPAN DATA
    private void transaksiUser() {
         String kode_transaksi="Vac-Buy".trim();
         String id_mitra="Vac-0326".trim();
         String status_transaksi="Aktif".trim();
         String jumlah_pesanan=jumlah.getText().toString().trim();
         String harga_item="50000".trim();
         String saldo = user.get("saldo");
         String id_user = user.get("uid");

        register(kode_transaksi,status_transaksi,jumlah_pesanan,harga_item,id_mitra,saldo,id_user);
        db.updateSaldo(saldo,id_user);
    }

    private void register(String kode_transaksi, String status_transaksi, String jumlah_pesanan, String harga_item, String id_mitra, String saldo, String id_user) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(VacationCounterPesanan.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("kode_transaksi",params[0]);
                data.put("status_transaksi",params[1]);
                data.put("jumlah_pesanan",params[2]);
                data.put("harga_item",params[3]);
                data.put("id_mitra",params[4]);
                data.put("saldo",params[5]);
                data.put("id_user",params[6]);

                String result = ruc.sendPostRequest(TRANSAKSI_URL,data);

                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(kode_transaksi,status_transaksi,jumlah_pesanan,harga_item,id_mitra,saldo,id_user);

    }
    //BATAS SIMPAN


    public class MyTimerTask extends TimerTask{

        @Override
        public void run() {

            VacationCounterPesanan.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }



}
