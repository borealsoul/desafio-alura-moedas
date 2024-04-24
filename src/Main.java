import com.auroram.desafio_alura.logica.Conversao;
import com.auroram.desafio_alura.logica.InterfaceHTTP;
import com.auroram.desafio_alura.logica.InterfaceTerminal;
import com.auroram.desafio_alura.logica.MapeadorJSON;
import com.auroram.desafio_alura.modelos.MoedasJSON;

import java.util.Optional;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {
        String chaveAPI = "777bed9a3d24560355c8dd08";
        String URLAPI = "https://v6.exchangerate-api.com/v6/";

        // Classes do java em geral.
        InterfaceTerminal interacaoTerminal = new InterfaceTerminal();
        InterfaceHTTP requisitorHTTP = new InterfaceHTTP();
        MapeadorJSON mapeador = new MapeadorJSON();

        Conversao moedaTeste = new Conversao(
                interacaoTerminal.PerguntaValorOrigem(),
                interacaoTerminal.PerguntaMoedaOriginal(),
                interacaoTerminal.PerguntaMoedaDestino()
        );

        interacaoTerminal.ExibeResultado(
                moedaTeste.getValorOriginal(),
                moedaTeste.getMoedaOriginal(),
                moedaTeste.getMoedaDestino(),
                moedaTeste.Converta(
                        moedaTeste.getValorOriginal(),
                        mapeador.Mapeie(
                                requisitorHTTP.RequisitaHTTP(
                                        Optional.of(URLAPI),
                                        Optional.of(chaveAPI),
                                        moedaTeste.getMoedaOriginal()
                                )
                        )
                )
        );
    }
}
