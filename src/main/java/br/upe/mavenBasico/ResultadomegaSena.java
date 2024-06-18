package br.upe.mavenBasico;

import java.util.ArrayList;
import java.util.regex.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

/**

* Classe que obtém os números do último sorteio da mega-sena.

*/

public class ResultadomegaSena {
/** URL que possui as dezenas sorteadas. */
private final static String URL =

"https://servicebus2.caixa.gov.br/portaldeloterias/api/megasena";

/**
* Método que se conecta ao site da CEF para obter as dezenas
do último sorteio.
* @return array de Strings, onde cada elemento é uma dezena
sorteada.
*/
public static ArrayList<String> obtemUltimoResultado() {
    //Criação do cliente HTTP que fará a conexão com o site
    HttpClient httpclient = new DefaultHttpClient();
    try {
    // Definição da URL a ser utilizada
    HttpGet httpget = new HttpGet(URL);
    // Manipulador da resposta da conexão com a URL
    ResponseHandler<String> responseHandler = new
    BasicResponseHandler();
    // Resposta propriamente dita
    String html = httpclient.execute(httpget,
    responseHandler);
    //Retorno das dezenas, após tratamento
    return obterDezenas(html);
    } catch (Exception e) {
    // Caso haja erro, dispara exceção.
    throw new RuntimeException("Um erro inesperado ocorreu.", e);
    } finally {
    //Destruição do cliente para liberação dos recursos do sistema.
    httpclient.getConnectionManager().shutdown();
    }
    }

    /**
* Tratamento da resposta HTML obtida pelo método
obtemUltimoResultado().
* @param html resposta HTML obtida
* @return array de Strings, onde cada elemento é uma dezena
sorteada.
*/
private static ArrayList<String> obterDezenas(String html) {
    ArrayList<String> numeros = new ArrayList<>();
    String regex = "\"listaDezenas\": \\[\\s*\"(\\d+)\",\\s*\"(\\d+)\",\\s*\"(\\d+)\",\\s*\"(\\d+)\",\\s*\"(\\d+)\",\\s*\"(\\d+)\"\\s*\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                numeros.add(matcher.group(i));
            }
        } else {
            System.out.println("Lista de dezenas não encontrada.");
        }
    return numeros;
    }
}