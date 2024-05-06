package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.champion.ContextProvider;
import com.alexpell.championpedia.champion.Initialise;
import com.alexpell.championpedia.databinding.ActivityMainBinding;
import com.alexpell.championpedia.landing_page.LandingPageActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static ArrayList<Integer> images;
    AllDAO allDAO;
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.alexpell.championpedia", Context.MODE_PRIVATE);

        ContextProvider.initialize(getApplicationContext());
        if (!sharedPreferences.getBoolean("db",false)){
            try {
                Initialise.initialiseDB(getApplicationContext());
                editor = sharedPreferences.edit();
                editor.putBoolean("db",true);
                editor.apply();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (sharedPreferences.getBoolean("loggedIn", false)) {
            Intent intent = LandingPageActivity.LandingPageIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.LoginActivityIntentFactory(getApplicationContext(),false);
                startActivity(intent);
            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.LoginActivityIntentFactory(getApplicationContext(),true);
                startActivity(intent);
            }
        });
    }

    public static String parseString(String string) {
        return string.toLowerCase().replace("'","");
    }

    public static int getImageFromUsername(String username) {
        //make the sum of all letters (translated to ASCII) in the username
        //then use a modulo to get an image from the list
        /*
        int sum = 0;
        for (int i = 0; i < username.length(); i++) {
            sum += (int) username.charAt(i);
        }
        int index = sum % images.size();
        return images.get(index);
         */
        return 0;
    }

    public static void initImages() {
        images = new ArrayList<>();
        /*
        images.add(R.drawable.annie);
        images.add(R.drawable.ashe);
        images.add(R.drawable.aurelionsol);
        images.add(R.drawable.azir);
        images.add(R.drawable.bard);
        images.add(R.drawable.blitzcrank);
        images.add(R.drawable.brand);
        images.add(R.drawable.braum);
        images.add(R.drawable.caitlyn);
        images.add(R.drawable.camille);
        images.add(R.drawable.cassiopeia);
        images.add(R.drawable.chogath);
        images.add(R.drawable.corki);
        images.add(R.drawable.darius);
        images.add(R.drawable.diana);
        images.add(R.drawable.draven);
        images.add(R.drawable.drmundo);
        images.add(R.drawable.ekko);
        images.add(R.drawable.elise);
        images.add(R.drawable.evelynn);
        images.add(R.drawable.ezreal);
        images.add(R.drawable.fiddlesticks);
        images.add(R.drawable.fiora);
        images.add(R.drawable.fizz);
        images.add(R.drawable.galio);
        images.add(R.drawable.gangplank);
        images.add(R.drawable.garen);
        images.add(R.drawable.gnar);
        images.add(R.drawable.gragas);
        images.add(R.drawable.graves);
        images.add(R.drawable.hecarim);
        images.add(R.drawable.heimerdinger);
        images.add(R.drawable.illaoi);
        images.add(R.drawable.irelia);
        images.add(R.drawable.ivern);
        images.add(R.drawable.janna);
        images.add(R.drawable.jarvaniv);
        images.add(R.drawable.jax);
        images.add(R.drawable.jayce);
        images.add(R.drawable.jhin);
        images.add(R.drawable.jinx);
        images.add(R.drawable.kaisa);
        images.add(R.drawable.kalista);
        images.add(R.drawable.karma);
        images.add(R.drawable.karthus);
        images.add(R.drawable.kassadin);
        images.add(R.drawable.katarina);
        images.add(R.drawable.kayle);
        images.add(R.drawable.kayn);
        images.add(R.drawable.kennen);
        images.add(R.drawable.khazix);
        images.add(R.drawable.kindred);
        images.add(R.drawable.kled);
        images.add(R.drawable.kogmaw);
        images.add(R.drawable.leblanc);
        images.add(R.drawable.leesin);
        images.add(R.drawable.leona);
        images.add(R.drawable.lillia);
        images.add(R.drawable.lissandra);
        images.add(R.drawable.lucian);
        images.add(R.drawable.lulu);
        images.add(R.drawable.lux);
        images.add(R.drawable.malphite);
        images.add(R.drawable.malzahar);
        images.add(R.drawable.maokai);
        images.add(R.drawable.masteryi);
        images.add(R.drawable.missfortune);
        images.add(R.drawable.mordekaiser);
        images.add(R.drawable.morgana);
        images.add(R.drawable.nami);
        images.add(R.drawable.nasus);
        images.add(R.drawable.nautilus);
        images.add(R.drawable.neeko);
        images.add(R.drawable.nidalee);
        images.add(R.drawable.nocturne);
        images.add(R.drawable.nunuandwillump);
        images.add(R.drawable.olaf);
        images.add(R.drawable.orianna);
        images.add(R.drawable.ornn);
        images.add(R.drawable.pantheon);
        images.add(R.drawable.poppy);
        images.add(R.drawable.pyke);
        images.add(R.drawable.qiyana);
        images.add(R.drawable.quinn);
        images.add(R.drawable.rakan);
        images.add(R.drawable.rammus);
        images.add(R.drawable.reksai);
        images.add(R.drawable.renekton);
        images.add(R.drawable.rengar);
        images.add(R.drawable.riven);
        images.add(R.drawable.rumble);
        images.add(R.drawable.ryze);
        images.add(R.drawable.samira);
        images.add(R.drawable.sejuani);
        images.add(R.drawable.senna);
        images.add(R.drawable.seraphine);
        images.add(R.drawable.sett);
        images.add(R.drawable.shaco);
        images.add(R.drawable.shen);
        images.add(R.drawable.shyvana);
        images.add(R.drawable.singed);
        images.add(R.drawable.sion);
        images.add(R.drawable.sivir);
        images.add(R.drawable.skarner);
        images.add(R.drawable.sona);
        images.add(R.drawable.soraka);
        images.add(R.drawable.swain);
        images.add(R.drawable.sylas);
        images.add(R.drawable.syndra);
        images.add(R.drawable.tahmkench);
        images.add(R.drawable.taliyah);
        images.add(R.drawable.talon);
        images.add(R.drawable.taric);
        images.add(R.drawable.teemo);
        images.add(R.drawable.thresh);
        images.add(R.drawable.tristana);
        images.add(R.drawable.trundle);
        images.add(R.drawable.tryndamere);
        images.add(R.drawable.twistedfate);
        images.add(R.drawable.twitch);
        images.add(R.drawable.udyr);
        images.add(R.drawable.urgot);
        images.add(R.drawable.varus);
        images.add(R.drawable.vayne);
        images.add(R.drawable.veigar);
        images.add(R.drawable.velkoz);
        images.add(R.drawable.vi);
        images.add(R.drawable.viego);
        images.add(R.drawable.viktor);
        images.add(R.drawable.vladimir);
        images.add(R.drawable.volibear);
        images.add(R.drawable.warwick);
        images.add(R.drawable.wukong);
        images.add(R.drawable.xayah);
        images.add(R.drawable.xerath);
        images.add(R.drawable.xinzhao);
        images.add(R.drawable.yasuo);
        images.add(R.drawable.yone);
        images.add(R.drawable.yorick);
        images.add(R.drawable.yuumi);
        images.add(R.drawable.zac);
        images.add(R.drawable.zed);
        images.add(R.drawable.ziggs);
        images.add(R.drawable.zilean);
        images.add(R.drawable.zoe);
        images.add(R.drawable.zyra);
        */
    }
    public static Intent MainActivityIntentFactory(Context context){
        return new Intent(context,MainActivity.class);
    }
}