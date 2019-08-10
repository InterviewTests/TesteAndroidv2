package Presenters;

import com.example.testesantander.DetalheActivityInput;

import java.util.ArrayList;
import java.util.List;

import Models.Detalhe;

public class DetalhePresenter implements DetalhePresenterInput {
    private DetalheActivityInput activity;

    public DetalhePresenter(DetalheActivityInput activity){
        this.activity = activity;
    }

    @Override
    public void criaListaDetalhes(){
        ArrayList<Detalhe> det = new ArrayList<Detalhe>();

        det.add(new Detalhe("Pagamento", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento1", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento3", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento2", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento4", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento5", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento3", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento2", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento4", "Conta Vivo", "09/08/2019", 49.99));
        det.add(new Detalhe("Pagamento5", "Conta Vivo", "09/08/2019", 49.99));
        List<Detalhe> lista = (List<Detalhe>) det;
        activity.injetarDependencia(lista);
    }
}
