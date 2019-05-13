package br.com.satandertest.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import br.com.satandertest.R;
import br.com.satandertest.dao.BancoLocal;

public class SplashActivity extends AppCompatActivity {

    public static final int segundos = 3;
    public static final int milisegundos = segundos * 1000;

    //Declaracão da view logo da splash
    private ImageView mLaunchLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Chamando função que faz a splash demorar 3 segundos
        mLaunchLogo = (ImageView) findViewById(R.id.launch_logo);

            /*Animation animBounce1 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.bounce);
            animBounce1.setStartOffset(500);*/

        final AlphaAnimation animFade1 = new AlphaAnimation(0.0f, 1.0f);
        animFade1.setStartOffset(1000);
        animFade1.setDuration(1000);
        animFade1.setFillAfter(true);

        mLaunchLogo.startAnimation(animFade1);
        contInicio();

    }

    private void contInicio() {

        //CountDown: passa-se o tempo total de espera e o tempo de tick.
        new CountDownTimer(milisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            //Método chamado ao acabar o tempo
            @Override
            public void onFinish() {

                BancoLocal bancoLocal = new BancoLocal(SplashActivity.this);
                if (bancoLocal.getUserAccount() != null) {

                    //Usuário já logou e está salvo localmente
                    Intent it = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(it);

                } else {

                    //Usuário não logado é direcionado para a tela de login
                    Intent it = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(it);

                }


                finish();
            }
        }.start();
    }
}
