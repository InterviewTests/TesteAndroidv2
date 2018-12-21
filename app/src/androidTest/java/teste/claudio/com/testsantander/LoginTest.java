package teste.claudio.com.testsantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    @Test
    public  void retrofitConverter() {

        String unidadeEnt = "1";
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<RespostaServidor> call = service.login(unidadeEnt);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {

                if (response.isSuccessful()) {

                    RespostaServidor respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {

                        if(respostaServidor.isValid()) {

                            respostaServidor.setuserId(respostaServidor.getuserId());
                            respostaServidor.setname(respostaServidor.getname());
                            respostaServidor.setbankAccount(respostaServidor.getbankAccount());
                            respostaServidor.setagency(respostaServidor.getagency());
                            respostaServidor.setbalance(respostaServidor.getbalance());
                            respostaServidor.setValid(respostaServidor.isValid());

                            assertEquals("3",respostaServidor.getuserId());

                        } else{

                            //Util.msg(LoginActivity.this,"Insira unidade e valores válidos");
                        }

                    } else {

                        //Util.msg(LoginActivity.this,"Resposta nula do servidor");
                    }

                } else {

                    //Util.msg(LoginActivity.this,"Resposta não foi sucesso");
                    // segura os erros de requisiçãoH
                    ResponseBody errorBody = response.errorBody();
                }

            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

                //Util.msg(LoginActivity.this,"Erro na chamada ao servidor");
            }
        });

    }

}


