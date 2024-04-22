import com.auroram.desafio_alura.logica.Conversao;
import com.auroram.desafio_alura.logica.InterfaceHTTP;
import com.auroram.desafio_alura.logica.MapeadorJSON;
import com.auroram.desafio_alura.modelos.MoedasJSON;

import java.util.Optional;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        String chaveAPI = "777bed9a3d24560355c8dd08";
        String URLAPI = "https://v6.exchangerate-api.com/v6/";
        String moedaUsuario = "USD";
        Conversao moedaTeste = new Conversao(moedaUsuario, "BRL");
        InterfaceHTTP requisitorHTTP = new InterfaceHTTP();
        MapeadorJSON mapeador = new MapeadorJSON();

        OptionalDouble resultado = moedaTeste.Converta(
                10,
                mapeador.Mapeie(
                        requisitorHTTP.RequisitaHTTP(
                                Optional.of(URLAPI),
                                Optional.of(chaveAPI),
                                Optional.of(moedaUsuario)
                        )
                )
        );

        System.out.println(resultado.getAsDouble());

    }
}
