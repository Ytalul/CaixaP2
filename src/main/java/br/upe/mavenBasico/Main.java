package br.upe.mavenBasico;

public class Main {
    public static void main( String[] args )
    {
        String[] resultado = ResultadoMegasena.obtemUltimoResultado();
        for (String dezena: resultado) {
            System.out.print(dezena + " ");
        }
    }

}
