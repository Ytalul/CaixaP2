package br.upe.mavenBasico;
import java.util.ArrayList;


public class App 
{
    public static void main( String[] args )
    {
    ArrayList<String> resultado = ResultadomegaSena.obtemUltimoResultado();
    for (String dezena: resultado) {
    System.out.print(dezena + " ");
    }
    }
}