package util;

import java.io.Serializable;

public class Resposta implements Serializable {

    public final static int OK = 0;
    public final static int OPERADOR_INVALIDO = 1;
    public final static int DIVISAO_POR_ZERO = 2;

    private static final long serialVersionUID = -4948706896760639241L;
    private double resultado;
    private int status;

    public Resposta() {
    }

    public Resposta(double resultado, int status) {
        this.resultado = resultado;
        this.status = status;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
